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
import java.time.LocalDateTime;
import java.util.Date;

public class FileTest {
    Config conf = new Config().initConfigByLocal();
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
//        FileInfo fileInfo = new FileInfo("D:\\system\\Downloads\\孟骏清发票.jpg");
        FileInfo fileInfo2 = new FileInfo("D:\\Step1\\494424303.mp3");
//        fileInfo.setStuName("孟骏清");
        fileInfo2.setStuName("孟骏清");

//        filesOpr.addFileInfoList(fileInfo);
        filesOpr.addFileInfoList(fileInfo2);
        fileInfoService.uploadFiles(filesOpr.getFileInfoList());
    }

    // a test function for download
    @Test
    public void downloadMany() throws IOException {
//         download file
        System.out.println("download file:");
//        boolean downStatus = fileInfoService.downloadMany("stuName","孟骏清",
//                "D:\\");
        System.out.println("download file done!");

    }

    @Test
    public void getTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        Date d = new Date();
        System.out.println(d.getTime());
        System.out.println(d.toString());
        System.out.println(localDateTime.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println(localDateTime.toLocalTime());
        System.out.println(localDateTime.toString());
        System.out.println(localDateTime.toString().replace("T"," "));

        // use localDateTime to get time with format "YYYY-MM-DD_HH-MM-SS"
        String time = localDateTime.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
        System.out.println(time);
//        System.out.println(d.());
    }

    @Test
    public void deleteMany() {
        System.out.println("delete file:");
        fileInfoService.deleteMany("stuName","孟骏清");
        System.out.println("delete file done!");
    }

}
