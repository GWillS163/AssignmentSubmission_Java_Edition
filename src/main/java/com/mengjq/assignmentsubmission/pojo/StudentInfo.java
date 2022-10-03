package com.mengjq.assignmentsubmission.pojo;

import org.bson.Document;


public class StudentInfo {
    String stuId;
    String stuClazz;
    String stuName;
    String mail;
    String qq;
    String phone;
    String dormId;

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuClazz() {
        return stuClazz;
    }

    public void setStuClazz(String stuClazz) {
        this.stuClazz = stuClazz;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    public Document toDocument(){
        return new Document()
                .append("stuId", stuId)
                .append("stuClazz", stuClazz)
                .append("stuName", stuName)
                .append("mail", mail)
                .append("qq", qq)
                .append("phone", phone)
                .append("dormId", dormId);
    }
}
