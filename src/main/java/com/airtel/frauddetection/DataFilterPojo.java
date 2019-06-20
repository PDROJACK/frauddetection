package com.airtel.frauddetection;

import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;

@Entity
@Table(name="transactions")
@FilterDef(name="amountFilter", parameters = {@ParamDef(name="filterAmount", type="java.lang.Integer") })
@Filter(name="amountFilter", condition = "amount> :filterAmount")
@EntityListeners(AuditingEntityListener.class)
public class DataFilterPojo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int txnId;

    @Column(name="retailer_id", nullable = false)
    private int retailer_id;

    @Column(name="amount", nullable = false)
    private int amount;

    @Column(name="customer_no", nullable = false)
    private long customer_no;

    @Column(name="user_type", nullable = false)
    private String user_type;

    public int getRetailer_id(){
        return retailer_id;
    }

    public void setRetailer_id(int retailer_id){
        this.retailer_id = retailer_id;
    }

    public int getTxnId() {
        return txnId;
    }

    public long getCustomer_no(){
        return customer_no;
    }

    public void setCustomer_no(long customer_no){
        this.customer_no = customer_no;
    }

    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }
    
    public String getUser_type(){
        return user_type;
    }

    public void setUser_Type(String user_type){
        this.user_type = user_type;
    }


}