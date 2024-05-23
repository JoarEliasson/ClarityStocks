package analysis.regression;

import analysis.interfaces.LinearRegressions;
import common.data.fundamental.IncomeStatement;
import common.enums.IncomeStatementVariable;
import java.util.List;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/**
 * Class for implementing a linear regression. Linear regression is used for predicting the future
 * price of the stock by analyzing historical data. Uses an X and Y value which are data pairs.
 * Model implements Pearson's linear regression.
 *
 * @author Olivia Svensson, Joar Eliasson
 */
public class RegressionCalculator implements LinearRegressions {

  private final List<IncomeStatement> incomeStatements;
  private final double[] priceData;

  /**
   * Constructor for the regression.RegressionCalculator class. Takes in an AlphaVantage Client and a stock
   * symbol.
   */
  public RegressionCalculator(List<IncomeStatement> incomeStatements, double[] priceData) {
    this.incomeStatements = incomeStatements;
    this.priceData = priceData;
  }

  @Override
  public long[] fetchVariableData(IncomeStatementVariable variable) {
    long[] variableData = new long[incomeStatements.size()];
    for (int i = 0; i < incomeStatements.size(); i++) {
      variableData[i] = incomeStatements.get(i).getVariable(variable.name());
    }
    return variableData;
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
  public double[] indexVariableData(long[] variableData) {
    double[] indexedVariableData = new double[variableData.length];
    for (int i = 0; i < indexedVariableData.length; i++) {
      double indexValue = (double) variableData[i] / variableData[0];
      double roundedValue = Math.round(indexValue * 100.0) / 100.0;
      indexedVariableData[i] = roundedValue;
    }
    return indexedVariableData;
  }

  public double[] createRegressionModel(double[] indexedVariableData) {
    XYSeries series = new XYSeries("Regression Data");
    for (int i = 0; i < indexedVariableData.length; i++) {
      series.add(indexedVariableData[i], priceData[i]);
    }
    XYSeriesCollection dataset = new XYSeriesCollection(series);
    double[] coefficients = Regression.getOLSRegression(dataset, 0);

    return coefficients;
  }

  private String generateSimpleDescription(double[] coefficients, String variableName) {
    double slope = coefficients[1];
    double intercept = coefficients[0];
    return String.format("Variable: %s%nIntercept: %.2f%nSlope: %.2f%n",
        variableName, intercept, slope);
  }

  /**
   * Method for getting a simple description of the linear regression analysis of a specific stock.
   *
   * @return a string with the description of the linear regression analysis.
   */
  @Override
  public String generateDescription(double[] coefficients, String variableName) {
    double slope = coefficients[1];
    double intercept = coefficients[0];
    String percentCharacter = "%";
    return String.format("The slope for the variable %s is: %.2f.%n"
            + "The intercept is: %.2f.%n The slope represents the rate of change in"
            + "the dependent variable for a one-unit change in the independent variable. It quantifies"
            + "the effect of the independent variable on the dependent variable.%n",
        variableName, slope, intercept);
  }

  @Override
  public PricePrediction generatePrediction(double[] coefficients, String variableName, double variableData) {
    double intercept = coefficients[0];
    double slope = coefficients[1];
    double predictedPrice = intercept + slope * variableData;
    return new PricePrediction(
        variableName,
        incomeStatements.get(incomeStatements.size() - 1).getFiscalDateEnding(),
        priceData[priceData.length - 1],
        predictedPrice,
        "The predicted price is calculated by the linear regression model. The model uses the"
            + "historical data of the stock to predict the future price of the stock. The prediction"
            + "is based on the relationship between the independent variable and the dependent"
            + "variable. The model uses the slope of the regression line to predict the future price"
            + "of the stock."
    );
  }

  @Override
  public RegressionResult runAnalysis(IncomeStatementVariable variable) {
    double[] indexedVariableData = indexVariableData(fetchVariableData(variable));
    double[] coefficients = createRegressionModel(indexedVariableData);
    return new RegressionResult(
        variable.name(),
        coefficients,
        generateSimpleDescription(coefficients, variable.name()),
        generatePrediction(coefficients, variable.name(), indexedVariableData[indexedVariableData.length - 1]),
        priceData,
        calculatePredictedValues(coefficients, indexedVariableData)
    );
  }

  private double[] calculatePredictedValues(double[] coefficients, double[] indexedVariableData) {
    double intercept = coefficients[0];
    double slope = coefficients[1];
    double[] predictedValues = new double[indexedVariableData.length];
    for (int i = 0; i < indexedVariableData.length; i++) {
      predictedValues[i] = intercept + slope * indexedVariableData[i];
    }
    return predictedValues;
  }
}