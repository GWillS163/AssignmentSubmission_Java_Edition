package com.mengjq.assignmentsubmission.test;

public class MycallableTest {
    public static void main(String[] args) {
        MyCallable myCallable = new MyCallable();
        MyCallable res = myCallable.load();

        System.out.println(res.value);
    }
}
