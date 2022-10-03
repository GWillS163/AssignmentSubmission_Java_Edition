package com.mengjq.assignmentsubmission.pojo;


import org.bson.Document;

public class OprInfo {
    String type; // register, login, update, upload, download, delete
    String stuId;
    String deviceMAC;
    String dateTime;
    String stuName;
    String lastDeviceMAC;
    String relateFileId;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getDeviceMAC() {
        return deviceMAC;
    }

    public void setDeviceMAC(String deviceMAC) {
        this.deviceMAC = deviceMAC;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getLastDeviceMAC() {
        return lastDeviceMAC;
    }

    public void setLastDeviceMAC(String lastDeviceMAC) {
        this.lastDeviceMAC = lastDeviceMAC;
    }

    public String getRelateFileId() {
        return relateFileId;
    }

    public void setRelateFileId(String relateFileId) {
        this.relateFileId = relateFileId;
    }

    @Override
    public String toString() {
        return "OprInfo{" +
                "type='" + type + '\'' +
                ", stuId='" + stuId + '\'' +
                ", deviceMAC='" + deviceMAC + '\'' +
                ", dateTime='" + dateTime + '\'' +
                ", stuName='" + stuName + '\'' +
                ", lastDeviceMAC='" + lastDeviceMAC + '\'' +
                ", relateFileId='" + relateFileId + '\'' +
                '}';
    }

    public Document toDocument(){
        return new Document()
                .append("type", type)
                .append("stuId", stuId)
                .append("deviceMAC", deviceMAC)
                .append("dateTime", dateTime)
                .append("stuName", stuName)
                .append("lastDeviceMAC", lastDeviceMAC)
                .append("relateFileId", relateFileId);
    }
}
