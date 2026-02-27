package com.bridglabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testConversion_FeetToInches() {

        double result =
                QuantityMeasurementApp.Length.convert(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(12.0, result, 1e-6);
    }

    @Test
    void testConversion_YardsToFeet() {

        double result =
                QuantityMeasurementApp.Length.convert(
                        3.0,
                        QuantityMeasurementApp.LengthUnit.YARDS,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(9.0, result, 1e-6);
    }

    @Test
    void testConversion_CentimetersToInches() {

        double result =
                QuantityMeasurementApp.Length.convert(
                        2.54,
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS,
                        QuantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(1.0, result, 1e-6);
    }

    @Test
    void testConversion_ZeroValue() {

        double result =
                QuantityMeasurementApp.Length.convert(
                        0.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(0.0, result, 1e-6);
    }

    @Test
    void testConversion_NegativeValue() {

        double result =
                QuantityMeasurementApp.Length.convert(
                        -1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES);

        assertEquals(-12.0, result, 1e-6);
    }

    @Test
    void testConversion_RoundTrip() {

        double original = 5.0;

        double inches =
                QuantityMeasurementApp.Length.convert(
                        original,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES);

        double feet =
                QuantityMeasurementApp.Length.convert(
                        inches,
                        QuantityMeasurementApp.LengthUnit.INCHES,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertEquals(original, feet, 1e-6);
    }

    @Test
    void testConversion_InvalidValue() {

        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.Length.convert(
                        Double.NaN,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES));
    }

    @Test
    void testConversion_NullUnit() {

        assertThrows(IllegalArgumentException.class,
                () -> QuantityMeasurementApp.Length.convert(
                        1.0,
                        null,
                        QuantityMeasurementApp.LengthUnit.FEET));
    }
}