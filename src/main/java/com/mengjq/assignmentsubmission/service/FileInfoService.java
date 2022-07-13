package com.mengjq.assignmentsubmission.service;

import com.mengjq.assignmentsubmission.pojo.FileInfo;
import com.mengjq.assignmentsubmission.util.MongodbGFS;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Base64;
import java.util.List;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class FileInfoService {
    private MongodbGFS mgfs;
    public MongoCollection<Document> fileInfoDBCollection;
    public FileInfoService(MongoDatabase clazzDB, String fileInfo) {
        fileInfoDBCollection = clazzDB.getCollection(fileInfo);
//        System.out.println(fileInfoDBCollection.getNamespace());

    }

    public FindIterable<Document> getMySubmitStatus(String stuId){
        // TOOD: 也要在另一个大文件表中进行查询
        // 存储桶内查询

        FindIterable<Document> docs = fileInfoDBCollection.find(
                eq("stuId", stuId))
                .projection(fields(
                        exclude("_id", "fileContent", "stuId", "stuName")
                        ))
                .sort(descending("uploadTime"));
        return docs;
    }

    // todo :Version2 ACComplish this function
    public FindIterable<Document> getAllSubmittedInfoStatusGroupByStuId() {
        // TODO: 查询时 两种格式依然一致
        //

        // can't use groupBy()
        // get all files from mongoDB group by stuId
        FindIterable<Document> docs = fileInfoDBCollection.find()
                .projection(fields(
                        exclude("_id", "fileContent")
                ))
                .sort(ascending("stuId"));
        return docs;
    }

    public FindIterable<Document> getAllSubmittedFileInfo() {
        // TODO: 范围文件时，两种查询格式也需要一致。

        // query all the file status form mongoDB
        FindIterable<Document> docs = fileInfoDBCollection.find()
                .projection(fields(exclude("_id", "fileContent")))
                .sort(ascending("userId"));
        return docs;
    }

    public boolean uploadFiles(List<FileInfo> fileInfos) {
        // calculate the time of upload
        long start = System.currentTimeMillis();
        // TODO: 插入文件时，两种文件格式信息需要一致
        for (FileInfo fileInfo : fileInfos){
            // if fileInfo fileSize over than 15M, then return false
            if (fileInfo.getFileSize() > 15 * 1024 * 1024){
                System.out.println(fileInfo.getRawName() + "File size is too large, please upload again!" + fileInfo.getFileSize());
//                fileInfos.pop(fileInfo);
//                // TODO: Version2 ACComplish this function
//                mgfs = new MongodbGFS();
//
//                ObjectId id=mgfs.saveFile(url);
//                System.out.println(id);
                System.out.println("the new file upload method is not implemented yet!");
                continue;
            }
            Document document = new Document()
                    .append("assiId",      fileInfo.getAssiId())
                    .append("fileContent", fileInfo.getFileContent())
                    .append("fileSize",    fileInfo.getFileSize())
                    .append("stuId",      fileInfo.getStuId())
                    .append("stuName",   fileInfo.getStuName())
                    .append("rawName",    fileInfo.getRawName())
                    .append("formatName", fileInfo.getFormatName())
                    .append("hash",       fileInfo.getHash())
                    .append("status",     fileInfo.getStatus())
                    .append("uploadTime", fileInfo.getUploadTime());
            fileInfoDBCollection.insertOne(document);
            long end = System.currentTimeMillis();
            System.out.println("uploadFiles time: " + (end - start)/1000 + "s");

        }
        return true;
    }

    // download the file form mongoDB
    public void downloadFile(String fileId, String value, String path) {
        FindIterable<Document> docs = fileInfoDBCollection.find(eq(fileId, value));
        if(docs.cursor().hasNext()) {
            Document doc = docs.first();
            String fileContent = doc.getString("fileContent");
            byte[] bytes = Base64.getDecoder().decode(fileContent);
            String formatName = doc.getString("formatName");
            saveBinaryData(bytes, path + formatName);

//            System.out.println(doc.toJson());
//            System.out.println(fileContent);
        }else {
            System.out.println("no file found with this:\n\t fileId: " + fileId + "  and value: " + value);
        }

    }

    private boolean saveBinaryData(byte[] decode, String path) {
        // save binary data to file
        try {
            java.io.FileOutputStream fos = new java.io.FileOutputStream(path);
            fos.write(decode);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
