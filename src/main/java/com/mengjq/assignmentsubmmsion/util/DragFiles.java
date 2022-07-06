package com.mengjq.assignmentsubmmsion.util;

import com.mengjq.assignmentsubmmsion.pojo.FileInfo;

import java.util.List;

public class DragFiles {
    List<FileInfo> fileInfos;

    public List<FileInfo> getDragFileInfo(String[] args) {
        for (String path : args) {
            System.out.println(path);

            //1. get file name and file size
            FileInfo fileInfo = new FileInfo();
            fileInfo.setRawName(getRawName(path));
            fileInfo.setFileSize(getFileSize(path));

            fileInfos.add(fileInfo);
        }
        return fileInfos;
    }
    public static String getRawName(String path){
        // get filename from path
        String[] pathArray = path.split("\\\\");
        return pathArray[pathArray.length - 1];
    }

    public static int getFileSize(String filePath) {
        int fileSize = -1;
        // get file size by filePath
        fileSize = (int) (new java.io.File(filePath)).length();
        return fileSize;
    }

}
