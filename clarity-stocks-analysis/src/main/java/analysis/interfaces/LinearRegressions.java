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

  void fetchAdjustedData(String fiscalDateEnding);

  void reverseVariableData();

  long[] getVariableData(IncomeStatementVariable variable);

  double[] getIndexedVariableData(long[] variableData);

  SimpleRegression getRegression(String variableName, double[] indexedVariableData);

  String fetchDescription(SimpleRegression regression, String variableName);

  PricePrediction fetchPricePrediction(IncomeStatementVariable variable, double indexedVariableData,
      SimpleRegression regression);

  RegressionResult runRegression(IncomeStatementVariable variable);
}
