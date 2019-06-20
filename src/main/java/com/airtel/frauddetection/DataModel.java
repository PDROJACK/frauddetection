package com.airtel.frauddetection;

import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name="transactions")
@EntityListeners(AuditingEntityListener.class)
public class DataModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int txnId;

    @Column(name="customer_no", nullable = false)
    private long customer_no;

    @Column(name="amount", nullable = false)
    private int amount;

    @Column(name="user_type", nullable = false)
    private String user_type;

    @Column(name="retailer_detail", nullable = false)
    private String retailer_detail;

    @Column(name="mpin", nullable = false)
    private int mpin;

    @Column(name = "recharge_type", nullable = false)
    private String recharge_type;

    @Column(name = "retailer_id")
    private int retailer_id;

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

    public String getRetailer_detail(){
        return retailer_detail;
    }

    public void setRetailer_detail(String retailer_detail){
        this.retailer_detail = retailer_detail;
    }

    public int getMpin(){
        return mpin;
    }

    public void setMpin(int mpin){
        this.mpin = mpin;
    }

    public String getRecharge_type(){
        return recharge_type;
    }

    public void setRecharge_type(String recharge_type){
        this.recharge_type = recharge_type;
    }

    @Override
    public String toString() {
            return "Transaction [retailerId=" + retailer_id + ", customer_no=" + customer_no + ", retailer_detail ="+ retailer_detail + "]";
    }

}