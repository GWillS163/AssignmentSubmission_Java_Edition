package com.mengjq.assignmentsubmission.test.service;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.service.AssignmentInfoService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;

public class AssignmentTest {
    Config conf = new Config();
    EchoCLI echoCLI = new EchoCLI();

    MongoClient mongoClient = MongoClients.create(conf.mongodbUrl);
    MongoDatabase clazzDB = mongoClient.getDatabase(conf.clazz);
    AssignmentInfoService assignmentInfoService = new AssignmentInfoService(clazzDB, conf.assignmentInfoDB);

    public static void main(String[] args) {

        // getTestAssignments
//        System.out.println("\ngetTestAssignments");
//        Document testAssignment = assignmentInfoService.getTestAssignment();
//        System.out.println(testAssignment);
    }
    @Test
    public void getCollectingAssignments(){
        assignmentInfoService.getCollectingAssignments();
    }

    @Test
    public void getTestAssignment(){
        assignmentInfoService.assignmentInfoMapper.request(
                        new Document().append("assiId", "14"))
                .first();
    }
}
