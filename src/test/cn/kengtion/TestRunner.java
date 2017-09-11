package test.cn.kengtion;


import cn.kengtion.Utils.InputFormatUtil;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import test.cn.kengtion.Utils.GymManagerTest;
import test.cn.kengtion.Utils.InputFormatUtilTest;

/**
 * Created by 洪坤峰 on 2017/9/11.
 */
public class TestRunner {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(GymManagerTest.class);
        for(Failure failure:result.getFailures()){
            System.out.println(failure.toString());
        }
        result = JUnitCore.runClasses(InputFormatUtilTest.class);
        for(Failure failure:result.getFailures()){
            System.out.println(failure.toString());
        }
        System.out.println(result.wasSuccessful());
    }
}
