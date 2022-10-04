package com.mengjq.assignmentsubmission.bin;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.conf.LanguageSet;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.core.Menu;
import com.mengjq.assignmentsubmission.pojo.DeviceInfo;
import com.mengjq.assignmentsubmission.core.ServiceMainMongoDB;
import com.mengjq.assignmentsubmission.pojo.StudentInfo;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.Scanner;

public class main_noArgs {
    // 配置界面 Configure CLI
    public static void run(StudentInfo stuInfo, EchoCLI echoCLI,
                           ServiceMainMongoDB mongoDBService, DeviceInfo deviceInfo,
                           Config conf, LanguageSet languageSet, boolean isRegistered) {

        stuInfo = mongoDBService.firstRegister(deviceInfo, stuInfo, isRegistered);
        Scanner sc = new Scanner(System.in);
        while (true) {
            int select = Menu.selectSettingMenu(languageSet);
            switch (select) {
                case 1:
                    // 更新个人信息 - Update Personal Info
                    assert stuInfo != null;
                    String lastMAC = mongoDBService.getLastMAC(stuInfo.getStuId());
                    mongoDBService.updateRecord(deviceInfo, stuInfo, lastMAC);
                    mongoDBService.regCurrentDevice(deviceInfo);
                    break;
                case 2:
                    if (stuInfo == null) {
                        echoCLI.colorPrint(languageSet.setStuInfoFirst, "red");
                        break;
                    }
                    // 查看自己提交的作业 - View your submitted assignments
                    mongoDBService.requestSelfRecord(deviceInfo);
                    FindIterable<Document> myFiles = mongoDBService.getMySubmittedFileInfo(stuInfo.getStuId());
                    echoCLI.showMySubmitStatus(myFiles);
                    echoCLI.manageMySubmits(myFiles, stuInfo, mongoDBService, languageSet);
                    break;
                case 3:
                    // 查看所有提交的作业 - View all submitted assignments
                    mongoDBService.requestAllRecord(deviceInfo);
                    FindIterable<Document> assignments = mongoDBService.getCollectingAssignments();
                    FindIterable<Document> allFiles = mongoDBService.getAllSubmittedFileInfo();
                    FindIterable<Document> allStuInfo = mongoDBService.getAllStuInfo();
                    echoCLI.showAllSubmitStatus(assignments, allFiles, allStuInfo, languageSet);
                    break;
                case 4:
                    echoCLI.getMenuAbout(languageSet);
                    break;
                case 5:
                    System.exit(0);
                    return;
                default:
                    echoCLI.colorPrint(languageSet.setModeInvalidInput, "red");
                    break;
            }
            echoCLI.colorPrint(languageSet.sendModeAnyKeyContinue, "green");
            sc.nextLine();
        }
    }
}
