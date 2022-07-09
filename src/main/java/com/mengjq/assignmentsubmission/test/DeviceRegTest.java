package com.mengjq.assignmentsubmission.test;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.pojo.DeviceReg;
import com.mengjq.assignmentsubmission.service.DeviceRegService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DeviceRegTest {
    public static void main(String[] args) {
        Config conf = new Config();

        MongoClient mongoClient = MongoClients.create(conf.mongodbUrl);
        MongoDatabase clazzDB = mongoClient.getDatabase(conf.clazz);
        DeviceRegService deviceRegService = new DeviceRegService(clazzDB, conf.deviceRegDB);

        // regCurrentDevice
        DeviceReg deviceReg = new DeviceReg("19852331");
        System.out.println("regCurrentDevice");
        System.out.println("deviceReg:  " + deviceReg.toString());

        System.out.println(deviceRegService.regCurrentDevice(deviceReg));
        System.out.println("MongoDB insert DeviceReg success!");

    }
}
