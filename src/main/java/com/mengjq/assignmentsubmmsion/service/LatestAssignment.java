package com.mengjq.assignmentsubmmsion.service;

import com.mengjq.assignmentsubmmsion.pojo.AssignmentInfo;

import javax.swing.text.Document;

public interface LatestAssignment {
    public Document getLatestAssignment();
    public void printAssignmentInfo();
    public void getUserChoice();
}
