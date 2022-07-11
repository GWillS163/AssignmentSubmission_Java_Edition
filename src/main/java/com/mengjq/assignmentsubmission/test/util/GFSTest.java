package com.mengjq.assignmentsubmission.test.util;

import com.mengjq.assignmentsubmission.util.MongodbGFS;
import org.bson.types.ObjectId;
import org.junit.Before;

public class GFSTest {

    private MongodbGFS mgfs;

    @Before
    public void init() {
        mgfs=new MongodbGFS();
    }

    public static void main(String[] args) {

        String url = "E:\\亚西亚的孤儿-Shanghai.mp4";
//        url = "D:\\system\\桌面\\Client_submit_mainV3.3 测试版.exe";
        // 测试存储
        MongodbGFSTest mgfsTest=new MongodbGFSTest();
        mgfsTest.init();

        // iterate 10 times to test the performance of downFile()
        for (int i = 0; i < 2; i++) {
            // calculate the time of get file
            long start = System.currentTimeMillis();

//            mgfsTest.downFile();
            mgfsTest.saveFile(url);
//            mgfsTest.saveFile2(url);

            long end = System.currentTimeMillis();
            // show time with seconds
            System.out.println("time:" + (end - start) / 1000);
        }

//        mgfsTest.deleteFile();

    }
}
