package com.mengjq.assignmentsubmission.core;

import com.mengjq.assignmentsubmission.conf.LanguageSet;
import com.mengjq.assignmentsubmission.pojo.FileInfo;
import com.mengjq.assignmentsubmission.conf.LanguageSet;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// add comments here
public class Menu {

    public static ArrayList<String> selectRenameMenu(Iterable<Document> assignments, FilesOpr filesOpr, Document stuInfo, LanguageSet languageSet) {
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
                System.out.println(languageSet.menuRenameSelectPrompt +
                        "\n\t" + fileInfo.getFilePath());
                System.out.println(languageSet.menuPlzInputAssiId);
                String assiId = sc.nextLine();

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
                    System.out.println(languageSet.menuAssiIdNotExist);
                }
            }
        }
        return selectList;
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

    public static int selectSettingMenu(LanguageSet languageSet) {
        String[] settings = {
                languageSet.menu1title,
                languageSet.menu2title,
                languageSet.menu3title,
                languageSet.menu4title,
                languageSet.menu5title,
            };
        System.out.println("_______________________________");
        for (String setting : settings) {
            System.out.println(setting);
        }
        System.out.println("_______________________________");
        return selectLoop(0, settings.length, languageSet);
    }
}




