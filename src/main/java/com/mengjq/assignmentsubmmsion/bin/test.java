package com.mengjq.assignmentsubmmsion.bin;

import com.mengjq.assignmentsubmmsion.core.MongoDBService;

public class test {
    public static void main(String[] args) {
        String clazz = "1909";
        String mongodbUrl = "mongodb+srv://mengjq:OXDueFslVZNWtiqT@assignmentsubmmsion.nttaj.mongodb.net/?retryWrites=true&w=majority";
        String assignmentInfoDB = "AssignmentInfo";
        String deviceRegDB = "DeviceReg";
        String fileInfoDB = "FileInfo";
        String studentInfoDB = "StudentInfo";
        MongoDBService mongoDBService = new MongoDBService(mongodbUrl, clazz, assignmentInfoDB, deviceRegDB, fileInfoDB, studentInfoDB);
        mongoDBService.getSubmitStatus("19852331");
    }
}
