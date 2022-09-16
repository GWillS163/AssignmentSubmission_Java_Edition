package com.mengjq.assignmentsubmission.bin;
import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.core.Menu;
import com.mengjq.assignmentsubmission.service.MongoDBService;
import com.mengjq.assignmentsubmission.pojo.DeviceReg;
import com.mengjq.assignmentsubmission.core.FilesOpr;
import org.bson.Document;

import com.mengjq.assignmentsubmission.bin.main_Args;
import com.mengjq.assignmentsubmission.bin.main_noArgs;

public class main {
    public main(String[] args) {
        // The below variables are read from the config file.
//        String mongodbUrl = "mongodb+srv://mengjq:OXDueFslVZNWtiqT@assignmentsubmmsion.nttaj.mongodb.net/?retryWrites=true&w=majority";
//        String assignmentInfoDB = "AssignmentInfo";
//        String deviceRegDB = "DeviceReg";
//        String fileInfoDB = "FileInfo";
//        String studentInfoDB = "StudentInfo";
//        String clazz = "1909";
//        String userId = "19852331";

        EchoCLI echoCLI = new EchoCLI();
        echoCLI.loading("Initializing");
        DeviceReg deviceReg = new DeviceReg(); // 注册时用, 提交作业用,
        FilesOpr filesOpr = new FilesOpr();

        Config conf = new Config(); // 读取本地配置文件
        MongoDBService mongoDBService = new MongoDBService(conf.mongodbUrl, conf.clazz,
                conf.assignmentInfoDB, conf.deviceRegDB, conf.fileInfoDB, conf.studentInfoDB);
        echoCLI.loading("Loading Up-to-date Assignments Info");

        // 主程序是
        // 读取云端配置信息 Read Cloud Config
        Document stuInfo = mongoDBService.tryGetStuInfoByMAC(deviceReg.getDeviceMAC());
        // 输出读取到的配置信息 print student value info
        echoCLI.showStuInfo(stuInfo);

        if (args.length == 0) {
            // 信息配置页面
            main_noArgs.main(stuInfo, echoCLI, mongoDBService,
                    deviceReg, conf);
        }else{
            // 作业发送页面 Send Menu Function
            main_Args.main(args, stuInfo, echoCLI, mongoDBService, filesOpr);
        }

    }
}
