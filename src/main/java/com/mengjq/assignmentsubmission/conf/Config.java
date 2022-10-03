package com.mengjq.assignmentsubmission.conf;
import com.configcat.*;
import com.mengjq.assignmentsubmission.core.EchoCLI;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.sleep;

public class Config implements Callable<Config> {
    public EchoCLI echoCLI = new EchoCLI();
    public String mongodbUrl;
    public String assignmentInfoDB;
    public String deviceRegDB;
    public String fileInfoDB;
    public String studentInfoDB;
    public String oprInfoDB;
    public String clazz;

    // 控制面板 - Configure Panel
    public String LanguageNationCode;
    public String loadTypes = "test";
    public Integer flushTime = 1000;


    // 本地配置 - init config by local
    public Config initConfigByLocal(){
        mongodbUrl = "mongodb+srv://mengjq:H98mTrQ1dMP43Iy6@assignmentsubmmsion.nttaj.mongodb.net/?retryWrites=true&w=majority";
        assignmentInfoDB = "AssignmentInfo";
        deviceRegDB = "DeviceReg";
        fileInfoDB = "FileInfo";
        studentInfoDB = "StudentInfo";
        oprInfoDB = "OprInfo";
        clazz = "1909";
        LanguageNationCode = "cn";
        return this;
    }

//    TODO: 没有增加OprInfo
    // 使用云测试配置 - Use Cloud Test Config
    private Config testConfig(){
        return initConfigByConfigCat("S5zaCCUpl0mE6YfDKBjcYw/4B5tPjF6FE61Dokh7HKCTQ");
    };

    private Config demoConfig(){
        return initConfigByConfigCat("S5zaCCUpl0mE6YfDKBjcYw/gyf2e9blo06wLdHUsCNwZQ");
    };

    // 使用云配置 - Use Cloud Config
    private Config initConfigByConfigCat(String sdKey){
        ConfigCatClient client = ConfigCatClient.newBuilder()
                .logLevel(LogLevel.INFO) // <-- Set the log level to INFO to track how your feature flags were evaluated. When moving to production, you can remove this line to avoid too detailed logging.
                .build(sdKey); // <-- This is the actual SDK Key for your 'Test' environment.

        String mongoDatabaseName = client.getValue(String.class, "mongoDatabaseName", "Default");
        String mongodatabasepassword = client.getValue(String.class, "mongodatabasepassword", "Default");

        mongodbUrl = "mongodb+srv://" + mongoDatabaseName + ":" + mongodatabasepassword +
                "@assignmentsubmmsion.nttaj.mongodb.net/?retryWrites=true&w=majority";
        assignmentInfoDB = client.getValue(String.class, "theNameOfAssignmentDb", "Default");
        deviceRegDB = client.getValue(String.class, "theNameOfDeviceDb", "Default");
        fileInfoDB = client.getValue(String.class, "theNameOfFileInfoDb", "Default");
        studentInfoDB = client.getValue(String.class, "theNameOfStudentInfoDb", "Default");
        clazz = client.getValue(String.class, "theClazzName", "Default");
        LanguageNationCode = client.getValue(String.class, "LanguageNationCode", "Default");
        return this;
    }

    // 线程：加载动画 - Loading & Animation
    public Config loading() {
        FutureTask<Config> futureTask = new FutureTask<>(new Config());
        Thread thread = new Thread(futureTask);
        int waitTime = flushTime / 5;
        thread.start();

        List<String> animation = new LinkedList<>();
        animation.add("|");
        animation.add("/");
        animation.add("-");
        animation.add("\\");

        StringBuilder dots = new StringBuilder();
        while (!futureTask.isDone()) {
            for (String anima : animation) {
                System.out.print("\r" + "Config Loading" + anima + dots);
                try {
                    sleep(waitTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            dots.append(".");
            try {
                sleep(waitTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            Config config = futureTask.get();
            echoCLI.colorPrint("[Done]", "green");
            return config;
        } catch (InterruptedException | ExecutionException e) {
            echoCLI.colorPrint("[Failed]", "red");
            e.printStackTrace();
        }
        return null;
    }

    // 线程：指定加载的函数
    @Override
    public Config call(){
        Config config;
        switch (loadTypes){
            case "test":
                config = testConfig();
                break;
            case "demo":
                config = demoConfig();
                break;
            default:
                config = initConfigByLocal();
        }
        return config;
    }

    // to String
    @Override
    public String toString() {
        return "Config{" +
                "mongodbUrl='" + mongodbUrl + '\'' +
                ", assignmentInfoDB='" + assignmentInfoDB + '\'' +
                ", deviceRegDB='" + deviceRegDB + '\'' +
                ", fileInfoDB='" + fileInfoDB + '\'' +
                ", studentInfoDB='" + studentInfoDB + '\'' +
                ", clazz='" + clazz + '\'' +
                ", LanguageNationCode='" + LanguageNationCode + '\'' +
                ", loadTypes='" + loadTypes + '\'' +
                ", flushTime=" + flushTime +
                '}';
    }
}
