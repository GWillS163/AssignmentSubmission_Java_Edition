package com.mengjq.assignmentsubmission.test;

import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.core.Menu;
import com.mengjq.assignmentsubmission.core.MongoDBService;
import com.mengjq.assignmentsubmission.pojo.DeviceReg;
import org.bson.Document;

import java.util.ArrayList;

public class ServiceTest {
    public static void main(String[] args) {
        String userId = "19852331";
        String clazz = "1909";
        String mongodbUrl = "mongodb+srv://mengjq:OXDueFslVZNWtiqT@assignmentsubmmsion.nttaj.mongodb.net/?retryWrites=true&w=majority";
        String assignmentInfoDB = "AssignmentInfo";
        String deviceRegDB = "DeviceReg";
        String fileInfoDB = "FileInfo";
        String studentInfoDB = "StudentInfo";
        EchoCLI echoCLI = new EchoCLI();
        MongoDBService mongoDBService = new MongoDBService(mongodbUrl, clazz, assignmentInfoDB, deviceRegDB, fileInfoDB, studentInfoDB);
        DeviceReg deviceReg = new DeviceReg();

        // mongoDB service test
        System.out.println(mongoDBService.getStuInfo(deviceReg.getDeviceMAC()));

//        mongoDBService.getSubmitStatus("19852331");
//        deviceReg.setAllandUserId(userId);

//        mongoDBService.getStuInfo(deviceReg.getDeviceMAC());
//        mongoDBService.addAssignment();
//        String[] options = {"1. Register Device", "2. Get Submit Status", "3. Upload Files", "4. Get Student Info", "5. Exit"};
//        String[] files = {"meng.docx", "li.docx"};
//
//
//        Iterable<Document> assignments =  mongoDBService.getAssignments();
//        echoCLI.showAssignments(assignments);

//        ArrayList<Integer> selected = Menu.selectRenameMenu(assignments, files);
//        System.out.println(selected);


    }
}
