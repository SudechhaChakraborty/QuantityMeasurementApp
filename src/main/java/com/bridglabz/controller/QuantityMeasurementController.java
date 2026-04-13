package com.bridglabz.controller;

import com.bridglabz.dto.QuantityDTO;
import com.bridglabz.service.IQuantityMeasurementService;

public class QuantityMeasurementController {
    private IQuantityMeasurementService service;

    public QuantityMeasurementController(IQuantityMeasurementService service) {
        this.service = service;
    }

    public void performAddition(QuantityDTO q1, QuantityDTO q2) {
        System.out.println("Addition: " + service.performAddition(q1, q2));
    }

    public void performSubtraction(QuantityDTO q1, QuantityDTO q2) {
        System.out.println("Subtraction: " + service.performSubtraction(q1, q2));
    }

    public void performDivision(QuantityDTO q1, QuantityDTO q2) {
        System.out.println("Division: " + service.performDivision(q1, q2));
    }

    public void performComparison(QuantityDTO q1, QuantityDTO q2) {
        System.out.println("Comparison: " + service.performComparison(q1, q2));
    }

    public void performConversion(QuantityDTO q1, String targetUnit) {
        System.out.println("Conversion: " + service.performConversion(q1, targetUnit));
    }
}
