package cn.kengtion.Utils;

import cn.kengtion.Bean.OrderBean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by 洪坤峰 on 2017/9/9.
 * 输入输出格式化工具
 */
public class InputFormatUtil {

    /*
        根据输入生成订单
     */
    public static OrderBean generateOrder(String input) {
        OrderBean order = new OrderBean();
        String[] params = input.split(" ");
        if (!match(input)||!inputCheck(params))//检查输入合法性
            return null;
        order.setUserId(params[0]);
        order.setDate(params[1]);
        order.setWeekend(isWeekend(params[1]));
        int[] hours = getStartHourAndEndHour(params[2]);
        order.setStartHour(hours[0]);
        order.setEndHour(hours[1]);
        order.setGymNo(getIndex(params[3]));
        order.setRawInput(input);
        if (params.length == 4)
            order.setCanceld(true);
        return order;
    }

    /*
        输入格式检查
     */
    public static boolean match(String input) {
        String pattern = ".*\\s" +
                "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})" +
                "-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))" +
                "|((0[469]|11)-(0[1-9]|[12][0-9]|30))" +
                "|(02-(0[1-9]|[1][0-9]|2[0-8]))))" +
                "|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|" +
                "((0[48]|[2468][048]|[3579][26])00))-02-29)";
        return true;
    }

    /*
        输入有效性检查
     */
    public static boolean inputCheck(String[] params) {
        if (params.length != 4 || params.length != 5) {//输入参数数量校验
            System.out.print(OrderBean.reject);
            return false;
        } else if (params.length == 5 && !params[4].equals("C")) {//取消订单最后一位是否为C
            System.out.print(OrderBean.reject);
            return false;
        }
        return true;
    }

    /*
        从String中读取开始时间和结束时间
     */
    public static int[] getStartHourAndEndHour(String param) {
        int[] result = new int[2];
        result[0] = Integer.valueOf(param.substring(0, 2));
        result[1] = Integer.valueOf(param.substring(6, 8));
        return result;
    }


    //根据参数ABCD获取对应编号
    public static int getIndex(String tag) {
        switch (tag) {
            case "A":
                return 0;
            case "B":
                return 1;
            case "C":
                return 2;
            case "D":
                return 3;
        }
        return -1;
    }

    //根据日期判断是否为周末
    public static boolean isWeekend(String param) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = dateFormat.parse(param);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY ||
                calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        } else
            return false;
    }
}
