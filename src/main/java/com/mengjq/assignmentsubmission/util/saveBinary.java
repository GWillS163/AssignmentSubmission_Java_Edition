package com.mengjq.assignmentsubmission.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class saveBinary {
    public static void main(String[] args) {
//        JSONObject student1_json = JSON.parseObject(student1.toJson());
//        String content = (String) JSON.parseObject(JSON.parseObject(student1_json.get("content").toString()).get("$binary").toString()).get("base64");
//        saveBinaryData(Base64.getDecoder().decode(content), "D:\\Project\\java-quick-start\\src\\main\\java\\com\\mongodb\\quickstart\\test.txt.txt");
//        System.out.println("Student 1_json: " + student1_json);
//        System.out.println("Student 1_content: " + content);

//        upload
        String content = "123213";
        System.out.println(content.getBytes());

        String encoded = Base64.getEncoder().encodeToString(content.getBytes());
        System.out.println(encoded);

        String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
        System.out.println(decoded);

    }
}
