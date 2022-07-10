package com.mengjq.assignmentsubmission.test;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.service.MongoDBService;
import com.mengjq.assignmentsubmission.service.AssignmentInfoService;
import com.mengjq.assignmentsubmission.service.FileInfoService;
import com.mengjq.assignmentsubmission.util.Device;
import com.mongodb.client.*;
import org.bson.Document;

public class mainTest {
    public static void main(String[] args) {
        EchoCLI echoCLI = new EchoCLI();
        Config conf = new Config();

        MongoClient mongoClient = MongoClients.create(conf.mongodbUrl);
        MongoDatabase clazzDB = mongoClient.getDatabase(conf.clazz);
//        AssignmentInfoService assignmentInfoService = new AssignmentInfoService(clazzDB, conf.assignmentInfoDB);
//        FileInfoService fileInfoService = new FileInfoService(clazzDB, conf.fileInfoDB);

        MongoDBService mongoDBService = new MongoDBService(conf.mongodbUrl, conf.clazz,
                conf.assignmentInfoDB, conf.deviceRegDB, conf.fileInfoDB, conf.studentInfoDB);

//
//// getAll submit status
//        System.out.println("getAllSubmitStatus:");
//        FindIterable<Document> assignments = mongoDBService.getCollectingAssignments();
//        echoCLI.showAssignments(assignments);
//
//// getAll submit status group by student
//        System.out.println("\ngetAllSubmitStatusGroupByStudent:");
//        FindIterable<Document> allSubmitStatusDocs = mongoDBService.getAllSubmittedFileInfo();
//        echoCLI.showMySubmitStatus(allSubmitStatusDocs);
//
//        // get all of stuInfo
//        System.out.println("\ngetAllStuInfo:");
//        FindIterable<Document> allStuInfo = mongoDBService.getAllStuInfo();
//        System.out.println("\n");
//        System.out.println("showAllSubmitStatus:");
//        echoCLI.showAllSubmitStatus(assignments, allSubmitStatusDocs, allStuInfo);

        System.out.println(Device.getCurrentTime());
    }
}
