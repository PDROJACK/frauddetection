package com.airtel.frauddetection.model;


import com.airtel.frauddetection.model.Pair;

/**
 * OrderedPair
 */
public class ValueType<V,T> implements Pair<V,T> {
    
    private V value;
    private T type;

    public ValueType(V value, T type) {
        this.value = value;
        this.type = type;
    }

    @Override
    public V getValue() {
        return value;
    }

    @Override
    public T getType() {
        return type;
    }
    
}