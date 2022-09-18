package com.mengjq.assignmentsubmission.mapper;

import com.mengjq.assignmentsubmission.pojo.DeviceInfo;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;

public class DeviceInfoMapper {
    public MongoCollection<Document> deviceRegDBCollection;

    public DeviceInfoMapper(MongoDatabase clazzDB, String deviceReg) {
        deviceRegDBCollection = clazzDB.getCollection(deviceReg);
    }

    // Create
    public boolean create(DeviceInfo deviceInfo) {
        Document deviceRegDoc = new Document()
                .append("stuId", deviceInfo.getStuId())
                .append("deviceMAC", deviceInfo.getDeviceMAC())
                .append("deviceName", deviceInfo.getDeviceName())
                .append("updateTime", deviceInfo.getRegisterTime())
                .append("deviceUser", deviceInfo.getDeviceUser());
        deviceRegDBCollection.insertOne(deviceRegDoc);
        return true;
    }

    // Update
    public void update(String deviceMAC, Document newDocument) {
        deviceRegDBCollection.updateOne(eq("deviceMAC", deviceMAC), new Document("$set", newDocument));
    }

    // Request
    public FindIterable<Document> request(Document document) {
        return deviceRegDBCollection.find(document);
    }

    // Delete
    public boolean delete(String deviceId) {
        deviceRegDBCollection.deleteOne(new Document().append("deviceId", deviceId));
        return true;
    }

}
