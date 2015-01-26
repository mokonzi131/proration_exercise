import java.util.Date;

/**
 * Created by MLandes on 1/26/2015.
 */
public class InputParameters {
    private double m_baseFee;
    private int m_cycleBillDay;
    private Date m_purchaseDate;
    
    public InputParameters(String[] args) {
        // TODO compute real values based on args
        m_baseFee = 0.0;
        m_cycleBillDay = 1;
        m_purchaseDate = new Date();        
    }
    
    public double getBaseFee() {
        return m_baseFee;        
    }
    
    public int getCycleBillDay() {
        return m_cycleBillDay;        
    }
    
    public Date getPurchaseDate() {
        return m_purchaseDate;
    }
}
