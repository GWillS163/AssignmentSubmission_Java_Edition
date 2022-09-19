package com.mengjq.assignmentsubmission.service;
import com.mengjq.assignmentsubmission.mapper.StudentInfoMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class StudentInfoService {
    public StudentInfoMapper studentInfoMapper;

    public StudentInfoService(MongoDatabase clazzDB, String studentInfo) {
        studentInfoMapper = new StudentInfoMapper(clazzDB, studentInfo);
//        System.out.println(studInfoDBCollection.getNamespace());
    }

    // getStudentInfo
    public Document getStudentInfo(String stuId) {
        // query by stuId
        return studentInfoMapper.request(
                new Document().append("stuId", stuId)).first();
    }

    public FindIterable<Document> findStuInfoByBson(String field, String value) {
        return studentInfoMapper.request(new Document().append(field, value));
    }

    public FindIterable<Document> getAllStuInfo() {
        return studentInfoMapper.request(new Document());
    }
}
