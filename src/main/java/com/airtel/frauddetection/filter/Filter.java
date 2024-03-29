package com.airtel.frauddetection.filter;

import com.airtel.frauddetection.filter.utils.FilterReader;

import com.airtel.frauddetection.model.ObjectType;
import com.airtel.frauddetection.model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filter {
    private static ObjectType[] filterArray = FilterReader.getFilteredArray();
    public static Map<String,DataPojo<?>> getFilteredData(Map<String, Object> data) {
        int columns = data.size();
            Map<String,DataPojo<?>> dataRow = new HashMap<String,DataPojo<?>>(columns);
            for(ObjectType filterColumn: filterArray){
                String columnName = filterColumn.getColumnName();
                String type = filterColumn.getType();
                DataPojo<?> p1 ;
                Object value = data.get(columnName);
                if(type == "string"){
                    p1 = new ValueModel<String>((String)value);
                    dataRow.put(columnName, p1);
                } else if (type == "int") {
                    p1 = new ValueModel<Integer>((Integer)value);
                    dataRow.put(columnName,p1);
                } else if (type == "long") {
                    p1 = new ValueModel<Long>((Long)value);
                    dataRow.put(columnName,p1);
                } else if (type == "date") {
                    p1 = new ValueModel<Date>((Date)value);
                    dataRow.put(columnName,p1);
                } else if (type == "boolean") {
                    p1 = new ValueModel<Boolean>((Boolean)value);
                    dataRow.put(columnName,p1);
                } else {
                    p1 = new ValueModel<Object>(value);
                    dataRow.put(columnName,p1);
                }
            }
            return dataRow;
    } 
}