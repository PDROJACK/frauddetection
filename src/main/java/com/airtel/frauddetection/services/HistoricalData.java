package com.airtel.frauddetection.services;

import java.util.ArrayList;
import java.util.Iterator;

import com.airtel.frauddetection.model.RetailerModel;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;



/**
 * MongoTest
 */
public class HistoricalData {
    
    static MongoClient mongo = new MongoClient("localhost", 27017);
    static MongoDatabase database = mongo.getDatabase("retailers");
    static MongoTemplate mongoTemplate = new MongoTemplate(mongo, "retailers");
    // Find
    static MongoCollection<Document> collection = database.getCollection("retailer_data");     
    
    /**
     * @return the collection
     */
    public static MongoCollection<Document> getCollection() {
        return collection;
    }

    public static MongoTemplate getMongoTemplate(String dbname) {
        MongoClient mongo = new MongoClient("localhost",27017);
        MongoDatabase database = mongo.getDatabase(dbname);
        MongoTemplate mongoTemplate = new MongoTemplate(mongo, dbname);
        return mongoTemplate;
    }

    public static RetailerModel getHistoricalData(int id, String label) throws Exception {
        Document obj = (Document) collection.find(Filters.eq(label,id)).first();
        RetailerModel retailer = mongoTemplate.getConverter().read(RetailerModel.class, obj);
        return retailer;
    }

}