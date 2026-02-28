package com.bridglabz;

import java.util.Objects;

public class QuantityMeasurementApp {

    /* ============================
       LENGTH CLASS (Inner Class)
       ============================ */
    public static class Length {

        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 0.0001;

        public Length(double value, LengthUnit unit) {
            if (unit == null)
                throw new IllegalArgumentException("Unit cannot be null");

            if (!Double.isFinite(value))
                throw new IllegalArgumentException("Invalid numeric value");

            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        /* ===== Conversion ===== */
        public Length convertTo(LengthUnit targetUnit) {
            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double valueInFeet = unit.toFeet(value);
            double converted = targetUnit.fromFeet(valueInFeet);

            return new Length(converted, targetUnit);
        }

        /* ===== UC6 Addition ===== */
        public Length add(Length other) {
            return add(other, this.unit);
        }

        /* ===== UC7 Addition (Explicit Target) ===== */
        public Length add(Length other, LengthUnit targetUnit) {

            if (other == null)
                throw new IllegalArgumentException("Length to add cannot be null");

            if (targetUnit == null)
                throw new IllegalArgumentException("Target unit cannot be null");

            double base1 = this.unit.toFeet(this.value);
            double base2 = other.unit.toFeet(other.value);

            double sumFeet = base1 + base2;

            double result = targetUnit.fromFeet(sumFeet);

            return new Length(result, targetUnit);
        }

        /* ===== Equality ===== */
        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Length)) return false;

            Length other = (Length) obj;

            double thisFeet = this.unit.toFeet(this.value);
            double otherFeet = other.unit.toFeet(other.value);

            return Math.abs(thisFeet - otherFeet) < EPSILON;
        }

        @Override
        public int hashCode() {
            return Objects.hash(unit.toFeet(value));
        }

        @Override
        public String toString() {
            return String.format("Quantity(%.3f, %s)", value, unit);
        }

        /* ===== Enum ===== */
        public enum LengthUnit {
            FEET(1.0),
            INCHES(1.0 / 12.0),
            YARDS(3.0),
            CENTIMETERS(0.0328084);

            private final double toFeetFactor;

            LengthUnit(double toFeetFactor) {
                this.toFeetFactor = toFeetFactor;
            }

            public double toFeet(double value) {
                return value * toFeetFactor;
            }

            public double fromFeet(double feetValue) {
                return feetValue / toFeetFactor;
            }
        }
    }

    /* ============================
       MAIN METHOD
       ============================ */
    public static void main(String[] args) {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println(l1.add(l2, Length.LengthUnit.FEET));
        System.out.println(l1.add(l2, Length.LengthUnit.INCHES));
        System.out.println(l1.add(l2, Length.LengthUnit.YARDS));
    }
}