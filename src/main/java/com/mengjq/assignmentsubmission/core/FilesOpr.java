package com.mengjq.assignmentsubmission.core;
import com.mengjq.assignmentsubmission.pojo.FileInfo;
import com.mengjq.assignmentsubmission.pojo.StudentInfo;
import com.mongodb.client.FindIterable;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
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

    public List<FileInfo> setBasicFileInfo(String[] files) {
        for ( String file : files ) {
            try {
                FileInfo fileInfo = new FileInfo(file);
                fileInfoList.add(fileInfo);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        return fileInfoList;
    }


}
