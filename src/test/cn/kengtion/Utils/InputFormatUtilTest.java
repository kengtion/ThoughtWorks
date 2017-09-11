package test.cn.kengtion.Utils;

import cn.kengtion.Utils.InputFormatUtil;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import static org.junit.Assert.assertEquals;


/**
 * The type Input format util test.
 */
public class InputFormatUtilTest {

    /**
     * Method: matchPattern(String input)
     * 测试输入是否符合{⽤户ID} {预订⽇期 yyyy-MM-dd} {预订时间段 HH:mm~HH:mm} {场地} C?格式
     *
     * @throws Exception the exception
     */
    @Test
    public void testMatchPattern() throws Exception {
        String[] input = new String[10];
        input[0] = "abcdefghijklmnopqrst1234567890";
        input[1] = "U001 2016-06-02 22:00~22:00 A";
        input[2] = "U001 2016-06-4 22:00~22:00 A ";
        input[3] = "U004 2017-15-03 15:00~16:00 C";
        input[4] = "@#$%^&*#";
        input[5] = "U001 2016-06-02 22:00~22:00 C C";
        input[6] = "U001 2000-02-29 22:00~22:00 A";
        input[7] = "U001 17-03-48 22:00~22:00 A";
        input[8] = "U001 2097-02-29 22:00~22:00 A";
        input[9] = "U001 2016-13-29 22:00~22:00 A D";
        System.out.println(input[0]);
        assertEquals(false, InputFormatUtil.matchPattern(input[0]));
        System.out.println(input[1]);
        assertEquals(true, InputFormatUtil.matchPattern(input[1]));
        System.out.println(input[2]);
        assertEquals(false, InputFormatUtil.matchPattern(input[2]));
        System.out.println(input[3]);
        assertEquals(false, InputFormatUtil.matchPattern(input[3]));
        System.out.println(input[4]);
        assertEquals(false, InputFormatUtil.matchPattern(input[4]));
        System.out.println(input[5]);
        assertEquals(true, InputFormatUtil.matchPattern(input[5]));
        System.out.println(input[6]);
        assertEquals(true, InputFormatUtil.matchPattern(input[6]));
        System.out.println(input[7]);
        assertEquals(false, InputFormatUtil.matchPattern(input[7]));
        System.out.println(input[8]);
        assertEquals(false, InputFormatUtil.matchPattern(input[8]));
        System.out.println(input[0]);
        assertEquals(false, InputFormatUtil.matchPattern(input[9]));
        System.out.println(input[0]);

    }

} 
