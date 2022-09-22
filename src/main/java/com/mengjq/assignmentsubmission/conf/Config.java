package com.mengjq.assignmentsubmission.conf;
import com.configcat.*;

public class Config {
    public String mongodbUrl;
    public String assignmentInfoDB;
    public String deviceRegDB;
    public String fileInfoDB;
    public String studentInfoDB;
    public String clazz;
    public String LanguageNationCode;

    // Configure Panel
    public Config(){
        initConfigByLocal();
//        testConfig();
        LanguageNationCode = "cn";
    }

    // 本地配置 - init config by local
    public void initConfigByLocal(){
        mongodbUrl = "mongodb+srv://mengjq:H98mTrQ1dMP43Iy6@assignmentsubmmsion.nttaj.mongodb.net/?retryWrites=true&w=majority";
        assignmentInfoDB = "AssignmentInfo";
        deviceRegDB = "DeviceReg";
        fileInfoDB = "FileInfo";
        studentInfoDB = "StudentInfo";
        clazz = "1909";
    }
    // 使用云测试配置 - Use Cloud Test Config
    public void testConfig(){
        initConfigByConfigCat("S5zaCCUpl0mE6YfDKBjcYw/4B5tPjF6FE61Dokh7HKCTQ");
    };

    public void demoConfig(){
        initConfigByConfigCat("S5zaCCUpl0mE6YfDKBjcYw/gyf2e9blo06wLdHUsCNwZQ");
    };

    // 使用云配置 - Use Cloud Config
    private void initConfigByConfigCat(String sdKey){
        ConfigCatClient client = ConfigCatClient.newBuilder()
                .logLevel(LogLevel.INFO) // <-- Set the log level to INFO to track how your feature flags were evaluated. When moving to production, you can remove this line to avoid too detailed logging.
                .build(sdKey); // <-- This is the actual SDK Key for your 'Test' environment.

        String mongoDatabaseName = client.getValue(String.class, "mongoDatabaseName", "Default");
        String mongoDatabasePassword = client.getValue(String.class, "mongodatabasepassword", "Default");
        String theNameOfAssignmentDb = client.getValue(String.class, "theNameOfAssignmentDb", "Default");
        String theNameOfDeviceDb = client.getValue(String.class, "theNameOfDeviceDb", "Default");
        String theNameOfFileInfoDb = client.getValue(String.class, "theNameOfFileInfoDb", "Default");
        String theNameOfStudentInfoDb = client.getValue(String.class, "theNameOfStudentInfoDb", "Default");
        String theClazzName = client.getValue(String.class, "theClazzName", "Default");

        mongodbUrl = "mongodb+srv://" + mongoDatabaseName + ":" + mongoDatabasePassword + "" +
                "@assignmentsubmmsion.nttaj.mongodb.net/?retryWrites=true&w=majority";
        assignmentInfoDB = theNameOfAssignmentDb;
        deviceRegDB = theNameOfDeviceDb;
        fileInfoDB = theNameOfFileInfoDb;
        studentInfoDB = theNameOfStudentInfoDb;
        clazz = theClazzName;

        LanguageNationCode = "cn";
    }


}
