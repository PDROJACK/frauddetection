package com.airtel.frauddetection.filter.utils;

import java.io.File;
import java.io.FileReader;

import com.airtel.frauddetection.model.ObjectType;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.springframework.util.ResourceUtils;


public class FilterReader {
    static ObjectType[] objects;
    public static ObjectType[] getFilteredArray(){
        try {
            Gson gson = new Gson();
            File file = ResourceUtils.getFile("classpath:static/filter.json");
            JsonReader reader = new JsonReader(new FileReader(file));
            objects = gson.fromJson(reader, ObjectType[].class);
            return objects;
        } catch (Exception e) {
            //TODO: handle exception
        }
        return objects;
    }
}
