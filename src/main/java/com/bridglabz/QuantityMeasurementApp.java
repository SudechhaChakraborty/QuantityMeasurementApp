package com.bridglabz;

import com.bridglabz.controller.QuantityMeasurementController;
import com.bridglabz.dto.QuantityDTO;
import com.bridglabz.factory.RepositoryFactory;
import com.bridglabz.repository.IQuantityMeasurementRepository;
import com.bridglabz.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        IQuantityMeasurementRepository repository =
                RepositoryFactory.getRepository();

        QuantityMeasurementServiceImpl service =
                new QuantityMeasurementServiceImpl(repository);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        QuantityDTO q1 =
                new QuantityDTO(10,"FEET","LENGTH");

        QuantityDTO q2 =
                new QuantityDTO(5,"FEET","LENGTH");

        controller.performAddition(q1,q2);
        controller.performComparison(q1,q2);
        controller.performSubtraction(q1,q2);
        controller.performDivision(q1,q2);
        controller.performConversion(q1,"INCH");
    }
}