package com.mengjq.assignmentsubmission.bin;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.core.MongoDBService;
import com.mengjq.assignmentsubmission.pojo.DeviceReg;
import com.mengjq.assignmentsubmission.pojo.FileInfo;
import com.mengjq.assignmentsubmission.core.Menu;
import com.mengjq.assignmentsubmission.core.DragFiles;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {
        // The below variables are read from the config file.
        String mongodbUrl = "mongodb+srv://mengjq:OXDueFslVZNWtiqT@assignmentsubmmsion.nttaj.mongodb.net/?retryWrites=true&w=majority";
        String assignmentInfoDB = "AssignmentInfo";
        String deviceRegDB = "DeviceReg";
        String fileInfoDB = "FileInfo";
        String studentInfoDB = "StudentInfo";
        String clazz = "1909";
        String userId = "19852331";


        EchoCLI echoCLI = new EchoCLI();
        DeviceReg deviceReg = new DeviceReg();
        DragFiles dragFiles = new DragFiles();
        MongoDBService mongoDBService = new MongoDBService(mongodbUrl, assignmentInfoDB, deviceRegDB, fileInfoDB, studentInfoDB, clazz);

        // 主程序是
        // select menu function
        // TODO: 这里应该是一个循环，每次输入一个选项，然后调用相应的函数，直到用户选择退出。
        String[] settingMenu = {"1. Register Device", "2. Get Submit Status", "3. Upload Files", "4. Get Student Info", "5. Exit"};
        Menu.selectSettingMenu(settingMenu);

        //选择1 输入学生信息 （保存至云端？识别当前设备特征为.xxx）
        deviceReg.setAllWithUserId(userId);
        mongoDBService.regCurrentDevice(deviceReg);

        //选择2 查询提交状态
        mongoDBService.getStatusByStuId(userId);

        //发送文件
        //2. 展示最新Assignment数据
        Iterable<Document> assignments =  mongoDBService.getAssignments();
        echoCLI.showAssignments(assignments);

        //0. get drag info from user
        List<FileInfo> fileInfos = dragFiles.getDragFileInfo(args);

        //identify user by mac address  > Document{{"userId":"19852331","deviceId":"00:00:00:00:00:00","assignmentId":"1909","fileName":"test.txt","fileSize":0,"fileType":"txt","filePath":"test.txt","fileHash":""}}
        Document stuInfo = mongoDBService.getStuInfo(deviceReg.getDeviceMAC());

//        //3. [Record] 获取用户选择 > [3,1]
//        String[] assignments = {"1. Register Device", "2. Get Submit Status", "3. Upload Files", "4. Get Student Info", "5. Exit"};
        String[] files = {"meng.docx", "li.docx"};
        ArrayList<Integer> selected = Menu.selectRenameMenu(assignments, files);
        System.out.println(selected);

        Document testAssign =  mongoDBService.getTestAssignment();
        //4. [Record] 尝试重命名文件，并提示报错 if exists
        for (FileInfo fileInfo : fileInfos) {
            fileInfo.setFormatNameByAssi(testAssign, stuInfo);
        }
        //5. [Record] 上交 Files
        mongoDBService.uploadFiles(fileInfos);

    }
}
