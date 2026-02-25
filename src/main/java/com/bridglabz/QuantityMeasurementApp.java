package com.bridglabz;

import java.util.Objects;

public class QuantityMeasurementApp {

    // ===============================
    // ENUM FOR LENGTH UNITS
    // ===============================
    public enum LengthUnit {

        FEET(12.0),     // 1 foot = 12 inches
        INCHES(1.0);    // base unit

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // ===============================
    // GENERIC LENGTH CLASS
    // ===============================
    public static class Length {

        private final double value;
        private final LengthUnit unit;

        public Length(double value, LengthUnit unit) {

            if (Double.isNaN(value))
                throw new IllegalArgumentException("Value must be numeric");

            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            this.value = value;
            this.unit = unit;
        }

        // Convert everything to base unit (inches)
        private double toBaseUnit() {
            return this.value * this.unit.getConversionFactor();
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null)
                return false;

            if (this.getClass() != obj.getClass())
                return false;

            Length other = (Length) obj;

            return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(toBaseUnit());
        }
    }

    // ===============================
    // STATIC DEMO METHODS
    // ===============================

    public static boolean checkEquality(double v1, LengthUnit u1,
                                        double v2, LengthUnit u2) {

        Length l1 = new Length(v1, u1);
        Length l2 = new Length(v2, u2);

        return l1.equals(l2);
    }

    // ===============================
    // MAIN METHOD
    // ===============================

    public static void main(String[] args) {

        System.out.println(
                checkEquality(1.0, LengthUnit.FEET,
                        12.0, LengthUnit.INCHES)
        );

        System.out.println(
                checkEquality(1.0, LengthUnit.INCHES,
                        1.0, LengthUnit.INCHES)
        );
    }
}