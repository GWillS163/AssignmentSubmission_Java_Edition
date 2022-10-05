package com.mengjq.assignmentsubmission.core;

import com.mengjq.assignmentsubmission.conf.LanguageSet;
import com.mengjq.assignmentsubmission.pojo.FileInfo;
import com.mengjq.assignmentsubmission.conf.LanguageSet;
import com.mengjq.assignmentsubmission.pojo.StudentInfo;
import com.mengjq.assignmentsubmission.util.UserInputCheck;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// add comments here
public class Menu {
    public static UserInputCheck userInputCheck;

    public Menu()
    {userInputCheck = new UserInputCheck();
    }
    public static ArrayList<String> selectRenameMenu(Iterable<Document> assignments, FilesOpr filesOpr, StudentInfo stuInfo, LanguageSet languageSet) {
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
                            fileInfo.setStuId(stuInfo.getStuId());
                            fileInfo.setStuName(stuInfo.getStuName());
                            fileInfo.setAssiId(assiId);
                            String fileName = assignment.getString("fileNameRule");
                            if ( fileName.contains("班级")){
                                fileName = fileName.replace("班级", stuInfo.getStuClazz());
                            }
                            if ( fileName.contains("学号")){
                                fileName = fileName.replace("学号", stuInfo.getStuId());
                            }
                            if ( fileName.contains("姓名")){
                                fileName = fileName.replace("姓名", stuInfo.getStuName());
                            }
                            fileInfo.setFormatName(fileName);
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
        return UserInputCheck.selectLoop(0, settings.length, languageSet);
    }
}




