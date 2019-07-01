package com.airtel.FraudDetectionSystem.Model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name="txn")
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
    
    @Column(name = "timestamp")
    private Timestamp timestamp;

	public int getTxnId() {
		return txnId;
	}

	public void setTxnId(int txnId) {
		this.txnId = txnId;
	}

	public long getCustomer_no() {
		return customer_no;
	}

	public void setCustomer_no(long customer_no) {
		this.customer_no = customer_no;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getRetailer_detail() {
		return retailer_detail;
	}

	public void setRetailer_detail(String retailer_detail) {
		this.retailer_detail = retailer_detail;
	}

	public int getMpin() {
		return mpin;
	}

	public void setMpin(int mpin) {
		this.mpin = mpin;
	}

	public String getRecharge_type() {
		return recharge_type;
	}

	public void setRecharge_type(String recharge_type) {
		this.recharge_type = recharge_type;
	}

	public int getRetailer_id() {
		return retailer_id;
	}

	public void setRetailer_id(int retailer_id) {
		this.retailer_id = retailer_id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "DataModel [txnId=" + txnId + ", customer_no=" + customer_no + ", amount=" + amount + ", user_type="
				+ user_type + ", retailer_detail=" + retailer_detail + ", mpin=" + mpin + ", recharge_type="
				+ recharge_type + ", retailer_id=" + retailer_id + ", timestamp=" + timestamp + "]";
	}

    

}
