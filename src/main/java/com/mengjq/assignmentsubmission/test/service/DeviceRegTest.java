package com.mengjq.assignmentsubmission.test.service;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.pojo.DeviceInfo;
import com.mengjq.assignmentsubmission.service.DeviceInfoService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.junit.Test;

public class DeviceRegTest {
    Config conf = new Config();

    MongoClient mongoClient = MongoClients.create(conf.mongodbUrl);
    MongoDatabase clazzDB = mongoClient.getDatabase(conf.clazz);
    DeviceInfoService deviceInfoService = new DeviceInfoService(clazzDB, conf.deviceRegDB);

    DeviceInfo deviceInfo = new DeviceInfo();
    @Test
    public void regDevice(){
        // regCurrentDevice
        System.out.println("regCurrentDevice");
        System.out.println("deviceReg:  " + deviceInfo.toString());

        deviceInfoService.regCurrentDevice(deviceInfo);
        System.out.println("MongoDB insert DeviceReg success!");
    }

    @Test
    public void findUserIdByDeviceMAC(){
        // findUserIdByDeviceMAC
        System.out.println("findUserIdByDeviceMAC");
        System.out.println("the userId of Current Device mappings:  " +
                deviceInfoService.findUserIdByDeviceMAC(deviceInfo.getDeviceMAC()));
    }
}
