package com.bridglabz.repository;

import com.bridglabz.model.QuantityMeasurementEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class QuantityMeasurementDatabaseRepositoryTest {

    private QuantityMeasurementDatabaseRepository repository;

    @BeforeAll
    static void setupEnv() {
        System.setProperty("env", "test");
    }

    @BeforeEach
    void setUp() {
        repository = new QuantityMeasurementDatabaseRepository();
        
        // Let's clear the table before each test simply by executing a statement on the connection pool
        try (java.sql.Connection conn = com.bridglabz.util.ConnectionPool.getConnection();
             java.sql.PreparedStatement stmt = conn.prepareStatement("DELETE FROM measurements")) {
            stmt.executeUpdate();
            com.bridglabz.util.ConnectionPool.releaseConnection(conn);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testSaveAndGetAllMeasurements() {
        QuantityMeasurementEntity entity1 = new QuantityMeasurementEntity();
        entity1.setValue(10.0);
        entity1.setUnit("FEET");
        entity1.setType("LENGTH");

        QuantityMeasurementEntity entity2 = new QuantityMeasurementEntity();
        entity2.setValue(5.0);
        entity2.setUnit("FEET");
        entity2.setType("LENGTH");

        repository.save(entity1);
        repository.save(entity2);

        List<QuantityMeasurementEntity> measurements = repository.getAllMeasurements();

        assertNotNull(measurements);
        assertEquals(2, measurements.size());
        assertEquals(10.0, measurements.get(0).getValue());
        assertEquals("FEET", measurements.get(0).getUnit());
    }
}
