package com.mengjq.assignmentsubmission.core;


import org.bson.Document;

public class EchoCLI {
    public void showAssignments(Iterable<Document> assignments){
        System.out.println("--------------------------");
        for (Document assignment : assignments) {
            System.out.print(assignment.get("assId"));
            System.out.print(assignment.get("briefName"));
            System.out.print(assignment.get("describe"));
            System.out.print(assignment.get("fileNameRule"));
            System.out.print(assignment.get("DDL"));
            System.out.println(assignment.get("status"));
        }
        System.out.println("--------------------------");

    }
}
