package com.bridglabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.bridglabz.QuantityMeasurementApp.Length;
import com.bridglabz.QuantityMeasurementApp.LengthUnit;

public class QuantityMeasurementAppTest {

    // Same Unit Equality
    @Test
    void testEquality_FeetToFeet_SameValue() {
        assertTrue(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(1.0, LengthUnit.FEET)));
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        assertTrue(new Length(1.0, LengthUnit.INCHES)
                .equals(new Length(1.0, LengthUnit.INCHES)));
    }

    // Cross Unit Equality
    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        assertTrue(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(12.0, LengthUnit.INCHES)));
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        assertTrue(new Length(12.0, LengthUnit.INCHES)
                .equals(new Length(1.0, LengthUnit.FEET)));
    }

    // Different Values
    @Test
    void testEquality_Feet_DifferentValue() {
        assertFalse(new Length(1.0, LengthUnit.FEET)
                .equals(new Length(2.0, LengthUnit.FEET)));
    }

    @Test
    void testEquality_Inch_DifferentValue() {
        assertFalse(new Length(1.0, LengthUnit.INCHES)
                .equals(new Length(2.0, LengthUnit.INCHES)));
    }

    // Null Comparison
    @Test
    void testEquality_NullComparison() {
        assertFalse(new Length(1.0, LengthUnit.FEET)
                .equals(null));
    }

    // Same Reference
    @Test
    void testEquality_SameReference() {
        Length l = new Length(1.0, LengthUnit.FEET);
        assertTrue(l.equals(l));
    }

    // Invalid Unit
    @Test
    void testEquality_NullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(1.0, null));
    }

    // Invalid Numeric Input
    @Test
    void testEquality_NonNumericInput() {
        assertThrows(IllegalArgumentException.class,
                () -> new Length(Double.NaN, LengthUnit.FEET));
    }
}