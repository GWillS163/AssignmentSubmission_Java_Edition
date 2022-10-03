package com.mengjq.assignmentsubmission.service;

import com.mengjq.assignmentsubmission.mapper.FileInfoMapper;
import com.mengjq.assignmentsubmission.mapper.OprInfoMapper;
import com.mengjq.assignmentsubmission.pojo.DeviceInfo;
import com.mengjq.assignmentsubmission.util.utils;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OprInfoService {
    public OprInfoMapper oprInfoMapper;
    public utils utils;
    public LocalDateTime localDateTime;
    
    public OprInfoService(MongoDatabase clazzDB, String oprInfo) {
        oprInfoMapper = new OprInfoMapper(clazzDB, oprInfo);
        utils = new utils();
    }

    // Register
    public void register(DeviceInfo deviceInfo) {
        Document doc = new Document()
                .append("type", "register")
                .append("stuId", deviceInfo.getStuId())
                .append("stuName", deviceInfo.getStuName())
                .append("deviceMAC", deviceInfo.getDeviceMAC())
                .append("dateTime", LocalDateTime.now());
        oprInfoMapper.create(doc);
    }

    // Login
    public void login(DeviceInfo deviceInfo) {
        Document doc = new Document()
                .append("type", "login")
                .append("stuId", deviceInfo.getStuId())
                .append("stuName", deviceInfo.getStuName())
                .append("deviceMAC", deviceInfo.getDeviceMAC())
                .append("dateTime", LocalDateTime.now());
        oprInfoMapper.create(doc);
    }

    // Update
    public void update(DeviceInfo deviceInfo, String lastDeviceMAC) {
        Document doc = new Document()
                .append("type", "update")
                .append("stuId", deviceInfo.getStuId())
                .append("stuName", deviceInfo.getStuName())
                .append("deviceMAC", deviceInfo.getDeviceMAC())
                .append("lastDeviceMAC", lastDeviceMAC)
                .append("dateTime", LocalDateTime.now());
        oprInfoMapper.create(doc);
    }

    // Upload
    public void upload(DeviceInfo deviceInfo, String relateFileId) {
        Document doc = new Document()
                .append("type", "update")
                .append("stuId", deviceInfo.getStuId())
                .append("stuName", deviceInfo.getStuName())
                .append("deviceMAC", deviceInfo.getDeviceMAC())
                .append("dateTime", LocalDateTime.now())
                .append("relateFileId", relateFileId);
        oprInfoMapper.create(doc);
    }

    public void uploadMany(DeviceInfo deviceInfo, List<String> relateFileIds) {
        List<Document> docs = new ArrayList<>();
        for (String fileId : relateFileIds) {
            docs.add(new Document().append("type", "upload")
                    .append("stuId", deviceInfo.getStuId())
                    .append("deviceMAC", deviceInfo.getDeviceMAC())
                    .append("dateTime", LocalDateTime.now())
                    .append("stuName", deviceInfo.getStuName())
                    .append("relateFileId", fileId));
        }
        oprInfoMapper.createMany(docs);
    }


    // Download
    public void downloadMany(DeviceInfo deviceInfo, List<String> relateFileIds) {
        List<Document> docs = new ArrayList<>();
        for (String fileId : relateFileIds) {
            docs.add(new Document().append("type", "download")
                .append("stuId", deviceInfo.getStuId())
                .append("deviceMAC", deviceInfo.getDeviceMAC())
                .append("dateTime", LocalDateTime.now())
                .append("stuName", deviceInfo.getStuName())
                .append("relateFileId", fileId));
        }
        oprInfoMapper.createMany(docs);
    }

    // Delete
    public void deleteMany(DeviceInfo deviceInfo, List<String> relateFileIds) {
        List<Document> docs = new ArrayList<>();
        for (String fileId : relateFileIds) {
            docs.add(new Document().append("type", "delete")
                .append("stuId", deviceInfo.getStuId())
                .append("deviceMAC", deviceInfo.getDeviceMAC())
                .append("dateTime", LocalDateTime.now())
                .append("stuName", deviceInfo.getStuName())
                .append("relateFileId", fileId));
        }
        oprInfoMapper.createMany(docs);
    }
}
