package cn.kengtion;

import cn.kengtion.Utils.GymManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        GymManager manager = new GymManager();
        Scanner sc = new Scanner(System.in);
        while (true){
            String command = sc.nextLine();
            if("".equals(command)){
                manager.outputIncome();
                continue;
            }else {
                manager.executeCommand(command);
                continue;
            }
        }
    }
}
