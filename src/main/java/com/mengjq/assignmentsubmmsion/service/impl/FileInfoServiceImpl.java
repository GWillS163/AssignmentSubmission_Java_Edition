package com.mengjq.assignmentsubmmsion.service.impl;
import com.mengjq.assignmentsubmmsion.pojo.FileInfo;
import com.mengjq.assignmentsubmmsion.service.FileInfoService;


public class FileInfoServiceImpl implements FileInfoService {

    @Override
    public void upload(FileInfo fileInfo) {
        // TODO Auto-generated method stub
        // call the mongoDB to insert the fileInfo

    }

    @Override
    public void download(String fileName, String filePath) {

    }

    @Override
    public void delete(String fileName) {

    }

    @Override
    public String getFileUploadTime(String fileName) {
        return null;
    }

    @Override
    public String getFileDownloadTime(String fileName) {
        return null;
    }
}
