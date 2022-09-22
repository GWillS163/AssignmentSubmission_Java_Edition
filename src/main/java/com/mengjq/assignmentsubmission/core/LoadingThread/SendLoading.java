package com.mengjq.assignmentsubmission.core.LoadingThread;

import org.docx4j.docProps.variantTypes.Null;

import java.util.concurrent.Callable;

import static java.lang.Thread.sleep;

public class SendLoading implements Callable<Null> {

    public SendLoading(){
        System.out.println("SendLoading...");
    }

    @Override
    public Null call(){
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
