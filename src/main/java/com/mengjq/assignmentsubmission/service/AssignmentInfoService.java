package com.mengjq.assignmentsubmission.service;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.ascending;

public class AssignmentInfoService {
    public MongoCollection<Document> assiInfoDBCollection;

    public AssignmentInfoService(MongoDatabase clazzDB, String assignmentInfo) {
        assiInfoDBCollection = clazzDB.getCollection(assignmentInfo);
        System.out.println(assiInfoDBCollection.getNamespace());
    }

    public void addAssignment(String assId, String briefName, String describe, String fileNameRule, String DDL, Boolean status) {
        assiInfoDBCollection.insertOne(new Document()
                .append("assId", assId)
                .append("briefName", briefName)
                .append("describe", describe)
                .append("fileNameRule", fileNameRule)
                .append("DDL", DDL)
                .append("status", status)
        );
    }

    public Iterable<Document> getAssignments() {
        Iterable<Document> assigns = assiInfoDBCollection.find(
                        new Document().append("status", true))
                .projection(fields(exclude("_id", "status")))
                .sort(ascending("DDL"));
        return assigns;
    }

    public Document getTestAssignment() {
        Document doc = assiInfoDBCollection.find(
                new Document().append("assId", "94")).first();
        return doc;
    }
}
