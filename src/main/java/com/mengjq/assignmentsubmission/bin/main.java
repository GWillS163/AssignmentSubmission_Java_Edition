package com.mengjq.assignmentsubmission.bin;
import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.core.Menu;
import com.mengjq.assignmentsubmission.service.MongoDBService;
import com.mengjq.assignmentsubmission.pojo.DeviceReg;
import com.mengjq.assignmentsubmission.core.FilesOpr;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        // The below variables are read from the config file.
//        String mongodbUrl = "mongodb+srv://mengjq:OXDueFslVZNWtiqT@assignmentsubmmsion.nttaj.mongodb.net/?retryWrites=true&w=majority";
//        String assignmentInfoDB = "AssignmentInfo";
//        String deviceRegDB = "DeviceReg";
//        String fileInfoDB = "FileInfo";
//        String studentInfoDB = "StudentInfo";
//        String clazz = "1909";
//        String userId = "19852331";


        EchoCLI echoCLI = new EchoCLI();
        Config conf = new Config();
        DeviceReg deviceReg = new DeviceReg(); // 注册时用, 提交作业用,
        FilesOpr filesOpr = new FilesOpr();
        Scanner sc = new Scanner(System.in);

        MongoDBService mongoDBService = new MongoDBService(conf.mongodbUrl, conf.clazz,
                conf.assignmentInfoDB, conf.deviceRegDB, conf.fileInfoDB, conf.studentInfoDB);

        // 主程序是
        // setting Menu function
        Document stuInfo = mongoDBService.tryGetStuInfoByMAC(deviceReg.getDeviceMAC());
        // print student value info
        echoCLI.showStuInfo(stuInfo);
        if (args.length == 0) {

            while (true) {
                String[] settingMenu = {"1. 配置个人信息", "2. 查看已提交作业(云端)", "3. 查看所有人已提交(云端)", "4. 关于", "5. Exit"};
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
        }else{
        // Send Menu Function
        //identify user by mac address  > Document{{"userId":"19852331","deviceId":"00:00:00:00:00:00","assignmentId":"1909","fileName":"test.txt","fileSize":0,"fileType":"txt","filePath":"test.txt","fileHash":""}}
            if (stuInfo == null) {
            System.out.println("请先配置个人信息！");
            return;
            }

            //2. 展示最新Assignment数据
            FindIterable<Document> assignments =  mongoDBService.getCollectingAssignments();
            echoCLI.showAssignments(assignments);

            filesOpr.setBasicFileInfo(args);

            //3. [Record] 获取用户选择 > [3,1]
            Menu.selectRenameMenu(assignments, filesOpr, stuInfo);

            //5. [Record] 上交 Files
            Boolean res = mongoDBService.uploadFiles(filesOpr.fileInfoList);
            if(res){
                System.out.println("上交处理结束！");
            }
        }
    }
}
