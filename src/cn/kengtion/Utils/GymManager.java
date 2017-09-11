package cn.kengtion.Utils;

import cn.kengtion.Bean.GymBean;
import cn.kengtion.Bean.OrderBean;

/**
 * Created by 洪坤峰 on 2017/9/9.
 */
public class GymManager {
    /*
        体育场数组
     */
    private GymBean[] gyms = new GymBean[4];


    /**
     * Instantiates a new Gym manager.
     */
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

    /**
     * 执行预定的命令
     *
     * @param order 订单
     * @return
     */
    public boolean bookGym(OrderBean order) {
        return this.gyms[order.getGymNo()].bookGym(order);
    }

    /**
     * 执行取消预定的命令
     *
     * @param order the order
     * @return the boolean
     */
    public boolean cancelBook(OrderBean order) {
        return this.gyms[order.getGymNo()].cancelBook(order);
    }

    /**
     * 执行命令
     *
     * @param input the input
     */
    public void executeCommand(String input) {
        if (InputFormatUtil.matchPattern(input)) {
            OrderBean order = InputFormatUtil.generateOrder(input);
            if (order == null) {
                outputExecuteResult(false);
                return;
            }if (!order.isCanceld() && bookGym( order)) {
                outputExecuteResult(true);
            } else if (order.isCanceld() && cancelBook(order)) {
                outputExecuteResult(true);
            } else {
                outputExecuteResult(false);
            }
        } else
            outputExecuteResult(false);
    }

    /**
     * 输出收入结果
     */
    public void outputIncome() {
        int totalIncome = 0;
        StringBuilder sb = new StringBuilder();
        sb.append("收入汇总\n");
        sb.append("---\n");
        for (int i = 0; i < 3; i++) {
            sb.append(gyms[i].getOutput());
            totalIncome += gyms[i].getTotalIncome();
            sb.append("\n");
        }
        sb.append(gyms[3].getOutput()).append("\n").append("---\n");
        totalIncome+=gyms[3].getTotalIncome();
        sb.append("总计: ").append(totalIncome).append("元");
        System.out.println(sb.toString());
    }

    /**
     * 输出执行结果
     *
     * @param isAccept true 执行成功
     *                 false 执行失败（格式错误或冲突）
     */
    public void outputExecuteResult(boolean isAccept) {
        if (isAccept)
            System.out.print(OrderBean.accept);
        else
            System.out.print(OrderBean.reject);
    }
}
