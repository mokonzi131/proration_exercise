package com.example;

import java.time.LocalDateTime;

public class ProrationCalculator {
    public static double calculateProrationFee(InputParameters parameters) {
        // because we are using InputParameters, we can assume these are valid values
        double baseFee = parameters.getBaseFee();
        int cycleDay = parameters.getCycleBillDay();
        LocalDateTime dateTime = parameters.getPurchaseDate();

        throw new UnsupportedOperationException("method not yet implemented");
    }
}
