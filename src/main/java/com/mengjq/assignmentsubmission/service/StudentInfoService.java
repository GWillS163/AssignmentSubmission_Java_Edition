package com.mengjq.assignmentsubmission.service;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.SortedSet;

public class StudentInfoService {
    public MongoCollection<Document> studInfoDBCollection;

    public StudentInfoService(MongoDatabase clazzDB, String studentInfo) {
        studInfoDBCollection = clazzDB.getCollection(studentInfo);
//        System.out.println(studInfoDBCollection.getNamespace());
    }

    // getStudentInfo
    public Document getStudentInfo(String stuId) {
        // query by stuId
        return studInfoDBCollection.find(
                new Document().append("stuId", stuId)).first();
    }

    public FindIterable<Document> findStuInfoByBson(Bson eq) {
        return studInfoDBCollection.find(eq);
    }

    public FindIterable<Document> getAllStuInfo() {
        return studInfoDBCollection.find();
    }
}
