package com.mengjq.assignmentsubmmsion.service.impl;

import com.mengjq.assignmentsubmmsion.pojo.AssignmentInfo;
import com.mengjq.assignmentsubmmsion.service.LatestAssignment;

import javax.swing.text.Document;

public class LatestAssignmentImpl implements LatestAssignment {

    public Document assignmentInfo;
    @Override
    public Document getLatestAssignment() {
        return null;
    }

    @Override
    public void printAssignmentInfo() {
        Document assignmentInfo = getLatestAssignment();
        System.out.println("Assignment Info: ");
        // TODO: Print assignment info
//        System.out.println("Assignment ID: " + assignmentInfo.get("_id"));
//        System.out.println("Assignment Name: " + assignmentInfo.get("name"));
//        System.out.println("Assignment Description: " + assignmentInfo.get("description"));
//        System.out.println("Assignment Deadline: " + assignmentInfo.get("deadline"));

    }

    @Override
    public void getUserChoice() {

    }

}
