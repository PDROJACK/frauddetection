package com.airtel.frauddetection;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

import com.airtel.frauddetection.DataModel;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.Filter;
import org.hibernate.cfg.Configuration;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;


import com.airtel.frauddetection.DataFilterPojo;

public class DataFilter {
    private static SessionFactory sessionFactory;

    private Session getSession(){
        Session s = null;
        try{
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
            s = sessionFactory.openSession();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return s;
    }

    @SuppressWarnings("unchecked")
    public List<DataFilterPojo> getDataFilter(int amount){
        List<DataFilterPojo> list = null;
        try{
            Session session = getSession();
            Filter filter = session.enableFilter("amountFilter");
            filter.setParameter("filterAmount",amount);
            list =  session.createCriteria(DataFilterPojo.class).list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    
}