package test.cn.kengtion.Utils;

import cn.kengtion.Utils.GymManager;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


/**
 * The type Gym manager test.
 */
public class GymManagerTest {

    /**
     * The Manager.
     */
    GymManager manager = new GymManager();


    /**
     * Method: executeCommand(String input)
     * 总体测试，测试针对命令是否正确的输出，其中输出收入的测试通过判断总收入是否正确进行
     * @throws Exception the exception
     */
    @Test
    public void testExecuteCommand() throws Exception {
    //TODO: Test goes here...
        String[] command = new String[7];
        command[0] = "U002 2017-08-01 19:00~22:00 A";
        command[1] = "U003 2017-08-01 18:00~20:00 A";
        command[2] = "U002 2017-08-01 19:00~22:00 A C";
        command[3] = "U002 2017-08-01 19:00~22:00 A C";
        command[4] = "U003 2017-08-01 18:00~20:00 A";
        command[5] = "U003 2017-08-02 13:00~17:00 B";
        command[6] = "";
        System.out.println(command[0]);
        assertEquals(0,manager.executeCommand(command[0]));
        System.out.println(command[1]);
        assertEquals(2,manager.executeCommand(command[1]));
        System.out.println(command[2]);
        assertEquals(0,manager.executeCommand(command[2]));
        System.out.println(command[3]);
        assertEquals(3,manager.executeCommand(command[3]));
        System.out.println(command[4]);
        assertEquals(0,manager.executeCommand(command[4]));
        System.out.println(command[5]);
        assertEquals(0,manager.executeCommand(command[5]));
        System.out.println(command[6]);
        assertEquals(460,manager.executeCommand(command[6]));
    }

} 
