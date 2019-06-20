package com.airtel.frauddetection;

import java.util.List;
import javax.transaction.Transactional;


//import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.airtel.frauddetection.IDataDAO;
import com.airtel.frauddetection.DataRowMapper;
import com.airtel.frauddetection.DataModel;

@Transactional
@Repository
public class DataDAO implements IDataDAO {  
    @Autowired
    private JdbcTemplate jdbcTemplate;
    // @Override
    // pubic DataModel getDataByTxnId(int txnId) {
    //     String sql = "SELECT txnId, customer_no, amount, recharge_type, retailer_detail FROM transactions WHERE txnId = ?";
    //     RowMapper<DataModel> rowMapper = new BeanPropertyRowMapper<DataModel>(DataModel.class);
    //     DataModel data = jdbcTemplate.queryForObject(sql, rowMapper, txnId);
    //     return data;
    // }

    @Override
    public List<DataModel> getAllData(){
        String sql = "SELECT txnId, customer_no, amount, recharge_type, retailer_detail FROM transactions";
        RowMapper<DataModel> rowMapper = new DataRowMapper();
        return this.jdbcTemplate.query(sql, rowMapper);
    }

    @Override
    public void addData(DataModel data){
        //Add data
        String sql = "INSERT INTO transactions (customer_no, amount,user_type,retailer_detail, mpin ,recharge_type) VALUES (?,?,?,?,?) ";
        jdbcTemplate.update(sql, data.getCustomer_no(), data.getAmount(),data.getUser_type(),data.getRetailer_detail(), data.getMpin(), data.getRecharge_type());
        
    }   
    
}