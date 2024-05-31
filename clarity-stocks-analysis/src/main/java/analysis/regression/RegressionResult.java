package analysis.regression;

import java.util.List;

/**
 * Class for storing the results of a regression analysis.
 * <p>
 * This class holds the regression coefficients, a description of the analysis, the price prediction,
 * observed values, and predicted values.
 * </p>
 *
 * <ul>
 *   <li>{@code variable} - The financial variable being analyzed.</li>
 *   <li>{@code coefficients} - The regression coefficients (intercept and slope).</li>
 *   <li>{@code description} - A textual description of the analysis.</li>
 *   <li>{@code pricePrediction} - The price prediction based on the regression analysis.</li>
 *   <li>{@code observedValues} - The observed stock prices.</li>
 *   <li>{@code predictedValues} - The predicted stock prices based on the regression model.</li>
 * </ul>
 *
 * @see PricePrediction
 * @see RegressionCalculator
 * @see RegressionAnalysis
 *
 * @author Joar Eliasson
 */
public class RegressionResult {

  private final String variable;
  private final double[] coefficients;
  private final String description;
  private final PricePrediction latestPrediction;
  private final List<PricePrediction> historicalPredictions;
  private final double[] observedValues;
  private final double[] predictedValues;
  private final String[] dates;

  /**
   * Constructs a RegressionResult object.
   *
   * @param variable the financial variable being analyzed
   * @param coefficients the regression coefficients
   * @param description the description of the analysis
   * @param latestPrediction the price prediction based on the regression analysis
   * @param observedValues the observed stock prices
   * @param predictedValues the predicted stock prices based on the regression model
   */
  public RegressionResult(String variable, double[] coefficients, String description,
      PricePrediction latestPrediction, List<PricePrediction> historicalPredictions, double[] observedValues, double[] predictedValues,
      String[] dates) {
    this.variable = variable;
    this.coefficients = coefficients;
    this.description = description;
    this.latestPrediction = latestPrediction;
    this.historicalPredictions = historicalPredictions;
    this.observedValues = observedValues;
    this.predictedValues = predictedValues;
    this.dates = dates;
  }

  public double[] getPredictionVsObserved() {
    double[] predictionVsObserved = new double[observedValues.length];
    for (int i = 0; i < observedValues.length; i++) {
      predictionVsObserved[i] = predictedValues[i] - observedValues[i];
    }
    return predictionVsObserved;
  }

  public String[] getDates() {
    return dates;
  }

  /**
   * Gets the financial variable being analyzed.
   *
   * @return the financial variable
   */
  public String getVariable() {
    return variable;
  }

  /**
   * Gets the regression coefficients.
   *
   * @return an array of regression coefficients
   */
  public double[] getCoefficients() {
    return coefficients;
  }

  public double[] getObservedValues() {
    return observedValues;
  }

  public double[] getPredictedValues() {
    return predictedValues;
  }

  /**
   * Gets the description of the analysis.
   *
   * @return the description of the analysis
   */
  public String getDescription() {
    return description;
  }

  /**
   * Gets the price prediction based on the regression analysis.
   *
   * @return the price prediction
   */
  public PricePrediction getLatestPrediction() {
    return latestPrediction;
  }

  /**
   * Gets the R-squared value of the regression model.
   *
   * @return the R-squared value
   */
  public double getRSquare() {
    double ssTotal = 0.0;
    double ssResidual = 0.0;
    double mean = 0.0;

    for (double observedValue : observedValues) {
      mean += observedValue;
    }
    mean /= observedValues.length;

    for (int i = 0; i < observedValues.length; i++) {
      ssTotal += Math.pow(observedValues[i] - mean, 2);
      ssResidual += Math.pow(observedValues[i] - predictedValues[i], 2);
    }

    return 1 - (ssResidual / ssTotal);
  }



}
