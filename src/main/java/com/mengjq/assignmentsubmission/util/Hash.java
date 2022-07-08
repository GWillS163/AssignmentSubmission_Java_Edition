package com.mengjq.assignmentsubmission.util;

public class Hash {
    public static String getHash(String str) {
        int hash = 0;
        for (int i = 0; i < str.length(); i++) {
            hash = hash * 31 + str.charAt(i);
        }
        return Integer.toString(hash);
    }

}
