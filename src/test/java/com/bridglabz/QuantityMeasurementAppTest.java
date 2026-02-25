package com.bridglabz;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import com.bridglabz.QuantityMeasurementApp.Feet;

public class QuantityMeasurementAppTest {

    @Test
    void givenSameFeetValue_whenCompared_thenReturnTrue() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);

        assertTrue(f1.equals(f2));
    }

    @Test
    void givenDifferentFeetValue_whenCompared_thenReturnFalse() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(2.0);

        assertFalse(f1.equals(f2));
    }

    @Test
    void givenNull_whenCompared_thenReturnFalse() {
        Feet f1 = new Feet(1.0);

        assertFalse(f1.equals(null));
    }

    @Test
    void givenDifferentType_whenCompared_thenReturnFalse() {
        Feet f1 = new Feet(1.0);

        assertFalse(f1.equals("1.0"));
    }

    @Test
    void givenSameReference_whenCompared_thenReturnTrue() {
        Feet f1 = new Feet(1.0);

        assertTrue(f1.equals(f1));
    }
}