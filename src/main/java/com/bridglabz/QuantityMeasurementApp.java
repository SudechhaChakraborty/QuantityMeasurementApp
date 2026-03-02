package com.bridglabz;



/**
 * QuantityMeasurementApp
 *
 * Supports:
 *  - Length measurements (UC1–UC8)
 *  - Weight measurements (UC9)
 *
 * NOTE:
 *  Length functionality remains unchanged.
 *  Weight functionality added separately.
 */
public class QuantityMeasurementApp {

    // ============================================================
    // ===================== LENGTH METHODS =======================
    // ============================================================

    // Equality check using objects
    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    // Equality check using raw values
    public static boolean demonstrateLengthComparison(
            double value1, LengthUnit unit1,
            double value2, LengthUnit unit2) {

        Length l1 = new Length(value1, unit1);
        Length l2 = new Length(value2, unit2);

        return l1.equals(l2);
    }

    // Convert using raw values
    public static Length demonstrateLengthConversion(
            double value, LengthUnit from, LengthUnit to) {

        Length length = new Length(value, from);
        return length.convertTo(to);
    }

    // Convert using Length object
    public static Length demonstrateLengthConversion(
            Length length, LengthUnit targetUnit) {

        return length.convertTo(targetUnit);
    }

    // Addition (default first operand unit)
    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    // Addition (explicit target unit)
    public static Length demonstrateLengthAddition(
            Length l1, Length l2, LengthUnit targetUnit) {

        return l1.add(l2, targetUnit);
    }


    // ============================================================
    // ===================== WEIGHT METHODS =======================
    // ============================================================

    // Equality check using objects
    public static boolean demonstrateWeightEquality(
            QuantityWeight w1, QuantityWeight w2) {

        return w1.equals(w2);
    }

    // Equality check using raw values
    public static boolean demonstrateWeightComparison(
            double value1, WeightUnit unit1,
            double value2, WeightUnit unit2) {

        QuantityWeight w1 = new QuantityWeight(value1, unit1);
        QuantityWeight w2 = new QuantityWeight(value2, unit2);

        return w1.equals(w2);
    }

    // Convert using raw values
    public static QuantityWeight demonstrateWeightConversion(
            double value, WeightUnit from, WeightUnit to) {

        QuantityWeight weight = new QuantityWeight(value, from);
        return weight.convertTo(to);
    }

    // Convert using object
    public static QuantityWeight demonstrateWeightConversion(
            QuantityWeight weight, WeightUnit targetUnit) {

        return weight.convertTo(targetUnit);
    }

    // Addition (default → first operand unit)
    public static QuantityWeight demonstrateWeightAddition(
            QuantityWeight w1, QuantityWeight w2) {

        return w1.add(w2);
    }

    // Addition (explicit target unit)
    public static QuantityWeight demonstrateWeightAddition(
            QuantityWeight w1, QuantityWeight w2, WeightUnit targetUnit) {

        return w1.add(w2, targetUnit);
    }


    // ============================================================
    // ========================= MAIN =============================
    // ============================================================

    public static void main(String[] args) {

        // -------- LENGTH SAMPLE --------
        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println("Length Equality: " + l1.equals(l2));

        // -------- WEIGHT SAMPLE --------
        QuantityWeight w1 = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight w2 = new QuantityWeight(1000.0, WeightUnit.GRAM);

        System.out.println("Weight Equality: " + w1.equals(w2));

        QuantityWeight sum = w1.add(w2);
        System.out.println("Weight Addition: " + sum);

        QuantityWeight converted = w1.convertTo(WeightUnit.POUND);
        System.out.println("1 KG in Pounds: " + converted);
    }
}