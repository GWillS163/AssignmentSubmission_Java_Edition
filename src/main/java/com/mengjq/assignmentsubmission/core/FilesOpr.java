package com.mengjq.assignmentsubmission.core;
import com.mengjq.assignmentsubmission.pojo.FileInfo;

import java.io.IOException;
import java.util.List;

// 用来存储文件信息 用于文件上传
public class FilesOpr {
    public List<FileInfo> fileInfoList;

    public FilesOpr() {
        this.fileInfoList = new java.util.ArrayList<FileInfo>();
    }

    public List<FileInfo> getFileInfoList() {
        return fileInfoList;
    }

    public void addFileInfoList(FileInfo fileInfo) {
        this.fileInfoList.add(fileInfo);
    }

    public void setBasicFileInfo(String[] files) {
        for ( String file : files ) {
            // 读取文件信息
            FileInfo fileInfo = new FileInfo(file);
            fileInfoList.add(fileInfo);
//            try {
//            } catch (IOException e) {
//                System.out.println("Error: " + e.getMessage());
//            }
        }
    }


}
