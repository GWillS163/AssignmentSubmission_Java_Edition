package com.mengjq.assignmentsubmmsion.core;

import com.mongodb.client.*;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.descending;

public class MongoDBService {
    public MongoCollection<Document> assiInfoDBCollection;
    public MongoCollection<Document> devicRegDBCollection;
    public MongoCollection<Document> fileInfoDBCollection;
    public MongoCollection<Document> studInfoDBCollection;

    public MongoDBService(String mongodbUrl, String clazz, String assignmentInfo, String deviceReg, String fileInfo, String studentInfo) {
        MongoClient mongoClient = MongoClients.create(mongodbUrl);

        MongoDatabase clazzDB = mongoClient.getDatabase(clazz);
        assiInfoDBCollection = clazzDB.getCollection(assignmentInfo);
        devicRegDBCollection = clazzDB.getCollection(deviceReg);
        fileInfoDBCollection = clazzDB.getCollection(fileInfo);
        studInfoDBCollection = clazzDB.getCollection(studentInfo);
        System.out.println(assiInfoDBCollection.getNamespace());
        System.out.println(devicRegDBCollection.getNamespace());
        System.out.println(fileInfoDBCollection.getNamespace());
        System.out.println(studInfoDBCollection.getNamespace());

    }

    public void regCurrentDevice() {
        // upload current device to mongoDB DeviceReg
        devicRegDBCollection.updateOne(
                new Document("device_id", "123456789"),
                new Document("$set", new Document("device_id", "123456789")));
        System.out.println("MongoDB insert DeviceReg success!");
    }

    public void getSubmitStatus(String userId) {
        // query status form mongoDB
        String assId = "1";
        FindIterable<Document> docs = fileInfoDBCollection.find(and(eq("userId", userId)))
                .projection(fields(exclude("_id", "content"),
                                   include()))
                .sort(descending("userId"));

        System.out.println("userId and assId: " + userId + " " + assId);
//        System.out.println(doc);
        for (Document doc : docs) {
            System.out.println(doc.toJson());
        }
    }

    public void getStatusByStuId(String studentId){
        FindIterable<Document> docs = studInfoDBCollection.find(and(eq("studentId", studentId)))
                .projection(fields(exclude("_id", "content"),
                                   include("assignmentId", "status")))
                .sort(descending("studentId"));
        System.out.println("studentId: " + studentId);
        int n = 0;
        for (Document doc : docs) {
            System.out.println(doc.toJson());
            n += 1;
        }
        System.out.println("在库中的作业数量：" + n);
    }
    private Bson excludeId() {
        return exclude("_id", "content");

    }

}
