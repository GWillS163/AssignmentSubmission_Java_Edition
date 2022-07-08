package com.mengjq.assignmentsubmission.test;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import javax.swing.event.DocumentListener;
import javax.swing.event.UndoableEditListener;

public class MongoTest {

    public static void main(String[] args) {
       String userId = "19852331";
       String clazz = "1909";
       String mongodbUrl = "mongodb+srv://mengjq:OXDueFslVZNWtiqT@assignmentsubmmsion.nttaj.mongodb.net/?retryWrites=true&w=majority";
       String assignmentInfoDB = "AssignmentInfo";
       String deviceRegDB = "DeviceReg";
       String fileInfoDB = "FileInfo";
       String studentInfoDB = "StudentInfo";
        MongoClient mongoClient = MongoClients.create(mongodbUrl);
        MongoDatabase clazzDB = mongoClient.getDatabase(clazz);
        MongoCollection assignmentInfoCollection = clazzDB.getCollection(assignmentInfoDB);
        System.out.println(assignmentInfoCollection.getNamespace());
//        assignmentInfoCollection.insertOne(new Document()
//                .append("userId", userId));
        System.out.println(assignmentInfoCollection.find().first());

    }
}
