package com.bridglabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPS = 1e-6;

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        var l1 = new QuantityMeasurementApp.Length(1.0,
                QuantityMeasurementApp.Length.LengthUnit.FEET);
        var l2 = new QuantityMeasurementApp.Length(2.0,
                QuantityMeasurementApp.Length.LengthUnit.FEET);

        var result = l1.add(l2);

        assertEquals(3.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        var l1 = new QuantityMeasurementApp.Length(1.0,
                QuantityMeasurementApp.Length.LengthUnit.FEET);
        var l2 = new QuantityMeasurementApp.Length(12.0,
                QuantityMeasurementApp.Length.LengthUnit.INCHES);

        var result = l1.add(l2);

        assertEquals(2.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_Commutativity() {
        var l1 = new QuantityMeasurementApp.Length(1.0,
                QuantityMeasurementApp.Length.LengthUnit.FEET);
        var l2 = new QuantityMeasurementApp.Length(12.0,
                QuantityMeasurementApp.Length.LengthUnit.INCHES);

        var r1 = l1.add(l2);
        var r2 = l2.add(l1);

        assertTrue(r1.equals(r2));
    }

    @Test
    void testAddition_WithZero() {
        var l1 = new QuantityMeasurementApp.Length(5.0,
                QuantityMeasurementApp.Length.LengthUnit.FEET);
        var l2 = new QuantityMeasurementApp.Length(0.0,
                QuantityMeasurementApp.Length.LengthUnit.INCHES);

        var result = l1.add(l2);

        assertEquals(5.0, result.getValue(), EPS);
    }

    @Test
    void testAddition_NegativeValues() {
        var l1 = new QuantityMeasurementApp.Length(5.0,
                QuantityMeasurementApp.Length.LengthUnit.FEET);
        var l2 = new QuantityMeasurementApp.Length(-2.0,
                QuantityMeasurementApp.Length.LengthUnit.FEET);

        var result = l1.add(l2);

        assertEquals(3.0, result.getValue(), EPS);
    }

    @Test
    void testConversion_FeetToInches() {
        double result = QuantityMeasurementApp.Length.convert(
                1.0,
                QuantityMeasurementApp.Length.LengthUnit.FEET,
                QuantityMeasurementApp.Length.LengthUnit.INCHES);

        assertEquals(12.0, result, EPS);
    }

    @Test
    void testConversion_YardsToFeet() {
        double result = QuantityMeasurementApp.Length.convert(
                3.0,
                QuantityMeasurementApp.Length.LengthUnit.YARDS,
                QuantityMeasurementApp.Length.LengthUnit.FEET);

        assertEquals(9.0, result, EPS);
    }

    @Test
    void testConversion_CentimetersToInches() {
        double result = QuantityMeasurementApp.Length.convert(
                2.54,
                QuantityMeasurementApp.Length.LengthUnit.CENTIMETERS,
                QuantityMeasurementApp.Length.LengthUnit.INCHES);

        assertEquals(1.0, result, EPS);
    }

    @Test
    void testEquality_CrossUnit() {
        var l1 = new QuantityMeasurementApp.Length(1.0,
                QuantityMeasurementApp.Length.LengthUnit.FEET);
        var l2 = new QuantityMeasurementApp.Length(12.0,
                QuantityMeasurementApp.Length.LengthUnit.INCHES);

        assertTrue(l1.equals(l2));
    }

    @Test
    void testAddition_NullSecondOperand() {
        var l1 = new QuantityMeasurementApp.Length(1.0,
                QuantityMeasurementApp.Length.LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> l1.add(null));
    }
}