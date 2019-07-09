package com.airtel.frauddetection;

import java.util.ArrayList;
import java.util.Iterator;

import com.airtel.frauddetection.model.RetailerModel;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


import org.bson.Document;
import org.springframework.data.mongodb.core.MongoTemplate;


import java.util.List;

/**
 * MongoTest
 */
public class GetHistoricalData {

    public static MongoCollection<Document> collections(String user,String collec_name, String dbname, String pwd){
        MongoClient mongo = new MongoClient("localhost", 27017);
        MongoCredential credential;
        credential = MongoCredential.createCredential(user, dbname, pwd.toCharArray());
        MongoDatabase database = mongo.getDatabase(dbname);
        MongoTemplate mongoTemplate = new MongoTemplate(mongo, dbname);
        // Find
        MongoCollection<Document> collection = database.getCollection(collec_name);
        return collection;      
    }

    public static MongoTemplate getMongoTemplate(String dbname) {
        MongoClient mongo = new MongoClient("localhost",27017);
        MongoDatabase database = mongo.getDatabase(dbname);
        MongoTemplate mongoTemplate = new MongoTemplate(mongo, dbname);
        return mongoTemplate;
    }

    public static List<RetailerModel> historicalData() throws Exception {
        MongoCollection<Document> collection = collections("pdrojack", "retailer_data", "retailers", "12345678");
        FindIterable<Document> doc = collection.find();
        int i = 1;
        List<RetailerModel> returnList = new ArrayList<RetailerModel>();
        Iterator it = doc.iterator();
        MongoTemplate mongoTemplate = getMongoTemplate("retailers");
        while(it.hasNext()){
            Document obj = (Document) it.next();
            RetailerModel retailer = mongoTemplate.getConverter().read(RetailerModel.class, obj);
            returnList.add(retailer);
        }
        //System.out.println(returnList.get(0).getDaily_avg());
        return returnList;

    }
}