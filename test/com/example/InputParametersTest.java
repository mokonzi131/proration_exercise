package com.example;

import com.example.InputParameters;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import static org.junit.Assert.*;

public class InputParametersTest {

    @Test(expected=IllegalArgumentException.class)
    public void testInputLength() throws Exception {
        String[] params = {"30", "42"};
        new InputParameters(params);
    }
    
    @Test(expected=IllegalArgumentException.class)
     public void testBogusBaseFee() throws Exception {
        String[] params = {"world", "42", "2001-07-04T12:08:56"};
        new InputParameters(params);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testNegativeBaseFee() throws Exception {
        String[] params = {"-12.42", "42", "2001-07-04T12:08:56"};
        new InputParameters(params);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void testBogusCycleBillDay() throws Exception {
        String[] params = {"3.0", "4.2", "2001-07-04T12:08:56"};
        new InputParameters(params);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testLowCycleBillDay() throws Exception {
        String[] params = {"3.0", "0", "2001-07-04T12:08:56"};
        new InputParameters(params);
    }

    @Test(expected=IllegalArgumentException.class)
    public void testHighCycleBillDay() throws Exception {
        String[] params = {"3.0", "32", "2001-07-04T12:08:56"};
        new InputParameters(params);
    }
    
    @Test(expected=DateTimeParseException.class)
    public void testBogusDate() throws Exception {
        String[] params = {"3.0", "31", "hello"};
        new InputParameters(params);
    }

    @Test
    public void testGetBaseFee() throws Exception {
        final double ACCURACY = 0.001; // test base fee correct to the nearest penny
        
        String[] arguments = {"12.42", "18", "2001-07-04T12:08:56"};
        InputParameters input = new InputParameters(arguments);
        assertEquals(12.42, input.getBaseFee(), ACCURACY);
    }

    @Test
    public void testGetCycleBillDay() throws Exception {
        String[] arguments = {"12.42", "18", "2001-07-04T12:08:56"};
        InputParameters input = new InputParameters(arguments);
        assertEquals(18, input.getCycleBillDay());
    }

    @Test
    public void testGetPurchaseDate() throws Exception {
        String[] arguments = {"12.42", "18", "2001-07-04T12:08:56"};
        InputParameters input = new InputParameters(arguments);
        LocalDateTime dt = input.getPurchaseDate();
        assertEquals(2001, dt.getYear());
        assertEquals(7, dt.getMonthValue());
        assertEquals(4, dt.getDayOfMonth());
        assertEquals(12, dt.getHour());
        assertEquals(8, dt.getMinute());
        assertEquals(56, dt.getSecond());
    }
}