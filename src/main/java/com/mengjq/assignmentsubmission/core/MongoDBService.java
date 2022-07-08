// DB layer operations for assignment submission
package com.mengjq.assignmentsubmission.core;

import com.mengjq.assignmentsubmission.pojo.DeviceReg;
import com.mengjq.assignmentsubmission.pojo.FileInfo;
import com.mengjq.assignmentsubmission.service.AssignmentInfoService;
import com.mengjq.assignmentsubmission.service.DeviceRegService;
import com.mengjq.assignmentsubmission.service.FileInfoService;
import com.mengjq.assignmentsubmission.service.StudentInfoService;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.List;

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

        AssignmentInfoService assignmentInfoService = new AssignmentInfoService(clazzDB, assignmentInfo);
        DeviceRegService deviceRegService = new DeviceRegService(clazzDB, deviceReg);
        FileInfoService fileInfoService = new FileInfoService(clazzDB, fileInfo);
        StudentInfoService studentInfoService = new StudentInfoService(clazzDB, studentInfo);
        assiInfoDBCollection = assignmentInfoService.assiInfoDBCollection;
        devicRegDBCollection = deviceRegService.devicRegDBCollection;
        studInfoDBCollection = studentInfoService.studInfoDBCollection;
        fileInfoDBCollection = fileInfoService.fileInfoDBCollection;
    }


    public Document getStuInfo(String deviceMAC) {
        Document deviceInfo = deviceRegService.devicRegDBCollection.find(eq("deviceMAC", deviceMAC)).first();
        System.out.println(deviceInfo);
        Document stuInfo = studInfoDBCollection.find(eq("stuId", deviceInfo.get("stuId"))).first();
//        System.out.println(stuInfo);
        return stuInfo;
    }


    public void regCurrentDevice(DeviceReg deviceReg) {
        deviceRegService.regCurrentDevice(deviceReg);
    }

    public void getStatusByStuId(String userId) {
        fileInfoService.getStatusByStuId(userId);
    }

    public Iterable<Document> getAssignments() {
//        Iterable<Document>
//        System.out.println();assignmentInfoService.getAssignments();
//        return result;
        return assignmentInfoService.getAssignments();
    }


    public void uploadFiles(List<FileInfo> fileInfos) {
        fileInfoService.uploadFiles(fileInfos);
    }

    public Document getTestAssignment() {
        return assignmentInfoService.getTestAssignment();
    }

    public void addAssignment(String assId, String briefName, String describe, String fileNameRule, String DDL, Boolean status) {
        assignmentInfoService.addAssignment(assId, briefName, describe, fileNameRule, DDL, status);
    }
}
