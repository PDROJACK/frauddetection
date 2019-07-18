package com.airtel.frauddetection.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.airtel.frauddetection.model.DataModel;

@RestController
public class DataController {
    public static Map<Integer, DataModel> dataRepo = new HashMap<>();
    @RequestMapping(value="/transactions", method = RequestMethod.POST)
    public ResponseEntity<Object> createObject(@RequestBody DataModel data) {
        
        dataRepo.put(data.getTxnId(), data);
        return new ResponseEntity<>("Transaction added to database", HttpStatus.CREATED);
    }

    


}