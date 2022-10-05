package com.mengjq.assignmentsubmission.bin;
import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.conf.LanguageSet;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.core.FilesOpr;
import com.mengjq.assignmentsubmission.core.ServiceMainMongoDB;
import com.mengjq.assignmentsubmission.pojo.DeviceInfo;
import com.mengjq.assignmentsubmission.pojo.StudentInfo;
import org.bson.Document;


public class main {
    public static void main(String[] args) {
        EchoCLI echoCLI = new EchoCLI();

        Config config = new Config().initConfigByLocal(); // 读取本地配置文件 - Read local config file
        Config conf = config.loading(); // 多线程加载 - Multi-threaded loading

        LanguageSet languageSet = new LanguageSet(conf.LanguageNationCode); // 设置语言 - Set language
        DeviceInfo deviceInfo = new DeviceInfo(); // 获取设备信息 - Get device info
        FilesOpr filesOpr = new FilesOpr();
        StudentInfo studentInfo = new StudentInfo();

        echoCLI.colorPrint("连接云端 - Link to DB", "cyan");
        ServiceMainMongoDB mongoDBService = new ServiceMainMongoDB(conf.mongodbUrl, conf.clazz,
                conf.assignmentInfoDB, conf.deviceRegDB, conf.fileInfoDB, conf.studentInfoDB, conf.oprInfoDB);

        // 读取云端配置信息 - Read Cloud Config
        echoCLI.colorPrint("读取云端配置信息 - Read Cloud Config", "green");
        Document stuInfo = mongoDBService.tryGetStuInfoByMAC(deviceInfo.getDeviceMAC()); // 本设备的上一条学生信息 - The last student information of this device
        boolean isRegistered = stuInfo != null;
        if (!isRegistered) {
            echoCLI.echo(languageSet.echoCLIShowAllStatusNoStu);
        }else{
            deviceInfo.updateStudentInfo(stuInfo); // 更新学生信息 - Update student information
            studentInfo.updateStudent(stuInfo);
        }
        // 输出读取到的配置信息 - print student value info
        echoCLI.showStuInfo(stuInfo);

        if (args.length == 0) {
            // 信息配置页面 - Information Configure Page
            main_noArgs.run(studentInfo, echoCLI, mongoDBService,
                    deviceInfo, conf, languageSet, isRegistered);
        }else{
            // 作业发送页面 - Send Menu Function
            main_Args.run(args, studentInfo, echoCLI, mongoDBService,
                    deviceInfo, filesOpr, languageSet, isRegistered);
        }

    }
}
