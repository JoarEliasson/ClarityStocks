package analysis.regression;

import analysis.interfaces.LinearRegressions;
import common.enums.IncomeStatementVariable;
import common.data.fundamental.IncomeStatement;
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


  private final List<IncomeStatement> incomeStatements;
  private final double[] priceData;
  private double[] indexedVariableData;

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
    for (int i = 0; i < incomeStatements.size(); i ++) {
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

  @Override
  public SimpleRegression createRegressionModel(String variableName, double[] indexedVariableData) {
    SimpleRegression regression = new SimpleRegression();
    for (int i = 0; i < indexedVariableData.length; i++) {
      regression.addData(indexedVariableData[i], priceData[i]);
    }
    return regression;
  }

  private String generateSimpleDescription(SimpleRegression regression, String variableName) {
    return String.format("Variable: %s%nR: %.2f%nR-square: %.2f%n",
        variableName, regression.getR(), regression.getRSquare()
    );
  }

  /**
   * Method for getting a simple description of the linear regression analysis of a specific stock.
   *
   * @return a string with the description of the linear regression analysis.
   */
  @Override
  public String generateDescription(SimpleRegression linearRegression, String variableName) {
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

  @Override
  public PricePrediction generatePrediction(SimpleRegression regression, String variableName, double variableData) {
    double predictedPrice = regression.predict(variableData);
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

  @Override
  public RegressionResult runAnalysis(IncomeStatementVariable variable) {
    double[] indexedVariableData = indexVariableData(fetchVariableData(variable));
    SimpleRegression regression = createRegressionModel(variable.name(), indexedVariableData);
    return new RegressionResult(
        variable.name(),
        regression,
        generateSimpleDescription(regression, variable.name()),
        //generateDescription(regression, variable.name()),
        generatePrediction(regression, variable.name(),
            indexedVariableData[indexedVariableData.length - 1]
        )
    );
  }
}
