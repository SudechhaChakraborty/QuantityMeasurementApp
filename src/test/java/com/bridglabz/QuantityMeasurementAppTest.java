package com.bridglabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    @Test
    void testEquality_YardToFeet_EquivalentValue() {

        QuantityMeasurementApp.Length yard =
                new QuantityMeasurementApp.Length(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS);

        QuantityMeasurementApp.Length feet =
                new QuantityMeasurementApp.Length(
                        3.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        assertTrue(yard.equals(feet));
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {

        QuantityMeasurementApp.Length yard =
                new QuantityMeasurementApp.Length(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS);

        QuantityMeasurementApp.Length inches =
                new QuantityMeasurementApp.Length(
                        36.0,
                        QuantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(yard.equals(inches));
    }

    @Test
    void testEquality_CentimeterToInches_EquivalentValue() {

        QuantityMeasurementApp.Length cm =
                new QuantityMeasurementApp.Length(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS);

        QuantityMeasurementApp.Length inches =
                new QuantityMeasurementApp.Length(
                        0.393701,
                        QuantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(cm.equals(inches));
    }

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {

        QuantityMeasurementApp.Length yard =
                new QuantityMeasurementApp.Length(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS);

        QuantityMeasurementApp.Length feet =
                new QuantityMeasurementApp.Length(
                        3.0,
                        QuantityMeasurementApp.LengthUnit.FEET);

        QuantityMeasurementApp.Length inches =
                new QuantityMeasurementApp.Length(
                        36.0,
                        QuantityMeasurementApp.LengthUnit.INCHES);

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }
}