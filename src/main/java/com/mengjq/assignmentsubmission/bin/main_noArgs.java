package com.mengjq.assignmentsubmission.bin;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.core.Menu;
import com.mengjq.assignmentsubmission.pojo.DeviceReg;
import com.mengjq.assignmentsubmission.service.MongoDBService;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.Scanner;

public class main_noArgs {
    // 配置界面 Configure CLI
    public static void main(Document stuInfo, EchoCLI echoCLI,
                            MongoDBService mongoDBService, DeviceReg deviceReg,
                            Config conf) {

        Scanner sc = new Scanner(System.in);
        while (true) {
            String[] settingMenu = {
                    "1. 配置个人信息",
                    "2. 查看已提交作业(云端)",
                    "3. 查看所有人已提交(云端)",
                    "4. 关于",
                    "5. Exit"};
            int select = Menu.selectSettingMenu(settingMenu);
            switch (select) {
                case 1:
                    mongoDBService.regCurrentDevice(deviceReg);
                    break;
                case 2:
                    if (stuInfo == null) {
                        echoCLI.showError("请先配置个人信息！");
                        break;
                    }
//                        System.out.println(stuInfo);
                    FindIterable<Document> myFiles = mongoDBService.getMySubmittedFileInfo(stuInfo.getString("stuId"));
                    echoCLI.showMySubmitStatus(myFiles);
                    break;
                case 3:
                    FindIterable<Document> assignments = mongoDBService.getCollectingAssignments();
                    FindIterable<Document> allFiles = mongoDBService.getAllSubmittedFileInfo();
                    FindIterable<Document> allStuInfo = mongoDBService.getAllStuInfo();
                    echoCLI.showAllSubmitStatus(assignments, allFiles, allStuInfo);
                    break;
                case 4:
                    conf.getMenuAbout();
                    break;
                case 5:
                    System.exit(0);
                    return;
                default:
                    System.out.println("Invalid input!");
                    break;

            }
            // please input any key to continue.
            System.out.println("Please input any key to continue.");
            sc.nextLine();
        }
    }
}
