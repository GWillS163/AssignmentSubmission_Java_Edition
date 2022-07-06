package com.mengjq.assignmentsubmmsion.service.impl;
import com.mengjq.assignmentsubmmsion.pojo.AssignmentInfo;
import com.mengjq.assignmentsubmmsion.service.AssignmentInfoService;
import org.bson.Document;

import java.util.List;
import java.util.Random;

public class AssignmentInfoServiceImpl implements AssignmentInfoService {

    private static final Random rand = new Random();

    @Override
    public void insert(AssignmentInfoService assignmentInfoService) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(AssignmentInfoService assignmentInfoService) {

    }

    @Override
    public Document getLatestAssignment() {
        // TODO: implement this method
        return null;
    }

    @Override
    public List<AssignmentInfo> printAssignmentInfo() {
        Document assignmentInfo = getLatestAssignment();
        System.out.println("Assignment Info: ");
        System.out.println("Assignment ID: " + assignmentInfo.get("_id"));
        System.out.println("Assignment Name: " + assignmentInfo.get("name"));
        System.out.println("Assignment Description: " + assignmentInfo.get("description"));
        System.out.println("Assignment Deadline: " + assignmentInfo.get("deadline"));

        return null;
    }

}
