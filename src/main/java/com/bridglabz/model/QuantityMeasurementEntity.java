package com.bridglabz.model;

import java.io.Serializable;

public class QuantityMeasurementEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String operation;
    private QuantityModel<?> firstQuantity;
    private QuantityModel<?> secondQuantity;
    private Object result;
    private boolean error;
    private String errorMessage;

    public QuantityMeasurementEntity(String operation,
                                     QuantityModel<?> firstQuantity,
                                     QuantityModel<?> secondQuantity,
                                     Object result) {
        this.operation = operation;
        this.firstQuantity = firstQuantity;
        this.secondQuantity = secondQuantity;
        this.result = result;
        this.error = false;
    }

    public QuantityMeasurementEntity(String operation, String errorMessage) {
        this.operation = operation;
        this.errorMessage = errorMessage;
        this.error = true;
    }

    public String getOperation() {
        return operation;
    }

    public QuantityModel<?> getFirstQuantity() {
        return firstQuantity;
    }

    public QuantityModel<?> getSecondQuantity() {
        return secondQuantity;
    }

    public Object getResult() {
        return result;
    }

    public boolean hasError() {
        return error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String toString() {
        if(error){
            return "Operation : " + operation +
                    " Error : " + errorMessage;
        }

        return "Operation : " + operation +
                " First : " + firstQuantity +
                " Second : " + secondQuantity +
                " Result : " + result;
    }
}