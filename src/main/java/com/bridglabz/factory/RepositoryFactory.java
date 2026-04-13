package com.bridglabz.factory;

import com.bridglabz.repository.IQuantityMeasurementRepository;
import com.bridglabz.repository.QuantityMeasurementCacheRepository;
import com.bridglabz.repository.QuantityMeasurementDatabaseRepository;
import com.bridglabz.util.ApplicationConfig;

public class RepositoryFactory {

    public static IQuantityMeasurementRepository getRepository() {
        String type = ApplicationConfig.get("repository.type");
        if ("database".equalsIgnoreCase(type)) {
            return new QuantityMeasurementDatabaseRepository();
        }
        return QuantityMeasurementCacheRepository.getInstance();
    }
}
