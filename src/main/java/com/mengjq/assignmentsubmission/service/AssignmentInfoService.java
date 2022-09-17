package com.mengjq.assignmentsubmission.service;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.ascending;
import com.mengjq.assignmentsubmission.mapper.AssignmentInfoMapper;

public class AssignmentInfoService {
    AssignmentInfoMapper assignmentInfoMapper;

    public AssignmentInfoService(MongoDatabase clazzDB, String assignmentInfo) {
        assignmentInfoMapper = new AssignmentInfoMapper(clazzDB, assignmentInfo);
//        System.out.println(assiInfoDBCollection.getNamespace());
    }

    // 查询正在收集的作业
    public FindIterable<Document> getCollectingAssignments() {
        // TODO: 为什么 equals("status", true) 不能用？
//        eq("status", "true")
//                or(eq("status", true), eq("status", "true"))
        return assignmentInfoMapper.request(
                ).projection(fields(exclude("_id")))
                .sort(ascending("DDL"));
    }

    // 获得测试作业
    public Document getTestAssignment() {
        return assignmentInfoMapper.request(
                new Document().append("assiId", "14"))
                .first();
    }
}
