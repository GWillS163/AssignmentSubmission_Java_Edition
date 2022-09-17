package com.mengjq.assignmentsubmission.mapper;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;


public class StudentInfoMapper {
    public MongoCollection<Document> studentInfoDBCollection;

    public StudentInfoMapper(MongoDatabase clazzDB, String studentInfo) {
        studentInfoDBCollection = clazzDB.getCollection(studentInfo);
    }

    // Create
    public boolean create(Document document) {
        studentInfoDBCollection.insertOne(document);
        return true;
    }

    // Update
    public boolean update(String stuId, Document newDocument) {
        studentInfoDBCollection.updateOne(new Document().append("stuId", stuId), newDocument);
        return true;
    }

    // Request
    public FindIterable<Document> request(Document document) {
        return studentInfoDBCollection.find(document);
    }

    // Delete
    public boolean delete(String stuId) {
        studentInfoDBCollection.deleteOne(new Document().append("stuId", stuId));
        return true;
    }

}
