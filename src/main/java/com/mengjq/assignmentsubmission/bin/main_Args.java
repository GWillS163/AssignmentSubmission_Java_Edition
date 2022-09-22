package com.mengjq.assignmentsubmission.bin;

import com.mengjq.assignmentsubmission.conf.LanguageSet;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.core.FilesOpr;
import com.mengjq.assignmentsubmission.core.Menu;
import com.mengjq.assignmentsubmission.core.mongoDBOpr;
import com.mengjq.assignmentsubmission.pojo.DeviceInfo;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.ArrayList;

public class main_Args {

    public static void run(String[] args, Document stuInfo, EchoCLI echoCLI,
                           mongoDBOpr mongoDBService, DeviceInfo deviceInfo, FilesOpr filesOpr,  LanguageSet languageSet) {
        for (String arg : args) {
            System.out.println(languageSet.sendModePrompt+ arg);
        }
        //identify user by mac address  > Document{{"userId":"19852331","deviceId":"00:00:00:00:00:00","assignmentId":"1909","fileName":"test.txt","fileSize":0,"fileType":"txt","filePath":"test.txt","fileHash":""}}
        if (stuInfo == null) {
            echoCLI.noStuInfo(languageSet);
            return;
        }
        //1. 记录本次登录 - Record this login
        // TODO: usingHistory 内记录登录数据
        // Login 19852331 孟骏清 BA-D8-88-A9-8B-9F 2022-9-19 21:22:59

        //2. 展示最新Assignment数据 - Show the latest assignment data
        FindIterable<Document> assignments =  mongoDBService.getCollectingAssignments();
        echoCLI.showAssignments(assignments);
        filesOpr.setBasicFileInfo(args);

        //3. 依照用户选择改名 - Rename the file according to the user's choice
            ArrayList<String> selectList = Menu.selectRenameMenu(assignments, filesOpr, stuInfo, languageSet);

        //5. 上交 Files - Submit files
        boolean res = mongoDBService.uploadFiles(filesOpr.fileInfoList);
        if(res){
            echoCLI.fileUpOver(languageSet);
        }
    }
}
