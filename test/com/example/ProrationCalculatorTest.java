package com.example;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProrationCalculatorTest {
    private InputParameters m_parameters;
    
    @Before
    public void setUp() throws Exception {
        String[] arguments = {"12.42", "18", "2001-07-04T12:08:56"};
        m_parameters = new InputParameters(arguments);
    }

    @Test
    public void testCalculateProrationFee() throws Exception {
        
    }

}