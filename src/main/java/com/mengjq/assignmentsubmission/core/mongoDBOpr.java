// DB layer operations for assignment submission
package com.mengjq.assignmentsubmission.core;

import com.mengjq.assignmentsubmission.pojo.DeviceInfo;
import com.mengjq.assignmentsubmission.pojo.FileInfo;
import com.mengjq.assignmentsubmission.service.AssignmentInfoService;
import com.mengjq.assignmentsubmission.service.DeviceInfoService;
import com.mengjq.assignmentsubmission.service.FileInfoService;
import com.mengjq.assignmentsubmission.service.StudentInfoService;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class mongoDBOpr {
    public MongoDatabase clazzDB;
    public AssignmentInfoService assignmentInfoService;
    public DeviceInfoService deviceInfoService;
    public FileInfoService fileInfoService;
    public StudentInfoService studentInfoService;

    public mongoDBOpr(String mongodbUrl, String clazz, String assignmentInfo, String deviceReg, String fileInfo, String studentInfo) {
        MongoClient mongoClient = MongoClients.create(mongodbUrl);
        clazzDB = mongoClient.getDatabase(clazz);

        assignmentInfoService = new AssignmentInfoService(clazzDB, assignmentInfo);
        deviceInfoService = new DeviceInfoService(clazzDB, deviceReg);
        studentInfoService = new StudentInfoService(clazzDB, studentInfo);
        fileInfoService= new FileInfoService(clazzDB, fileInfo);
    }

    // 设置菜单 - Setting Menu
    public void regCurrentDevice(DeviceInfo deviceInfo) {
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
            // 智能补全用户信息 用户确认 - intelligent complement user confirm the stuId
            Document document = studentInfoService.findStuInfoByStuId(eq("stuId", stuId)).first();
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
                deviceInfo.setStuId(stuId);
                deviceInfoService.regCurrentDevice(deviceInfo);
                return;
            }
        }
    }

    // 获得个人已提交的作业 - get personal submitted assignments
    public FindIterable<Document> getMySubmittedFileInfo(String stuId) {
//        Document devDoc = deviceRegService.tryGetStudentInfo("deviceMAC", deviceMAC);
//        return fileInfoService.getMySubmitStatus(devDoc.get("stuId").toString());
        return fileInfoService.getMySubmitStatus(stuId);
    }

    // 获得所有人提交的作业 - get all submitted assignments
    public FindIterable<Document> getAllSubmittedFileInfo() {
        return fileInfoService.getAllSubmittedFileInfo();
    }

    // 获得所有人信息 - get all student info
    public FindIterable<Document> getAllStuInfo() {
        return studentInfoService.getAllStuInfo();
    }

    // 通过设备MAC地址获得用户信息 - Get user information
    public Document tryGetStuInfoByMAC(String deviceMAC) {
        Document deviceInfo = deviceInfoService.tryGetStudentInfo("deviceMAC", deviceMAC);
        if (deviceInfo == null) {
            return null;
        }
        return studentInfoService.findStuInfoByStuId(eq("stuId", deviceInfo.get("stuId"))).first();
    }

    // 批量上传文件 Batch upload files
    public boolean uploadFiles(List<FileInfo> fileInfos) {
        return fileInfoService.uploadFiles(fileInfos);
    }

    // 测试接口 Test Interface
    public Document getTestAssignment() {
        return assignmentInfoService.getTestAssignment();
    }

    // main_Args 获得正在收集的作业 - get the assignment that is being collected
    public FindIterable<Document> getCollectingAssignments() {
        return assignmentInfoService.getCollectingAssignments();
    }
}
