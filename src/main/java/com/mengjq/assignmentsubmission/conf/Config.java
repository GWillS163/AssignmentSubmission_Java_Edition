package com.mengjq.assignmentsubmission.conf;

public class Config {
    public String mongodbUrl = "mongodb://localhost:27017";
    public String assignmentInfoDB = "AssignmentInfo";
    public String deviceRegDB = "DeviceReg";
    public String fileInfoDB = "FileInfo";
    public String studentInfoDB = "StudentInfo";
    public String clazz = "1909";
    public String userId = "19852331";

    public Config() {
    }

    public String getAssignmentInfoDB() {
        return assignmentInfoDB;
    }

    public void setAssignmentInfoDB(String assignmentInfoDB) {
        this.assignmentInfoDB = assignmentInfoDB;
    }

    public String getDeviceRegDB() {
        return deviceRegDB;
    }

    public void setDeviceRegDB(String deviceRegDB) {
        this.deviceRegDB = deviceRegDB;
    }

    public String getFileInfoDB() {
        return fileInfoDB;
    }

    public void setFileInfoDB(String fileInfoDB) {
        this.fileInfoDB = fileInfoDB;
    }

    public String getStudentInfoDB() {
        return studentInfoDB;
    }

    public void setStudentInfoDB(String studentInfoDB) {
        this.studentInfoDB = studentInfoDB;
    }

    public String getClazz() {
        return clazz;
    }

    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMongodbUrl() {
        return mongodbUrl;
    }

    public void setMongodbUrl(String mongodbUrl) {
        this.mongodbUrl = mongodbUrl;
    }
}
