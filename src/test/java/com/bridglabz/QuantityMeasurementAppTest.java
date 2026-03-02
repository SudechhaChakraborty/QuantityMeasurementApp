package com.bridglabz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-3;

    // ============================================================
    // ===================== INTERFACE TESTS ======================
    // ============================================================

    @Test
    void testIMeasurable_LengthUnitImplementation() {
        IMeasurable feet = LengthUnit.FEET;
        assertEquals(12.0, feet.getConversionFactor());
        assertEquals("FEET", feet.getUnitName());
    }

    @Test
    void testIMeasurable_WeightUnitImplementation() {
        IMeasurable kg = WeightUnit.KILOGRAM;
        assertEquals(1.0, kg.getConversionFactor());
        assertEquals("KILOGRAM", kg.getUnitName());
    }

    // ============================================================
    // ===================== LENGTH TESTS =========================
    // ============================================================

    @Test
    void testLengthEquality() {
        Quantity<LengthUnit> l1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> l2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(l1, l2);
    }

    @Test
    void testLengthConversion() {
        Quantity<LengthUnit> l =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                l.convertTo(LengthUnit.INCHES);

        assertEquals(12.0, result.getValue(), EPSILON);
    }

    @Test
    void testLengthAddition() {
        Quantity<LengthUnit> l1 =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> l2 =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result =
                l1.add(l2, LengthUnit.FEET);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    // ============================================================
    // ===================== WEIGHT TESTS =========================
    // ============================================================

    @Test
    void testWeightEquality_KilogramToGram() {
        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(kg, gram);
    }

    @Test
    void testWeightEquality_KilogramToPound() {
        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> pound =
                new Quantity<>(2.20462, WeightUnit.POUND);

        assertEquals(kg, pound);
    }

    @Test
    void testWeightConversion() {
        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                kg.convertTo(WeightUnit.GRAM);

        assertEquals(1000.0, gram.getValue(), EPSILON);
    }

    @Test
    void testWeightAddition() {
        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result =
                kg.add(gram, WeightUnit.KILOGRAM);

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    // ============================================================
    // ================= CROSS CATEGORY TEST ======================
    // ============================================================

    @Test
    void testCrossCategoryPrevention() {
        Quantity<LengthUnit> length =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(length, weight);
    }

    // ============================================================
    // =================== VALIDATION TESTS =======================
    // ============================================================

    @Test
    void testNullUnit() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    void testInvalidValue() {
        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testImmutability() {
        Quantity<WeightUnit> kg =
                new Quantity<>(5.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result =
                kg.convertTo(WeightUnit.GRAM);

        assertNotSame(kg, result);
    }

    // ============================================================
    // =================== HASHCODE TEST ==========================
    // ============================================================

    @Test
    void testHashCodeConsistency() {
        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(kg.hashCode(), gram.hashCode());
    }
}