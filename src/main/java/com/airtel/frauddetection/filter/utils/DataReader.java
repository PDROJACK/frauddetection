package com.airtel.frauddetection.filter.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.util.ResourceUtils;

public class DataReader {
    public static List<String> getFilteredArray(){
        List<String> filterArray = new ArrayList<String>();
        JSONArray json = new JSONArray();
        try {
            File file = ResourceUtils.getFile("classpath:static/filter.json");
            json = (JSONArray) new JSONParser().parse(new FileReader (file));
            filterArray = json;
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return filterArray;
    }
}
