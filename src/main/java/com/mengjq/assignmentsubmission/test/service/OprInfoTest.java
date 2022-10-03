package com.mengjq.assignmentsubmission.test.service;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.pojo.DeviceInfo;
import com.mengjq.assignmentsubmission.service.OprInfoService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class OprInfoTest {
    Config conf = new Config().initConfigByLocal();

    MongoClient mongoClient = MongoClients.create(conf.mongodbUrl);
    MongoDatabase clazzDB = mongoClient.getDatabase(conf.clazz);
    OprInfoService oprInfoService = new OprInfoService(clazzDB, conf.oprInfoDB);
    DeviceInfo deviceInfo = new DeviceInfo("赵云龙", "19852333");

    @Test
    public void registerOpr(){
        System.out.println("Register Opr Test");
        oprInfoService.register(deviceInfo);
    }

    @Test
    public void loginOpr(){
        System.out.println("Login Opr Test");
        oprInfoService.login(deviceInfo);
    }

    @Test
    public void updateOpr(){
        System.out.println("Update Opr Test");
        oprInfoService.update(deviceInfo, "123456789");
    }

    @Test
    public void uploadOpr(){
        System.out.println("Upload Opr Test");
        oprInfoService.upload(deviceInfo, "1");
    }

    @Test
    public void uploadManyOpr(){
        System.out.println("Upload Many Opr Test");
        List<String> relateFileIds = new ArrayList<>();
        relateFileIds.add("111");
        relateFileIds.add("222");
        oprInfoService.uploadMany(deviceInfo, relateFileIds);
    }

    @Test
    public void downloadManyOpr(){
        System.out.println("downloadManyOpr Opr Test");
        List<String> relateFileIds = new ArrayList<>();
        relateFileIds.add("333");
        relateFileIds.add("444");
        oprInfoService.downloadMany(deviceInfo, relateFileIds);
    }

    @Test
    public void deleteManyOpr(){
        System.out.println("Delete Opr Test");
        List<String> relateFileIds = new ArrayList<>();
        relateFileIds.add("555");
        relateFileIds.add("666");
        oprInfoService.deleteMany(deviceInfo, relateFileIds);
    }
}
