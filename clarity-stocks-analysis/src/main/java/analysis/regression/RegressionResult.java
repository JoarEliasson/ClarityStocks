package analysis.regression;

/**
 * @author Olivia Svensson, Joar Eliasson
 */
public class RegressionResult {

  private final String variable;
  private final double[] coefficients;
  private final String description;
  private final PricePrediction pricePrediction;
  private final double[] observedValues;
  private final double[] predictedValues;

  public RegressionResult(String variable, double[] coefficients,
      String description, PricePrediction pricePrediction, double[] observedValues, double[] predictedValues) {
    this.variable = variable;
    this.coefficients = coefficients;
    this.description = description;
    this.pricePrediction = pricePrediction;
    this.observedValues = observedValues;
    this.predictedValues = predictedValues;
  }

  public String getVariable() {
    return variable;
  }

  public double[] getCoefficients() {
    return coefficients;
  }

  public String getDescription() {
    return description;
  }

  public PricePrediction getPricePrediction() {
    return pricePrediction;
  }

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