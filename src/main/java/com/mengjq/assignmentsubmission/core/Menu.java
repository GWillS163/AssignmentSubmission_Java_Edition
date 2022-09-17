package com.mengjq.assignmentsubmission.core;

import com.mengjq.assignmentsubmission.pojo.FileInfo;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// add comments here
public class Menu {
    // create a menuList to store all the menu items
//    private static List<String> menuList = new ArrayList<String>();

    public static void setMenuList(List<String> menuList) {
//        menu.menuList = menuList;
        Collections.addAll(menuList, "1. Input Student Information", "2. Query Submission Status", "3. Exit");
    }

    public static ArrayList<String> selectRenameMenu(Iterable<Document> assignments, FilesOpr filesOpr, Document stuInfo) {
        ArrayList<String> selectList = new ArrayList<>();
        // create a list to store all the assignment assiId
        ArrayList<String> assiIdList = new ArrayList<>();
        // get user input
        Scanner sc = new Scanner(System.in);
        for (Document assignment : assignments) {
            assiIdList.add(assignment.getString("assiId"));
        }
        // iterate the filesOpr.getFileList() to get the file name
        for (FileInfo fileInfo : filesOpr.fileInfoList) {
            while (true){
                System.out.println("Which assignments fit with :" +
                        "\n\t" + fileInfo.getFilePath());
                System.out.println("\tPlease input the assiId you want to rename:");
                String assiId = sc.nextLine();

                //TODO: assiId 需要随机生成
                if (assiIdList.contains(assiId)) {
                    assignments.forEach(assignment -> {
                        if (assignment.getString("assiId").equals(assiId)) {
                            fileInfo.setStuId(stuInfo.getString("stuId"));
                            fileInfo.setStuName(stuInfo.getString("stuName"));
                            fileInfo.setAssiId(assiId);
                            fileInfo.setFormatName(assignment.getString("briefName")
                                    .replace("班级", stuInfo.getString("clazz"))
                                    .replace("姓名", stuInfo.getString("stuName"))
                                    .replace("学号", stuInfo.getString("stuId")));
                        }
                    });
                    selectList.add(assiId);
                    break;
                } else {
                    System.out.println("The assiId is not exist!");
                }
            }
        }
        return selectList;
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


