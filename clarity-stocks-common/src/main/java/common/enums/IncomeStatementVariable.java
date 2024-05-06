package common.enums;

/**
 * @author Olivia Svensson, Joar Eliasson
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
