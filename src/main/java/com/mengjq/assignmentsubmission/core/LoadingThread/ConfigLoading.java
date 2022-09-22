package com.mengjq.assignmentsubmission.core.LoadingThread;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.test.MyCallable;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.sleep;
public class ConfigLoading implements Callable<Config> {

    public ConfigLoading(Integer flushTime){
        try {
            ConfigLoading(flushTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public Integer ConfigLoading(Integer flushTime) throws InterruptedException {
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        Thread thread = new Thread(futureTask);
        int waitTime = flushTime / 5;
        Integer value = null;
        thread.start();

        List<String> animation = new LinkedList<>();
        animation.add("|");
        animation.add("/");
        animation.add("-");
        animation.add("\\");

        StringBuilder dots = new StringBuilder();
        while (!futureTask.isDone()) {
            for (String anima : animation) {
                System.out.print("\r" + anima + dots);
                sleep(waitTime);
            }
            dots.append(".");
            sleep(waitTime);
        }
        try {
            value = futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(value);
        return value;
    }

    @Override
    public Config call(){
        return new Config();
    }
}
