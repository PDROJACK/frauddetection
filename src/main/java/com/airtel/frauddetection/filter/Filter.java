package com.airtel.frauddetection.filter;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.*;
import org.springframework.util.ResourceUtils;
import com.airtel.frauddetection.filter.utils.*;

public class Filter {
    static List<String> filterArray = DataReader.getFilteredArray();
    
    public static List<Map<String, Object>> getFilteredData(List<Map<String, Object>> data) {
        int columns = data.get(1).size();
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        for(int j=0;j<data.size();j++){
            Map<String, Object> dataRow = new HashMap<String, Object>(columns);
            for(String filterColumn: filterArray){
                if(data.get(j).containsKey(filterColumn)){
                    dataRow.put(filterColumn, data.get(j).get(filterColumn));
                }
            }
            dataList.add(dataRow);
        }
        return dataList;
    } 
}