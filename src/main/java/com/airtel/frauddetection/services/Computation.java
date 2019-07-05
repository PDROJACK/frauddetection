package com.airtel.frauddetection.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import com.airtel.frauddetection.filter.utils.FilterReader;
import com.airtel.frauddetection.model.DataPojo;

import org.codehaus.janino.ExpressionEvaluator;
import org.codehaus.janino.Parser.ParseException;
import org.codehaus.janino.CompileException;
import org.codehaus.janino.Scanner.ScanException;

/**
 * Computation
 */
public class Computation {
    static private String[] expression = FilterReader.getExpression();

    public static void expressionEvaluator(List<Map<String, DataPojo<?>>> filteredData, String label)
            throws CompileException, ParseException, ScanException, InvocationTargetException {
        
        ExpressionEvaluator ee = new ExpressionEvaluator();
        
        ee.setParameters(new String[]{label}, new Class[]{filteredData.get(1).get(label).getValue().getClass()});
        
        ee.setExpressionType(int.class);
        
        ee.cook(expression[0]);
        
        for(Map<String,DataPojo<?>> data : filteredData ){
            Object value = data.get(label).getValue();
            int response = (Integer) ee.evaluate(new Object[]{ value });
            System.out.println(response);
        }
    }
}