package com.mengjq.assignmentsubmmsion.service.impl;
import com.mengjq.assignmentsubmmsion.pojo.FileInfo;
import com.mengjq.assignmentsubmmsion.service.FileInfoService;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import org.bson.Document;
import org.bson.types.ObjectId;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static java.util.Arrays.asList;

public class FileInfoServiceImpl implements FileInfoService {

    public FileInfoServiceImpl() {
        // call the mongoDB to insert the fileInfo
        MongoClient mongoClient = MongoClients.create(System.getProperty("mongodb.uri"));

        MongoDatabase sampleTrainingDB = mongoClient.getDatabase("Files");
        MongoCollection<Document> testCollection = sampleTrainingDB.getCollection("1909");

    }


    @Override
    public void upload(MongoCollection<Document> gradesCollection) {
        // TODO change fake data to real data
        gradesCollection.insertOne(generateNewGrade());
        System.out.println("MongoDB insert File success!");
    }

    @Override
    public void uploadMany(MongoCollection<Document> gradesCollection) {
        // TODO change fake data to real data
            List<Document> grades = new ArrayList<>();
            for (double classId = 1d; classId <= 10d; classId++) {
                grades.add(generateNewGrade());
            }

            gradesCollection.insertMany(grades, new InsertManyOptions().ordered(false));
            System.out.println("Ten grades inserted for studentId 10001.");
        }

    private static Document generateNewGrade() {
        return new Document("_id", new ObjectId())
                .append("student_id", 19852331)
                .append("class_id", 10000d)
                .append("scores", 1d);
    }
    @Override
    public void download(String fileName, String filePath) {

    }

    @Override
    public void delete(String fileName) {

    }

    @Override
    public String getFileUploadTime(String fileName) {
        return null;
    }

    @Override
    public String getFileDownloadTime(String fileName) {
        return null;
    }
}
