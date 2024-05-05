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
  private final PricePrediction pricePrediction;

  public RegressionResult(IncomeStatementVariable variable, SimpleRegression simpleRegression,
      String description, PricePrediction pricePrediction) {
    this.variable = variable;
    this.simpleRegression = simpleRegression;
    this.description = description;
    this.pricePrediction = pricePrediction;
    //System.out.println(pricePrediction.getDescription());
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

  public PricePrediction getPricePrediction() {
    return pricePrediction;
  }
}
