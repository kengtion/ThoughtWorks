package cn.kengtion.Utils;

import cn.kengtion.Bean.GymBean;
import cn.kengtion.Bean.OrderBean;
import com.sun.org.apache.xpath.internal.operations.Or;

/**
 * Created by 洪坤峰 on 2017/9/9.
 */
public class GymManager {
    /*
        体育场数组
     */
    private GymBean[] gyms = new GymBean[4];


    /*
        初始化，给体育场添加标签
     */
    public GymManager() {
        this.gyms[0] = new GymBean();
        this.gyms[0].setTag('A');
        this.gyms[1] = new GymBean();
        this.gyms[1].setTag('B');
        this.gyms[2] = new GymBean();
        this.gyms[2].setTag('C');
        this.gyms[3] = new GymBean();
        this.gyms[3].setTag('D');
    }

    //预定
    public boolean bookGym(int no, OrderBean order) {
        return this.gyms[no].bookGym(order);
    }

    //取消预定
    public boolean cancelBook(int no, OrderBean order) {
        return this.gyms[no].cancelBook(order);
    }

    public void executeCommand(String input) {
        if (InputFormatUtil.matchPattern(input)) {
            OrderBean order = InputFormatUtil.generateOrder(input);
            if (order == null) {
                outputExecuteResult(false);
                return;
            }if (!order.isCanceld() && gyms[order.getGymNo()].bookGym(order)) {
                outputExecuteResult(true);
            } else if (order.isCanceld() && gyms[order.getGymNo()].cancelBook(order)) {
                outputExecuteResult(true);
            } else {
                outputExecuteResult(false);
            }
        } else
            outputExecuteResult(false);
    }

    //输出收入情况
    public void outputIncome() {
        int totalIncome = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("收入汇总\n");
        sb.append("---\n");
        for (int i = 0; i < 3; i++) {
            sb.append(gyms[i].toString());
            totalIncome += gyms[i].getTotalIncome();
            sb.append("\n");
        }
        sb.append(gyms[3].toString()).append("\n").append("---\n");
        sb.append("总计: ").append(totalIncome).append("元");
        System.out.println(sb.toString());
    }

    public void outputExecuteResult(boolean isAccept) {
        if (isAccept)
            System.out.print(OrderBean.accept);
        else
            System.out.print(OrderBean.reject);
    }
}
