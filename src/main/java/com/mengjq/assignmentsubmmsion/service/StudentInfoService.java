package com.mengjq.assignmentsubmmsion.service;

public interface StudentInfoService {
    public void insert(StudentInfoService studentInfoService);
    public void delete(String id);
    public void update(StudentInfoService studentInfoService);
    public StudentInfoService findById(String id);
    public StudentInfoService findByName(String name);

}
