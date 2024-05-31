package analysis.interfaces;

import analysis.regression.PricePrediction;
import analysis.regression.RegressionCalculator;
import analysis.regression.RegressionResult;
import common.enums.IncomeStatementVariable;

/**
 * Interface for performing linear regression analysis on stock data.
 * <p>
 * This interface provides methods for analyzing stock data and obtaining detailed results of the
 * analysis.
 * </p>
 *
 * @see RegressionCalculator
 *
 * @author Joar Eliason
 * @author Olivia Svensson
 */
public interface LinearRegressions {

  long[] fetchVariableData(IncomeStatementVariable variable);

  double[] indexVariableData(long[] variableData);

  double[] createRegressionModel(double[] indexedVariableData);

  String generateDescription(double[] coefficients, String variableName);

  PricePrediction generatePrediction(double[] coefficients, String variableName, double variableData);

  RegressionResult runAnalysis(IncomeStatementVariable variable);
}
