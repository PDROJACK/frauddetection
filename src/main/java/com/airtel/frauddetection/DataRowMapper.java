package com.airtel.frauddetection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class DataRowMapper implements RowMapper<DataModel> {
    @Override
    public DataModel mapRow(ResultSet row, int rowNum) throws SQLException {
        DataModel dataModel = new DataModel();
        dataModel.setAmount(row.getInt("amount"));
        dataModel.setCustomer_no(row.getInt("customer_no"));
        dataModel.setMpin(row.getInt("mpin"));
        dataModel.setRecharge_type(row.getString("recharge_type"));
        dataModel.setUser_Type(row.getString("user_type"));
        dataModel.setRetailer_detail(row.getString("retailer_detail"));
        return dataModel;
    }
}