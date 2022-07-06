package com.mengjq.assignmentsubmmsion.pojo;

import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class mongoTest {
    public static void main(String[] args) {
        String userPsw="mengjq:OXDueFslVZNWtiqT";
        String uri = "mongodb+srv://" + userPsw + "@assignmentsubmmsion.nttaj.mongodb.net/?authSource=db1&ssl=true";
        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("studentInfo");
            MongoCollection<Document> collection = database.getCollection("1909");
            org.bson.Document doc = collection.find(eq("studentName", "孟骏清")).first();
            System.out.println(doc.toJson());
        }
    }
}

