package com.airtel.frauddetection.model;

/**
 * RetailerModel
 */

public class RetailerModel {

   
    private int retailer_id;
    private int daily_avg;
    private float one_month_avg;
    private float six_months_avg;  
    
    
    public RetailerModel(
        int retailer_id,
        int daily_avg,
        float one_month_avg,
        float six_months_avg
    ){
        this.retailer_id = retailer_id;
        this.daily_avg = daily_avg;
        this.one_month_avg = one_month_avg;
        this.six_months_avg = six_months_avg;
    }



    /**
     * @return the daily_avg
     */
    public int getDaily_avg() {
        return daily_avg;
    }
    /**
     * @param daily_avg the daily_avg to set
     */
    public void setDaily_avg(int daily_avg) {
        this.daily_avg = daily_avg;
    }
    /**
     * @return the one_month_avg
     */
    public float getOne_month_avg() {
        return one_month_avg;
    }
    /**
     * @param one_month_avg the one_month_avg to set
     */
    public void setOne_month_avg(float one_month_avg) {
        this.one_month_avg = one_month_avg;
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
     * @return the six_months_avg
     */
    public float getSix_months_avg() {
        return six_months_avg;
    }
    /**
     * @param six_months_avg the six_months_avg to set
     */
    public void setSix_months_avg(float six_months_avg) {
        this.six_months_avg = six_months_avg;
    }
}

