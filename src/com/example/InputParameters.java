package com.example;

import java.text.ParseException;
import java.time.LocalDateTime;

public class InputParameters {
    private double m_baseFee;
    private int m_cycleBillDay;
    private LocalDateTime m_purchaseDate;
    
    public InputParameters(String[] args) throws ParseException {
        if (args.length < 3)
            throw new IllegalArgumentException("input needs at least three arguments");
        
        // find parameters from input array
        String baseFeeParameter = args[0];
        String cycleBillDayParameter = args[1];
        String purchaseDate = args[2];

        // convert parameters to expected internal values
        m_baseFee = Double.parseDouble(baseFeeParameter);
        if (m_baseFee < 0)
            throw new IllegalArgumentException("base fee should be a positive value");
        
        m_cycleBillDay = Integer.parseInt(cycleBillDayParameter);
        if (m_cycleBillDay <= 0 || m_cycleBillDay > 31)
            throw new IllegalArgumentException("billing cycle day should fall within the month [1,31]");
        
        m_purchaseDate = LocalDateTime.parse(purchaseDate);
    }
    
    public double getBaseFee() {
        return m_baseFee;        
    }
    
    public int getCycleBillDay() {
        return m_cycleBillDay;        
    }
    
    public LocalDateTime getPurchaseDate() {
        return m_purchaseDate;
    }
}
