package com.airtel.frauddetection.services;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.airtel.frauddetection.filter.utils.FilterReader;
import com.airtel.frauddetection.model.DataPojo;
import com.airtel.frauddetection.model.RetailerModel;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import org.codehaus.janino.ExpressionEvaluator;
import org.codehaus.janino.Parser.ParseException;
import org.bson.Document;
import org.codehaus.janino.CompileException;
import org.codehaus.janino.Scanner.ScanException;
import com.airtel.frauddetection.GetHistoricalData;
/**
 * Computation
 */
public class Computation {
    static private String[] expression = FilterReader.getExpression();
    static private MongoCollection<Document> collection = GetHistoricalData.collections("pdrojack", "retailer_data", "retailers", "12345678");
    
    public static List<Map<Integer,Integer>> expressionEvaluator(List<Map<String, DataPojo<?>>> filteredData, List<RetailerModel> historicalData)
    throws CompileException, ParseException, ScanException, InvocationTargetException {
        
        List<Map<Integer,Integer>> newData = new ArrayList<Map<Integer,Integer>>();
        Class cls = filteredData.get(1).get("amount").getValue().getClass();

        ExpressionEvaluator ee = new ExpressionEvaluator();
        
        ee.setParameters(new String[]{"amount","daily_avg"}, new Class[]{cls,Integer.class});
        
        ee.setExpressionType(Integer.class);
        
        ee.cook(expression[0]);
                
        for(Map<String,DataPojo<?>> data : filteredData ){
            for(RetailerModel retailer : historicalData) {
                if(retailer.getRetailer_id()==(Integer) data.get("retailer_id").getValue()){
                    Map<Integer,Integer> newVal = new HashMap<Integer,Integer>();
                    Integer id = (Integer)data.get("retailer_id").getValue();
                    Object value = (Integer) data.get("amount").getValue();
                    Integer daily_avg = (Integer) retailer.getDaily_avg();
                    Integer result = (Integer)ee.evaluate(new Object[]{ value, daily_avg });
                    newVal.put(id, result);
                    collection.updateOne(Filters.eq("retailer_id",id), Updates.set("daily_avg",result));;
                    newData.add(newVal);
                    }
                }
            }
            return newData;
        }
    }






