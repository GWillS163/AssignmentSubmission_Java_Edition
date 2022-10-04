package com.mengjq.assignmentsubmission.service;

import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.mapper.FileInfoMapper;
import com.mengjq.assignmentsubmission.pojo.FileInfo;
import com.mengjq.assignmentsubmission.util.MongodbGFS;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.apache.commons.lang.StringUtils;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.io.File;
import java.nio.file.FileAlreadyExistsException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class FileInfoService {
    public FileInfoMapper fileInfoMapper;
//    EchoCLI echoCLI = new EchoCLI();
    public LocalDateTime localDateTime;

    public FileInfoService(MongoDatabase clazzDB, String fileInfo) {
        fileInfoMapper = new FileInfoMapper(clazzDB, fileInfo);
        localDateTime = LocalDateTime.now();
//        System.out.println(fileInfoDBCollection.getNamespace());
    }

    // 个人查询 - 查询个人所有已提交的作业
    public FindIterable<Document> getMySubmitStatus(String stuId){

        return fileInfoMapper.request((Document) new Document().append("stuId", stuId))
                .projection(fields(
                        exclude("_id", "fileContent", "stuId", "stuName")
                ))
                .sort(descending("uploadTime"));
    }

    // 公共查询 - 查询所有人已提交的作业
    public FindIterable<Document> getAllSubmittedFileInfo() {
        // query all the file status form mongoDB
        return   fileInfoMapper.request()
                .projection(fields(exclude("_id", "fileContent")
//                        , group("stuId")
                ))
                .sort(ascending("userId"));
    }

    // 上传文件到mongoDB - upload file to mongoDB
    public boolean uploadFiles(List<FileInfo> fileInfos) {
        // calculate the time of upload
        long start = System.currentTimeMillis();
        for (FileInfo fileInfo : fileInfos){
            System.out.println("uploading " + fileInfo.filePath);
            // print loading animation until finish
            fileInfoMapper.createByGFS(fileInfo.filePath, fileInfo.toDocument());
            long end = System.currentTimeMillis();
            System.out.println("uploadFiles time: " + (end - start)/1000 + "s");
        }
        return true;
    }

    // 下载文件 - download the file form mongoDB
    public boolean downloadMany(Document criteria, String path) {
        // 得到所有符合查询的文档 - get all the documents
        FindIterable<Document> docs = fileInfoMapper.request(criteria);
//        System.out.println(docs.cursor());
        // 遍历所有文档 - traverse all the documents
        if (docs != null) {
            for (Document doc : docs) {
                Object gfsId =  doc.get("gfsId");
                // if gfsId is null
                if (gfsId == null) {
                    System.out.println("此文件内容已被删除！");
                    continue;
                }

                // 得到文件名 - get the file name
                String fileName = doc.getString("fileFormatName");
                if (fileName == null) {
                    fileName = doc.getString("rawName");
                }
                // 可能存在同名文件 - maybe the same file name exist
                String savePath = path + fileName;
                // check the savePath is exists
                File file = new File(savePath);
                if (file.exists()) {
                    // add the time to the file name before "." to avoid the same file name
                    String[] split = fileName.split("\\.");
                    // get now time add to the file name
                    String time = doc.getString("uploadTime").replace(":", "-").replace(" ", "_");
                    System.out.println(time);
                    System.out.println(time);
//                    String time = localDateTime.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss"));
                    String newFileName = split[0] + "_" + time + "." + split[1];
                    savePath = path + newFileName;

                }

                // 保存文件 - save the file
                System.out.println("saving:" + savePath);
                fileInfoMapper.downloadByGFS(savePath, (ObjectId) gfsId);
            }
            return true;
        } else {
            System.out.println("no file found with this:\n\t : " + criteria.toJson());
            System.out.println("download file failed!");
            return false;
        }
    }

    // 删除文件 - delete the file form mongoDB
    public boolean deleteMany(String fileId, String value) {
        // 得到所有符合查询的文档 - get all the documents
        FindIterable<Document> docs = fileInfoMapper.request(new Document().append(fileId, value));
        // 遍历所有文档 - traverse all the documents
        if (docs != null) {
            for (Document doc : docs) {
                // 得到文件名 - get the file name
                // if doc.get("gfsId") type isn't ObjectId, then delete the file
                if (doc.get("gfsId") instanceof ObjectId) {
                    ObjectId gfsId = (ObjectId) doc.get("gfsId");
                    // 删除文件 - delete the file
                    fileInfoMapper.deleteByGFS(gfsId);
                }
                // update fileInfo by bson
                doc.put("gfsId", null);
                doc.put("status", "Deleted");
                fileInfoMapper.update(doc);
            }
            return true;
        } else {
            System.out.println("no file found with this:\n\t fileId: " + fileId + "  and value: " + value);
            System.out.println("delete file failed!");
            return false;
        }
    }

    public boolean deleteMany(String field, Integer value) {
        // 得到所有符合查询的文档 - get all the documents
        FindIterable<Document> docs = fileInfoMapper.request(new Document().append(field, value));
        // 遍历所有文档 - traverse all the documents
        if (docs != null) {
            for (Document doc : docs) {
                // 得到文件名 - get the file name
                // if doc.get("gfsId") type isn't ObjectId, then delete the file
                if (doc.get("gfsId") instanceof ObjectId) {
                    ObjectId gfsId = (ObjectId) doc.get("gfsId");
                    // 删除文件 - delete the file
                    fileInfoMapper.deleteByGFS(gfsId);
                }
                // update fileInfo by bson
                doc.put("gfsId", null);
                doc.put("status", "Deleted");
                fileInfoMapper.update(doc);
            }
            return true;
        } else {
            System.out.println("no file found with this:\n\t fileId: " + field + "  and value: " + value);
            System.out.println("delete file failed!");
            return false;
        }
    }

}
