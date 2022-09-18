package com.mengjq.assignmentsubmission.mapper;

import com.mengjq.assignmentsubmission.pojo.FileInfo;
import com.mongodb.client.*;
import org.bson.Document;

import static com.mongodb.client.model.Accumulators.first;
import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Projections.fields;

public class FileInfoMapper {
    public MongoCollection<Document> fileInfoDBCollection;

    public FileInfoMapper(String mongodbUrl, String clazz, String fileInfoDB) {
        // 通过完整信息 新连接数据库
        MongoClient mongoClient = MongoClients.create(mongodbUrl);
        MongoDatabase clazzDB = mongoClient.getDatabase(clazz);
        fileInfoDBCollection = clazzDB.getCollection(fileInfoDB);
    }

    public FileInfoMapper(MongoDatabase clazzDB, String fileInfoDB) {
        // 通过已有clazzDB获取数据库表
        fileInfoDBCollection = clazzDB.getCollection(fileInfoDB);
    }

    // add file info to database
    public void create(FileInfo fileInfo) {
        Document document = new Document()
                .append("assiId",      fileInfo.fileId)
                .append("fileContent", fileInfo.fileContent)
                .append("fileSize",    fileInfo.fileSize )
                .append("stuId",      fileInfo.stuId )
                .append("stuName",   fileInfo.stuName)
                .append("rawName",    fileInfo.rawName)
                .append("formatName", fileInfo.formatName)
                .append("hash",       fileInfo.hash)
                .append("status",     fileInfo.status)
                .append("uploadTime", fileInfo.uploadTime);
        fileInfoDBCollection.insertOne(document);
    }

    // Update
    public void update(String fileId, Document newDocument) {
        // TODO: update file info in database
        fileInfoDBCollection.updateOne(new Document().append("fileId", fileId),newDocument);
//                new Document().append("$set", new Document().append("fileId", fileId)));
    }

    // Request
    public FindIterable<Document> request(Document document) {
        return fileInfoDBCollection.find(document);
    }
    public FindIterable<Document> request() {
        return fileInfoDBCollection.find();
    }


    // Delete
    public void deleteFileInfo(String fileId) {
        fileInfoDBCollection.deleteOne(new Document().append("fileId", fileId));

    }
}
