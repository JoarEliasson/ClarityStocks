package alphaVantage.regression;

/**
 * Interface which all the linear regression classes implements. The interface contains methods which can be applied
 * generally for linear regressions to be made.
 *
 * not sure yet if this interface should be implemented
 * @author Olivia Svensson
 * */
public interface LinearRegressions {
    public void setIncomeStatements(String symbol);
    public void setClosePrices();
    public void reverseIncomeStatements();
    public void setIncomeData();
    public void indexIncomeData();
    public void setTimeSeriesMonthly(String symbol);
    public void linearRegressionIncomeData();
    public String getDescription();
    }
