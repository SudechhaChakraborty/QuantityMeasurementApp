package com.bridglabz.service;

import com.bridglabz.dto.QuantityDTO;

public interface IQuantityMeasurementService {
    double performAddition(QuantityDTO q1, QuantityDTO q2);
    double performSubtraction(QuantityDTO q1, QuantityDTO q2);
    double performDivision(QuantityDTO q1, QuantityDTO q2);
    boolean performComparison(QuantityDTO q1, QuantityDTO q2);
    double performConversion(QuantityDTO q1, String targetUnit);
}
