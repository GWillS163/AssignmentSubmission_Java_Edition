package com.mengjq.assignmentsubmission.test.service;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.core.FilesOpr;
import com.mengjq.assignmentsubmission.pojo.FileInfo;
import com.mengjq.assignmentsubmission.service.FileInfoService;
import com.mongodb.client.*;
import org.bson.Document;

import java.io.IOException;

public class FileTest {
    public static void main(String[] args) {
        Config conf = new Config();
        MongoClient mongoClient = MongoClients.create(conf.mongodbUrl);
        MongoDatabase clazzDB = mongoClient.getDatabase(conf.clazz);

        FileInfoService fileInfoService = new FileInfoService(clazzDB, conf.fileInfoDB);
        FilesOpr filesOpr = new FilesOpr();
        EchoCLI echoCLI= new EchoCLI();

        // getAllSubmittedFileInfo
        System.out.println("getAllSubmittedFileInfo:");
        FindIterable<Document> allSubmitStatusDocs = fileInfoService.getAllSubmittedFileInfo();
        echoCLI.showMySubmitStatus(allSubmitStatusDocs);


        // uploadFiles
        System.out.println("upload file:");
        FileInfo fileInfo = null;
        try {
            fileInfo = new FileInfo("D:\\system\\桌面\\Client_submit_mainV3.3 测试版.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }
        filesOpr.addFileInfoList(fileInfo);
        fileInfoService.uploadFiles(filesOpr.getFileInfoList());
//
        // download file
//        System.out.println("download file:");
//        fileInfoService.downloadFile("rawName","readme.md",
//                "D:\\Project\\AssignmentSubmission_Java_Edition\\src\\main\\java\\com\\mengjq\\assignmentsubmission\\");
//        System.out.println("download file done!");

        // getMySubmitStatus
        System.out.println("getMySubmitStatus:");
        FindIterable<Document> mySubmitStatusDocs = fileInfoService.getMySubmitStatus("19852331");
        echoCLI.showMySubmitStatus(mySubmitStatusDocs);

    }


}
