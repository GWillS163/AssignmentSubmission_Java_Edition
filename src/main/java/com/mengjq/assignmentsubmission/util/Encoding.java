package com.mengjq.assignmentsubmission.util;

import com.mongodb.client.MongoClient;
        import com.mongodb.client.MongoClients;
        import com.mongodb.client.MongoCollection;
        import com.mongodb.client.MongoDatabase;

        import org.bson.Document;
        import java.net.URLEncoder;

import static com.mongodb.client.model.Filters.eq;

public class Encoding {

    public static void main(String [] args){

        try{
            String username = URLEncoder.encode("mengjq", "UTF-8");
            String password = URLEncoder.encode("OXDueFslVZNWtiqT", "UTF-8");
            String cluster = "assignmentsubmmsion.nttaj.mongodb.net";
            String authSource = "db1";
            String authMechanism = "<authMechanism>";

            String uri = "mongodb+srv://" + username + ":" + password + "@" + cluster +
                    "/?authSource=" + authSource;


            MongoClient mongoClient = MongoClients.create(uri);

            MongoDatabase database = mongoClient.getDatabase("StudentInfo");
            System.out.println(database.getName());

            MongoCollection<Document> collection = database.getCollection("1909");
            System.out.println(collection.getNamespace().getCollectionName());

            System.out.println(collection.countDocuments());

//            Document doc = collection.find(eq("studentName", "孟骏清")).first();
//            System.out.println(doc.toJson());
            System.out.println(collection.countDocuments());
            collection.find().forEach(doc -> System.out.println(doc.toJson()));


        } catch(Exception e){
            System.err.println(e.getCause());

        }
    }
}
