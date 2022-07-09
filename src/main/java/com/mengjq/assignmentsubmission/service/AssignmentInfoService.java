package com.mengjq.assignmentsubmission.service;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.ascending;

public class AssignmentInfoService {
    public MongoCollection<Document> assiInfoDBCollection;

    public AssignmentInfoService(MongoDatabase clazzDB, String assignmentInfo) {
        assiInfoDBCollection = clazzDB.getCollection(assignmentInfo);
        System.out.println(assiInfoDBCollection.getNamespace());
    }

    public boolean addAssignment(String assId, String briefName, String describe, String fileNameRule, String DDL, Boolean status) {
        assiInfoDBCollection.insertOne(new Document()
                .append("assId", assId)
                .append("briefName", briefName)
                .append("describe", describe)
                .append("fileNameRule", fileNameRule)
                .append("DDL", DDL)
                .append("status", status)
        );
        return true;
    }

    public FindIterable<Document> getCollectingAssignments() {
        // TODO: 为什么 equals("status", true) 不能用？
//        eq("status", "true")
//                or(eq("status", true), eq("status", "true"))
        return assiInfoDBCollection.find(
//                new Document().append("status", "true")
//                or(eq("status", true), eq("status", "true"))
                ).projection(fields(exclude("_id")))
                .sort(ascending("DDL"));
    }

    public Document getTestAssignment() {
        Document doc = assiInfoDBCollection.find(
                new Document().append("assId", "14")).first();
        return doc;
    }
}
