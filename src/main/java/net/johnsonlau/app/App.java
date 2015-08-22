package net.johnsonlau.app;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Date;

import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

public class App 
{
    public static void main(String[] args) throws InterruptedException
    {
        System.out.println("start...");
        System.out.println("start..." + (new Date()).toString());

        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("test");
        MongoCollection collection = database.getCollection("test");
        collection.drop();
        collection.insertOne(new Document("name", "Johnson").append("age", 23));
        collection.insertOne(new Document("name", "Anne").append("age", 22));

        MongoCursor<Document> cur = collection.find(Document.class).iterator();
        cur.next();
        Document doc = cur.next();

        get("/", (req, res) -> {
            //Document doc = (Document)collection.find().first();
            List<Document> docs = new ArrayList();
            collection.find().into(docs);
            Map<String, Object> map = new HashMap<>();
            map.put("docs", docs);

            return new ModelAndView(map, "hello.ftl");
        }, new FreeMarkerEngine());

        //Thread.sleep(1000000000);
        System.out.println("end...");
    }
}
