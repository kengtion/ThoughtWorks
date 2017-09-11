package cn.kengtion.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by 洪坤峰 on 2017/9/9.
 */
public class GymBean {
    /*
        体育馆编号
     */
    private char Tag;

    /*
        预定时间表
     */
    private HashMap<String, boolean[]> dateList = new HashMap();

    /*
        订单表
     */
    private List<OrderBean> orderBeanList = new ArrayList<>(50);//设定一个较大的初始容量减少ArrayList扩容耗时

    /*
        本场馆总收入
     */
    private int totalIncome = 0;

    /**
     * 执行预定命令.
     *
     * @param order 订单
     * @return 执行状态 true为成功，false为失败
     */
    public boolean bookGym(OrderBean order) {
        boolean[] bookedTime;
        if ((bookedTime = dateList.get(order.getDate())) != null) {
            for (int i = order.getStartHour() - 1; i < order.getEndHour(); i++) {
                if (bookedTime[i])
                    return false;
            }
            orderBeanList.add(order);
            return true;
        } else {
            String date = order.getDate();
            bookedTime = new boolean[24];
            bookedTime[order.getStartHour()] = true;
            bookedTime[order.getEndHour()] = true;
            dateList.put(date, bookedTime);
            orderBeanList.add(order);
            return true;
        }
    }


    /**
     * 执行取消预定命令
     *
     * @param order 待取消的订单信息
     * @return 执行结果 true为成功，false为不存在该订单，失败
     */
    public boolean cancelBook(OrderBean order) {
        for (OrderBean orderBean : orderBeanList) {
            if (orderBean.equals(order)) {
                orderBean.setCanceld(true);
                orderBean.setIncome(orderBean.getIncome()*(orderBean.isWeekend()?0.25:0.5));
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
        for (OrderBean orderBean : orderBeanList) {
            sb.append(orderBean.getOutput());
            totalIncome += orderBean.getIncome();
        }
        sb.append("⼩计： ").append(totalIncome).append("元\n");
        return sb.toString();
    }

    /* ------------Getter and Setter ---------------*/

    /**
     * Gets tag.
     *
     * @return the tag
     */
    public char getTag() {
        return Tag;
    }

    /**
     * Sets tag.
     *
     * @param tag the tag
     */
    public void setTag(char tag) {
        Tag = tag;
    }

    /**
     * Gets total income.
     *
     * @return the total income
     */
    public int getTotalIncome() {
        return totalIncome;
    }
}
