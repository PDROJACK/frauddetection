package com.airtel.frauddetection;

import com.airtel.frauddetection.filter.Filter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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


import org.json.simple.JSONArray;
import org.json.simple.parser.*;

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
        List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
        try {
            conn = getConnection();
            System.out.println("Conn=" + conn);
            String query = "SELECT * from transactions";
            stmt = conn.createStatement();
            res = stmt.executeQuery(query);
            try {
                rows = Filter.getFilteredData(res);
                System.out.println(rows);
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}

//     private static List<Map<String, Object>> resultSetToList(ResultSet rs) throws SQLException {
//         ResultSetMetaData md = rs.getMetaData();
//         int columns = md.getColumnCount();
//         List<Map<String, Object>> rows = new ArrayList<Map<String, Object>>();
//         JSONArray json = new JSONArray();
//         List<String> list=new ArrayList<String>();
//         Object obj;
//         try {
//             obj = new JSONParser().parse(new FileReader(
//                     "G:\\Eclipse\\frauddetection\\src\\main\\java\\com\\airtel\\frauddetection\\filter\\utils\\filter.json"));
//             json = (JSONArray) obj;
//             list = json;
//         } catch (ParseException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         } catch (FileNotFoundException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         } catch (IOException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         }
//         while (rs.next()){
//             Map<String, Object> row = new HashMap<String, Object>(columns);
//             //Looping over the items
//             for(int i = 1; i <= columns; ++i){
//                 for(String o: list){
//                    if(md.getColumnLabel(i).equals(o)){
//                        row.put(md.getColumnLabel(i),rs.getObject(i));
//                    }
//                     // System.out.println(md.getColumnName(i));
//                    // System.out.println((String)json.get(j));
//                 }
//             } 
//             //Map<String, Object> result = row.entrySet().stream().filter(map->map.getKey().equals("customer_no")).collect(Collectors.toMap(map->map.getKey(),map->map.getValue()));
//             rows.add(row);
//         }
//         return rows;
//     } 
// }
