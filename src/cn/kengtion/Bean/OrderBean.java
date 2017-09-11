package cn.kengtion.Bean;

/**
 * Created by 洪坤峰 on 2017/9/9.
 * 订单实体
 */
public class OrderBean implements Comparable<OrderBean> {

    /**
     * 命令格式错误
     */
    public static final String invalid = "Error: the booking is invalid!\n";
    /**
     * 命令格式正确，接受并执行.
     */
    public static final String accept = "Success: the booking is accepted!\n";
    /**
     * 命令冲突
     */
    public static final String conflit = "Error: the booking conflicts with existing bookings!\n";
    /**
     * 订单不存在
     */
    public static final String notExist = "Error: the booking being cancelled does not exist!\n";
    /*
        用户ID
     */
    private String userId;
    /*
        预定使用场馆日期
     */
    private String date;
    /*
        预定场馆编号
     */
    private int gymNo;
    /*
        预定开始时间
     */
    private int startHour;
    /*
        预定结束时间
     */
    private int endHour;
    /*
        预定是否被取消
     */
    private boolean isCanceld;

    /*
        原始输入
     */
    private String rawInput;

    /*
        是否为周末
     */
    private boolean isWeekend;

    /*
        本单收入
     */
    private double income;


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
    public int calcuteIncome(int startHour, int endHour) {
        int income = 0;
        if (isWeekend) {
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

    /*----------------输出收入-----------------------*/

    /**
     * 获取这个订单的输出，最后拼接为总体输出
     *
     * @return the output
     */
    public String getOutput() {
        StringBuilder sb = new StringBuilder();
        if (startHour < 10)
            sb.append(date).append(" 0").append(startHour).append(":00~");
        else
            sb.append(date).append(" ").append(startHour).append(":00~");
        if (endHour < 10)
            sb.append("0").append(endHour).append(":00 ");
        else
            sb.append(endHour).append(":00 ");
        if (isCanceld) {
            if (isWeekend) {
                income = calcuteIncome(startHour, endHour) * 0.25;
                sb.append("违约金 ").append((int) income).append("元\n");
            } else {
                income = calcuteIncome(startHour, endHour) * 0.5;
                sb.append("违约金 ").append((int) income).append("元\n");
            }
        } else {
            income = calcuteIncome(startHour, endHour);
            sb.append((int) income).append("元\n");
        }
        return sb.toString();
    }

    /*----------------重载equal方法，方便取消操作-----------------------*/

    @Override
    public boolean equals(Object obj) {
        OrderBean order = (OrderBean) obj;
        if (order.getRawInput().contains(this.rawInput))
            return true;
        else
            return false;
    }

    /*----------------Getter and Setter-----------------------*/

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * Gets gym no.
     *
     * @return the gym no
     */
    public int getGymNo() {
        return gymNo;
    }

    /**
     * Sets gym no.
     *
     * @param gymNo the gym no
     */
    public void setGymNo(int gymNo) {
        this.gymNo = gymNo;
    }

    /**
     * Gets start hour.
     *
     * @return the start hour
     */
    public int getStartHour() {
        return startHour;
    }

    /**
     * Sets start hour.
     *
     * @param startHour the start hour
     */
    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    /**
     * Gets end hour.
     *
     * @return the end hour
     */
    public int getEndHour() {
        return endHour;
    }

    /**
     * Sets end hour.
     *
     * @param endHour the end hour
     */
    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    /**
     * Is canceld boolean.
     *
     * @return the boolean
     */
    public boolean isCanceld() {
        return isCanceld;
    }

    /**
     * Sets canceld.
     *
     * @param canceld the canceld
     */
    public void setCanceld(boolean canceld) {
        isCanceld = canceld;
    }

    /**
     * Gets raw input.
     *
     * @return the raw input
     */
    public String getRawInput() {
        return rawInput;
    }

    /**
     * Sets raw input.
     *
     * @param rawInput the raw input
     */
    public void setRawInput(String rawInput) {
        this.rawInput = rawInput;
    }

    /**
     * Is weekend boolean.
     *
     * @return the boolean
     */
    public boolean isWeekend() {
        return isWeekend;
    }

    /**
     * Sets weekend.
     *
     * @param weekend the weekend
     */
    public void setWeekend(boolean weekend) {
        isWeekend = weekend;
    }

    /**
     * Gets income.
     *
     * @return the income
     */
    public double getIncome() {
        return income;
    }

    /**
     * Sets income.
     *
     * @param income the income
     */
    public void setIncome(double income) {
        this.income = income;
    }

    @Override
    public int compareTo(OrderBean o) {
        return (this.date + this.startHour).compareTo(o.getDate() + o.getStartHour());
    }
}
