package analysis.regression;

import common.enums.IncomeStatementVariable;
import org.apache.commons.math3.stat.regression.SimpleRegression;

/**
 * @author Olivia Svensson, Joar Eliasson
 */
public class RegressionResult {

  private final IncomeStatementVariable variable;
  private final SimpleRegression simpleRegression;
  private final String description;
  private final String prediction;

  public RegressionResult(IncomeStatementVariable variable, SimpleRegression simpleRegression,
      String description, String prediction) {
    this.variable = variable;
    this.simpleRegression = simpleRegression;
    this.description = description;
    this.prediction = prediction;
  }

  public IncomeStatementVariable getVariable() {
    return variable;
  }

  public SimpleRegression getSimpleRegression() {
    return simpleRegression;
  }

  public String getDescription() {
    return description;
  }

  public String getPrediction() {
    return prediction;
  }
}
