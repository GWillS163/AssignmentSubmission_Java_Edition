package com.mengjq.assignmentsubmission.test;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.sleep;

public class MyCallable implements Callable<Integer> {

    private int doSomething(){
        // do something
        // time wait for 5 seconds
        for (int i = 0; i < 10; i++){
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }}
        return 33;
    }

    @Override
    public Integer call() throws Exception {
        int res;
        System.out.println("[Start]");
        res = doSomething();
        System.out.println("[Done]");
        return res;
    }
    @Test
    public void withoutThread(){
        System.out.println("Loading...");
        Integer res = doSomething();
        System.out.println("Done");
        System.out.println(res);
    }

    @Test
    public void load(){
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        Thread thread = new Thread(futureTask);
        thread.start();
        Integer value = null;
        while (!futureTask.isDone()){
            System.out.println("线程未执行完毕......");
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            value = futureTask.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(value);
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*
         * futureTask 实现了 Runnable接口
         * 所以新建线程的时候可以传入futureTask
         * FutureTask重写的run方法中实际是调用了Callable接口在call()方法
         * 所以执行线程的时候执行call方法的内容
         */
        FutureTask<Integer> futureTask = new FutureTask<>(new MyCallable());
        Thread thread = new Thread(futureTask);
        thread.start();
        Integer value = futureTask.get();
        System.out.println(value);
    }
}

