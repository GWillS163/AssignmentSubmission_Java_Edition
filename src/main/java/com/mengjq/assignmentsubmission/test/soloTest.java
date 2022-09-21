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

public class soloTest {
    Config conf = new Config();
    // disable slf4j logging

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
        waitingAnimation(t, 1000);
    }

    @Test
    public void waitingDemo() throws InterruptedException {
        Thread t = new Thread(this::doSomething);

        int res = waitingAnimation(t, 1000);
        System.out.println(res);
    }

    private int waitingAnimation(Thread t, Integer flushTime) throws InterruptedException {
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
        int res = 0;
        return res;
    }

    private int doSomething(){
        // do something
        // time wait for 5 seconds
        for (int i = 0; i < 30; i++){
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }}
        return 33;
    }
}
