package com.mengjq.assignmentsubmission.bin;
import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.core.FilesOpr;
import com.mengjq.assignmentsubmission.core.mongoDBOpr;
import com.mengjq.assignmentsubmission.pojo.DeviceInfo;
import org.bson.Document;

public class main {
    public static void main(String[] args) {

        EchoCLI echoCLI = new EchoCLI();
        DeviceInfo deviceInfo = new DeviceInfo(); // 获取设备信息 - Get device info
        FilesOpr filesOpr = new FilesOpr();
        Config conf = new Config(); // 读取本地配置文件 - Read local config file
        mongoDBOpr mongoDBService = new mongoDBOpr(conf.mongodbUrl, conf.clazz,
                conf.assignmentInfoDB, conf.deviceRegDB, conf.fileInfoDB, conf.studentInfoDB);

        // 读取云端配置信息 - Read Cloud Config
        Document stuInfo = mongoDBService.tryGetStuInfoByMAC(deviceInfo.getDeviceMAC());

        // 输出读取到的配置信息 - print student value info
        echoCLI.showStuInfo(stuInfo);
        // clear the CLI all of outputs

        if (args.length == 0) {
            // 信息配置页面 - Information Configure Page
            main_noArgs.run(stuInfo, echoCLI, mongoDBService,
                    deviceInfo, conf);
        }else{
            // 作业发送页面 - Send Menu Function
            main_Args.run(args, stuInfo, echoCLI, mongoDBService, deviceInfo, filesOpr);
        }

    }
}
