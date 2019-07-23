package com.airtel.frauddetection.model;

/**
 * ComputedPojo
 */
public class ComputedPojo {

    private int id;
    private int value;

    public ComputedPojo(int id,int value){
        this.id = id;
        this.value = value;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @param value the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }
}