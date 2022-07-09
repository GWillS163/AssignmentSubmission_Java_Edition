package com.mengjq.assignmentsubmission.test;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mongodb.client.*;
import org.bson.Document;

import static com.mongodb.client.model.Projections.exclude;
import static com.mongodb.client.model.Projections.fields;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class LinkTest {

    public static void main(String[] args) {
        Config conf = new Config();

        MongoClient mongoClient = MongoClients.create(conf.mongodbUrl);
        MongoDatabase clazzDB = mongoClient.getDatabase(conf.clazz);

        // Assignment DB
        System.out.println("Assignment DB Link Test");
        MongoCollection<Document> assignDBCollection = clazzDB.getCollection(conf.assignmentInfoDB);
        System.out.println(assignDBCollection.find().first());


//        DeviceReg Link Test
        System.out.println("\nDeviceReg DB Link Test");
        MongoCollection<Document> devicRegDBCollection = clazzDB.getCollection(conf.deviceRegDB);
//        devicRegDBCollection.insertOne(new Document("userId", "123456789"));
        System.out.println(devicRegDBCollection.find().first());


        // StudentDB  Link Test
        System.out.println("\nStudentDB Link Test");
        MongoCollection<Document> studentDBCollection = clazzDB.getCollection(conf.studentInfoDB);
        Document doc = studentDBCollection.find(new Document("stuId", "19852331")).first();
        System.out.println(doc);


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
