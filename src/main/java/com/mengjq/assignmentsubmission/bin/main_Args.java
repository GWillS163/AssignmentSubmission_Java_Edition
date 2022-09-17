package com.mengjq.assignmentsubmission.bin;

import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.core.FilesOpr;
import com.mengjq.assignmentsubmission.core.Menu;
import com.mengjq.assignmentsubmission.core.mongoDBOpr;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.util.ArrayList;

public class main_Args {
    public static void main(String[] args, Document stuInfo, EchoCLI echoCLI,
                            mongoDBOpr mongoDBService, FilesOpr filesOpr) {
    //identify user by mac address  > Document{{"userId":"19852331","deviceId":"00:00:00:00:00:00","assignmentId":"1909","fileName":"test.txt","fileSize":0,"fileType":"txt","filePath":"test.txt","fileHash":""}}
    if (stuInfo == null) {
        echoCLI.noStuInfo();
        return;
    }

    //2. 展示最新Assignment数据
    FindIterable<Document> assignments =  mongoDBService.getCollectingAssignments();
            echoCLI.showAssignments(assignments);
            filesOpr.setBasicFileInfo(args);

    //3. [Record] 依照用户选择改名
        ArrayList<String> selectList = Menu.selectRenameMenu(assignments, filesOpr, stuInfo);

    //5. [Record] 上交 Files
    boolean res = mongoDBService.uploadFiles(filesOpr.fileInfoList);
            if(res){

                echoCLI.fileUpOver();
    }
    }
}
