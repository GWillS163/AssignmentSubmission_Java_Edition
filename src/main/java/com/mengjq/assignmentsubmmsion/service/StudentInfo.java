package com.mengjq.assignmentsubmmsion.service;

public interface StudentInfo {
    public void insert(StudentInfo studentInfo);
    public void delete(String id);
    public void update(StudentInfo studentInfo);
    public StudentInfo findById(String id);
    public StudentInfo findByName(String name);
    public StudentInfo findByEmail(String email);
    public StudentInfo findByPhone(String phone);
    public StudentInfo findByClass(String className);
    public StudentInfo findByStatus(String status);
    public StudentInfo findByCreateTime(String createTime);
    public StudentInfo findByUpdateTime(String updateTime);
}
