package com.mengjq.assignmentsubmmsion.service;

import com.mengjq.assignmentsubmmsion.pojo.FileInfo;

public interface FileInfoService {
    public void upload(FileInfo fileInfo);
    public void download(String fileName, String filePath);
    public void delete(String fileName);
    public String getFileUploadTime(String fileName);
    public String getFileDownloadTime(String fileName);
}
