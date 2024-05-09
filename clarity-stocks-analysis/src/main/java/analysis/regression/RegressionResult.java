package analysis.regression;

import org.apache.commons.math3.stat.regression.SimpleRegression;

/**
 * @author Olivia Svensson, Joar Eliasson
 */
public class RegressionResult {

  private final String variable;
  private final SimpleRegression simpleRegression;
  private final String description;
  private final PricePrediction pricePrediction;

  public RegressionResult(String variable, SimpleRegression simpleRegression,
      String description, PricePrediction pricePrediction) {
    this.variable = variable;
    this.simpleRegression = simpleRegression;
    this.description = description;
    this.pricePrediction = pricePrediction;
  }

  public String getVariable() {
    return variable;
  }

  public SimpleRegression getSimpleRegression() {
    return simpleRegression;
  }

  public String getDescription() {
    return description;
  }

  public PricePrediction getPricePrediction() {
    return pricePrediction;
  }
}
