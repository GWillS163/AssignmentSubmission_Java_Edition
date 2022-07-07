package com.mengjq.assignmentsubmmsion.bin;
import com.mengjq.assignmentsubmmsion.core.MongoDBService;
import com.mengjq.assignmentsubmmsion.pojo.FileInfo;
import com.mengjq.assignmentsubmmsion.core.Menu;
import com.mengjq.assignmentsubmmsion.core.DragFiles;

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

        System.out.println("Hello World!");

        MongoDBService mongoDBService = new MongoDBService(mongodbUrl, assignmentInfoDB, deviceRegDB, fileInfoDB, studentInfoDB, clazz);

        // 主程序是
        // select menu function
        Menu.printMenu();
        Menu.selectMenu();

        //选择1 输入学生信息 （保存至云端？识别当前设备特征为.xxx）
        mongoDBService.regCurrentDevice();
        //选择2 查询提交状态
        mongoDBService.getSubmitStatus(clazz);

        //发送文件
        //0. get drag info from user
        DragFiles dragFiles = new DragFiles();
        List<FileInfo> fileInfos = dragFiles.getDragFileInfo(args);

//        //2. 展示最新Assignment数据
//        mongoDBService.showLatestAssignmentData();
//        //3. [Record] 获取用户选择
//        getUserChoice();
//        //4. [Record] 尝试重命名文件，并提示报错 if exists
//        reNameFiles(fileInfos);
//        //5. [Record] 上交 Files
//        mongoDBService.uploadFiles(fileInfos);

    }
}
