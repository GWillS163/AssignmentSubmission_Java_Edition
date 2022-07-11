package com.mengjq.assignmentsubmission.test.service;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.service.AssignmentInfoService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class AssignmentTest {
    public static void main(String[] args) {
        Config conf = new Config();
        EchoCLI cli = new EchoCLI();

        MongoClient mongoClient = MongoClients.create(conf.mongodbUrl);
        MongoDatabase clazzDB = mongoClient.getDatabase(conf.clazz);
        AssignmentInfoService assignmentInfoService = new AssignmentInfoService(clazzDB, conf.assignmentInfoDB);

        // addAssignments
//        System.out.println("addAssignments");
//        System.out.println(assignmentInfoService.addAssignment("92",
//                "已完成作业",
//                "完成设计文件",
//                "作业-姓名-学号.doc",
//                "2022-10-10 19:00:00",
//                false
//        ));

        // getAssignments
//        System.out.println("getAssignments:");
//        FindIterable<Document> assignments = assignmentInfoService.getCollectingAssignments();
//        cli.showCollectingAssignments(assignments);

        // getTestAssignments
//        System.out.println("\ngetTestAssignments");
//        Document testAssignment = assignmentInfoService.getTestAssignment();
//        System.out.println(testAssignment);
    }
}
