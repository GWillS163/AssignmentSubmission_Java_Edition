package com.mengjq.assignmentsubmission.test.service;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.pojo.DeviceInfo;
import com.mengjq.assignmentsubmission.service.DeviceInfoService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DeviceRegTest {
    public static void main(String[] args) {
        Config conf = new Config();

        MongoClient mongoClient = MongoClients.create(conf.mongodbUrl);
        MongoDatabase clazzDB = mongoClient.getDatabase(conf.clazz);
        DeviceInfoService deviceInfoService = new DeviceInfoService(clazzDB, conf.deviceRegDB);

        // regCurrentDevice
        DeviceInfo deviceInfo = new DeviceInfo();
        System.out.println("regCurrentDevice");
        System.out.println("deviceReg:  " + deviceInfo.toString());

        deviceInfoService.regCurrentDevice(deviceInfo);
        System.out.println("MongoDB insert DeviceReg success!");

    }
}
