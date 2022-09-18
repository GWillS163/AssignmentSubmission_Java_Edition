package com.mengjq.assignmentsubmission.test;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mongodb.client.*;
import org.bson.Document;
import org.junit.Test;

import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

// 测试数据库连接性
public class DBLinkTest {
    Config conf = new Config();

    MongoClient mongoClient = MongoClients.create(conf.mongodbUrl);
    MongoDatabase clazzDB = mongoClient.getDatabase(conf.clazz);

    @Test
    // Assignment DB Test
    public void getAssignmentInfo(){
        System.out.println("Assignment DB Link Test");
        MongoCollection<Document> assignDBCollection = clazzDB.getCollection(conf.assignmentInfoDB);
        System.out.println(assignDBCollection.find().first());
    }

    @Test
    // DeviceReg Link Test
    public void getDeviceRegInfo(){
        System.out.println("DeviceReg DB Link Test");
        MongoCollection<Document> deviceRegDBCollection = clazzDB.getCollection(conf.deviceRegDB);
        System.out.println(deviceRegDBCollection.find().first());
    }

    @Test
    // StudentDB  Link Test
    public void getStudentInfo(){
        System.out.println("Student DB Link Test");
        MongoCollection<Document> studentDBCollection = clazzDB.getCollection(conf.studentInfoDB);
        Document doc = studentDBCollection.find(new Document("stuId", "19852331")).first();
        System.out.println(doc);
    }

    // fileInfoDB  Link Test
    @Test
    public void getFileInfo(){
        System.out.println("FileInfo DB Link Test");

        // TODO: query all the file group by stuId
        MongoCollection<Document> fileDBCollection = clazzDB.getCollection(conf.fileInfoDB);
        FindIterable<Document> docs = fileDBCollection.find()
                .projection(fields(
                        exclude("_id", "fileContent", "stuId", "stuName")
                ))
                .sort(descending("uploadTime"));
        System.out.println("\nAll file group by the key stuId");
        for (Document doc1 : docs) {
            System.out.println(doc1);
        }
    }
}
