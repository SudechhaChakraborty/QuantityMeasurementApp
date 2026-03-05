package com.bridglabz;

import java.util.Objects;

/**
 * Generic Quantity class representing a measurable quantity with a value and unit.
 * Supports equality comparison, conversion, addition, subtraction, and division.
 */

public final class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-3;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (Double.isNaN(value) || Double.isInfinite(value))
            throw new IllegalArgumentException("Invalid numeric value");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // =====================================================
    // EQUALITY
    // =====================================================

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Quantity<?> other))
            return false;

        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    @Override
    public int hashCode() {
        double baseValue = unit.convertToBaseUnit(value);
        return Objects.hash(baseValue);
    }

    // =====================================================
    // CONVERSION
    // =====================================================

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        if (!unit.getClass().equals(targetUnit.getClass()))
            throw new IllegalArgumentException("Incompatible unit categories");

        double baseValue = unit.convertToBaseUnit(value);

        double converted = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(converted, targetUnit);
    }

    // =====================================================
    // ADDITION
    // =====================================================

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Incompatible unit categories");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double baseSum = base1 + base2;

        double result = targetUnit.convertFromBaseUnit(baseSum);

        return new Quantity<>(result, targetUnit);
    }

    // =====================================================
    // SUBTRACTION  (UC12)
    // =====================================================

    public Quantity<U> subtract(Quantity<U> other) {
        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Incompatible unit categories");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double baseResult = base1 - base2;

        double result = targetUnit.convertFromBaseUnit(baseResult);

        // Round to 2 decimal places
        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, targetUnit);
    }

    // =====================================================
    // DIVISION  (UC12)
    // =====================================================

    public double divide(Quantity<U> other) {

        if (other == null)
            throw new IllegalArgumentException("Other quantity cannot be null");

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Incompatible unit categories");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        if (base2 == 0)
            throw new ArithmeticException("Division by zero");

        return base1 / base2;
    }

    // =====================================================
    // STRING REPRESENTATION
    // =====================================================

    @Override
    public String toString() {
        return "Quantity(" + value + ", " + unit + ")";
    }
}