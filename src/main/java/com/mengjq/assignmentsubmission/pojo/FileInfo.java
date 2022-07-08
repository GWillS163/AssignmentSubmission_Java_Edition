package com.mengjq.assignmentsubmission.pojo;

import org.bson.Document;

public class FileInfo {
    String fileId; //: 01
    String assiId; //: 08
    String stuId; //: 19852331
    String stuName; //: 孟骏清
    Integer fileSize; //: 302334323
    String rawName; //: 孟骏清数据结构6.docx
    String formatName; //: 数据结构-1909班-19852331-第六次.docx
    String fileContent; //: "!l.dfd"
    String hash; //: fae0b27c451c728867a567e8c1bb4e53

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public String getFileContent() {
        return fileContent;
    }

    public void setFileContent(String fileContent) {
        this.fileContent = fileContent;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getAssiId() {
        return assiId;
    }

    public void setAssiId(String assiId) {
        this.assiId = assiId;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }


    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public String getFormatName() {
        return formatName;
    }

    public void setFormatNameByAssi(Document assi, Document stu) {
        this.formatName = assi.get("fileNameRule").toString()
                .replace("班级",      stu.get("stuClazz").toString())
                .replace("{assiId}", assiId)
                .replace("{stuName}", stuName)
                .replace("{rawName}", rawName);
    }


    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}
