package com.mengjq.assignmentsubmission.mapper;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class OprInfoMapper {
    public MongoCollection<Document> oprInfoDBCollection;

    // Link to DB
    public OprInfoMapper(MongoDatabase clazzDB, String oprInfo) {
        // connect to collection if not exists , create it
        if (!clazzDB.listCollectionNames().into(new ArrayList<>()).contains(oprInfo)) {
            clazzDB.createCollection(oprInfo);
        }
        oprInfoDBCollection = clazzDB.getCollection(oprInfo);
    }

    // Create
    public void create(Document doc) {
        oprInfoDBCollection.insertOne(doc);
    }
    public void createMany(List<Document> docs) {
        oprInfoDBCollection.insertMany(docs);
    }

    // update
    public void update(String deviceMAC, Document newDocument) {
        oprInfoDBCollection.updateOne(new Document().append("deviceMAC", deviceMAC), new Document("$set", newDocument));
    }

    // request
    public Document request(Document document) {
        return oprInfoDBCollection.find(document).first();
    }

    // delete
    public boolean delete(String deviceMAC) {
        oprInfoDBCollection.deleteOne(new Document().append("deviceMAC", deviceMAC));
        return true;
    }

}
