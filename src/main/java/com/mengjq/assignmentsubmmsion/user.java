package com.mengjq.assignmentsubmmsion;

public class user {
    private final String psw;
    private final String name;

    public user(String tim, String s) {
        this.name = tim;
        this.psw = s;
    }

    public String getname() {
        return name;
    }

    public String getpsw() {
        return psw;
    }
}
