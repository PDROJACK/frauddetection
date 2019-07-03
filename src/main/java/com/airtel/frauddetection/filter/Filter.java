package com.airtel.frauddetection.filter;

import com.airtel.frauddetection.filter.utils.FilterReader;

import com.airtel.frauddetection.model.ObjectType;
import com.airtel.frauddetection.model.ValueType;
import com.airtel.frauddetection.model.Pair;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filter {
    private static ObjectType[] filterArray = FilterReader.getFilteredArray();
    
    public static List<Map<String,Pair>> getFilteredData(List<Map<String, Object>> data) {
        int columns = data.get(1).size();
        List<Map<String, Pair>> dataList = new ArrayList<Map<String,Pair>>();
        for(Map<String, Object> dataMap : data) {
            Map<String,Pair> dataRow = new HashMap<String,Pair>(columns);
            for(ObjectType filterColumn: filterArray){
                if(dataMap.containsKey(filterColumn.getColumnName())){
                    String columnName = filterColumn.getColumnName();
                    String type = filterColumn.getType();
                    Object value = dataMap.get(columnName);
                     switch (type) {
                        case "string":
                            Pair<String,String> p1 = new ValueType<String,String>((String)value, type);
                            dataRow.put(columnName, p1);
                            break;
                        case "long":
                            Pair<Long,String> p2 = new ValueType<Long,String>((Long)value, type);
                            dataRow.put(columnName,p2);
                            break;
                        case "int":
                            Pair<Integer,String> p3 = new ValueType<Integer,String>((Integer)value, type);
                            dataRow.put(columnName,p3); 
                            break;
                        case "date":
                            Pair<Date,String> p4 = new ValueType<Date,String>((Date)value, type);
                            dataRow.put(columnName,p4);
                            break;
                        case "boolean":
                            Pair<Boolean,String> p5 = new ValueType<Boolean,String>((Boolean)value, type);
                            dataRow.put(columnName,p5);
                            break;
                        default:
                            Pair<Object,String> p6 = new ValueType<Object,String>(value, type);
                            dataRow.put(columnName,p6);
                            break;
                    }           
            }
            dataList.add(dataRow);
            }
        }
        return dataList;
    } 
}