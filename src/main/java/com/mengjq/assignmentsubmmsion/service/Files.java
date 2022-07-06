package com.mengjq.assignmentsubmmsion.service;

public interface Files {
    public void upload(String fileName, String filePath);
    public void download(String fileName, String filePath);
    public void delete(String fileName);
    public String getFileUploadTime(String fileName);
    public String getFileDownloadTime(String fileName);
}
