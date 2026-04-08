package com.bridglabz.service;

import com.bridglabz.dto.QuantityDTO;
import com.bridglabz.exception.QuantityMeasurementException;
import com.bridglabz.model.QuantityMeasurementEntity;
import com.bridglabz.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
    }

    @Override
    public QuantityDTO convert(QuantityDTO quantityDTO, String targetUnit) {

        QuantityDTO result =
                new QuantityDTO(
                        quantityDTO.getValue(),
                        targetUnit,
                        quantityDTO.getMeasurementType());

        repository.save(
                new QuantityMeasurementEntity(
                        "CONVERT",
                        null,
                        null,
                        result));

        return result;
    }

    @Override
    public boolean compare(QuantityDTO first, QuantityDTO second) {

        boolean result =
                first.getValue() == second.getValue();

        repository.save(
                new QuantityMeasurementEntity(
                        "COMPARE",
                        null,
                        null,
                        result));

        return result;
    }

    @Override
    public QuantityDTO add(QuantityDTO first, QuantityDTO second) {

        double resultValue =
                first.getValue() + second.getValue();

        QuantityDTO result =
                new QuantityDTO(
                        resultValue,
                        first.getUnit(),
                        first.getMeasurementType());

        repository.save(
                new QuantityMeasurementEntity(
                        "ADD",
                        null,
                        null,
                        result));

        return result;
    }

    @Override
    public QuantityDTO subtract(QuantityDTO first, QuantityDTO second) {

        double resultValue =
                first.getValue() - second.getValue();

        QuantityDTO result =
                new QuantityDTO(
                        resultValue,
                        first.getUnit(),
                        first.getMeasurementType());

        repository.save(
                new QuantityMeasurementEntity(
                        "SUBTRACT",
                        null,
                        null,
                        result));

        return result;
    }

    @Override
    public double divide(QuantityDTO first, QuantityDTO second) {

        if(second.getValue()==0){
            throw new QuantityMeasurementException("Divide by zero");
        }

        double result =
                first.getValue()/second.getValue();

        repository.save(
                new QuantityMeasurementEntity(
                        "DIVIDE",
                        null,
                        null,
                        result));

        return result;
    }
}