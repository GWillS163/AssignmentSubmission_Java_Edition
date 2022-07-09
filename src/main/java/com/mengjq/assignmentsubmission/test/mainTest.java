package com.mengjq.assignmentsubmission.test;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.service.AssignmentInfoService;
import com.mengjq.assignmentsubmission.service.FileInfoService;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class mainTest {
    public static void main(String[] args) {
        EchoCLI echoCLI = new EchoCLI();
        Config conf = new Config();

        MongoClient mongoClient = MongoClients.create(conf.mongodbUrl);
        MongoDatabase clazzDB = mongoClient.getDatabase(conf.clazz);
        AssignmentInfoService assignmentInfoService = new AssignmentInfoService(clazzDB, conf.assignmentInfoDB);
        FileInfoService fileInfoService = new FileInfoService(clazzDB, conf.fileInfoDB);

// getAll submit status
        System.out.println("getAllSubmitStatus:");
        FindIterable<Document> assignments = assignmentInfoService.getCollectingAssignments();
        echoCLI.showCollectingAssignments(assignments);

// getAll submit status group by student

        FindIterable<Document> allSubmitStatusDocs = fileInfoService.getAllSubmittedFileInfo();
        echoCLI.showAllSubmitStatus(allSubmitStatusDocs);

//        echoCLI.showAllSubmitStatus(assignments, allSubmitStatusDocs);

    }
}
