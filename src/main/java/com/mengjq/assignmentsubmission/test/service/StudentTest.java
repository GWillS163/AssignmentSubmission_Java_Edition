package com.mengjq.assignmentsubmission.test.service;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.service.StudentInfoService;
import com.mongodb.client.*;
import org.bson.Document;
import org.junit.Test;

public class StudentTest {
    Config conf = new Config();
    MongoClient mongoClient = MongoClients.create(conf.mongodbUrl);
    MongoDatabase clazzDB = mongoClient.getDatabase(conf.clazz);
    StudentInfoService studentInfoService = new StudentInfoService(clazzDB, conf.studentInfoDB);

    @Test
    public void getStuInfo(){
        // getStudentInfo
        System.out.println("getStudentInfo");
        System.out.println(studentInfoService.getStudentInfo("19852331"));
    }

    @Test
    public void findStuInfoByBson(){
        // findStuInfoByBson
        System.out.println("findStuInfoByBson");
        System.out.println(studentInfoService.findStuInfoByBson("stuId", "19852331").first());
    }

    @Test
    public void getAllStuInfo(){
        System.out.println("获得所有学生信息");
        FindIterable<Document> allStuInfos = studentInfoService.getAllStuInfo();
        for (Document allStuInfo : allStuInfos) {
            System.out.println(allStuInfo);
        }
    }

}
