package com.bridglabz;

public class QuantityMeasurementApp {

    // ===== FEET CLASS (From UC1) =====
    public static class Feet {

        private final double value;

        public Feet(double value) {
            if (Double.isNaN(value)) {
                throw new IllegalArgumentException("Value must be numeric");
            }
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null)
                return false;

            if (this.getClass() != obj.getClass())
                return false;

            Feet other = (Feet) obj;

            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // ===== INCHES CLASS (NEW FOR UC2) =====
    public static class Inches {

        private final double value;

        public Inches(double value) {
            if (Double.isNaN(value)) {
                throw new IllegalArgumentException("Value must be numeric");
            }
            this.value = value;
        }

        @Override
        public boolean equals(Object obj) {

            if (this == obj)
                return true;

            if (obj == null)
                return false;

            if (this.getClass() != obj.getClass())
                return false;

            Inches other = (Inches) obj;

            return Double.compare(this.value, other.value) == 0;
        }

        @Override
        public int hashCode() {
            return Double.hashCode(value);
        }
    }

    // ===== STATIC METHODS (As per UC2 main flow) =====

    public static boolean checkFeetEquality(double v1, double v2) {
        Feet f1 = new Feet(v1);
        Feet f2 = new Feet(v2);
        return f1.equals(f2);
    }

    public static boolean checkInchesEquality(double v1, double v2) {
        Inches i1 = new Inches(v1);
        Inches i2 = new Inches(v2);
        return i1.equals(i2);
    }

    // ===== MAIN METHOD (Demonstration Only) =====
    public static void main(String[] args) {

        System.out.println("1.0 ft vs 1.0 ft : " + checkFeetEquality(1.0, 1.0));
        System.out.println("1.0 inch vs 1.0 inch : " + checkInchesEquality(1.0, 1.0));
    }
}