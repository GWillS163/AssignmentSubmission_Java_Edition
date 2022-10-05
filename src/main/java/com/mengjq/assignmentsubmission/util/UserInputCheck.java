package com.mengjq.assignmentsubmission.util;

import com.mengjq.assignmentsubmission.conf.LanguageSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInputCheck {

    public void UserInputOpr() {
    }

    public static Integer selectLoop(int start, int end, LanguageSet languageSet) {
        while (true) {
            // get nextLine from user by Scanner
            Scanner scanner = new Scanner(System.in);
            int select = 0;
            try {
                select = scanner.nextInt();
            } catch (Exception e) {
                System.out.println(languageSet.selectLoopSelectInvalid);
                continue;
            }
            // check if the input is valid
            if (select >= start && select <= end) {
                return select;
            }
            System.out.println(languageSet.selectLoopSelectInvalid);
        }
    }

    public static String getStuId() {
        while (true) {
            // Scan the user input
            Scanner sc = new Scanner(System.in);
            System.out.println("Please input your stuId: ");
            String stuId = sc.nextLine();
            System.out.println("Your stuId is: " + stuId);

            if (stuId == null){
                System.out.println("Your stuId is null!");
                continue;
            }
            return stuId;
        }
    }

    // Check command input whether correct when manage user file
    public boolean manageFileInput1(List<Integer> validInt, String[] inputSplit, LanguageSet languageSet) {
        // 2. 判断输入是否合法
        if (inputSplit.length != 2) {
            System.out.println(languageSet.echoCLIMySubmitsInvalidInput);
            return false;
        }
        // 3. 判断输入的第一个参数是否合法
        if (!inputSplit[0].equals("del") && !inputSplit[0].equals("down")) {
            System.out.println(languageSet.echoCLIMySubmitsInvalidInput);
            return false;
        }
        return true;
    }
    public List<Integer> manageFileInput2(List<Integer> validInt, String[] inputSplit, LanguageSet languageSet) {
        // 4. 判断输入的第二个参数是否合法
        List<Integer> batchOpr = new ArrayList<>();
        String[] fileIds = inputSplit[1].split(",");
        for ( String fileId : fileIds) {
            try {
                int fileIdInt = Integer.parseInt(fileId);
                if (!validInt.contains(fileIdInt)) {
                    System.out.println(languageSet.echoCLIMySubmitsInvalidInput);
                    continue;
                }
                batchOpr.add(fileIdInt);
            } catch (Exception e) {
                System.out.println(languageSet.echoCLIMySubmitsInvalidInput);
                return new ArrayList<>();
            }
        }
        return batchOpr;

    }
}


