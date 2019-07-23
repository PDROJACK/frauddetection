package com.airtel.frauddetection;

import com.airtel.frauddetection.filter.Filter;
import com.airtel.frauddetection.model.DataPojo;
import com.airtel.frauddetection.services.Computation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

public class FilteredData {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/transactions?useSSL=false";
        String username = "root";
        String password = "PDrojack@455";
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
    public static void main(String[] args) {
        Connection connection = null;
        Statement statement= null;
        ResultSet response = null; 
        try {
            connection = getConnection();
            String query = "SELECT * from transactions";
            statement = connection.createStatement();
            response = statement.executeQuery(query);
            ResultSetMetaData metaData = response.getMetaData();
            int columns = metaData.getColumnCount();
            while(response.next()){
                Map<String, Object> row = new HashMap<String, Object>(columns);
                for(int i = 1; i <= columns; ++i){
                    row.put(metaData.getColumnLabel(i),response.getObject(i));
                }
                Map<String,DataPojo<?>> filteredData = Filter.getFilteredData(row);
                Computation.thresholdCheck(filteredData);
            }
        } catch (Exception e) {
                e.printStackTrace();
                System.exit(1);
        }
    }
}