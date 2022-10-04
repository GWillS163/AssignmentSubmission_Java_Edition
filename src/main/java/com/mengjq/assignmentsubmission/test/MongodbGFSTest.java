package com.mengjq.assignmentsubmission.test;
import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.util.MongodbGFS;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MongodbGFSTest {
    private MongodbGFS mgfs;

    String url = "E:\\亚西亚的孤儿-Shanghai.mp4";
    String savePath = "E:\\";

    @Before
    public void MongodbGFSTest() {
        Config config = new Config();
        MongoClient mongoClient = MongoClients.create(config.mongodbUrl);
        MongoDatabase clazzDB = mongoClient.getDatabase(config.clazz);
//        gridFSBucket = GridFSBuckets.create(clazzDB);
        mgfs=new MongodbGFS(clazzDB, "files");
    }

    @Test
    public void uploadFile() {
        // calculate the time of get file
        long start = System.currentTimeMillis();
        mgfs.saveFile(url);
        long end = System.currentTimeMillis();
        System.out.println("time:" + (end - start) / 1000);
    }

    // 测试存储 1
    @Test
    public void downloadFile() {
//        mgfs.downFile(savePath, new ObjectId("5d1b1b1b1b1b1b1b1b1b1b1b"));
        mgfs.saveFile2(url);
    }

    // 测试存储 2
    @Test
    @Ignore
    public void saveFile(String url) {
        ObjectId id=mgfs.saveFile(url);
        System.out.println(id);
    }


    // 测试查询所有在当前数据库存储的文件
//    @Ignore
//    public void findAllFile() {
//        System.out.println(mgfs.findAllFile());
//    }

    // 测试下载文件，存数据库
    @Ignore
    public void downFile() {
        System.out.println(mgfs.downOneFile(url,new ObjectId("62cb92d33240c450eed4ec58")));
    }

    // 测试删除文件
    @Ignore
    public void deleteFile() {
        mgfs.deleteFile(new ObjectId("5a6ec218f9afa00c086d94bb"));
    }

    //测试重命名文件
    @Test
    public void rename() {
        mgfs.rename(new ObjectId("5a6ec218f9afa00c086d94bb"), "zhaotong.pdf");
    }

    public static void main(String[] args) {

    }
}