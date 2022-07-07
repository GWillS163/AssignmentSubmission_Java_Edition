package com.mengjq.assignmentsubmmsion.service;

import com.mengjq.assignmentsubmmsion.pojo.FileInfo;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

public interface FileInfoService {
    public void upload(MongoCollection<Document> fileInfo);
    public void uploadMany(MongoCollection<Document> gradesCollection);
    public void download(String fileName, String filePath);
    public void delete(String fileName);
    public String getFileUploadTime(String fileName);
    public String getFileDownloadTime(String fileName);
}
