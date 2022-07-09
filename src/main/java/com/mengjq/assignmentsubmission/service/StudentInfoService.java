package com.mengjq.assignmentsubmission.service;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class StudentInfoService {

    public MongoCollection<Document> studInfoDBCollection;

    public StudentInfoService(MongoDatabase clazzDB, String studentInfo) {
        studInfoDBCollection = clazzDB.getCollection(studentInfo);
        System.out.println(studInfoDBCollection.getNamespace());
    }
//    // regStudentInfo
//    public boolean regStudentInfo(String userId, String name, String password, String email, String phone, String clazz) {
//        studInfoDBCollection.insertOne(new Document()
//                .append("userId", userId)
//                .append("name", name)
//                .append("password", password)
//                .append("email", email)
//                .append("phone", phone)
//                .append("clazz", clazz)
//        );
//        return true;
//    }

    // TODO:not 测试 yet
    // getStudentInfo
    public Document getStudentInfo(String userId) {
        Document doc = studInfoDBCollection.find(
                new Document().append("userId", userId)
        ).first();
        return doc;
    }
}
