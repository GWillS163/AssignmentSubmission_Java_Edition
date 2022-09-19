package com.mengjq.assignmentsubmission.service;

import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.mapper.FileInfoMapper;
import com.mengjq.assignmentsubmission.pojo.FileInfo;
import com.mengjq.assignmentsubmission.util.MongodbGFS;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.Base64;
import java.util.List;

import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class FileInfoService {
    public FileInfoMapper fileInfoMapper;
    EchoCLI echoCLI = new EchoCLI();

    public FileInfoService(MongoDatabase clazzDB, String fileInfo) {
        fileInfoMapper = new FileInfoMapper(clazzDB, fileInfo);
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
            fileInfoMapper.createByGFS(fileInfo);
            long end = System.currentTimeMillis();
            System.out.println("uploadFiles time: " + (end - start)/1000 + "s");
        }
        return true;
    }

    // 下载文件 - download the file form mongoDB
    public boolean downloadFiles(String fileId, String value, String path) {
        FindIterable<Document> docs = fileInfoMapper.request(new Document().append(fileId, value));
        if(docs.cursor().hasNext()) {
            Document doc = docs.first();
            assert doc != null;
            // get the fileId, fileFormatName or rawName
            ObjectId fileGFS_id = (ObjectId) doc.get("fileGFS_id");
            String fileName = doc.getString("fileFormatName");
            if (fileName == null) {
                fileName = doc.getString("rawName");
            }

            // use the fileId to download the file
            fileInfoMapper.downloadByGFS(path + fileName, fileGFS_id);
            return true;

            // old version to save content in following
//            byte[] bytes = Base64.getDecoder().decode(fileContent);
//            String fileName = doc.getString("formatName");
//            if (fileName == null) {
//                fileName = doc.getString("rawName");
//            }
//            boolean saveStatus = saveBinaryData(bytes, path + fileName);
//            if (saveStatus) {
//                System.out.println("download file success!");
//            }
//            System.out.println(doc.toJson());
//            System.out.println(fileContent);
        }else {
            System.out.println("no file found with this:\n\t fileId: " + fileId + "  and value: " + value);
            System.out.println("download file failed!");
            return false;
        }
    }

    // 保存文件 - save the file to local
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
