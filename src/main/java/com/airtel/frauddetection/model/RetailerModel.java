package com.airtel.frauddetection.model;

/**
 * RetailerModel
 */

public class RetailerModel {
    private int retailer_id;
    private double ema;
    private double ems;
    
    
    public RetailerModel(
        int retailer_id,
        double ema,
        double ems
    ){
        this.retailer_id = retailer_id;
        this.ema = ema;
        this.ems = ems;
    }
    /**
     * @return the ema
    */
    public double getEma() {
        return ema;
    }

    /**
     * @param ema the ema to set
    */
    public void setEma(double ema) {
        this.ema = ema;
    }

    /**
     * @return the retailer_id
    */
    public int getRetailer_id() {
        return retailer_id;
    }

    /**
     * @param retailer_id the retailer_id to set
    */
    public void setRetailer_id(int retailer_id) {
        this.retailer_id = retailer_id;
    }

    /**
     * @return the ems
    */
    public double getEms() {
        return ems;
    }

    /**
     * @param ems the ems to set
    */
    public void setEms(double ems) {
        this.ems = ems;
    }
}

