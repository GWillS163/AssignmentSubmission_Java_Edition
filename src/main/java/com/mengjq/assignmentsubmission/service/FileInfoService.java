package com.mengjq.assignmentsubmission.service;

import com.mengjq.assignmentsubmission.pojo.FileInfo;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class FileInfoService {
    public MongoCollection<Document> fileInfoDBCollection;
    public FileInfoService(MongoDatabase clazzDB, String fileInfo) {
        fileInfoDBCollection = clazzDB.getCollection(fileInfo);
        System.out.println(fileInfoDBCollection.getNamespace());

    }

    // TODO: 未测试
    public void getStatusByStuId(String studentId){
        FindIterable<Document> docs = fileInfoDBCollection.find(and(eq("studentId", studentId)))
                .projection(fields(exclude("_id", "content"),
                        include("assignmentId", "status")))
                .sort(descending("studentId"));
        System.out.println("studentId: " + studentId);
        int n = 0;
        for (Document doc : docs) {
            System.out.println(doc.toJson());
            n += 1;
        }
        System.out.println("您在库中的作业数量：" + n);
    }


    // TODO: 未完成查询数据测试
    public void getAllSubmitStatus() {

        // query status form mongoDB
        FindIterable<Document> docs = fileInfoDBCollection.find()
                .projection(fields(exclude("content"),
                        include()))
                .sort(ascending("userId"));

//        System.out.println(doc);
        for (Document doc : docs) {
            System.out.println(doc.toJson());
        }
    }
    public void uploadFiles(List<FileInfo> fileInfos) {
        for (FileInfo fileInfo : fileInfos){
            Document document = new Document()
                    .append("assId",      fileInfo.getAssiId())
                    .append("fileContent", fileInfo.getFileContent())
                    .append("stuId",      fileInfo.getStuId())
                    .append("userName",   fileInfo.getStuName())
                    .append("rawName",    fileInfo.getRawName())
                    .append("formatName", fileInfo.getRawName())
                    .append("hash",       fileInfo.getHash());
            fileInfoDBCollection.insertOne(document);
            System.out.println("insert " + fileInfo.getFormatName() + "success!");
        }
    }

}
