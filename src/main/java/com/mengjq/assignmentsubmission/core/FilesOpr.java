package com.mengjq.assignmentsubmission.core;
import com.mengjq.assignmentsubmission.pojo.FileInfo;
import org.bson.Document;

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


}
