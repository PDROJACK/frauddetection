package com.airtel.frauddetection;

import java.util.Iterator;
import java.util.List;


import org.hibernate.Filter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import com.airtel.frauddetection.DataFilter;

public class Driver {

    public static void main(String[] args){
        List<DataFilterPojo> li;
        DataFilter u = new DataFilter();
        DataFilterPojo p = new DataFilterPojo();
        li = u.getDataFilter(100);
    
        for(Iterator<DataFilterPojo> iter = li.iterator(); iter.hasNext();)
        {
            p = (DataFilterPojo) iter.next();
            System.out.println("amount: "+ p.getAmount());
            System.out.println("customer_no: "+ p.getCustomer_no());
            System.out.println("txnId: "+ p.getTxnId());
            System.out.println("user_type: "+ p.getUser_type());
            System.out.println("retailer_id: "+ p.getRetailer_id());
            System.out.println("----------");
        }

    }
}