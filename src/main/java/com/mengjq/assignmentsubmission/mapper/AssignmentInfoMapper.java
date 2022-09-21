package com.mengjq.assignmentsubmission.mapper;

import com.mengjq.assignmentsubmission.pojo.AssignmentInfo;
import com.mongodb.client.*;
import org.bson.Document;

public class AssignmentInfoMapper {
    public MongoCollection<Document> assigmentInfoDBCollection;

    public AssignmentInfoMapper(String mongodbUrl, String clazz, String assigmentInfoDB) {
        // 通过完整信息 新连接数据库
        MongoClient mongoClient = MongoClients.create(mongodbUrl);
        MongoDatabase clazzDB = mongoClient.getDatabase(clazz);
        assigmentInfoDBCollection = clazzDB.getCollection(assigmentInfoDB);
    }

    public AssignmentInfoMapper(MongoDatabase clazzDB, String assignmentInfo) {
        // 通过已有clazzDB获取数据库表
        assigmentInfoDBCollection = clazzDB.getCollection(assignmentInfo);
    }

    // Server Upload Interface
    public boolean create(AssignmentInfo document) {
        assigmentInfoDBCollection.insertOne(new Document()
                .append("assiId", document.nickName));
//                .append("assiId", document.assiId)
//                .append("assiName", document.assiName)
//                .append("assiDesc", document.assiDesc)
//                .append("assiFile", document.assiFile)
//                .append("DDL", document.DDL)
//                .append("status", document.status));
        return true;
    }

    // update
    public boolean update(String assiId, Document newDocument) {
        assigmentInfoDBCollection.updateOne(new Document().append("assiId", assiId),newDocument);
        return true;
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
