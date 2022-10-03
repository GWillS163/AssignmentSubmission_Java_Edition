package com.mengjq.assignmentsubmission.mapper;

import com.mengjq.assignmentsubmission.pojo.AssignmentInfo;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;

// CURD - 传入完整的 document
public class AssignmentInfoMapper {
    public MongoCollection<Document> assigmentInfoDBCollection;

    public AssignmentInfoMapper(String mongodbUrl, String clazz, String assigmentInfoDB) {
        // 通过完整信息 新连接数据库
        MongoClient mongoClient = MongoClients.create(mongodbUrl);
        MongoDatabase clazzDB = mongoClient.getDatabase(clazz);
        assigmentInfoDBCollection = clazzDB.getCollection(assigmentInfoDB);
    }

    public AssignmentInfoMapper(MongoDatabase clazzDB, String assignmentInfo) {
        // connect to collection if not exists , create it
        if (!clazzDB.listCollectionNames().into(new ArrayList<>()).contains(assignmentInfo)) {
            clazzDB.createCollection(assignmentInfo);
        }
        assigmentInfoDBCollection = clazzDB.getCollection(assignmentInfo);
    }

    // Upload
    public void create(Document doc) {
        assigmentInfoDBCollection.insertOne(doc);
    }

    // Update
    public void update(String assiId, Document newDocument) {
        assigmentInfoDBCollection.updateOne(new Document().append("assiId", assiId),newDocument);
    }

    // Request
    public FindIterable<Document> request(Document document) {
        return assigmentInfoDBCollection.find(document);
    }
    public FindIterable<Document> request() {
        return assigmentInfoDBCollection.find();
    }

    // Delete
    public boolean delete(String assiId) {
        assigmentInfoDBCollection.deleteOne(new Document().append("assiId", assiId));
        return true;
    }

}
