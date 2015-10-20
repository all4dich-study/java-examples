package com.company.lge.mongodb.samples;

import com.mongodb.Block;
import com.mongodb.async.SingleResultCallback;
import com.mongodb.async.client.*;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Mongon_Async {
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        MongoClient mongoClient = MongoClients.create("mongodb://127.0.0.1");
        MongoDatabase db = mongoClient.getDatabase("cerberusLog");
        MongoIterable<String> st = db.listCollectionNames();
        st.forEach(new Block<String>() {
                       @Override
                       public void apply(String s) {
                           System.out.println(s);
                       }
                   },
                new SingleResultCallback<Void>() {
                    @Override
                    public void onResult(Void aVoid, Throwable throwable) {
                        System.out.println("Done");
                        synchronized (Mongon_Async.lock){
                            Mongon_Async.lock.notifyAll();
                        }
                    }
                }
        );
        synchronized (Mongon_Async.lock) {
            Mongon_Async.lock.wait();
        }

        MongoCollection coll = db.getCollection("starfish-drd4tv-verify-mtka5lr");

        coll.count(new SingleResultCallback<Long>() {
            @Override
            public void onResult(Long aLong, Throwable throwable) {
                System.out.println(aLong);
                synchronized (Mongon_Async.lock){
                    Mongon_Async.lock.notifyAll();
                }
            }
        });
    }
}
