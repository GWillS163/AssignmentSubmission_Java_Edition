package com.mengjq.assignmentsubmmsion.service;

public interface AssignmentInfo {
    public void insert(AssignmentInfo assignmentInfo);
    public void delete(String id);
    public void update(AssignmentInfo assignmentInfo);
    public AssignmentInfo findById(String id);

}
