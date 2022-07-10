package com.mengjq.assignmentsubmission.pojo;

//import com.mengjq.assignmentsubmission.util.Base64Util;
import com.mengjq.assignmentsubmission.util.Base64Util;
import com.mengjq.assignmentsubmission.util.Hash;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileInfo {
    String fileId = null; //: 01

    String assiId = null; //: 08
    String stuId = null; //: 19852331
    String stuName = null; //: 孟骏清

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    String uploadTime = null;
    String filePath;
    String rawName = null; //: 孟骏清数据结构6.docx
    Integer fileSize = null; //: 302334323
    String formatName = null; //: 数据结构-1909班-19852331-第六次.docx
    String fileContent = null; //: "!l.dfd"
    String hash = null; //: fae0b27c451c728867a567e8c1bb4e53
    String status = "未被查看"; //: fae0b27c451c728867a567e8c1bb4e53

    public FileInfo(String filePath) throws IOException {
        this.filePath = filePath;
        setRawName(filePath);
        setFileSize(filePath);
        setFileContent(filePath);
        setHash();
        setUploadTime();
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

    public String getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime() {
        // get current Date and Time
        this.uploadTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public void setFormatName(String formatName) {
        this.formatName = formatName;
    }

    private void setFileContent(String filePath) throws IOException {
        byte[] fileContent = Files.readAllBytes(Paths.get(filePath));
        this.fileContent = Base64Util.ToBase64(fileContent);
    }

    private void setHash() {
        this.hash = Hash.getMD5(this.fileContent);
    }

    // TODO: 也许不需要_ fileID 随机产生？
    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public void setAssiId(String assiId) {
        this.assiId = assiId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }


    public String getStuName() {
        return stuName;
    }

    public String getFileContent() {
        return fileContent;
    }


    public String getAssiId() {
        return assiId;
    }

    public String getStuId() {
        return stuId;
    }


    public String getRawName() {
        return rawName;
    }

    public String getFormatName() {
        return formatName;
    }


    public String getHash() {
        return hash;
    }

    public Integer getFileSize() {
        return fileSize;
    }

    public String getStatus() {
        return status;
    }
}
