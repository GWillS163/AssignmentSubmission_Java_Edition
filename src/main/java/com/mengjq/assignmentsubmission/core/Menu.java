package com.mengjq.assignmentsubmission.core;

import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// add comments here
public class Menu {

    // create a menuList to store all the menu items
    private static List<String> menuList = new ArrayList<String>();

    public static void setMenuList(List<String> menuList) {
//        menu.menuList = menuList;
        Collections.addAll(menuList, "1. Input Student Information", "2. Query Submission Status", "3. Exit");


    }

    // print menu
    public static void printMenu() {
        // print the item of menu iterately
        for (int i = 1; i < menuList.size(); i++) {
            System.out.println(i + "." + menuList.get(i));
        }
//        System.out.println("1. Upload file");
//        System.out.println("2. Download file");
//        System.out.println("3. Delete file");
//        System.out.println("4. Get file upload time");
//        System.out.println("5. Get file download time");
//        System.out.println("6. Exit");
    }

    public static ArrayList<Integer> selectRenameMenu(Iterable<Document> assignments, String[] files) {
        ArrayList<Integer> selectList = new ArrayList<>();
        for (String file : files) {
            System.out.println("What assignments fit with :" +file);
            int count = 0;
            for (Document assignment :assignments){
                System.out.print(assignment + "\t");
                count++;
            }
            System.out.println("");

            selectLoop(0, count);
        }return selectList;
    }

    public static Integer selectLoop(int start, int end) {
        while (true) {
            // get nextLine from user by Scanner
            Scanner scanner = new Scanner(System.in);
            int select = scanner.nextInt();
            // check if the input is valid
            if (select >= start && select <= end) {
                System.out.println("You selected :" + select);
                return select;
            } else {
                System.out.println("Invalid input, please try again.\r");
            }
        }
    }

    public static int selectSettingMenu(String[] settings) {
        while(true){
            ArrayList<Integer> selectList = new ArrayList<>();
            for (String setting : settings) {
                System.out.println(setting);
            }

            return selectLoop(0, settings.length);
        }
    }
}


