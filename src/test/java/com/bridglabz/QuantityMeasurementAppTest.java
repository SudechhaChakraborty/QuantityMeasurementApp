package com.bridglabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.001;

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {

        var l1 = new QuantityMeasurementApp.Length(
                1.0,
                QuantityMeasurementApp.Length.LengthUnit.FEET
        );

        var l2 = new QuantityMeasurementApp.Length(
                12.0,
                QuantityMeasurementApp.Length.LengthUnit.INCHES
        );

        var result = l1.add(
                l2,
                QuantityMeasurementApp.Length.LengthUnit.FEET
        );

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {

        var l1 = new QuantityMeasurementApp.Length(
                1.0,
                QuantityMeasurementApp.Length.LengthUnit.FEET
        );

        var l2 = new QuantityMeasurementApp.Length(
                12.0,
                QuantityMeasurementApp.Length.LengthUnit.INCHES
        );

        var result = l1.add(
                l2,
                QuantityMeasurementApp.Length.LengthUnit.YARDS
        );

        assertEquals(0.667, result.getValue(), EPSILON);
    }

    @Test
    void testNullTargetUnit() {

        var l1 = new QuantityMeasurementApp.Length(
                1.0,
                QuantityMeasurementApp.Length.LengthUnit.FEET
        );

        var l2 = new QuantityMeasurementApp.Length(
                12.0,
                QuantityMeasurementApp.Length.LengthUnit.INCHES
        );

        assertThrows(IllegalArgumentException.class,
                () -> l1.add(l2, null));
    }
}