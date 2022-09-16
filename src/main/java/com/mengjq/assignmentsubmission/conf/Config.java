package com.mengjq.assignmentsubmission.conf;

public class Config {
    // TODO: 这个配置应该统一写到哪里？
    public String mongodbUrl = "mongodb+srv://mengjq:H98mTrQ1dMP43Iy6@assignmentsubmmsion.nttaj.mongodb.net/?retryWrites=true&w=majority";
    public String assignmentInfoDB = "AssignmentInfo";
    public String deviceRegDB = "DeviceReg";
    public String fileInfoDB = "FileInfo";
    public String studentInfoDB = "StudentInfo";
    public String clazz = "1909";
    public String userId = "19852331";

    public Config() {
    }

    public void getMenuAbout(){
        System.out.println("Release Version: 1.0.0");
        System.out.println("Author: Mengjq");
        System.out.println("Github: git@github.com:GWillS163/AssignmentSubmission_Java_Edition.git");
        System.out.println("other Version: Python Edition");
        System.out.println("other Version: Django Edition");
        System.out.println("other Version: Spring Edition");
    }
}
