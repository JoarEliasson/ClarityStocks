package analysis.regression;

import analysis.interfaces.LinearRegressions;
import common.enums.IncomeStatementVariable;
import common.data.fundamental.IncomeStatement;
import common.data.series.DailyDataPoint;
import common.data.series.TimeSeriesMonthly;
import java.util.List;
import org.apache.commons.math3.stat.regression.SimpleRegression;

/**
 * Class for implementing a linear regression. Linear regression is used for predicting the future
 * price of the stock by analyzing historical data. Uses an X and Y value which are data pairs.
 * Model implements Pearson's linear regression.
 *
 * @author Olivia Svensson, Joar Eliasson
 */
public class RegressionCalculator implements LinearRegressions {

  private final TimeSeriesMonthly timeSeriesMonthly;
  private final List<IncomeStatement> incomeStatements;
  private double[] adjustedPrices;

  /**
   * Constructor for the regression.RegressionCalculator class. Takes in an AlphaVantage Client and a stock
   * symbol.
   */
  public RegressionCalculator(List<IncomeStatement> incomeStatements,
      TimeSeriesMonthly timeSeriesMonthly) {

    this.incomeStatements = incomeStatements;
    this.timeSeriesMonthly = timeSeriesMonthly;
    fetchAdjustedData(getFiscalDateEndingMonth());
    reverseVariableData();
  }

  @Override
  public RegressionResult runRegression(IncomeStatementVariable variable) {
    double[] indexedVariableData = getIndexedVariableData(getVariableData(variable));
    SimpleRegression regression = getRegression(variable.name(), indexedVariableData);
    return new RegressionResult(
        variable,
        regression,
        fetchDescription(regression, variable.name()),
        fetchPricePrediction(variable, indexedVariableData[indexedVariableData.length - 1], regression)
    );
  }

  /**
   * Method for setting the closing prices of a stock. Has a for-loop which loops through all the
   * close prices and sets the double array with the closing prices  at that particular index to the
   * adjusted closing price of the stock. The adjusted stock price is necessary as there could be
   * stock-splits which means that a company divides their stocks so that more shares are created,
   * making the price of the stock lower. If this is not considered, the analysis will be incorrect.
   * The adjusted close means that the historical prices are compared to the current prices and
   * takes into consideration stock-splits.
   */
  @Override
  public void fetchAdjustedData(String fiscalDateEndingMonth) {
    List<DailyDataPoint> closePrices = timeSeriesMonthly.getMonthlyClosePrices(
        fiscalDateEndingMonth,
        incomeStatements.size());
    adjustedPrices = new double[closePrices.size()];
    for (int i = 0; i < closePrices.size(); i++) {
      adjustedPrices[i] = closePrices.get(i).getAdjustedClose();
    }
  }

  /**
   * Method for indexing the net income of a stock. Loops through a double array and calculates the
   * index value by dividing each net income value by the first net income value. This indexes each
   * net income value to the first value in the array. For each iteration the index value gets
   * rounded to two decimal places, which is done by multiplying the index value by 100, which
   * rounds it to the nearest integer, then it divides it back by 100 to obtain the rounded value
   * with two decimal places. The index income is then set to the rounded value in the array.
   */
  @Override
  public double[] getIndexedVariableData(long[] variableData) {
    double[] indexedVariableData = new double[variableData.length];
    for (int i = 0; i < indexedVariableData.length; i++) {
      double indexValue = (double) variableData[i] / variableData[0];
      double roundedValue = Math.round(indexValue * 100.0) / 100.0;
      indexedVariableData[i] = roundedValue;
    }
    return indexedVariableData;
  }

  @Override
  public SimpleRegression getRegression(String variableName, double[] indexedVariableData) {
    SimpleRegression regression = new SimpleRegression();
    for (int i = 0; i < adjustedPrices.length; i++) {
      regression.addData(indexedVariableData[i], adjustedPrices[i]);
    }
    fetchDescription(regression, variableName);
    return regression;
  }

  @Override
  public long[] getVariableData(IncomeStatementVariable variable) {
    long[] variableData = new long[incomeStatements.size()];
    for (int i = 0; i < incomeStatements.size(); i ++) {
      variableData[i] = incomeStatements.get(i).getVariable(variable.name());
    }
    return variableData;
  }

  private String getFiscalDateEndingMonth() {
    return incomeStatements.getLast().getFiscalDateEnding().split("-")[1];
  }

  /**
   * Method for sorting the income statements by reversing them, as we want the latest income
   * statement to be first in the list.
   */
  @Override
  public void reverseVariableData() {
    incomeStatements.reversed();
  }

  /**
   * Method for predicting the price of the stock.
   *
   * @return the predicted y-value associated with the supplied x-value, based on data that has
   * been added to the model when the method is activated.
   */
  @Override
  public PricePrediction fetchPricePrediction(IncomeStatementVariable variable, double indexedVariableData,
      SimpleRegression regression) {
    double prediction = regression.predict(indexedVariableData);
    String description =  String.format("Price prediction for %s: %.2f$ R^2: %.2f", variable.name(),
        prediction, regression.getRSquare()
    );

    return new PricePrediction(
        timeSeriesMonthly.getSymbol(),
        variable.name(),
        adjustedPrices[adjustedPrices.length - 1],
        prediction,
        description
    );

  }

  /**
   * Method for getting a simple description of the linear regression analysis of a specific stock.
   *
   * @return a string with the description of the linear regression analysis.
   */
  @Override
  public String fetchDescription(SimpleRegression linearRegression, String variableName) {
    String percentCharacter = "%";
    return String.format("The R value for the variable %s is: %.2f.%n"
        + "R is the Pearson's product moment correlation coefficient. It measures the linear"
        + "relationship between two variables. %nThe coefficient ranges from -1 to 1, where: %n -1"
        + "indicates a perfect negative linear relationship.%n 1 indicates a perfect positive"
        + "linear relationship.%n 0 indicates no linear relationship.%n The R-square is: %.2f.%n"
        + "R-square is the coefficient of determination. It represents the proportion of the"
        + "variance in the dependent variable that is predictable from the independent variables."
        + "It indicates how well the independent variables explain the variability of the dependent"
        + "variable.%n R-square values range from 0 to 1, where: %n 0 indicates that the "
        + "independent variables do not explain any of the variability of the dependent variable.%n"
        + "1 indicates that the independent variables perfectly explain all the variability of the"
        + "dependent variable.%n The slope is: %.2f.%n The slope represents the rate of change in"
        + "the dependent variable for a one-unit change in the independent variable. It quantifies"
        + "the effect of the independent variable on the dependent variable.%n The significance is:"
        + "%.2f.%n Significance is the statistical significance of the estimated coefficients. It"
        + "indicated whether these coefficients are reliably different from 0.%n A significance"
        + "level less than 0.05 / 5%s indicates that the coefficient is statistically significant,"
        + "suggesting that there is sufficient evidence to reject the null hypothesis that the"
        + "coefficient is equal to 0.",
        variableName, linearRegression.getR(), linearRegression.getRSquare(),
        linearRegression.getSlope(), linearRegression.getSignificance(), percentCharacter
    );
  }
}
