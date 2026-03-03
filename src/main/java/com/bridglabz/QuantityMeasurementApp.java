package com.bridglabz;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        Quantity<VolumeUnit> volume1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> volume2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> volume3 =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("Equality:");
        System.out.println(volume1.equals(volume2));
        System.out.println(volume1.equals(volume3));

        System.out.println("\nConversion:");
        System.out.println(volume1.convertTo(VolumeUnit.MILLILITRE));
        System.out.println(volume3.convertTo(VolumeUnit.LITRE));

        System.out.println("\nAddition:");
        System.out.println(volume1.add(volume2));
        System.out.println(volume1.add(volume3, VolumeUnit.MILLILITRE));
    }
}