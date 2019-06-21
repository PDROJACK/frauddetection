package com.airtel.frauddetection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;



public class GetResultSet {
    public static Connection getConnection() throws Exception {
        String url = "jdbc:mysql://localhost:3306/transactions?useSSL=false";
        String username = "root";
        String password = "PDrojack@455";
        
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }


    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet res = null;

        try {
            conn = getConnection();
            System.out.println("Conn=" + conn);
            String query = "SELECT * from transactions";
            stmt = conn.createStatement();
            res = stmt.executeQuery(query);
            System.out.println(resultSetToList(res));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        } 
    }

    private static List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
        ResultSetMetaData md = rs.getMetaData();
        int columns = md.getColumnCount();
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        while (rs.next()){
            Map<String, Object> row = new HashMap<String, Object>(columns);
            for(int i = 1; i <= columns; ++i){
                row.put(md.getColumnName(i), rs.getObject(i));
            }
            rows.add(row);
            //Map<String, Object> result = row.entrySet().stream().filter(map->map.getKey().equals("customer_no")).collect(Collectors.toMap(map->map.getKey(),map->map.getValue()));
        }
        return rows;
    } 
}
