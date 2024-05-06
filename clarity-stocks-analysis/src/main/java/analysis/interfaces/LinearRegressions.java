package analysis.interfaces;

import analysis.regression.PricePrediction;
import analysis.regression.RegressionResult;
import common.enums.IncomeStatementVariable;
import org.apache.commons.math3.stat.regression.SimpleRegression;

/**
 * Interface which all the linear regression calculator classes implements. The interface contains
 * methods which can be applied generally for linear regressions to be made.
 *
 * @author Olivia Svensson, Joar Eliasson
 */
public interface LinearRegressions {

  long[] fetchVariableData(IncomeStatementVariable variable);

  double[] indexVariableData(long[] variableData);

  SimpleRegression createRegressionModel(String variableName, double[] indexedVariableData);

  String generateDescription(SimpleRegression regression, String variableName);

  PricePrediction generatePrediction(SimpleRegression regression, String variableName, double variableData);

  RegressionResult runAnalysis(IncomeStatementVariable variable);
}
