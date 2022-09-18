package com.mengjq.assignmentsubmission.bin;
import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.core.mongoDBOpr;
import com.mengjq.assignmentsubmission.pojo.DeviceInfo;
import com.mengjq.assignmentsubmission.core.FilesOpr;
import org.bson.Document;

public class main {
    public static void main(String[] args) {

        EchoCLI echoCLI = new EchoCLI();
        echoCLI.loading("Initializing");
        DeviceInfo deviceInfo = new DeviceInfo(); // 注册时用, 提交作业用,
        FilesOpr filesOpr = new FilesOpr();

        Config conf = new Config(); // 读取本地配置文件
        mongoDBOpr mongoDBService = new mongoDBOpr(conf.mongodbUrl, conf.clazz,
                conf.assignmentInfoDB, conf.deviceRegDB, conf.fileInfoDB, conf.studentInfoDB);
        echoCLI.loading("Loading Up-to-date Assignments Info");

        // 主程序是
        // 读取云端配置信息 Read Cloud Config
        Document stuInfo = mongoDBService.tryGetStuInfoByMAC(deviceInfo.getDeviceMAC());
        // 输出读取到的配置信息 print student value info
        echoCLI.showStuInfo(stuInfo);

        if (args.length == 0) {
            // 信息配置页面
            main_noArgs.run(stuInfo, echoCLI, mongoDBService,
                    deviceInfo, conf);
        }else{
            // 作业发送页面 Send Menu Function
            main_Args.run(args, stuInfo, echoCLI, mongoDBService, filesOpr);
        }

    }
}
