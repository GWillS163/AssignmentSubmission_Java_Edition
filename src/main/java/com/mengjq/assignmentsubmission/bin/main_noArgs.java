package com.mengjq.assignmentsubmission.bin;

import com.mengjq.assignmentsubmission.conf.Config;
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
                    stuInfo = mongoDBService.regCurrentDevice(deviceInfo);
                    break;
                case 2:
                    if (stuInfo == null) {
//                        echoCLI.showError("setting stuId first!");
                        echoCLI.showError("请先配置个人信息！");
                        break;
                    }
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
                    echoCLI.getMenuAbout();
                    break;
                case 5:
                    System.exit(0);
                    return;
                default:
//                    System.out.println("Invalid input!");
                    System.out.println("无效输入!");
                    break;

            }
            // please input any key to continue.
//            System.out.println("Please input any key to continue.");
            System.out.println("按任意键继续.");
            sc.next();
        }
    }
}
