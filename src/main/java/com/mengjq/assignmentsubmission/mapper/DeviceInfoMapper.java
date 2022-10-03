package com.mengjq.assignmentsubmission.mapper;

import com.mengjq.assignmentsubmission.pojo.DeviceInfo;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

// CURD
public class DeviceInfoMapper {
    public MongoCollection<Document> deviceRegDBCollection;

    public DeviceInfoMapper(MongoDatabase clazzDB, String deviceReg) {
        // connect to collection if not exists , create it
        if (!clazzDB.listCollectionNames().into(new ArrayList<>()).contains(deviceReg)) {
            clazzDB.createCollection(deviceReg);
        }
        deviceRegDBCollection = clazzDB.getCollection(deviceReg);
    }

    // Create
    public boolean create(Document doc) {
        deviceRegDBCollection.insertOne(doc);
        return true;
    }

    // Update
    public void update(String deviceMAC, Document newDocument) {
        deviceRegDBCollection.updateOne(
                eq("deviceMAC", deviceMAC),
                new Document("$set", newDocument));
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
