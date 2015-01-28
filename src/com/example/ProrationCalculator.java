package com.example;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.temporal.*;
import java.time.temporal.TemporalAdjusters.*;

public class ProrationCalculator {
    public static double calculateProrationFee(InputParameters parameters) {
        
        // because we are using InputParameters, we can assume these are valid values
        double baseFee = parameters.getBaseFee();
        int cycleDay = parameters.getCycleBillDay();
        LocalDateTime purchaseDate =
                parameters.getPurchaseDate().withHour(0).withMinute(0).withSecond(0);
        
        // find the previous and next billing dates, always check for an invalid date
        LocalDateTime previousBillingDate, nextBillingDate;
        try {
            previousBillingDate = purchaseDate.withDayOfMonth(cycleDay);
        } catch(DateTimeException e) {
            previousBillingDate = purchaseDate.with(TemporalAdjusters.lastDayOfMonth());
        }
        
        if (previousBillingDate.isAfter(purchaseDate)) {
            nextBillingDate = previousBillingDate;
            try {
                previousBillingDate = purchaseDate.minusMonths(1).withDayOfMonth(cycleDay);
            } catch(DateTimeException e) {
                previousBillingDate = purchaseDate.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
            }
        } else {
            try {
                nextBillingDate = purchaseDate.plusMonths(1).withDayOfMonth(cycleDay);
            } catch(DateTimeException e) {
                nextBillingDate = purchaseDate.plusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
            }
        }
        
        // find the difference
        double fullCycle = ChronoUnit.DAYS.between(previousBillingDate, nextBillingDate);
        double partCycle = ChronoUnit.DAYS.between(purchaseDate, nextBillingDate);
        double ratio = partCycle / fullCycle;
        
        // find result
        double fee = ratio * baseFee;
        return new BigDecimal(fee).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
