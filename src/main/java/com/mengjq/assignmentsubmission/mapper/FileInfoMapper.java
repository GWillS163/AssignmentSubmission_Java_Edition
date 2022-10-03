package com.mengjq.assignmentsubmission.mapper;

import com.mengjq.assignmentsubmission.pojo.FileInfo;
import com.mengjq.assignmentsubmission.util.MongodbGFS;
import com.mongodb.client.*;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;

import static com.mongodb.client.model.Aggregates.group;
import static com.mongodb.client.model.Projections.fields;

public class FileInfoMapper {
    public MongoCollection<Document> fileInfoDBCollection;
    private MongodbGFS mGFS;

    public FileInfoMapper(String mongodbUrl, String clazz, String fileInfoDB) {
        // 通过完整信息 新连接数据库
        MongoClient mongoClient = MongoClients.create(mongodbUrl);
        MongoDatabase clazzDB = mongoClient.getDatabase(clazz);
        fileInfoDBCollection = clazzDB.getCollection(fileInfoDB);
    }

    public FileInfoMapper(MongoDatabase clazzDB, String fileInfoDB) {
        // connect to collection if not exists , create it
        if (!clazzDB.listCollectionNames().into(new ArrayList<>()).contains(fileInfoDB)) {
            clazzDB.createCollection(fileInfoDB);
        }
        fileInfoDBCollection = clazzDB.getCollection(fileInfoDB);
        String bucketName = "Files";
        GridFSBuckets.create(clazzDB, bucketName);
        mGFS = new MongodbGFS(clazzDB, bucketName);
    }

    // add file info to database
    public void createByDirect(Document doc) {
        fileInfoDBCollection.insertOne(doc);
    }

    public void createByGFS(String filePath, Document doc) {
        // 1. insert to GFS first
        ObjectId gfsId=mGFS.saveFile(filePath);
//        System.out.println(gfsId);
        // 2. insert to fileInfoDB with GFS id
        doc.append("gfsId", gfsId);
        fileInfoDBCollection.insertOne(doc);
    }

    // Update
    public void update(String fileId, Document newDocument) {
        // TODO: update file info in database
        fileInfoDBCollection.updateOne(
                new Document().append("fileId", fileId),
                newDocument);
//                new Document().append("$set", new Document().append("fileId", fileId)));
    }

    // Request
    public FindIterable<Document> request(Document document) {
        return fileInfoDBCollection.find(document);
    }
    public FindIterable<Document> request() {
        return fileInfoDBCollection.find();
    }

    // Delete - new Document().append("fileId", fileId)
    public void deleteFileInfo(Document doc) {
        fileInfoDBCollection.deleteOne(doc);

    }

    // 特殊下载的实现方式 - 没有传入Document
    public void downloadByGFS(String path, ObjectId mGFS_id) {
        mGFS.downFile(path, mGFS_id);
    }
}
