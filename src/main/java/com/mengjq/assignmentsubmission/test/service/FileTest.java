package com.mengjq.assignmentsubmission.test.service;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.core.FilesOpr;
import com.mengjq.assignmentsubmission.pojo.FileInfo;
import com.mengjq.assignmentsubmission.service.FileInfoService;
import com.mongodb.client.*;
import org.bson.Document;
import org.junit.Test;

import java.io.IOException;

public class FileTest {
    Config conf = new Config();
    FilesOpr filesOpr = new FilesOpr();
    EchoCLI echoCLI= new EchoCLI();
    MongoClient mongoClient = MongoClients.create(conf.mongodbUrl);
    MongoDatabase clazzDB = mongoClient.getDatabase(conf.clazz);
    FileInfoService fileInfoService = new FileInfoService(clazzDB, conf.fileInfoDB);


    @Test
    public void getAllSubmittedFileInfo(){
        // getAllSubmittedFileInfo
        System.out.println("getAllSubmittedFileInfo:");
        FindIterable<Document> allSubmitStatusDocs = fileInfoService.getAllSubmittedFileInfo();
        echoCLI.showMySubmitStatus(allSubmitStatusDocs);
    }

    @Test
    public void getMySubmittedInfo(){
        // getMySubmitStatus
        System.out.println("getMySubmitStatus:");
        FindIterable<Document> mySubmitStatusDocs = fileInfoService.getMySubmitStatus("19852331");
        echoCLI.showMySubmitStatus(mySubmitStatusDocs);

    }

    // a test function for upload
    @Test
    public void uploadFile() throws IOException {
        // uploadFiles
        System.out.println("upload file:");
        FileInfo fileInfo = new FileInfo("D:\\system\\Downloads\\孟骏清发票.jpg");
        fileInfo.setStuName("赵云龙3");

        filesOpr.addFileInfoList(fileInfo);
        echoCLI.loading("uploading...");
        fileInfoService.uploadFiles(filesOpr.getFileInfoList());
    }

    // a test function for download
    @Test
    public void downloadFile() throws IOException {
//         download file
        System.out.println("download file:");
        boolean downStatus = fileInfoService.downloadFiles("rawName","494424303.mp3",
                "D:\\494424303.mp3");
        System.out.println("download file done!");

    }

}
