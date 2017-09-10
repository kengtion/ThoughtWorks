package cn.kengtion.Bean;

/**
 * Created by 洪坤峰 on 2017/9/9.
 * 订单实体
 */
public class OrderBean {

    public static final String reject = "Error: the booking is invalid!\n";
    public static final String accept = "Success: the booking is accepted!\n";

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


    /*
        递归计算收入
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

    /*----------------重载toString方法，方便输出收入-----------------------*/
    @Override
    public String toString() {
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
                sb.append("违约金 ").append(income).append("元\n");
            } else {
                income = calcuteIncome(startHour, endHour) * 0.5;
                sb.append("违约金 ").append(income).append("元\n");
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getGymNo() {
        return gymNo;
    }

    public void setGymNo(int gymNo) {
        this.gymNo = gymNo;
    }

    public int getStartHour() {
        return startHour;
    }

    public void setStartHour(int startHour) {
        this.startHour = startHour;
    }

    public int getEndHour() {
        return endHour;
    }

    public void setEndHour(int endHour) {
        this.endHour = endHour;
    }

    public boolean isCanceld() {
        return isCanceld;
    }

    public void setCanceld(boolean canceld) {
        isCanceld = canceld;
    }

    public String getRawInput() {
        return rawInput;
    }

    public void setRawInput(String rawInput) {
        this.rawInput = rawInput;
    }

    public boolean isWeekend() {
        return isWeekend;
    }

    public void setWeekend(boolean weekend) {
        isWeekend = weekend;
    }

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }
}
