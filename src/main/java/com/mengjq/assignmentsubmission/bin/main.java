package com.mengjq.assignmentsubmission.bin;
import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.conf.LanguageSet;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.core.FilesOpr;
import com.mengjq.assignmentsubmission.core.mongoDBOpr;
import com.mengjq.assignmentsubmission.pojo.DeviceInfo;
import org.bson.Document;


public class main {
    public static void main(String[] args) {
        EchoCLI echoCLI = new EchoCLI();

        Config config = new Config(); // 读取本地配置文件 - Read local config file
        Config conf = config.loading(); // 多线程加载 - Multi-threaded loading

        LanguageSet languageSet = new LanguageSet(conf.LanguageNationCode); // 设置语言 - Set language
        DeviceInfo deviceInfo = new DeviceInfo(); // 获取设备信息 - Get device info
        FilesOpr filesOpr = new FilesOpr();

        mongoDBOpr mongoDBService = new mongoDBOpr(conf.mongodbUrl, conf.clazz,
                conf.assignmentInfoDB, conf.deviceRegDB, conf.fileInfoDB, conf.studentInfoDB);

        // 读取云端配置信息 - Read Cloud Config
        Document stuInfo = mongoDBService.tryGetStuInfoByMAC(deviceInfo.getDeviceMAC());
        if (stuInfo == null) {
            echoCLI.echo(languageSet.echoCLIShowAllStatusNoStu);
            return;
        }
        // 输出读取到的配置信息 - print student value info
        echoCLI.showStuInfo(stuInfo);

        if (args.length == 0) {
            // 信息配置页面 - Information Configure Page
            main_noArgs.run(stuInfo, echoCLI, mongoDBService,
                    deviceInfo, conf, languageSet);
        }else{
            // 作业发送页面 - Send Menu Function
            main_Args.run(args, stuInfo, echoCLI, mongoDBService, deviceInfo, filesOpr, languageSet);
        }

    }
}
