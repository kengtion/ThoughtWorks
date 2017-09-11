package cn.kengtion.Bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by 洪坤峰 on 2017/9/11.
 */
public class GymBean extends AbstractGym {
    /*
        订单表
     */
    private List<AbstractOrder> abstractOrderList = new ArrayList<>(50);//设定一个较大的初始容量减少ArrayList扩容耗时
    /*
        体育馆编号
     */
    private char Tag;
    /*
        本场馆总收入
    */
    private int totalIncome = 0;

    @Override
    /**
     * 执行预定命令.
     *
     * @param order 订单
     * @return 执行状态 true为成功，false为失败
     */
    public boolean bookGym(AbstractOrder order) {
        for (AbstractOrder orderExist : abstractOrderList) {
            if (!orderExist.isCanceld() && isConflict(order, orderExist))
                return false;
        }
        abstractOrderList.add(order);
        return true;
    }

    @Override
    /**
     * 执行取消预定命令
     *
     * @param order 待取消的订单信息
     * @return 执行结果 true为成功，false为不存在该订单，失败
     */
    public boolean cancelBook(AbstractOrder order) {
        for (AbstractOrder abstractOrder : abstractOrderList) {
            if (abstractOrder.equals(order) && !abstractOrder.isCanceld()) {
                abstractOrder.setCanceld(true);
                abstractOrder.setIncome(abstractOrder.getIncome() * (abstractOrder.isWeekend() ? 0.25 : 0.5));
                return true;
            }
        }
        return false;
    }

    /**
     * 获取体育馆的收入输出字符串
     *
     * @return the output
     */
    public String getOutput() {
        StringBuilder sb = new StringBuilder();
        sb.append("场地:").append(Tag).append("\n");
        Collections.sort(abstractOrderList);
        for (AbstractOrder abstractOrder : abstractOrderList) {
            sb.append(abstractOrder.getOutput());
            totalIncome += abstractOrder.getIncome();
        }
        sb.append("⼩计： ").append(totalIncome).append("元\n");
        return sb.toString();
    }

    /**
     * 判断订单是否冲突
     *
     * @param order      the order
     * @param orderExist the order exist
     * @return true-冲突
     * false-不冲突
     */
    public boolean isConflict(AbstractOrder order, AbstractOrder orderExist) {
        if (order.getStartHour() < orderExist.getEndHour() &&
                order.getStartHour() > orderExist.getStartHour()) {
            return true;
        } else if (order.getEndHour() < orderExist.getEndHour() && order.getEndHour() > orderExist.getStartHour()) {
            return true;
        } else if (order.getStartHour() < orderExist.getStartHour() && orderExist.getEndHour() < order.getEndHour()) {
            return true;
        }
        return false;
    }

    /*------------getter and setter-------------*/

    public char getTag() {
        return Tag;
    }

    @Override
    public void setTag(char tag) {
        Tag = tag;
    }

    public int getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(int totalIncome) {
        this.totalIncome = totalIncome;
    }
}
