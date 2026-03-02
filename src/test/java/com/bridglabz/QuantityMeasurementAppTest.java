package com.bridglabz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // ============================================================
    // ===================== LENGTH TESTS =========================
    // ============================================================

    @Test
    void testFeetEquality() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(1.0, LengthUnit.FEET);
        assertEquals(l1, l2);
    }

    @Test
    void testFeetInchesEquality() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        assertEquals(l1, l2);
    }

    @Test
    void testLengthAddition() {
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);
        Length result = l1.add(l2);
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    // ============================================================
    // ===================== WEIGHT TESTS =========================
    // ============================================================

    @Test
    void testEquality_KilogramToKilogram() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertEquals(w1, w2);
    }

    @Test
    void testEquality_KilogramToGram() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight gram = new QuantityWeight(1000.0, WeightUnit.GRAM);
        assertEquals(kg, gram);
    }

    @Test
    void testEquality_KilogramToPound() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight pound = new QuantityWeight(2.20462, WeightUnit.POUND);
        assertEquals(kg, pound);
    }

    @Test
    void testEquality_GramToPound() {
        QuantityWeight gram = new QuantityWeight(453.592, WeightUnit.GRAM);
        QuantityWeight pound = new QuantityWeight(1.0, WeightUnit.POUND);
        assertEquals(gram, pound);
    }

    @Test
    void testConversion_KilogramToGram() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight gram = kg.convertTo(WeightUnit.GRAM);
        assertEquals(1000.0, gram.getValue(), EPSILON);
    }

    @Test
    void testConversion_PoundToKilogram() {
        QuantityWeight pound = new QuantityWeight(2.20462, WeightUnit.POUND);
        QuantityWeight kg = pound.convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.0, kg.getValue(), 1e-3);
    }

    @Test
    void testConversion_SameUnit() {
        QuantityWeight kg = new QuantityWeight(5.0, WeightUnit.KILOGRAM);
        QuantityWeight result = kg.convertTo(WeightUnit.KILOGRAM);
        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_RoundTrip() {
        QuantityWeight kg = new QuantityWeight(1.5, WeightUnit.KILOGRAM);
        QuantityWeight gram = kg.convertTo(WeightUnit.GRAM);
        QuantityWeight back = gram.convertTo(WeightUnit.KILOGRAM);
        assertEquals(1.5, back.getValue(), EPSILON);
    }

    @Test
    void testAddition_SameUnit() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(2.0, WeightUnit.KILOGRAM);
        QuantityWeight result = w1.add(w2);
        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight gram = new QuantityWeight(1000.0, WeightUnit.GRAM);
        QuantityWeight result = kg.add(gram);
        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit() {
        QuantityWeight kg = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight gram = new QuantityWeight(1000.0, WeightUnit.GRAM);
        QuantityWeight result = kg.add(gram, WeightUnit.GRAM);
        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_Commutativity() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight result1 = w1.add(w2);
        QuantityWeight result2 = w2.add(w1, WeightUnit.KILOGRAM);

        assertEquals(result1, result2);
    }

    @Test
    void testZeroValue() {
        QuantityWeight w1 = new QuantityWeight(0.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(0.0, WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    @Test
    void testNegativeWeight() {
        QuantityWeight w1 = new QuantityWeight(-1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(-1000.0, WeightUnit.GRAM);
        assertEquals(w1, w2);
    }

    @Test
    void testLargeValue() {
        QuantityWeight w1 = new QuantityWeight(1000000.0, WeightUnit.GRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.KILOGRAM);
        assertEquals(w1, w2);
    }

    @Test
    void testHashCodeConsistency() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);
        assertEquals(w1.hashCode(), w2.hashCode());
    }

    @Test
    void testNullComparison() {
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertNotEquals(w1, null);
    }

    @Test
    void testWeightVsLengthIncompatible() {
        QuantityWeight weight = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        Length length = new Length(1.0, LengthUnit.FEET);

        assertNotEquals(weight, length);
    }

    @Test
    void testInvalidUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(1.0, null));
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM));
    }
}