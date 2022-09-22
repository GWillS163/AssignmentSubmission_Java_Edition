package com.mengjq.assignmentsubmission.test;

import com.mengjq.assignmentsubmission.conf.Config;

public class ConfigTest {
    public static void main(String[] args) {
        Config config = new Config();
        Config conf = config.loading();
        System.out.println(conf.mongodbUrl);
    }
}
