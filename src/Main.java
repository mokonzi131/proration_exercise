/**
 * Created by MLandes on 1/26/2015.
 */

public class Main {
    public static void main(String[] args) {
        InputParameters input = new InputParameters(args);
        double proratedFee = ProrationCalculator.calculateProrationFee(
                input.getBaseFee(),
                input.getCycleBillDay(),
                input.getPurchaseDate());
        
        System.out.println("Prorated Fee is: " + proratedFee);
    }
    
}