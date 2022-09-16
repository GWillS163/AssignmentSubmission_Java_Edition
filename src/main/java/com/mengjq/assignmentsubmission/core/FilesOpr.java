package com.mengjq.assignmentsubmission.core;
import com.mengjq.assignmentsubmission.pojo.FileInfo;

import java.io.IOException;
import java.util.List;

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
            try {
                // 读取文件信息
                FileInfo fileInfo = new FileInfo(file);
                fileInfoList.add(fileInfo);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }


}
