package com.bridglabz;

@FunctionalInterface
interface SupportsArithmetic {
    boolean isSupported();
}

public interface IMeasurable {

    // default lambda -> all units support arithmetic
    SupportsArithmetic supportsArithmetic = () -> true;

    // =====================
    // Mandatory methods
    // =====================

    String getUnitName();

    double getConversionFactor();

    double convertToBaseUnit(double value);

    double convertFromBaseUnit(double baseValue);

    // =====================
    // Optional methods
    // =====================

    default boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    // default does nothing
    default void validateOperationSupport(String operation) {
        // overridden by units like Temperature
    }
}
