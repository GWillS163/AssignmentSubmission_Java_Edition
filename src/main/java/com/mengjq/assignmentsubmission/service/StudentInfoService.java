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
}
