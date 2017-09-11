package cn.kengtion.Utils;

import cn.kengtion.Bean.AbstractGym;
import cn.kengtion.Bean.AbstractOrder;
import cn.kengtion.Bean.GymBean;

/**
 * Created by 洪坤峰 on 2017/9/9.
 */
public class GymManager {
    /*
        体育场数组
     */
    private AbstractGym[] gyms = new AbstractGym[4];


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
    public boolean bookGym(AbstractOrder order) {
        return this.gyms[order.getGymNo()].bookGym(order);
    }

    /**
     * 执行取消预定的命令
     *
     * @param order the order
     * @return the boolean
     */
    public boolean cancelBook(AbstractOrder order) {
        return this.gyms[order.getGymNo()].cancelBook(order);
    }

    /**
     * 执行命令
     *
     * @param input the input
     */
    public int executeCommand(String input) {
        if(input == ""){
            return outputIncome();
        }else if (InputFormatUtil.matchPattern(input)) {
            int status;
            AbstractOrder order = InputFormatUtil.generateOrder(input);
            if(order == null){
                status = 1;
            }else {
                if(order.isCanceld()){
                    status = cancelBook(order)?0:3;
                }else {
                    status = bookGym(order)?0:2;
                }
            }
            outputExecuteResult(status);
            return status;
        }else
            outputExecuteResult(1);
        return 1;
    }

    /**
     * 输出收入结果
     */
    public int outputIncome() {
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
        totalIncome += gyms[3].getTotalIncome();
        sb.append("总计: ").append(totalIncome).append("元");
        System.out.println(sb.toString());
        return totalIncome;
    }

    /**
     * 输出执行结果
     *
     * @param status 0：成功
     *               1：格式不正确
     *               2：预定时间冲突
     *               3：订单不存在
     */
    public void outputExecuteResult(int status) {
        switch (status) {
            case 0:
                System.out.print(AbstractOrder.accept);
                break;
            case 1:
                System.out.print(AbstractOrder.invalid);
                break;
            case 2:
                System.out.print(AbstractOrder.conflit);
                break;
            case 3:
                System.out.print(AbstractOrder.notExist);
                break;
        }
    }
}
