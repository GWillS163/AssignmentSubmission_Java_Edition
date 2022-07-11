package com.mengjq.assignmentsubmission.test.util;
import com.mengjq.assignmentsubmission.util.MongodbGFS;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class MongodbGFSTest {

    private MongodbGFS mgfs;

    @Before
    public void init() {
        mgfs=new MongodbGFS();
    }

    String url = "E:\\亚西亚的孤儿-Shanghai.mp4";
    // 测试存储
    @Ignore
    public void saveFile(String url) {
        ObjectId id=mgfs.saveFile(url);
        System.out.println(id);
    }

    //测试存储二
    @Ignore
    public void saveFile2(String url) {
        ObjectId id=mgfs.saveFile2(url);
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
        System.out.println(mgfs.downFile(url,new ObjectId("62cb92d33240c450eed4ec58")));
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