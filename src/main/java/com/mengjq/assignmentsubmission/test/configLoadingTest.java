package com.mengjq.assignmentsubmission.test;

import com.mengjq.assignmentsubmission.core.LoadingThread.ConfigLoading;

public class configLoadingTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello World!");
        Integer configLoading = new ConfigLoading(1000);
        System.out.println(configLoading);

}
}
