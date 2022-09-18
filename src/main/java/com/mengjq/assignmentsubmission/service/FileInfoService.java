package com.mengjq.assignmentsubmission.service;

import com.mengjq.assignmentsubmission.mapper.FileInfoMapper;
import com.mengjq.assignmentsubmission.pojo.FileInfo;
import com.mengjq.assignmentsubmission.util.MongodbGFS;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Base64;
import java.util.List;

import static com.mongodb.client.model.Projections.*;
import static com.mongodb.client.model.Sorts.ascending;
import static com.mongodb.client.model.Sorts.descending;

public class FileInfoService {
    private MongodbGFS mgfs;
    public FileInfoMapper fileInfoMapper;

    public FileInfoService(MongoDatabase clazzDB, String fileInfo) {
        fileInfoMapper = new FileInfoMapper(clazzDB, fileInfo);
//        System.out.println(fileInfoDBCollection.getNamespace());
    }

    // 个人查询 - 查询个人所有已提交的作业
    public FindIterable<Document> getMySubmitStatus(String stuId){
        // TODO: 也要在另一个存储桶内查询

        return fileInfoMapper.request((Document) new Document().append("stuId", stuId))
                .projection(fields(
                        exclude("_id", "fileContent", "stuId", "stuName")
                ))
                .sort(descending("uploadTime"));
    }

    // 公共查询 - 查询所有人已提交的作业
    public FindIterable<Document> getAllSubmittedFileInfo() {
        // TODO: 范围文件时，两种查询格式也需要一致。
        // query all the file status form mongoDB
        return   fileInfoMapper.request()
                .projection(fields(exclude("_id", "fileContent")
//                        , group("stuId")
                ))
                .sort(ascending("userId"));
    }

    // todo :Version2 ACComplish this function
//    private FindIterable<Document> getAllSubmittedInfoStatusGroupByStuId() {
//        // TODO: 查询时 两种格式依然一致
//        // can't use groupBy()
//        // get all files from mongoDB group by stuId
//        return fileInfoMapper.request()
//                .projection(fields(
//                        exclude("_id", "fileContent")
//                ))
//                .sort(ascending("stuId"));
//    }

    // 上传文件 - 上传文件到mongoDB
    // TODO: 上传应该用存储桶
    public boolean uploadFiles(List<FileInfo> fileInfos) {
        // calculate the time of upload
        long start = System.currentTimeMillis();
        // TODO: 插入文件时，两种文件格式信息需要一致
        for (FileInfo fileInfo : fileInfos){
            // if fileInfo fileSize over than 15M, then return false
            if (fileInfo.getFileSize() > 15 * 1024 * 1024){
                System.out.println(fileInfo.getRawName() + "File size is too large, please upload again!" + fileInfo.getFileSize());
//                fileInfos.pop(fileInfo);
//                // TODO: Version2 Accomplish this function that can upload the file which size is over than 15M
//                mgfs = new MongodbGFS();
//
//                ObjectId id=mgfs.saveFile(url);
//                System.out.println(id);
                System.out.println("the new file upload method is not implemented yet!");
                continue;
            }

            fileInfoMapper.create(fileInfo);
            long end = System.currentTimeMillis();
            System.out.println("uploadFiles time: " + (end - start)/1000 + "s");

        }
        return true;
    }

    // 下载文件 - download the file form mongoDB
    public boolean downloadFile(String fileId, String value, String path) {
        FindIterable<Document> docs = fileInfoMapper.request(new Document().append(fileId, value));
        if(docs.cursor().hasNext()) {
            Document doc = docs.first();
            assert doc != null;
            String fileContent = doc.getString("fileContent");
            byte[] bytes = Base64.getDecoder().decode(fileContent);
            String fileName = doc.getString("formatName");
            if (fileName == null) {
                fileName = doc.getString("rawName");
            }
            boolean saveStatus = saveBinaryData(bytes, path + fileName);

            if (saveStatus) {
                System.out.println("download file success!");
            }
//            System.out.println(doc.toJson());
//            System.out.println(fileContent);
        }else {
            System.out.println("no file found with this:\n\t fileId: " + fileId + "  and value: " + value);
            System.out.println("download file failed!");
            return false;
        }
        return true;
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
