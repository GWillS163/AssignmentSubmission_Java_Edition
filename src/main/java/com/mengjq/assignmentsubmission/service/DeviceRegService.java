package com.mengjq.assignmentsubmission.service;

import com.mengjq.assignmentsubmission.pojo.DeviceReg;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Objects;
import java.util.Scanner;

import static com.mongodb.client.model.Filters.eq;

public class DeviceRegService {
    public MongoCollection<Document> devicRegDBCollection;

    public DeviceRegService(MongoDatabase clazzDB,  String deviceReg) {
        devicRegDBCollection = clazzDB.getCollection(deviceReg);
//        System.out.println(devicRegDBCollection.getNamespace());
    }
    // change JDK version if echo "Timed out after 30000 ms。。。"
    // TODO: 可能会冲突，注册前需要判断是否已经注册过了，如果已经注册过了，则不能再注册了。
    public boolean regCurrentDevice(DeviceReg deviceReg) {

        Document deviceRegDoc = new Document()
                .append("stuId", deviceReg.getStuId())
                .append("deviceMAC", deviceReg.getDeviceMAC())
                .append("deviceName", deviceReg.getDeviceName())
                .append("updateTime", deviceReg.getUpdateTime())
                .append("deviceUser", deviceReg.getDeviceUser());

        // upload current device to mongoDB DeviceReg
        // find the device whether exist or not
        Document existDevice = devicRegDBCollection.find(eq("deviceMAC", deviceReg.getDeviceMAC())).first();
        if (existDevice == null) {
            devicRegDBCollection.insertOne(deviceRegDoc);
            System.out.println("DeviceReg success!");
            return true;
        } else {
            devicRegDBCollection.updateOne(eq("deviceMAC", deviceReg.getDeviceMAC()), new Document("$set", deviceRegDoc));
            System.out.println("DeviceReg update success! The device has been registered!");
            return false;
        }
    }

    public Document tryGetStudentInfo(String field, String value) {
        Document deviceInfo = devicRegDBCollection.find(eq(field, value)).first();
        if (deviceInfo == null) {
            return null;
        }
        return deviceInfo;
    }
}
