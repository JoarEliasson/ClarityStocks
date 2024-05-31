package common.enums;

import common.data.fundamental.IncomeStatement;

/**
 * Enum for income statement variables.
 * <p>
 * This enum holds the different variables that can be found in an income statement.
 * </p>
 *
 * <p>
 * This class is used to loop through the different variables in an income statement.
 * </p>
 *
 * @see IncomeStatement
 *
 * @author Joar Eliason
 * @author Olivia Svensson
 */
public enum IncomeStatementVariable {

  grossProfit,
  totalRevenue,
  costOfRevenue,
  costOfGoodsAndServicesSold,
  operatingIncome,
  sellingGeneralAndAdministrative,
  researchAndDevelopment,
  operatingExpenses,
  investmentIncomeNet,
  netInterestIncome,
  interestIncome,
  interestExpense,
  nonInterestIncome,
  otherNonOperatingIncome,
  depreciation,
  depreciationAndAmortization,
  incomeBeforeTax,
  incomeTaxExpense,
  interestAndDebtExpense,
  netIncomeFromContinuingOperations,
  comprehensiveIncomeNetOfTax,
  ebit,
  ebitda,
  netIncome;

  public IncomeStatementVariable[] getValues() {
    return IncomeStatementVariable.values();
  }

  public int getLength() {
    return IncomeStatementVariable.values().length;
  }

}
