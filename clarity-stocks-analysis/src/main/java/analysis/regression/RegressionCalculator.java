package analysis.regression;

import analysis.interfaces.LinearRegressions;
import common.data.fundamental.IncomeStatement;
import common.enums.IncomeStatementVariable;
import java.util.ArrayList;
import java.util.List;
import org.jfree.data.statistics.Regression;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


/**
 * Class for implementing linear regression analysis.
 * <p>
 * Linear regression is used for predicting the future price of the stock by analyzing historical data.
 * This class uses Pearson's linear regression model to determine the relationship between stock price
 * and various financial variables.
 * </p>
 *
 * <ul>
 *   <li>{@code incomeStatements} - List of income statements for the stock.</li>
 *   <li>{@code priceData} - Array of historical stock prices.</li>
 * </ul>
 *
 * @see analysis.interfaces.LinearRegressions
 * @see common.data.fundamental.IncomeStatement
 * @see common.enums.IncomeStatementVariable
 * @author Joar Eliasson
 * @author Olivia Svensson
 */
public class RegressionCalculator implements LinearRegressions {

  private final List<IncomeStatement> incomeStatements;
  private final double[] priceData;

  /**
   * Constructs a RegressionCalculator object.
   *
   * @param incomeStatements the list of income statements
   * @param priceData the array of historical stock prices
   */
  public RegressionCalculator(List<IncomeStatement> incomeStatements, double[] priceData) {
    this.incomeStatements = incomeStatements;
    this.priceData = priceData;
  }

  /**
   * Fetches the data for a specific income statement variable.
   *
   * @param variable the income statement variable
   * @return an array of data for the specified variable
   */
  @Override
  public long[] fetchVariableData(IncomeStatementVariable variable) {
    long[] variableData = new long[incomeStatements.size()];
    for (int i = 0; i < incomeStatements.size(); i++) {
      variableData[i] = incomeStatements.get(i).getVariable(variable.name());
    }
    return variableData;
  }

  /**
   * Indexes the variable data to create a consistent dataset for analysis.
   *
   * @param variableData the array of variable data
   * @return an array of indexed variable data
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

  /**
   * Creates a regression model using the indexed variable data.
   *
   * @param indexedVariableData the array of indexed variable data
   * @return an array of regression coefficients
   */
  public double[] createRegressionModel(double[] indexedVariableData) {
    XYSeries series = new XYSeries("Regression Data");
    for (int i = 0; i < indexedVariableData.length; i++) {
      series.add(indexedVariableData[i], priceData[i]);
    }
    XYSeriesCollection dataset = new XYSeriesCollection(series);
    double[] coefficients = Regression.getOLSRegression(dataset, 0);

    return coefficients;
  }

  /**
   * Generates a simple description of the regression analysis.
   *
   * @param coefficients the regression coefficients
   * @param variableName the name of the variable
   * @return a string description of the regression analysis
   */
  private String generateSimpleDescription(double[] coefficients, String variableName) {
    double slope = coefficients[1];
    double intercept = coefficients[0];
    return String.format("Variable: %s%nIntercept: %.2f%nSlope: %.2f%n",
        variableName, intercept, slope);
  }

  /**
   * Generates a detailed description of the regression analysis.
   *
   * @param coefficients the regression coefficients
   * @param variableName the name of the variable
   * @return a string description of the regression analysis
   */
  @Override
  public String generateDescription(double[] coefficients, String variableName) {
    double slope = coefficients[1];
    double intercept = coefficients[0];
    return String.format("The slope for the variable %s is: %.2f.%n"
            + "The intercept is: %.2f.%n The slope represents the rate of change in"
            + "the dependent variable for a one-unit change in the independent variable. It quantifies"
            + "the effect of the independent variable on the dependent variable.%n",
        variableName, slope, intercept);
  }

  /**
   * Generates a price prediction based on the regression analysis.
   *
   * @param coefficients the regression coefficients
   * @param variableName the name of the variable
   * @param variableData the data for the variable
   * @return a PricePrediction object containing the prediction
   */
  @Override
  public PricePrediction generatePrediction(double[] coefficients, String variableName, double variableData) {
    double intercept = coefficients[0];
    double slope = coefficients[1];
    double predictedPrice = intercept + slope * variableData;
    return new PricePrediction(
        variableName,
        incomeStatements.getLast().getFiscalDateEnding(),
        priceData[priceData.length - 1],
        predictedPrice,
        "The predicted price is calculated by the linear regression model. The model uses the"
            + "historical data of the stock to predict the future price of the stock. The prediction"
            + "is based on the relationship between the independent variable and the dependent"
            + "variable. The model uses the slope of the regression line to predict the future price"
            + "of the stock."
    );
  }


  private List<PricePrediction> generateHistoricalPredictions(double[] coefficients, String name, double[] indexedVariableData) {
    List<PricePrediction> historicalPredictions = new ArrayList<>();
    for (int i = 0; i < indexedVariableData.length; i++) {
      double intercept = coefficients[0];
      double slope = coefficients[1];
      double predictedPrice = intercept + slope * indexedVariableData[i];
      historicalPredictions.add(new PricePrediction(
          name,
          incomeStatements.get(i).getFiscalDateEnding(),
          priceData[i],
          predictedPrice,
          "The predicted price is calculated by the linear regression model. The model uses the"
              + "historical data of the stock to predict the future price of the stock. The prediction"
              + "is based on the relationship between the independent variable and the dependent"
              + "variable. The model uses the slope of the regression line to predict the future price"
              + "of the stock."
      ));
    }
    return historicalPredictions;
  }

  /**
   * Runs the regression analysis for a specific variable.
   *
   * @param variable the income statement variable
   * @return a RegressionResult object containing the analysis results
   */
  @Override
  public RegressionResult runAnalysis(IncomeStatementVariable variable) {
    double[] indexedVariableData = indexVariableData(fetchVariableData(variable));
    double[] coefficients = createRegressionModel(indexedVariableData);
    return new RegressionResult(
        variable.name(),
        coefficients,
        generateSimpleDescription(coefficients, variable.name()),
        generatePrediction(coefficients, variable.name(), indexedVariableData[indexedVariableData.length - 1]),
        generateHistoricalPredictions(coefficients, variable.name(), indexedVariableData),
        priceData,
        calculatePredictedValues(coefficients, indexedVariableData),
        extractDates()

    );
  }

  /**
   * Calculates the predicted values based on the regression coefficients.
   *
   * @param coefficients the regression coefficients
   * @param indexedVariableData the indexed variable data
   * @return an array of predicted values
   */
  private double[] calculatePredictedValues(double[] coefficients, double[] indexedVariableData) {
    double intercept = coefficients[0];
    double slope = coefficients[1];
    double[] predictedValues = new double[indexedVariableData.length];
    for (int i = 0; i < indexedVariableData.length; i++) {
      predictedValues[i] = intercept + slope * indexedVariableData[i];
    }
    return predictedValues;
  }

  private String[] extractDates() {
    String[] dates = new String[incomeStatements.size()];
    for (int i = 0; i < incomeStatements.size(); i++) {
      dates[i] = incomeStatements.get(i).getFiscalDateEnding();
    }
    return dates;
  }
}