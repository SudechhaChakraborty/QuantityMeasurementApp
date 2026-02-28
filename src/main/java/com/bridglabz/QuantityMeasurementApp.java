package com.bridglabz;

public class QuantityMeasurementApp {

    public static class Length {

        private final double value;
        private final LengthUnit unit;
        private static final double EPSILON = 1e-6;

        // Enum with conversion factors relative to FEET (base unit)
        public enum LengthUnit {
            FEET(1.0),
            INCHES(1.0 / 12.0),
            YARDS(3.0),
            CENTIMETERS(0.0328084); // 1 cm = 0.0328084 feet

            private final double conversionFactor;

            LengthUnit(double conversionFactor) {
                this.conversionFactor = conversionFactor;
            }

            public double getConversionFactor() {
                return conversionFactor;
            }
        }

        public Length(double value, LengthUnit unit) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be finite");
            }
            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }
            this.value = value;
            this.unit = unit;
        }

        public double getValue() {
            return value;
        }

        public LengthUnit getUnit() {
            return unit;
        }

        private double toBaseUnit() {
            return value * unit.getConversionFactor();
        }

        public Length convertTo(LengthUnit targetUnit) {
            if (targetUnit == null) {
                throw new IllegalArgumentException("Target unit cannot be null");
            }

            double baseValue = toBaseUnit();
            double converted = baseValue / targetUnit.getConversionFactor();

            return new Length(converted, targetUnit);
        }

        public static double convert(double value, LengthUnit source, LengthUnit target) {
            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException("Value must be finite");
            }
            if (source == null || target == null) {
                throw new IllegalArgumentException("Units cannot be null");
            }

            double base = value * source.getConversionFactor();
            return base / target.getConversionFactor();
        }

        public Length add(Length other) {
            if (other == null) {
                throw new IllegalArgumentException("Other length cannot be null");
            }

            double baseSum = this.toBaseUnit() + other.toBaseUnit();
            double resultValue = baseSum / this.unit.getConversionFactor();

            return new Length(resultValue, this.unit);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Length)) return false;

            Length other = (Length) obj;

            double diff = Math.abs(this.toBaseUnit() - other.toBaseUnit());
            return diff < EPSILON;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(toBaseUnit());
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    // Demo helpers
    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    public static double demonstrateLengthConversion(double value,
                                                     Length.LengthUnit from,
                                                     Length.LengthUnit to) {
        return Length.convert(value, from, to);
    }

    public static void main(String[] args) {

        Length l1 = new Length(1.0, Length.LengthUnit.FEET);
        Length l2 = new Length(12.0, Length.LengthUnit.INCHES);

        Length result = l1.add(l2);

        System.out.println("Addition Result: " + result);
        System.out.println("Equality Check: " + l1.equals(l2));
        System.out.println("Conversion 1 foot to inches: "
                + Length.convert(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES));
    }
}