package com.mengjq.assignmentsubmission.test;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.junit.Test;


// disable slf4j logging

import java.util.LinkedList;
import java.util.List;
import com.configcat.*;

public class threadTest {
    Config conf = new Config();
    // disable slf4j logging

    @Test
    public void readConfig(){
        ConfigCatClient client = ConfigCatClient.newBuilder()
                .logLevel(LogLevel.INFO) // <-- Set the log level to INFO to track how your feature flags were evaluated. When moving to production, you can remove this line to avoid too detailed logging.
                .build("S5zaCCUpl0mE6YfDKBjcYw/4B5tPjF6FE61Dokh7HKCTQ"); // <-- This is the actual SDK Key for your 'Test' environment.
        String mongoDatabaseName = client.getValue(String.class, "mongoDatabaseName", "Default");
        String mongodatabasepassword = client.getValue(String.class, "mongodatabasepassword", "Default");

        System.out.println("mongoDatabaseName's value from ConfigCat: " + mongoDatabaseName);
        System.out.println("mongodatabasepassword's value from ConfigCat: " + mongodatabasepassword);

    }

    @Test
    public void linkDB(){
        // print a loading animation until the database is connected

        MongoClient mongoClient = MongoClients.create(conf.mongodbUrl);
        MongoDatabase database = mongoClient.getDatabase(conf.clazz);
        MongoCollection<Document> collection = database.getCollection(conf.assignmentInfoDB);
        System.out.println(collection.find().first());
        // loading animation end
        System.out.println("Done");
    }

    @Test
    public void linkingDemo() throws InterruptedException {
        Thread t = new Thread(this::linkDB);
        waitingAnimation(t, 800);
    }

    @Test
    public void waitingDemo() throws InterruptedException {
        Thread t = new Thread(this::doSomething);

        int res = waitingAnimation(t, 1000);
        System.out.println(res);
    }

    private int waitingAnimation(Thread t, Integer flushTime) throws InterruptedException {
        int res = 0;
        int waitTime = flushTime / 5;
        System.out.println("Start");
        List<String> animation = new LinkedList<>();
        animation.add("|");
        animation.add("/");
        animation.add("-");
        animation.add("\\");

        StringBuilder dots = new StringBuilder();
        t.start();
        while (t.isAlive()){
            for (String anima:animation) {
                System.out.print("\r" + anima + dots);
                Thread.sleep(waitTime);
            }
            dots.append(".");
            Thread.sleep(waitTime);
        };
        t.join();

        // loading animation end
        System.out.println("[Done]");
        return res;
    }

    public int doSomething(){
        // do something
        // time wait for 5 seconds
        for (int i = 0; i < 3; i++){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}
        return 33;
    }
}
