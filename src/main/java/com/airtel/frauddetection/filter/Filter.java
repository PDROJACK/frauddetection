package com.airtel.frauddetection.filter;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.*;
import org.springframework.util.ResourceUtils;

public class Filter {
    public static void main(String[] args) throws IOException {
    }

    public static List<Map<String, Object>> getFilteredData(List<Map<String, Object>> data) throws SQLException {
        JSONArray json = new JSONArray();
        List<String> filterArray = new ArrayList<String>();
        int columns = data.get(1).size();
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        try {
            File file = ResourceUtils.getFile("classpath:static/filter.json");
            json = (JSONArray) new JSONParser().parse(new FileReader (file));
            filterArray = json;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }

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