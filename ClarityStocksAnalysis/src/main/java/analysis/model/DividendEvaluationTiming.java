package analysis.model;

/** Class for calculating the dividend of a stock for a certain fiscal year, and calculating the dividend yield for that particular year.
 * @author Olivia Svensson
 * */
public class DividendEvaluationTiming {
    String symbol;
    int fiscalYear;
    double dividendPerShare;
    long dividendYield;
    long fiscalYearEnd;
    double dividend;
    double closingPrice;
    String description = "";
    public DividendEvaluationTiming(String symbol, int fiscalYear, double dividendPerShare, long dividendYield, long fiscalYearEnd) {
        this.symbol = symbol;
        this.fiscalYear = fiscalYear;
        this.dividendPerShare = dividendPerShare;
        this.dividendYield = dividendYield;
        this.fiscalYearEnd = fiscalYearEnd;
    }

    private double calculateDividendForFiscalYear() {

        return dividend;
    }

    private double calculateDividendYield() {
        return (dividendPerShare/closingPrice) * 100;
    }

    public String getDescription() {
        return description = "The dividend per share for fiscal year " + fiscalYear + ": " + dividendPerShare + "\nThe dividend yield for fiscal year " + fiscalYear + ": " + dividendYield + "%";
    }
}
