package com.mengjq.assignmentsubmission.bin;

import com.mengjq.assignmentsubmission.conf.LanguageSet;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.core.FilesOpr;
import com.mengjq.assignmentsubmission.core.Menu;
import com.mengjq.assignmentsubmission.core.ServiceMainMongoDB;
import com.mengjq.assignmentsubmission.pojo.DeviceInfo;
import com.mengjq.assignmentsubmission.pojo.StudentInfo;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.ArrayList;

public class main_Args {

    public static void run(String[] args, StudentInfo stuInfo, EchoCLI echoCLI,
                           ServiceMainMongoDB mongoDBService, DeviceInfo deviceInfo, FilesOpr filesOpr, LanguageSet languageSet
    , boolean isRegistered) {
        for (String arg : args) {
            System.out.println(languageSet.sendModePrompt+ arg);
        }
        //1. 记录本次登录 - Record this login
        mongoDBService.loginRecord(deviceInfo);

        //2. 展示最新Assignment数据 - Show the latest assignment data
        FindIterable<Document> assignments =  mongoDBService.getCollectingAssignments();
        echoCLI.showAssignments(assignments);
        filesOpr.setBasicFileInfo(args);

        // 3.0 若没注册 则注册 - If not registered, register
        if (!isRegistered){
            echoCLI.noStuInfo(languageSet);
            mongoDBService.registerRecord(deviceInfo);
            stuInfo = mongoDBService.firstRegister(deviceInfo, stuInfo, false);
        }
        //3. 依照用户选择改名 - Rename the file according to the user's choice
        ArrayList<String> selectList = Menu.selectRenameMenu(assignments, filesOpr, stuInfo, languageSet);

        //5. 上交 Files - Submit files
        boolean res = mongoDBService.uploadFiles(filesOpr.fileInfoList);
        if(res){
            echoCLI.fileUpSuccess(languageSet);
        }else{
            echoCLI.fileUpFailed(languageSet);
        }
        mongoDBService.uploadRecord(deviceInfo, filesOpr.getFileIdList());
    }
}
