package com.mengjq.assignmentsubmission.util;

public class Hash {
//        public static String getHash(String str) {
//        return String.format("%032x", new java.math.BigInteger(1, str.getBytes()));
//    }

    // get the hash code of the string using MD5 algorithm
    public static String getMD5(String str) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(str.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;

        }
    }
}
