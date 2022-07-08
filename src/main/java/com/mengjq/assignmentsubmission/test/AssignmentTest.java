package com.mengjq.assignmentsubmission.test;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.service.AssignmentInfoService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class AssignmentTest {
    public static void main(String[] args) {
        Config conf = new Config();

        MongoClient mongoClient = MongoClients.create(conf.mongodbUrl);
        MongoDatabase clazzDB = mongoClient.getDatabase(conf.clazz);
        AssignmentInfoService assignmentInfoService = new AssignmentInfoService(clazzDB, conf.assignmentInfoDB);

        // getAssignments
        Iterable<Document> assignments = assignmentInfoService.getAssignments();
        for ( Document assignment : assignments ) {
            System.out.println(assignment);
        }

        // addAssignments
        assignmentInfoService.addAssignment("92",
                "已完成作业",
                "完成设计文件",
                "作业-姓名-学号.doc",
                "2022-10-10 19:00:00",
                false
        );
    }
}
