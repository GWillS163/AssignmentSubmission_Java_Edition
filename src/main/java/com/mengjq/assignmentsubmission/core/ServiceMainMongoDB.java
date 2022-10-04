// DB layer operations for assignment submission
package com.mengjq.assignmentsubmission.core;

import com.mengjq.assignmentsubmission.pojo.DeviceInfo;
import com.mengjq.assignmentsubmission.pojo.FileInfo;
import com.mengjq.assignmentsubmission.pojo.OprInfo;
import com.mengjq.assignmentsubmission.pojo.StudentInfo;
import com.mengjq.assignmentsubmission.service.*;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class ServiceMainMongoDB {
    public MongoDatabase clazzDB;
    public AssignmentInfoService assignmentInfoService;
    public DeviceInfoService deviceInfoService;
    public FileInfoService fileInfoService;
    public StudentInfoService studentInfoService;
    public OprInfoService oprInfoService;

    public ServiceMainMongoDB(String mongodbUrl, String clazz, String assignmentInfo, String deviceReg, String fileInfo, String studentInfo, String oprInfo) {
        MongoClient mongoClient = MongoClients.create(mongodbUrl);
        clazzDB = mongoClient.getDatabase(clazz);

        assignmentInfoService = new AssignmentInfoService(clazzDB, assignmentInfo);
        deviceInfoService = new DeviceInfoService(clazzDB, deviceReg);
        studentInfoService = new StudentInfoService(clazzDB, studentInfo);
        fileInfoService= new FileInfoService(clazzDB, fileInfo);
        oprInfoService = new OprInfoService(clazzDB, oprInfo);
    }

    // Register
    public StudentInfo firstRegister(DeviceInfo deviceInfo, StudentInfo stuInfo, boolean isRegistered) {
        if (!isRegistered){
            registerRecord(deviceInfo);
            Document studentInfo = regCurrentDevice(deviceInfo);
            stuInfo.updateStudent(studentInfo);
        }
        return stuInfo;
    }

    // 设置菜单 - Setting Menu
    public Document regCurrentDevice(DeviceInfo deviceInfo) {
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
            Document stuInfoDoc = studentInfoService.findStuInfoByBson("stuId", stuId).first();
            if (Objects.isNull(stuInfoDoc)) {
                System.out.println("Your stuId is not exist.");
                continue;
            } else {
                System.out.println("your name is " + stuInfoDoc.getString("stuName"));
            }
            // confirm query data
            System.out.println("Are you sure? (y/n)");
            String confirm = sc.nextLine();
            if (Objects.equals(confirm, "y")) {
                deviceInfo.setStuId(stuId);
                deviceInfoService.regCurrentDevice(deviceInfo);
                return stuInfoDoc;
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
        String currentDeviceUserId = deviceInfoService.findUserIdByDeviceMAC(deviceMAC);
        if (currentDeviceUserId == null) {
            return null;
        }
        return studentInfoService.findStuInfoByBson("stuId", currentDeviceUserId).first();
    }

    // main_Args 获得正在收集的作业 - get the assignment that is being collected
    public FindIterable<Document> getCollectingAssignments() {
        return assignmentInfoService.getCollectingAssignments();
    }

    // 批量上传文件 - Batch upload files
    public boolean uploadFiles(List<FileInfo> fileInfos) {
        return fileInfoService.uploadFiles(fileInfos);
    }

    // 下载作业 - Download assignments
    public void downloadFiles(List<Integer> fileIds, String savePath) {
        for (Integer fileId : fileIds) {
            Document doc = new Document().append("fileId", fileId);
            fileInfoService.downloadMany(doc, savePath);
        }
    }

    // 删除作业 - Delete assignments
    public void deleteFiles(List<Integer> fileIds) {
        for (Integer fileId : fileIds) {
            fileInfoService.deleteMany("fileId", fileId);
    }
    }

    // register Record
    public void registerRecord(DeviceInfo deviceInfo) {
        oprInfoService.register(deviceInfo);
    }

    // login Record
    public void loginRecord(DeviceInfo deviceInfo) {
        oprInfoService.login(deviceInfo);
    }

    // update Record
    public void updateRecord(DeviceInfo deviceInfo, StudentInfo studentInfo, String lastDeviceMAC) {
        oprInfoService.update(deviceInfo, studentInfo, lastDeviceMAC);
    }

    // upload Record
    public void uploadRecord(DeviceInfo deviceInfo, List<Integer> relateFileIds) {
        oprInfoService.uploadMany(deviceInfo, relateFileIds);
    }

    // download Record
    public void downloadRecord(DeviceInfo deviceInfo, List<String> relateFileIds) {
        oprInfoService.downloadMany(deviceInfo, relateFileIds);
    }

    // delete Record
    public void deleteRecord(DeviceInfo deviceInfo, List<String>  relateFileIds) {
        oprInfoService.deleteMany(deviceInfo, relateFileIds);
    }

    // request Record
    public void requestSelfRecord(DeviceInfo deviceInfo) {
        oprInfoService.requestSelf(deviceInfo);
    }

    // request All Record
    public void requestAllRecord(DeviceInfo deviceInfo) {
        oprInfoService.requestAll(deviceInfo);
    }

    public String getLastMAC(String stuId) {
        return deviceInfoService.findMACByUserId(stuId);
    }
}
