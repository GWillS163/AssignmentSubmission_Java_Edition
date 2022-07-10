package com.mengjq.assignmentsubmission.test;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.pojo.DeviceReg;
import com.mengjq.assignmentsubmission.service.MongoDBService;

public class ServiceTest {
    public static void main(String[] args) {
        EchoCLI echoCLI = new EchoCLI();
        Config conf = new Config();
        MongoDBService mongoDBService = new MongoDBService(
                conf.mongodbUrl,
                conf.clazz,
                conf.assignmentInfoDB,
                conf.deviceRegDB,
                conf.fileInfoDB,
                conf.studentInfoDB);
        DeviceReg deviceReg = new DeviceReg();
        deviceReg.setStuId("19852331");
//
//        // mongoDB service test
//        System.out.println(mongoDBService.tryGetStuInfoByMAC(deviceReg.getDeviceMAC()));
//

//        mongoDBService.getSubmitStatus("19852331");
//        deviceReg.setAllandUserId(userId);

//        mongoDBService.getStuInfo(deviceReg.getDeviceMAC());
//        mongoDBService.addAssignment();
//        String[] options = {"1. Register Device", "2. Get Submit Status", "3. Upload Files", "4. Get Student Info", "5. Exit"};
//        String[] files = {"meng.docx", "li.docx"};

//        Iterable<Document> assignments =  mongoDBService.getAssignments();
//        echoCLI.showAssignments(assignments);

//        ArrayList<Integer> selected = Menu.selectRenameMenu(assignments, files);
//        System.out.println(selected);

        mongoDBService.getMySubmittedFileInfo(deviceReg.getDeviceMAC());
        }
}
