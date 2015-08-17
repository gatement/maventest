package net.johnsonlau.app;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;
import com.bson.Document.;

public class Mongo 
{
    public static void main(String[] args) throws InterruptedException
    {
        System.out.println("start...");

        MongoClient client = new MongoClient();
        MongoDatabase database = client.getDatabase("test");
        MongoCollection<Document> collection = database.getCollection("users");

        Thread.sleep(3000000);

        System.out.println("done...");
    }
}
