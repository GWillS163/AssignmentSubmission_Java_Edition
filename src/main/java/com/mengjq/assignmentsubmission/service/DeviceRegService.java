package com.mengjq.assignmentsubmission.service;

import com.mengjq.assignmentsubmission.pojo.DeviceReg;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DeviceRegService {
    public MongoCollection<Document> devicRegDBCollection;

    public DeviceRegService(MongoDatabase clazzDB,  String deviceReg) {
        devicRegDBCollection = clazzDB.getCollection(deviceReg);
        System.out.println(devicRegDBCollection.getNamespace());
    }
    // TODO:基本完成，需测试
    public void regCurrentDevice(DeviceReg deviceReg) {
        Document deviceRegDoc = new Document()
                .append("deviceMAC", deviceReg.getDeviceMAC())
                .append("deviceName", deviceReg.getDeviceName())
                .append("deviceUser", deviceReg.getDeviceUser());

        // upload current device to mongoDB DeviceReg
        devicRegDBCollection.insertOne(deviceRegDoc);
        System.out.println("MongoDB insert DeviceReg success!");
    }

}
