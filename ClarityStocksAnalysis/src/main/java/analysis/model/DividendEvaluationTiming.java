package analysis.model;

/** Class for calculating the dividend of a stock for a certain fiscal year, and calculating the dividend yield for that particular year.
 * @author Olivia Svensson
 * */
public class DividendEvaluationTiming {
    String symbol;
    int fiscalYear;
    double dividendPerShare;
    double dividendYield;
    String fiscalYearEnd;
    double dividend;
    double closingPrice;
    String description = "";
    public DividendEvaluationTiming(String symbol, int fiscalYear, double dividendPerShare, double dividendYield, String fiscalYearEnd) {
        this.symbol = symbol;
        this.fiscalYear = fiscalYear;
        this.dividendPerShare = dividendPerShare;
        this.dividendYield = dividendYield;
        this.fiscalYearEnd = fiscalYearEnd;
    }

    //not sure if necessary
    private double calculateDividendForFiscalYear() {
        return dividend;
    }

    //calculates dividend yield. Can get from the fullstockoverview though, so maybe not necessarry?
    private double calculateDividendYield() {
        return (dividendPerShare/closingPrice) * 100;
    }

    public String getDescription() {
        return description = "The dividend per share for fiscal year " + fiscalYear + ": " + dividendPerShare + "\nThe dividend yield for fiscal year " + fiscalYear + ": " + dividendYield + "%";
    }
}
