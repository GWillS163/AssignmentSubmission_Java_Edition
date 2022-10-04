package com.mengjq.assignmentsubmission.mapper;

import com.mengjq.assignmentsubmission.util.MongodbGFS;
import com.mongodb.client.*;
import com.mongodb.client.gridfs.GridFSBuckets;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import javax.print.Doc;
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
        // 旧方式，所有文件都在一个数据表中
        fileInfoDBCollection = clazzDB.getCollection(fileInfoDB);
        // connect to collection if not exists , create it
        if (!clazzDB.listCollectionNames().into(new ArrayList<>()).contains(fileInfoDB)) {
            clazzDB.createCollection(fileInfoDB);
        }
        GridFSBuckets.create(clazzDB, fileInfoDB);
        mGFS = new MongodbGFS(clazzDB, fileInfoDB);
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
    public void update(Document doc) {
        fileInfoDBCollection.updateOne(
                new Document().append("_id", doc.get("_id")),
                new Document("$set", doc));
    }

    // Request
    public FindIterable<Document> request(Document document) {
        return fileInfoDBCollection.find(document);
    }
    public FindIterable<Document> request() {
        return fileInfoDBCollection.find();
    }


    // 特殊下载的实现方式 - 没有传入Document
    public void downloadByGFS(String path, ObjectId gfsId) {
        mGFS.downOneFile(path, gfsId);
    }

    public void deleteByGFS(ObjectId gfsId) {
        mGFS.deleteFile(gfsId);
    }

}
