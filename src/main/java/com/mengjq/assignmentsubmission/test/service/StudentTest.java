package com.mengjq.assignmentsubmission.test.service;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.service.StudentInfoService;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class StudentTest {
    public static void main(String[] args) {

        Config conf = new Config();
        MongoClient mongoClient = MongoClients.create(conf.mongodbUrl);
        MongoDatabase clazzDB = mongoClient.getDatabase(conf.clazz);
        StudentInfoService studentInfoService = new StudentInfoService(clazzDB, conf.studentInfoDB);

        // getStudentInfo
        System.out.println("getStudentInfo");
        System.out.println(studentInfoService.getStudentInfo("19852331"));

//


    }
}
