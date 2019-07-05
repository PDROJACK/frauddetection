package com.airtel.frauddetection.services;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.airtel.frauddetection.filter.utils.FilterReader;
import com.airtel.frauddetection.model.DataPojo;

import org.codehaus.janino.ExpressionEvaluator;
import org.codehaus.janino.Parser.ParseException;
import org.apache.commons.text.StringSubstitutor;
import org.codehaus.janino.CompileException;
import org.codehaus.janino.Scanner.ScanException;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Computation
 */

public class Computation {
    static private String[] expression = FilterReader.getExpression();
    
    public static void expressionEvaluator(List<Map<String, DataPojo<?>>> filteredData, String label)
    throws CompileException, ParseException, ScanException, InvocationTargetException {
        
        ExpressionEvaluator ee = new ExpressionEvaluator();
        Class cls = filteredData.get(1).get(label).getValue().getClass();
        Map labelMap = new HashMap();
        labelMap.put("label",label);
        StringSubstitutor sub = new StringSubstitutor(labelMap);
        
        String templateString = expression[0];
        String expression = sub.replace(templateString);
        
        ee.setParameters(new String[]{label}, new Class[]{cls});
                
        ee.setExpressionType(cls);
                
        ee.cook(expression);
                
        for(Map<String,DataPojo<?>> data : filteredData ){
                Object value = data.get(label).getValue();
                System.out.println(ee.evaluate(new Object[]{ value }));
            }
        }
    }






