package com.mengjq.assignmentsubmission.test;

public class MyThread extends Thread
{
    private String value1;

    private String value2;


    public void run()
    {
        value1 = "value1";
        value2 = "value2";
    }

    public static void main(String[] args) throws InterruptedException
    {

        MyThread t1 = new MyThread();
        t1.start();

        while(t1.value1 == null || t1.value1 == null)
        {
            sleep(100);
        }
        System.out.println(t1.value1);
        System.out.println(t1.value2);
    }


}