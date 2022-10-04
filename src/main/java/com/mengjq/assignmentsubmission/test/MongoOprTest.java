package com.mengjq.assignmentsubmission.test;

import com.mengjq.assignmentsubmission.conf.Config;
import com.mengjq.assignmentsubmission.conf.LanguageSet;
import com.mengjq.assignmentsubmission.core.EchoCLI;
import com.mengjq.assignmentsubmission.core.ServiceMainMongoDB;
import com.mengjq.assignmentsubmission.pojo.FileInfo;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MongoOprTest {
    EchoCLI echoCLI = new EchoCLI();
    Config conf = new Config().initConfigByLocal();
    ServiceMainMongoDB mongoDBService = new ServiceMainMongoDB(conf.mongodbUrl, conf.clazz,
            conf.assignmentInfoDB, conf.deviceRegDB, conf.fileInfoDB, conf.studentInfoDB, conf.oprInfoDB);

    public static void main(String[] args) {

    }

    @Test
    public void getAllSubmittedFileInfo(){
        LanguageSet languageSet = new LanguageSet(conf.LanguageNationCode);
        System.out.println("所有学生 提交的所有人作业 状态");
        FindIterable<Document> assignments = mongoDBService.getCollectingAssignments();
        FindIterable<Document> allStuInfo = mongoDBService.getAllStuInfo();
        FindIterable<Document> allFiles = mongoDBService.getAllSubmittedFileInfo();
        System.out.println(allFiles);
        for (Document doc : allFiles) {
            System.out.println(doc);
        }
        echoCLI.showAllSubmitStatus(assignments, allFiles, allStuInfo, languageSet);
    }

    @Test
    public void getMySubmittedFileInfo(){
        // 2022-9-17 23:05:32 OK
        System.out.println("查看我的提交记录");
        FindIterable<Document> docs = mongoDBService.getMySubmittedFileInfo("19852331");
        for (Document doc : docs) {
            System.out.println(doc);
        }
    }
    @Test
    public void uploadFiles(){
        // 2022-9-17 23:05:32 OK
        System.out.println("上传文件");
        // create a list of FileInfo to insert into the database
        List<FileInfo> fileInfoList = new ArrayList<>();
        fileInfoList
//                .add(new FileInfo("D:\\system\\Downloads\\DCZZ_导出组织-20220913.xlsx"))
                .add(new FileInfo("D:\\system\\Downloads\\494424303.mp3"));

        mongoDBService.uploadFiles(fileInfoList);
    }

    @Test
    public void downloadFiles(){
        // 2022-9-19 23:29:46 OK
        System.out.println("下载文件");
        Document doc = new Document();
        doc.append("rawName", "494424303.mp3");
        List<Integer> fileIds = new ArrayList<>();
        fileIds.add(1);
        fileIds.add(2);
        mongoDBService.downloadFiles(fileIds,   "D:\\");
    }


    @Test
    public void tryGetStuInfoByMAC(){
        System.out.println("通过MAC地址获取学生信息");
        Document stuInfo = mongoDBService.tryGetStuInfoByMAC("00-FF-5D-B1-E2-98");
        System.out.println(stuInfo);
    }




    //聚合查询
    @Test
    public void groupMongod() {
        // 将筛选条件添加到文本集合中
        List<Document> doclist = new ArrayList<Document>();

        //对encode的数量进行分组，并且统计出个数
        Document doc = new Document();
        doc.append("_id", "$stuId");
        doc.append("deviceUser", new Document("$first", "$deviceUser"));
        doc.append("count", new Document("$sum", 1));
//        doc.append("count", new Document("$sum", 1));
        Document group = new Document("$group", doc);

        doclist.add(group);
//        //查找encode字段中数量大于18的
//        Document matchDoc = new Document();
//        matchDoc.put("_id", new Document("$gt", 18.0));
//        Document match = new Document("$match", matchDoc);
//        doclist.add(match);
//        //根据encode字段进行降序排序
//        Document sort = new Document("$sort", new Document("_id", 1));
//        doclist.add(sort);
        //聚合查询并输出
//        AggregateIterable<Document> aggregate = mongoDBService.fileInfoService.fileInfoMapper.fileInfoDBCollection.aggregate(doclist);
        AggregateIterable<Document> aggregate = mongoDBService.deviceInfoService.deviceInfoMapper.deviceRegDBCollection.aggregate(doclist);
//        AggregateIterable<Document> aggregate = mongoDBService.deviceInfoService.deviceInfoMapper.deviceRegDBCollection.
        System.out.println("聚合查询结果：");
        System.out.println(aggregate);
        MongoCursor<Document> iterator = aggregate.iterator();
        while (iterator.hasNext()) {
            Document next = iterator.next();
            for (String key : next.keySet()) {
                System.out.println(key + " : " + next.get(key));
            }
            System.out.println(next);
        }
    }
}
