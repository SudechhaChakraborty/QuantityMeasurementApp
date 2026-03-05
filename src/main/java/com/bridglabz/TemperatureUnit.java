package com.bridglabz;


import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS(false),
    FAHRENHEIT(true),
    KELVIN(false);

    private final boolean isFahrenheit;

    TemperatureUnit(boolean isFahrenheit) {
        this.isFahrenheit = isFahrenheit;
    }

    // =============================
    // Functional conversion lambdas
    // =============================

    private static final Function<Double, Double> CELSIUS_TO_CELSIUS =
            (celsius) -> celsius;

    private static final Function<Double, Double> FAHRENHEIT_TO_CELSIUS =
            (fahrenheit) -> (fahrenheit - 32) * 5 / 9;

    private static final Function<Double, Double> KELVIN_TO_CELSIUS =
            (kelvin) -> kelvin - 273.15;

    // Temperature does NOT support arithmetic
    SupportsArithmetic supportsArithmetic = () -> false;

    // =============================
    // Interface Methods
    // =============================

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public double getConversionFactor() {
        return 1.0;
    }

    @Override
    public double convertToBaseUnit(double value) {

        if (this == CELSIUS)
            return CELSIUS_TO_CELSIUS.apply(value);

        if (this == FAHRENHEIT)
            return FAHRENHEIT_TO_CELSIUS.apply(value);

        if (this == KELVIN)
            return KELVIN_TO_CELSIUS.apply(value);

        return value;
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {

        if (this == CELSIUS)
            return baseValue;

        if (this == FAHRENHEIT)
            return (baseValue * 9 / 5) + 32;

        if (this == KELVIN)
            return baseValue + 273.15;

        return baseValue;
    }

    @Override
    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    @Override
    public void validateOperationSupport(String operation) {

        if (!supportsArithmetic()) {
            throw new UnsupportedOperationException(
                    "Temperature does not support " + operation + " operations."
            );
        }
    }
}