package com.airtel.frauddetection.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.airtel.frauddetection.filter.utils.FilterReader;
import com.airtel.frauddetection.model.DataPojo;
import com.airtel.frauddetection.model.RetailerModel;

import org.codehaus.janino.ExpressionEvaluator;
import org.codehaus.janino.Parser.ParseException;
import org.codehaus.janino.CompileException;
import org.codehaus.janino.Scanner.ScanException;

/**
 * Computation
 */
public class Computation {
    static private String[] expression = FilterReader.getExpression();
    
    public static void expressionEvaluator(List<Map<String, DataPojo<?>>> filteredData, List<RetailerModel> historicalData)
    throws CompileException, ParseException, ScanException, InvocationTargetException {
        
        Class cls = filteredData.get(1).get("amount").getValue().getClass();
        ExpressionEvaluator ee = new ExpressionEvaluator();
        ee.setParameters(new String[]{"amount","daily_avg"}, new Class[]{cls,Integer.class});
        ee.setExpressionType(Integer.class);
        ee.cook(expression[0]);
                
        for(Map<String,DataPojo<?>> data : filteredData ){
            for(RetailerModel retailer : historicalData) {
                if(retailer.getRetailer_id()==(Integer) data.get("retailer_id").getValue()){
                    Object value = (Integer) data.get("amount").getValue();
                    Integer daily_avg = (Integer) retailer.getDaily_avg();
                    System.out.println(ee.evaluate(new Object[]{ value, daily_avg }));
                    }
                }
            }
        }
    }






