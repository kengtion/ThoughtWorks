package cn.kengtion.Bean;

/**
 * Created by 洪坤峰 on 2017/9/11.
 */
public class OrderBean extends AbstractOrder {
    /**
     * Calcute income int.
     *
     * @param startHour 开始时间
     * @param endHour   结束时间
     * @return 收入（未处理取消订单违约金）
     * @method
     * @auther 创建人 ：洪坤峰
     * @auther 修改时间 ：
     * @description：计算订单带来的收入
     */
    @Override
    public int calcuteIncome(int startHour, int endHour) {
        int income = 0;
        if (isWeekend()) {
            if (startHour <= 12 && endHour <= 12) {
                return 40 * (endHour - startHour);
            } else if (startHour <= 18 && endHour <= 18) {
                return 50 * (endHour - startHour);
            } else if (startHour <= 22 && endHour <= 22) {
                return 60 * (endHour - startHour);
            } else if (startHour <= 12 && endHour > 12) {
                return calcuteIncome(startHour, 12) + calcuteIncome(12, endHour);
            } else if (startHour <= 18 && endHour > 18) {
                return calcuteIncome(startHour, 18) + calcuteIncome(18, endHour);
            }
        } else {
            if (startHour <= 12 && endHour <= 12) {
                return 30 * (endHour - startHour);
            } else if (startHour <= 18 && endHour <= 18 && startHour >= 12 && endHour >= 12) {
                return 50 * (endHour - startHour);
            } else if (startHour <= 20 && endHour <= 20 && startHour >= 18 && endHour >= 18) {
                return 80 * (endHour - startHour);
            } else if (startHour <= 22 && endHour <= 22 && startHour >= 20 && endHour >= 20) {
                return 60 * (endHour - startHour);
            } else if (startHour <= 12 && endHour > 12) {
                return calcuteIncome(startHour, 12) + calcuteIncome(12, endHour);
            } else if (startHour <= 18 && endHour > 18) {
                return calcuteIncome(startHour, 18) + calcuteIncome(18, endHour);
            } else if (startHour <= 20 && endHour > 20) {
                return calcuteIncome(startHour, 20) + calcuteIncome(20, endHour);
            }
        }
        return income;
    }

    /**
     * 获取这个订单的输出，最后拼接为总体输出
     *
     * @return the output
     */
    @Override
    public String getOutput() {
        StringBuilder sb = new StringBuilder();
        if (getStartHour() < 10)
            sb.append(getDate()).append(" 0").append(getStartHour()).append(":00~");
        else
            sb.append(getDate()).append(" ").append(getStartHour()).append(":00~");
        if (getEndHour() < 10)
            sb.append("0").append(getEndHour()).append(":00 ");
        else
            sb.append(getEndHour()).append(":00 ");
        if (isCanceld()) {
            if (isWeekend()) {
                setIncome(calcuteIncome(getStartHour(), getEndHour()) * 0.25);
                sb.append("违约金 ").append((int) getIncome()).append("元\n");
            } else {
                setIncome(calcuteIncome(getStartHour(), getEndHour()) * 0.5);
                sb.append("违约金 ").append((int) getIncome()).append("元\n");
            }
        } else {
            setIncome(calcuteIncome(getStartHour(), getEndHour()) );
            sb.append((int) getIncome()).append("元\n");
        }
        return sb.toString();
    }

    /*----------------重载equal方法，方便判断取消订单-----------------------*/

    @Override
    public boolean equals(Object obj) {
        AbstractOrder order = (AbstractOrder) obj;
        if (order.getRawInput().contains(this.getRawInput()) && !order.getRawInput().equals(this.getRawInput()))
            return true;
        else
            return false;
    }
}
