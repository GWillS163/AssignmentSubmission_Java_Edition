package com.mengjq.assignmentsubmmsion.core;

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
    public static String selectMenu() {
        int select;
        while (true) {
            // get nextLine from user by Scanner
            Scanner scanner = new Scanner(System.in);
            select = scanner.nextInt();
            try {
            } catch (Exception e) {
                // if scanner is integer, continue, else reInput
                System.out.println("Please input a number");
            }
            // get the item of menu by select
            return menuList.get(select);
        }

    }
}
