package com.bridglabz;

import java.util.Objects;

public class QuantityMeasurementApp {

    public enum LengthUnit {

        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    public static class Length {

        private final double value;
        private final LengthUnit unit;

        private static final double EPSILON = 1e-6;

        public Length(double value, LengthUnit unit) {

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Value must be finite");

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

        private double toBaseUnit() {
            return value * unit.getConversionFactor();
        }

        // ===============================
        // STATIC CONVERSION METHOD (UC5)
        // ===============================
        public static double convert(
                double value,
                LengthUnit source,
                LengthUnit target) {

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Value must be finite");

            if (source == null || target == null)
                throw new IllegalArgumentException("Units cannot be null");

            double baseValue =
                    value * source.getConversionFactor();

            return baseValue / target.getConversionFactor();
        }

        // ===============================
        // INSTANCE CONVERSION
        // ===============================
        public Length convertTo(LengthUnit targetUnit) {

            double convertedValue =
                    convert(this.value, this.unit, targetUnit);

            return new Length(convertedValue, targetUnit);
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null || getClass() != obj.getClass())
                return false;

            Length other = (Length) obj;

            return Math.abs(
                    this.toBaseUnit() -
                            other.toBaseUnit()) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Objects.hash(
                    Math.round(toBaseUnit() / EPSILON));
        }

        @Override
        public String toString() {
            return String.format("%.6f %s",
                    value, unit);
        }
    }

    // ===============================
    // DEMO API METHODS
    // ===============================

    public static double demonstrateLengthConversion(
            double value,
            LengthUnit from,
            LengthUnit to) {

        return Length.convert(value, from, to);
    }

    public static Length demonstrateLengthConversion(
            Length length,
            LengthUnit toUnit) {

        return length.convertTo(toUnit);
    }
}