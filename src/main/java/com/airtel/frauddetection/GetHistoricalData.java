package com.airtel.frauddetection;

import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.text.DocumentFilter;

import com.airtel.frauddetection.model.RetailerModel;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.MongoClient;

import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;


import java.util.List;

/**
 * MongoTest
 */
public class GetHistoricalData {

    public static List<RetailerModel> historicalData() throws Exception {
        MongoClient mongo = new MongoClient("localhost", 27017);

        MongoCredential credential;
        credential = MongoCredential.createCredential("pdrojack", "retailers", "12345678".toCharArray());

        MongoDatabase database = mongo.getDatabase("retailers");
        MongoTemplate mongoTemplate = new MongoTemplate(mongo, "retailers");
        // Find
        MongoCollection<Document> collection = database.getCollection("retailer_data");
        Document query = new Document();



        FindIterable<Document> doc = collection.find();
        int i = 1;
       // FindIterable<Document> doc = collection.find(Filters.eq("retailer_id", 1));
        //List<RetailerModel> returnList = collection.find(query,RetailerModel.class).into(new ArrayList<RetailerModel>());
        List<RetailerModel> returnList = new ArrayList<RetailerModel>();
        
        Iterator it = doc.iterator();

        while(it.hasNext()){
            Document obj = (Document) it.next();
            RetailerModel retailer = mongoTemplate.getConverter().read(RetailerModel.class, obj);
            returnList.add(retailer);
        }
        
        //System.out.println(returnList.get(0).getDaily_avg());
        return returnList;

    }
}