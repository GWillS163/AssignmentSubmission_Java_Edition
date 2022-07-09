package com.mengjq.assignmentsubmission.service;

import com.mengjq.assignmentsubmission.pojo.DeviceReg;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class DeviceRegService {
    public MongoCollection<Document> devicRegDBCollection;

    public DeviceRegService(MongoDatabase clazzDB,  String deviceReg) {
        devicRegDBCollection = clazzDB.getCollection(deviceReg);
        System.out.println(devicRegDBCollection.getNamespace());
    }
    // change JDK version if echo "Timed out after 30000 ms。。。"
    public boolean regCurrentDevice(DeviceReg deviceReg) {
        Document deviceRegDoc = new Document()
                .append("userId", deviceReg.getUserId())
                .append("deviceMAC", deviceReg.getDeviceMAC())
                .append("deviceName", deviceReg.getDeviceName())
                .append("deviceUser", deviceReg.getDeviceUser());

        // upload current device to mongoDB DeviceReg
        devicRegDBCollection.insertOne(deviceRegDoc);
        return true;
    }

    public Document tryGetStudentInfo(String field, String value) {
        Document deviceInfo = devicRegDBCollection.find(eq(field, value)).first();
        if (deviceInfo.size() == 0) {
            return null;
        }
        return devicRegDBCollection.find(eq(field, value)).first();
    }
}
