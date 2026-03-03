package com.bridglabz;

import java.util.Objects;

public final class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 1e-3;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null) {
            throw new IllegalArgumentException("Unit cannot be null");
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // -------------------------
    // EQUALITY
    // -------------------------

    @Override
    public boolean equals(Object obj) {

        if (this == obj) return true;

        if (!(obj instanceof Quantity<?> other))
            return false;

        // Prevent cross-category comparison
        if (!unit.getClass().equals(other.unit.getClass()))
            return false;

        double thisBase = unit.convertToBaseUnit(value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < EPSILON;
    }

    @Override
    public int hashCode() {
        double baseValue = unit.convertToBaseUnit(value);
        return Objects.hash(baseValue);
    }

    // -------------------------
    // CONVERSION
    // -------------------------

    public Quantity<U> convertTo(U targetUnit) {

        if (!unit.getClass().equals(targetUnit.getClass()))
            throw new IllegalArgumentException("Incompatible unit category");

        double baseValue = unit.convertToBaseUnit(value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(convertedValue, targetUnit);
    }

    // -------------------------
    // ADDITION
    // -------------------------

    public Quantity<U> add(Quantity<U> other) {
        return add(other, this.unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (!unit.getClass().equals(other.unit.getClass()))
            throw new IllegalArgumentException("Incompatible unit category");

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        double sumBase = base1 + base2;
        double finalValue = targetUnit.convertFromBaseUnit(sumBase);

        return new Quantity<>(finalValue, targetUnit);
    }

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}