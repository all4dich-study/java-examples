package com.company.lge.mongodb.samples;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.client.*;

public class Mongo {
    public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient();
        MongoDatabase db = mongoClient.getDatabase("cerberusLog");
        MongoIterable<String> coll_list = db.listCollectionNames();
        coll_list.forEach(new Block<String>() {
            @Override
            public void apply(String s) {
                System.out.println(s);
            }
        });
        MongoCollection coll = db.getCollection("starfish-drd4tv-verify-mtka5lr");
        System.out.println(coll.count());
   }
}
