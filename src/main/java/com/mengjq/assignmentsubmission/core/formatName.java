package com.mengjq.assignmentsubmission.core;

public class formatName {
    public static String reName(String name) {
        String[] names = name.split("_");
        String reName = "";
        for (int i = 0; i < names.length; i++) {
            reName += names[i];
            if (i != names.length - 1) {
                reName += " ";
            }
        }
        return reName;
    }
}
