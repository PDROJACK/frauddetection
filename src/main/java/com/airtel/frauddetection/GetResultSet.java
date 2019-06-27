package com.airtel.frauddetection;

import com.airtel.frauddetection.filter.Filter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetResultSet {

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
        List<Map<String, Object>> unfilteredData = new ArrayList<Map<String, Object>>(); 
        try {
            connection = getConnection();
            String query = "SELECT * from transactions";
            statement = connection.createStatement();
            response = statement.executeQuery(query);
            ResultSetMetaData metaData = response.getMetaData();
            int columns = metaData.getColumnCount();
            while(response.next()){
                Map<String, Object> row = new HashMap<String, Object>(columns);
                //Looping over the items
                for(int i = 1; i <= columns; ++i){
                    row.put(metaData.getColumnLabel(i),response.getObject(i));
                }
                unfilteredData.add(row);
            }
            System.out.println(Filter.getFilteredData(unfilteredData));
        } catch (Exception e) {
                    e.printStackTrace();
                    System.exit(1);
        }
    }
}