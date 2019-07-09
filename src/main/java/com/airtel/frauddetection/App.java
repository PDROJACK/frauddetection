package com.airtel.frauddetection;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.airtel.frauddetection.model.DataPojo;
import com.airtel.frauddetection.model.RetailerModel;
import com.airtel.frauddetection.services.Computation;

import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
	public static void main(String[] args)
			throws Exception {
		//SpringApplication.run(App.class, args);
		List<Map<String, DataPojo<?>>> filteredData = new ArrayList<Map<String, DataPojo<?>>>();
		List<RetailerModel> historicalData = new ArrayList<RetailerModel>();
		filteredData = FilteredData.filteredData();
		historicalData = GetHistoricalData.historicalData();
		Computation.expressionEvaluator(filteredData, historicalData);
	}
}
