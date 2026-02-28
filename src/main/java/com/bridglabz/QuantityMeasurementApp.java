package com.bridglabz;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Length l1 = new Length(1.0, LengthUnit.FEET);
        Length l2 = new Length(12.0, LengthUnit.INCHES);

        System.out.println(l1.add(l2, LengthUnit.FEET));       // 2.0 FEET
        System.out.println(l1.add(l2, LengthUnit.INCHES));    // 24 INCHES
        System.out.println(l1.add(l2, LengthUnit.YARDS));     // 0.67 YARDS

        System.out.println(l1.convertTo(LengthUnit.INCHES));  // 12 INCHES
        System.out.println(new Length(36, LengthUnit.INCHES)
                .equals(new Length(1, LengthUnit.YARDS)));    // true
    }
}