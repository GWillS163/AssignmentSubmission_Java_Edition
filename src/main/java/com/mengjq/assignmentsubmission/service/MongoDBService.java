// DB layer operations for assignment submission
package com.mengjq.assignmentsubmission.service;

import com.mengjq.assignmentsubmission.pojo.DeviceReg;
import com.mengjq.assignmentsubmission.pojo.FileInfo;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class MongoDBService {
    public MongoDatabase clazzDB;
    public MongoCollection<Document> assiInfoDBCollection;
    public MongoCollection<Document> devicRegDBCollection;
    public MongoCollection<Document> studInfoDBCollection;
    public MongoCollection<Document> fileInfoDBCollection;
    public AssignmentInfoService assignmentInfoService;
    public DeviceRegService deviceRegService;
    public FileInfoService fileInfoService;
    public StudentInfoService studentInfoService;

    public MongoDBService(String mongodbUrl, String clazz, String assignmentInfo, String deviceReg, String fileInfo, String studentInfo) {
        MongoClient mongoClient = MongoClients.create(mongodbUrl);
        clazzDB = mongoClient.getDatabase(clazz);

        assignmentInfoService = new AssignmentInfoService(clazzDB, assignmentInfo);
        deviceRegService = new DeviceRegService(clazzDB, deviceReg);
        fileInfoService = new FileInfoService(clazzDB, fileInfo);
        studentInfoService = new StudentInfoService(clazzDB, studentInfo);

        assiInfoDBCollection = assignmentInfoService.assiInfoDBCollection;
        devicRegDBCollection = deviceRegService.devicRegDBCollection;
        studInfoDBCollection = studentInfoService.studInfoDBCollection;
        fileInfoDBCollection = fileInfoService.fileInfoDBCollection;
    }

    // Setting Menu
    public DeviceReg regCurrentDevice(DeviceReg deviceReg) {
        while (true) {
            // Scan the user input
            Scanner sc = new Scanner(System.in);
            System.out.println("Please input your stuId: ");
            String stuId = sc.nextLine();
            System.out.println("Your stuId is: " + stuId);

            if (stuId == null){
                System.out.println("Your stuId is null!");
                continue;
            }
            // user confirm the stuId
            Document document = studInfoDBCollection.find(eq("stuId", stuId)).first();
            if (Objects.isNull(document)) {
                System.out.println("Your stuId is not exist.");
                continue;
            } else {
                System.out.println("your name is " + document.getString("stuName"));
            }
            // confirm query data
            System.out.println("Are you sure? (y/n)");
            String confirm = sc.nextLine();
            if (Objects.equals(confirm, "y")) {
                deviceReg.setStuId(stuId);
                deviceRegService.regCurrentDevice(deviceReg);
                return deviceReg;
            }
        }
    }

    public FindIterable<Document> getMySubmittedFileInfo(String stuId) {
//        Document devDoc = deviceRegService.tryGetStudentInfo("deviceMAC", deviceMAC);
//        return fileInfoService.getMySubmitStatus(devDoc.get("stuId").toString());
        return fileInfoService.getMySubmitStatus(stuId);
    }

    public FindIterable<Document> getAllSubmittedFileInfo() {
        return fileInfoService.getAllSubmittedFileInfo();
    }

    public FindIterable<Document> getAllStuInfo() {
        return studInfoDBCollection.find();
    }
//    TODO: version 2  show online about Info
//    public Document getMenuAbout(){
//        return DeviceRegService.getMenuAbout();
//    }


    // Upload Interface
    public FindIterable<Document> getCollectingAssignments() {
        // get all files from mongoDB
        return assignmentInfoService.getCollectingAssignments();
    }

    public Document tryGetStuInfoByMAC(String deviceMAC) {
        Document deviceInfo = deviceRegService.tryGetStudentInfo("deviceMAC", deviceMAC);
        if (deviceInfo == null) {
            return null;
        }
        Document stuInfo = studInfoDBCollection.find(eq("stuId", deviceInfo.get("stuId"))).first();
//        System.out.println(stuInfo);
        return stuInfo;
    }

    public boolean uploadFiles(List<FileInfo> fileInfos) {
        return fileInfoService.uploadFiles(fileInfos);
    }


    // Server
    public void addAssignment(String assiId, String briefName, String describe, String fileNameRule, String DDL, Boolean status) {
        assignmentInfoService.addAssignment(assiId, briefName, describe, fileNameRule, DDL, status);
    }

    // Test Interface
    public Document getTestAssignment() {
        return assignmentInfoService.getTestAssignment();
    }

}
