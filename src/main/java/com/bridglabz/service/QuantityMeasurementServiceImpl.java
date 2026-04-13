package com.bridglabz.service;

import com.bridglabz.dto.QuantityDTO;
import com.bridglabz.model.QuantityMeasurementEntity;
import com.bridglabz.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {
    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    private void saveHistory(QuantityDTO input, String operation, double result) {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setValue(result);
        entity.setUnit(input.getUnit()); // Simplified for saving
        entity.setType(input.getType());
        repository.save(entity);
    }

    @Override
    public double performAddition(QuantityDTO q1, QuantityDTO q2) {
        double result = q1.getValue() + q2.getValue(); // Mock domain logic
        saveHistory(q1, "ADD", result);
        return result;
    }

    @Override
    public double performSubtraction(QuantityDTO q1, QuantityDTO q2) {
        double result = q1.getValue() - q2.getValue(); // Mock domain logic
        saveHistory(q1, "SUBTRACT", result);
        return result;
    }

    @Override
    public double performDivision(QuantityDTO q1, QuantityDTO q2) {
        double result = q1.getValue() / q2.getValue(); // Mock domain logic
        saveHistory(q1, "DIVIDE", result);
        return result;
    }

    @Override
    public boolean performComparison(QuantityDTO q1, QuantityDTO q2) {
        boolean result = q1.getValue() == q2.getValue(); // Mock domain logic
        saveHistory(q1, "COMPARE", result ? 1.0 : 0.0);
        return result;
    }

    @Override
    public double performConversion(QuantityDTO q1, String targetUnit) {
        double result = q1.getValue() * 12; // Mock domain logic
        saveHistory(q1, "CONVERT", result);
        return result;
    }
}
