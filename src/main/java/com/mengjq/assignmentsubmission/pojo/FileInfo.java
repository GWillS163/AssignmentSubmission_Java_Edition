package com.mengjq.assignmentsubmission.pojo;

//import com.mengjq.assignmentsubmission.util.Base64Util;
import com.mengjq.assignmentsubmission.util.Base64Util;
import com.mengjq.assignmentsubmission.util.Hash;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileInfo {
    public Integer fileId = null; //: 01
    public String assiId = null; //: 08
    public String stuId = null; //: 19852331
    public String stuName = null; //: 孟骏清
    public String uploadTime = null;
    public String filePath;
    public String rawName = null; //: 孟骏清数据结构6.docx
    public Integer fileSize = null; //: 302334323
    public String formatName = null; //: 数据结构-1909班-19852331-第六次.docx
    public String fileContent = null; //: "!l.dfd"
    public String hash = null; //: fae0b27c451c728867a567e8c1bb4e53
    public String status = "unCheck"; //: fae0b27c451c728867a567e8c1bb4e53

    public FileInfo(String filePath)  {
        // random id
        this.fileId = (int)(Math.random()*1000);
        this.filePath = filePath;
        setRawName(filePath);
        setFileSize(filePath);
//        setFileContent(filePath);
//        setHash();
        setUploadTime();
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }



    private void setRawName(String path){
        String[] pathArray = path.split("\\\\");
        this.rawName = pathArray[pathArray.length - 1];
    }

    private void setFileSize(String filePath) {
        this.fileSize = (int) (new java.io.File(filePath)).length();
    }

    // 是否放在这里?
    public static void setFormatName(Document assi, Document stu) {
        String formatName = assi.get("fileNameRule").toString()
                .replace("班级", stu.get("stuClazz").toString());
//                .replace("{assiId}", assiId)
//                .replace("{stuName}", stuName)
//                .replace("{rawName}", rawName);
    }

    public void setUploadTime() {
        // get current Date and Time
        this.uploadTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
    }

    private void setFileContent(String filePath) throws IOException {
        byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
        this.fileContent = Base64Util.ToBase64(fileContent);
    }

    private void setHash() {
        this.hash = Hash.getMD5(this.fileContent);
    }

    public Integer getFileId() {
        return fileId;
    }

    public String getAssiId() {
        return assiId;
    }

    public String getStuId() {
        return stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public String getUploadTime() {
        return uploadTime;
    }

    public String getRawName() {
        return rawName;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public String getFormatName() {
        return formatName;
    }

    public String getFileContent() {
        return fileContent;
    }

    public String getHash() {
        return hash;
    }

    public String getStatus() {
        return status;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public void setAssiId(String assiId) {
        this.assiId = assiId;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setUploadTime(String uploadTime) {
        this.uploadTime = uploadTime;
    }

    public void setFileSize(Integer fileSize) {
        this.fileSize = fileSize;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public Document toDocument(){
        return  new Document()
                .append("fileId", this.fileId)
                .append("assiId", this.assiId)
                .append("stuId", this.stuId)
                .append("stuName", this.stuName)
                .append("uploadTime", this.uploadTime)
                .append("filePath", this.filePath)
                .append("rawName", this.rawName)
                .append("fileSize", this.fileSize)
                .append("formatName", this.formatName)
                .append("fileContent", this.fileContent)
                .append("hash", this.hash)
                .append("status", this.status);
    }
}
