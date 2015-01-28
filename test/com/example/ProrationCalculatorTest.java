package com.example;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProrationCalculatorTest {
    private static double ACCURACY = 0.001;
    
    @Test
    public void testSimpleCalculateProrationFee() throws Exception {
        String[] arguments = {"31.00", "1", "2015-01-01T12:00:00"};
        InputParameters parameters = new InputParameters(arguments);
        assertEquals(31, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);
        
        arguments = new String[]{"28.00", "1", "2015-02-01T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(28, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);

        arguments = new String[]{"30.00", "1", "2015-04-01T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(30, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);
    }
    
    @Test
    public void testServiceFeeValues() throws Exception {
        String[] arguments = {"30.00", "1", "2015-04-01T12:00:00"};
        InputParameters parameters = new InputParameters(arguments);
        assertEquals(30, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);

        arguments = new String[]{"15.00", "1", "2015-04-01T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(15.00, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);

        arguments = new String[]{"0.00", "1", "2015-04-01T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(0.00, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);

        arguments = new String[]{"0.31", "1", "2015-04-01T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(0.31, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);
    }
    
    @Test
    public void testPurchaseDateValues() throws Exception {
        String[] arguments = {"30.00", "1", "2015-04-02T12:00:00"};
        InputParameters parameters = new InputParameters(arguments);
        assertEquals(29, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);

        arguments = new String[]{"30.00", "1", "2015-04-03T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(28, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);

        arguments = new String[]{"30.00", "1", "2015-04-29T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(2, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);

        arguments = new String[]{"30.00", "1", "2015-04-29T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(2, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);

        arguments = new String[]{"30.00", "1", "2015-04-30T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(1, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);

        arguments = new String[]{"30.00", "1", "2015-04-01T00:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(30, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);

        arguments = new String[]{"30.00", "1", "2015-04-01T23:59:00"};
        parameters = new InputParameters(arguments);
        assertEquals(30, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);
    }
    
    @Test public void testBillingCycleValues() throws Exception {
        String[] arguments = {"31.00", "30", "2015-04-01T12:00:00"};
        InputParameters parameters = new InputParameters(arguments);
        assertEquals(29, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);

        arguments = new String[]{"31.00", "29", "2015-04-01T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(28, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);

        arguments = new String[]{"31.00", "2", "2015-04-01T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(1, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);
    }
    
    @Test public void testRoundingToNearestPenny() throws Exception {
        String[] arguments = new String[]{"0.01", "1", "2015-04-01T12:00:00"};
        InputParameters parameters = new InputParameters(arguments);
        assertEquals(0.01, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);

        arguments = new String[]{"0.01", "1", "2015-04-10T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(0.01, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);


        arguments = new String[]{"0.01", "1", "2015-04-20T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(0.00, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);
    }
    
    @Test public void testBillingDayNotInMonth() throws Exception {
        // billing day is (last day) in month
        String[] arguments = {"31.00", "31", "2015-01-25T12:00:00"};
        InputParameters parameters = new InputParameters(arguments);
        assertEquals(6, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);

        arguments = new String[]{"31.00", "30", "2015-04-25T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(5, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);

        arguments = new String[]{"31.00", "28", "2015-02-25T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(3, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);

        // billing day is off by one (31-30, 29-28)
        arguments = new String[]{"30.00", "31", "2015-04-25T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(5, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);

        arguments = new String[]{"30.00", "29", "2015-02-25T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(3, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);
        
        // billing day is off by two (30-28)
        arguments = new String[]{"29.00", "30", "2015-02-25T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(3, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);

        // billing day is off by three (31-28)
        arguments = new String[]{"28.00", "31", "2015-02-25T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(3, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);
        
        // leap year (31-29)
        arguments = new String[]{"29.00", "31", "2016-02-25T12:00:00"};
        parameters = new InputParameters(arguments);
        assertEquals(4, ProrationCalculator.calculateProrationFee(parameters), ACCURACY);
    }
}