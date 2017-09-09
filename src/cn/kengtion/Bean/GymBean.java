package cn.kengtion.Bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
    private double totalIncome = 0;

    /*
        预定体育场
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

    /*
        取消预定
     */
    public boolean cancelBook(OrderBean order) {
        for (OrderBean orderBean : orderBeanList) {
            if (orderBean.equals(order)) {
                orderBean.setCanceld(true);
                return true;
            }
        }
        return false;
    }

    /*
        重载toString输出收入
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("场地:").append(Tag).append("\n");
        for (OrderBean orderBean : orderBeanList) {
            totalIncome += orderBean.getIncome();
            sb.append(orderBean.toString());
        }
        sb.append("⼩计： ").append(totalIncome).append("元\n");
        return sb.toString();
    }

    /* ------------Getter and Setter ---------------*/

    public char getTag() {
        return Tag;
    }

    public void setTag(char tag) {
        Tag = tag;
    }

    public double getTotalIncome() {
        return totalIncome;
    }
}
