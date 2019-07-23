package com.airtel.frauddetection.services;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.airtel.frauddetection.filter.utils.FilterReader;
import com.airtel.frauddetection.model.DataPojo;
import com.airtel.frauddetection.model.RetailerModel;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import org.codehaus.janino.CompileException;
import org.codehaus.janino.ExpressionEvaluator;
import org.codehaus.janino.Parser.ParseException;
import org.codehaus.janino.Scanner.ScanException;
import org.apache.log4j.Logger;
import org.bson.Document;
import com.airtel.frauddetection.services.HistoricalData;
import java.lang.Math;

/**
 * Computation
 */
public class Computation {
    static private String[] expression = FilterReader.getExpression("classpath:static/expression.json");
    static private String[] thresholds = FilterReader.getExpression("classpath:static/thresholds.json");
    static private MongoCollection<Document> collection = HistoricalData.getCollection();
    private static final Logger LOGGER = Logger.getLogger(Computation.class);

    public static void thresholdCheck(Map<String, DataPojo<?>> filteredData)
    throws Exception {
        Integer id = (Integer) filteredData.get("retailer_id").getValue();
        Integer value = (Integer) filteredData.get("amount").getValue();
        Integer txnId = (Integer) filteredData.get("txnId").getValue();
        RetailerModel retailer = HistoricalData.getHistoricalData(id, "retailer_id");
        Double ema = retailer.getEma();
        Double ems = retailer.getEms();
        ExpressionEvaluator ee = new ExpressionEvaluator();
        ee.setParameters(new String[]{"amount","ema","ems"}, new Class[]{Integer.class,Double.class,Double.class});
        ee.setExpressionType(Boolean.class);
        ee.cook(thresholds[0]);
        if((boolean) ee.evaluate(new Object[] { value, ema, ems }) == true) {
            System.out.println("-----W---A---R---N---I---N---G------");
            LOGGER.warn("Anomaly detected | Associated RetailerId:" + id + " | TxnId:" + txnId );
        } 
        historicalData(retailer,value,id);
    }

    public static void updateEma(int amount, Double ema, int id)
            throws InvocationTargetException, CompileException, ParseException, ScanException {
            ExpressionEvaluator ee = new ExpressionEvaluator();
            ee.setParameters(new String[]{"amount","ema"}, new Class[]{Integer.class,Double.class});
            ee.setExpressionType(Double.class);
            ee.cook(expression[0]);
            Double newEma = (Double) ee.evaluate(new Object[]{ amount, ema });
            collection.updateOne(Filters.eq("retailer_id",id), Updates.set("ema",newEma));
        }
        
    private static void updateEms(Integer amount, Double ems, Double ema,int id) throws InvocationTargetException, CompileException, ParseException, ScanException {
            ExpressionEvaluator ee = new ExpressionEvaluator();
            ee.setParameters(new String[]{"amount","ema","ems"}, new Class[]{Integer.class,Double.class,Double.class});
            ee.setExpressionType(Double.class);
            ee.cook(expression[1]);
            Double newEms = (Double) ee.evaluate(new Object[]{ amount, ema, ems });
            collection.updateOne(Filters.eq("retailer_id",id), Updates.set("ems",newEms));
        }

    public static void historicalData(RetailerModel retailer,int value,int id)
    throws Exception {
        if(retailer != null) {
            Double ema = (double) retailer.getEma();
            Double ems = (double) retailer.getEms();
            updateEma(value,ema,id);
            updateEms(value,ems,ema,id);
        } else {
            Document document = new Document()
            .append("retailer_id", id)
            .append("ema", 0)
            .append("ems",0);
            collection.insertOne(document);
            updateEma(value, (double) 0, id);
        }

    }

}
    






