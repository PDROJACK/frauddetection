package com.airtel.frauddetection.filter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.parser.*;

public class Filter {
    public static void main(String[] args) throws IOException {
    }
    

    public static List<Map<String, Object>> getFilteredData(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        JSONArray json = new JSONArray();
        List<String> list=new ArrayList<String>();
        Object obj;
        try {
            obj = new JSONParser().parse(new FileReader(
                "G:\\Eclipse\\frauddetection\\src\\main\\java\\com\\airtel\\frauddetection\\filter\\utils\\filter.json"));
            json = (JSONArray) obj;  
            list = json;
        } catch (ParseException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        while (rs.next()){
            Map<String, Object> row = new HashMap<String, Object>(columns);
            //Looping over the items
            for(int i = 1; i <= columns; ++i){
                for(String o: list){
                   if(md.getColumnLabel(i).equals(o)){
                       row.put(md.getColumnLabel(i),rs.getObject(i));
                    }
                   // System.out.println((String)json.get(j));
                }
            } 
            //Map<String, Object> result = row.entrySet().stream().filter(map->map.getKey().equals("customer_no")).collect(Collectors.toMap(map->map.getKey(),map->map.getValue()));
            rows.add(row);
        }
        return rows;
    } 

}