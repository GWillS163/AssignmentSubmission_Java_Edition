package com.mengjq.assignmentsubmission.bin;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.conf.LanguageSet;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.core.Menu;
import com.mengjq.assignmentsubmission.pojo.DeviceInfo;
import com.mengjq.assignmentsubmission.core.mongoDBOpr;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.Scanner;

public class main_noArgs {
    // 配置界面 Configure CLI
    public static void run(Document stuInfo, EchoCLI echoCLI,
                           mongoDBOpr mongoDBService, DeviceInfo deviceInfo,
                           Config conf, LanguageSet languageSet) {

        Scanner sc = new Scanner(System.in);
        while (true) {
            int select = Menu.selectSettingMenu(languageSet);
            switch (select) {
                case 1:
                    stuInfo = mongoDBService.regCurrentDevice(deviceInfo);
                    break;
                case 2:
                    if (stuInfo == null) {
                        echoCLI.colorPrint(languageSet.setStuInfoFirst, "red");
                        break;
                    }
                    FindIterable<Document> myFiles = mongoDBService.getMySubmittedFileInfo(stuInfo.getString("stuId"));
                    echoCLI.showMySubmitStatus(myFiles);
                    break;
                case 3:
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
