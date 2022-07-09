package com.mengjq.assignmentsubmission.core;


import com.mongodb.client.FindIterable;
import org.bson.Document;

public class EchoCLI {
    public void showCollectingAssignments(FindIterable<Document> assignments){
        if (!assignments.cursor().hasNext()) {
            System.out.println("No assignments");
            return;
        }
        System.out.println("--------------------------");
        String[] headers = {"assId", "briefName", "describe", "fileNameRule", "DDL", "status"};
        System.out.printf("%-10s %-15s %-20s %-20s %-20s %-10s\n", headers);
        System.out.println("--------------------------");
        for (Document assignment : assignments) {
            // print the value of headers with equal length
            System.out.printf("%-10s %-15s %-20s %-20s %-20s %-10s\n",
                    assignment.getString("assId"),
                    assignment.getString("briefName"),
                    assignment.getString("describe"),
                    assignment.getString("fileNameRule"),
                    assignment.getString("DDL"),
                    assignment.getString("status"));
        }
        System.out.println("--------------------------");

    }

    public void showMySubmitStatus(FindIterable<Document> docs){
        System.out.println("studentId: ");
        if (!docs.cursor().hasNext()) {
            System.out.println("No submissions");
            return;
        }
        // acquire the number of docs
        int num = 0;
        for (Document doc : docs) {
            num++;
        }

        String[] fields = {"status", "uploadTime", "fileSize", "formatName"};
        // print the values of fields with equal length
        System.out.println("--------------------------");
        System.out.printf("%-10s %-10s %-10s %-10s\n", fields);
        System.out.println("--------------------------");
        for (Document doc : docs) {
            // convert the String filesize to String fileSize with MB format
            String fileSize = doc.getString("fileSize");
            double fileSizeMB = Double.parseDouble(fileSize) / 1024 / 1024;
            // keep fileSizeMB with 2 decimal places
            fileSizeMB = Math.round(fileSizeMB * 100) / 100.0;
            System.out.printf("%-10s %-10s %-10s %-10s\n",
//                    doc.getString("stuId"),
//                    doc.getString("stuName"),
                    doc.getString("status"),
                    doc.getString("uploadTime"),
                    Double.toString(fileSizeMB) + "MB",
                    doc.getString("formatName"));
        }
        System.out.println("--------------------------");
        System.out.println("Total: " + num + " submissions");
    }

    // TODO:完成 查询数据测试
    public void showAllSubmitStatus(FindIterable<Document> docs){
        // get all assignments from mongoDB
        if (!docs.cursor().hasNext()) {
            System.out.println("No assignments");
            return;
        }

        // get all files from mongoDB

        // show other files

        String[] fields = {"assId", "stuId", "stuName", "uploadTime", "fileSize", "formatName", "status"};
        System.out.println("--------------------------");
        for (String field : fields) {
            System.out.print(field);
            System.out.print("\t");
        }
        System.out.println();
        System.out.println("--------------------------");
        for (Document doc : docs) {
            for (String field : fields) {
                System.out.print(doc.get(field));
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}
