package com.bridglabz;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-3;

    // ============================================================
    // VOLUME EQUALITY TESTS
    // ============================================================

    @Test
    void testEquality_LitreToLitre_SameValue() {
        assertEquals(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1.0, VolumeUnit.LITRE)
        );
    }

    @Test
    void testEquality_LitreToLitre_DifferentValue() {
        assertNotEquals(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(2.0, VolumeUnit.LITRE)
        );
    }

    @Test
    void testEquality_LitreToMillilitre_Equivalent() {
        assertEquals(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
        );
    }

    @Test
    void testEquality_LitreToGallon_Equivalent() {
        assertEquals(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(0.264172, VolumeUnit.GALLON)
        );
    }

    @Test
    void testEquality_GallonToLitre_Equivalent() {
        assertEquals(
                new Quantity<>(1.0, VolumeUnit.GALLON),
                new Quantity<>(3.78541, VolumeUnit.LITRE)
        );
    }

    @Test
    void testEquality_Symmetric() {
        Quantity<VolumeUnit> a =
                new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertTrue(a.equals(b));
        assertTrue(b.equals(a));
    }

    @Test
    void testEquality_Transitive() {
        Quantity<VolumeUnit> a =
                new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> c =
                new Quantity<>(0.264172, VolumeUnit.GALLON);

        assertTrue(a.equals(b));
        assertTrue(b.equals(c));
        assertTrue(a.equals(c));
    }

    @Test
    void testEquality_NullComparison() {
        assertNotEquals(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                null
        );
    }

    @Test
    void testEquality_SameReference() {
        Quantity<VolumeUnit> volume =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        assertEquals(volume, volume);
    }

    // ============================================================
    // CROSS CATEGORY TESTS
    // ============================================================

    @Test
    void testVolumeVsLength_Incompatible() {
        assertNotEquals(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1.0, LengthUnit.INCHES)
        );
    }

    @Test
    void testVolumeVsWeight_Incompatible() {
        assertNotEquals(
                new Quantity<>(1.0, VolumeUnit.LITRE),
                new Quantity<>(1.0, WeightUnit.KILOGRAM)
        );
    }

    // ============================================================
    // CONVERSION TESTS
    // ============================================================

    @Test
    void testConversion_LitreToMillilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE);

        assertEquals(1000.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_MillilitreToLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_GallonToLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.GALLON)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(3.78541, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_LitreToGallon() {
        Quantity<VolumeUnit> result =
                new Quantity<>(3.78541, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.GALLON);

        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    void testConversion_RoundTrip() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.5, VolumeUnit.LITRE)
                        .convertTo(VolumeUnit.MILLILITRE)
                        .convertTo(VolumeUnit.LITRE);

        assertEquals(1.5, result.getValue(), EPSILON);
    }

    // ============================================================
    // ADDITION TESTS
    // ============================================================

    @Test
    void testAddition_SameUnit() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(2.0, VolumeUnit.LITRE));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_LitrePlusMillilitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE));

        assertEquals(2.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_CrossUnit_MillilitrePlusLitre() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE)
                        .add(new Quantity<>(1.0, VolumeUnit.LITRE));

        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_ExplicitTargetUnit() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(1000.0, VolumeUnit.MILLILITRE),
                                VolumeUnit.MILLILITRE);

        assertEquals(2000.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_Commutativity() {
        Quantity<VolumeUnit> a =
                new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertEquals(a.add(b), b.add(a));
    }

    @Test
    void testAddition_WithZero() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(0.0, VolumeUnit.MILLILITRE));

        assertEquals(5.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_NegativeValues() {
        Quantity<VolumeUnit> result =
                new Quantity<>(5.0, VolumeUnit.LITRE)
                        .add(new Quantity<>(-2000.0, VolumeUnit.MILLILITRE));

        assertEquals(3.0, result.getValue(), EPSILON);
    }

    // ============================================================
    // EDGE CASES
    // ============================================================

    @Test
    void testZeroValueEquality() {
        assertEquals(
                new Quantity<>(0.0, VolumeUnit.LITRE),
                new Quantity<>(0.0, VolumeUnit.MILLILITRE)
        );
    }

    @Test
    void testLargeValues() {
        Quantity<VolumeUnit> result =
                new Quantity<>(1e6, VolumeUnit.LITRE)
                        .add(new Quantity<>(1e6, VolumeUnit.LITRE));

        assertEquals(2e6, result.getValue(), EPSILON);
    }

    // ============================================================
    // ENUM TESTS
    // ============================================================

    @Test
    void testVolumeUnit_LitreFactor() {
        assertEquals(1.0,
                VolumeUnit.LITRE.getConversionFactor(),
                EPSILON);
    }

    @Test
    void testVolumeUnit_MillilitreFactor() {
        assertEquals(0.001,
                VolumeUnit.MILLILITRE.getConversionFactor(),
                EPSILON);
    }

    @Test
    void testVolumeUnit_GallonFactor() {
        assertEquals(3.78541,
                VolumeUnit.GALLON.getConversionFactor(),
                EPSILON);
    }

    @Test
    void testHashCodeConsistency() {
        Quantity<VolumeUnit> a =
                new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertEquals(a.hashCode(), b.hashCode());
    }
}