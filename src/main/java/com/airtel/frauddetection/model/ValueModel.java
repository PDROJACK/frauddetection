package com.airtel.frauddetection.model;


import com.airtel.frauddetection.model.DataPojo;

/**
 * OrderedPair
 */
public class ValueModel<V> implements DataPojo<V> {
    
    private V value;

    public ValueModel(V value) {
        this.value = value;
        
    }

    @Override
    public V getValue() {
        return value;
    }
    
}