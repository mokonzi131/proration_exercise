package com.example;

public class Main {
    public static void main(String[] args) {
        
        InputParameters parameters;
        try {
            parameters = new InputParameters(args);
        } catch(Exception e) {
            System.out.println("Usage: program fee cycle date");
            System.out.println("\tfee:\tshould be a positive value representing dollar amount (ex: 12.44)");
            System.out.println("\tcycle:\tshould be a positive integer between 1 and 31");
            System.out.println("\tdate:\tshould be a date in the ISO_LOCAL_DATE_TIME format (ex: 2015-01-15T12:30:24)");
            return;
        }
        
        double proratedFee = ProrationCalculator.calculateProrationFee(parameters);

        System.out.println("Prorated Fee is: $" + proratedFee);
    }
    
}