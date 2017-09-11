package cn.kengtion.Utils;

import cn.kengtion.Bean.OrderBean;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by 洪坤峰 on 2017/9/9.
 * 输入格式化工具
 */
public class InputFormatUtil {

    /**
     * 根据输入生成订单信息
     *
     * @param input the input
     * @return the order bean
     */
    public static OrderBean generateOrder(String input) {
        OrderBean order = new OrderBean();
        String[] params = input.split(" ");
        if (!inputCheck(params))//检查输入合法性
            return null;
        order.setUserId(params[0]);
        order.setDate(params[1]);
        order.setWeekend(isWeekend(params[1]));
        int[] hours = getStartHourAndEndHour(params[2]);
        order.setStartHour(hours[0]);
        order.setEndHour(hours[1]);
        order.setGymNo(getIndex(params[3]));
        order.setRawInput(input);
        if (params.length == 5)
            order.setCanceld(true);
        return order;
    }

    /**
     * 判断输入的格式是否符合要求.
     *
     * @param input the input
     * @return the boolean
     */
    public static boolean matchPattern(String input) {
        String userIdpattern = "^(\\S+) ";
        String datePattern = "((\\d{2}(([02468][048])|([13579][26]))"
                + "[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|"
                + "(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?("
                + "(((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?"
                + "((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";
        String endPattern = " (\\d{2}:00)~(\\d{2}:00) [ABCD]( C)?";
        String pattern = userIdpattern + datePattern + endPattern;
        return Pattern.matches(pattern, input);
    }

    /**
     * 检查输入的参数是否合法
     *
     * @param params the params
     * @return the boolean
     */
    public static boolean inputCheck(String[] params) {
        int[] hours = getStartHourAndEndHour(params[2]);
        if (hours[0] >= hours[1] || hours[0] < 9 || hours[1] > 22)
            return false;
        return true;
    }

    /**
     * 根据输入的参数获取订单起始时间
     *
     * @param param the param
     * @return the int [ ]  [0]为开始时间，[1]为结束时间
     */
    public static int[] getStartHourAndEndHour(String param) {
        int[] result = new int[2];
        result[0] = Integer.valueOf(param.substring(0, 2));
        result[1] = Integer.valueOf(param.substring(6, 8));
        return result;
    }


    /**
     * 根据输入获取预定的体育馆编号
     * A-0 B-1 C-2 D-3
     *
     * @param tag the tag
     * @return the index
     */
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

    /**
     * 判断输入的日期是否是周末.
     *
     * @param param the param
     * @return the boolean
     */
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
