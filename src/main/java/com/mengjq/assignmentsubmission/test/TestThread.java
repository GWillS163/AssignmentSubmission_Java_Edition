package com.mengjq.assignmentsubmission.test;

import org.junit.Test;

import java.io.File;

public class TestThread {

    public static void main(String args[]) {
        RunnableDemo R1 = new RunnableDemo( "Thread-1");
        R1.start();
        RunnableDemo R2 = new RunnableDemo( "Thread-2");
        R2.start();
    }

    @Test
    public void getCurrentPath(){
        // get current Path that at the same level of the jar file
        String currentPath = System.getProperty("user.dir");
        System.out.println(currentPath);
        System.out.println(File.separator);
    }
}