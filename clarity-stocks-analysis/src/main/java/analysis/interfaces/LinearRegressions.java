package analysis.interfaces;

import analysis.regression.PricePrediction;
import analysis.regression.RegressionResult;
import common.enums.IncomeStatementVariable;


/**
 * Interface which all the linear regression calculator classes implements. The interface contains
 * methods which can be applied generally for linear regressions to be made.
 *
 * @author Olivia Svensson, Joar Eliasson
 */
public interface LinearRegressions {

  long[] fetchVariableData(IncomeStatementVariable variable);

  double[] indexVariableData(long[] variableData);

  double[] createRegressionModel(double[] indexedVariableData);

  String generateDescription(double[] coefficients, String variableName);

  PricePrediction generatePrediction(double[] coefficients, String variableName, double variableData);

  RegressionResult runAnalysis(IncomeStatementVariable variable);
}
