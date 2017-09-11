package cn.kengtion;

import cn.kengtion.Utils.GymManager;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GymManager manager = new GymManager();
        Scanner sc = new Scanner(System.in);
        while (true){
            String command = sc.nextLine();
            manager.executeCommand(command);
        }
    }
}
