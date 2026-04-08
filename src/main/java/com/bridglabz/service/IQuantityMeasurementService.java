package com.bridglabz.service;


import com.bridglabz.dto.QuantityDTO;

public interface IQuantityMeasurementService {

    QuantityDTO convert(QuantityDTO quantityDTO, String targetUnit);

    boolean compare(QuantityDTO first, QuantityDTO second);

    QuantityDTO add(QuantityDTO first, QuantityDTO second);

    QuantityDTO subtract(QuantityDTO first, QuantityDTO second);

    double divide(QuantityDTO first, QuantityDTO second);

}