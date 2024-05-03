package common.data.fundamental;

public class IncomeStatement {

  private final String symbol;
  private String fiscalDateEnding;
  private String reportedCurrency;
  private long grossProfit;
  private long totalRevenue;
  private long costOfRevenue;
  private long costofGoodsAndServicesSold;
  private long operatingIncome;
  private long sellingGeneralAndAdministrative;
  private long researchAndDevelopment;
  private long operatingExpenses;
  private long investmentIncomeNet;
  private long netInterestIncome;
  private long interestIncome;
  private long interestExpense;
  private long nonInterestIncome;
  private long otherNonOperatingIncome;
  private long depreciation;
  private long depreciationAndAmortization;
  private long incomeBeforeTax;
  private long incomeTaxExpense;
  private long interestAndDebtExpense;
  private long netIncomeFromContinuingOperations;
  private long comprehensiveIncomeNetOfTax;
  private long ebit;
  private long ebitda;
  private long netIncome;

  public IncomeStatement(String symbol) {
    this.symbol = symbol;
  }

  public long getVariable(String variableName) {
    switch (variableName) {
      case "grossProfit":
        return grossProfit;
      case "totalRevenue":
        return totalRevenue;
      case "costOfRevenue":
        return costOfRevenue;
      case "costofGoodsAndServicesSold":
        return costofGoodsAndServicesSold;
      case "operatingIncome":
        return operatingIncome;
      case "sellingGeneralAndAdministrative":
        return sellingGeneralAndAdministrative;
      case "researchAndDevelopment":
        return researchAndDevelopment;
      case "operatingExpenses":
        return operatingExpenses;
      case "investmentIncomeNet":
        return investmentIncomeNet;
      case "netInterestIncome":
        return netInterestIncome;
      case "interestIncome":
        return interestIncome;
      case "interestExpense":
        return interestExpense;
      case "nonInterestIncome":
        return nonInterestIncome;
      case "otherNonOperatingIncome":
        return otherNonOperatingIncome;
      case "depreciation":
        return depreciation;
      case "depreciationAndAmortization":
        return depreciationAndAmortization;
      case "incomeBeforeTax":
        return incomeBeforeTax;
      case "incomeTaxExpense":
        return incomeTaxExpense;
      case "interestAndDebtExpense":
        return interestAndDebtExpense;
      case "netIncomeFromContinuingOperations":
        return netIncomeFromContinuingOperations;
      case "comprehensiveIncomeNetOfTax":
        return comprehensiveIncomeNetOfTax;
      case "ebit":
        return ebit;
      case "ebitda":
        return ebitda;
      case "netIncome":
        return netIncome;
      default:
        return 0;
    }
  }

  public String getSymbol() {
    return symbol;
  }

  public String getFiscalDateEnding() {
    return fiscalDateEnding;
  }

  public void setFiscalDateEnding(String fiscalDateEnding) {
    this.fiscalDateEnding = fiscalDateEnding;
  }

  public String getReportedCurrency() {
    return reportedCurrency;
  }

  public void setReportedCurrency(String reportedCurrency) {
    this.reportedCurrency = reportedCurrency;
  }

  public long getGrossProfit() {
    return grossProfit;
  }

  public void setGrossProfit(long grossProfit) {
    this.grossProfit = grossProfit;
  }

  public long getTotalRevenue() {
    return totalRevenue;
  }

  public void setTotalRevenue(long totalRevenue) {
    this.totalRevenue = totalRevenue;
  }

  public long getCostOfRevenue() {
    return costOfRevenue;
  }

  public void setCostOfRevenue(long costOfRevenue) {
    this.costOfRevenue = costOfRevenue;
  }

  public long getCostofGoodsAndServicesSold() {
    return costofGoodsAndServicesSold;
  }

  public void setCostofGoodsAndServicesSold(long costofGoodsAndServicesSold) {
    this.costofGoodsAndServicesSold = costofGoodsAndServicesSold;
  }

  public long getOperatingIncome() {
    return operatingIncome;
  }

  public void setOperatingIncome(long operatingIncome) {
    this.operatingIncome = operatingIncome;
  }

  public long getSellingGeneralAndAdministrative() {
    return sellingGeneralAndAdministrative;
  }

  public void setSellingGeneralAndAdministrative(long sellingGeneralAndAdministrative) {
    this.sellingGeneralAndAdministrative = sellingGeneralAndAdministrative;
  }

  public long getResearchAndDevelopment() {
    return researchAndDevelopment;
  }

  public void setResearchAndDevelopment(long researchAndDevelopment) {
    this.researchAndDevelopment = researchAndDevelopment;
  }

  public long getOperatingExpenses() {
    return operatingExpenses;
  }

  public void setOperatingExpenses(long operatingExpenses) {
    this.operatingExpenses = operatingExpenses;
  }

  public long getInvestmentIncomeNet() {
    return investmentIncomeNet;
  }

  public void setInvestmentIncomeNet(long investmentIncomeNet) {
    this.investmentIncomeNet = investmentIncomeNet;
  }

  public long getNetInterestIncome() {
    return netInterestIncome;
  }

  public void setNetInterestIncome(long netInterestIncome) {
    this.netInterestIncome = netInterestIncome;
  }

  public long getInterestIncome() {
    return interestIncome;
  }

  public void setInterestIncome(long interestIncome) {
    this.interestIncome = interestIncome;
  }

  public long getInterestExpense() {
    return interestExpense;
  }

  public void setInterestExpense(long interestExpense) {
    this.interestExpense = interestExpense;
  }

  public long getNonInterestIncome() {
    return nonInterestIncome;
  }

  public void setNonInterestIncome(long nonInterestIncome) {
    this.nonInterestIncome = nonInterestIncome;
  }

  public long getOtherNonOperatingIncome() {
    return otherNonOperatingIncome;
  }

  public void setOtherNonOperatingIncome(long otherNonOperatingIncome) {
    this.otherNonOperatingIncome = otherNonOperatingIncome;
  }

  public long getDepreciation() {
    return depreciation;
  }

  public void setDepreciation(long depreciation) {
    this.depreciation = depreciation;
  }

  public long getDepreciationAndAmortization() {
    return depreciationAndAmortization;
  }

  public void setDepreciationAndAmortization(long depreciationAndAmortization) {
    this.depreciationAndAmortization = depreciationAndAmortization;
  }

  public long getIncomeBeforeTax() {
    return incomeBeforeTax;
  }

  public void setIncomeBeforeTax(long incomeBeforeTax) {
    this.incomeBeforeTax = incomeBeforeTax;
  }

  public long getIncomeTaxExpense() {
    return incomeTaxExpense;
  }

  public void setIncomeTaxExpense(long incomeTaxExpense) {
    this.incomeTaxExpense = incomeTaxExpense;
  }

  public long getInterestAndDebtExpense() {
    return interestAndDebtExpense;
  }

  public void setInterestAndDebtExpense(long interestAndDebtExpense) {
    this.interestAndDebtExpense = interestAndDebtExpense;
  }

  public long getNetIncomeFromContinuingOperations() {
    return netIncomeFromContinuingOperations;
  }

  public void setNetIncomeFromContinuingOperations(long netIncomeFromContinuingOperations) {
    this.netIncomeFromContinuingOperations = netIncomeFromContinuingOperations;
  }

  public long getComprehensiveIncomeNetOfTax() {
    return comprehensiveIncomeNetOfTax;
  }

  public void setComprehensiveIncomeNetOfTax(long comprehensiveIncomeNetOfTax) {
    this.comprehensiveIncomeNetOfTax = comprehensiveIncomeNetOfTax;
  }

  public long getEbit() {
    return ebit;
  }

  public void setEbit(long ebit) {
    this.ebit = ebit;
  }

  public long getEbitda() {
    return ebitda;
  }

  public void setEbitda(long ebitda) {
    this.ebitda = ebitda;
  }

  public long getNetIncome() {
    return netIncome;
  }

  public void setNetIncome(long netIncome) {
    this.netIncome = netIncome;
  }

  @Override
  public String toString() {
    return String.format(
        "[IncomeStatement (%s) fiscalDateEnding=%s]%n"
            + "reportedCurrency=%s%n" + "grossProfit=%d%n" + "totalRevenue=%d%n"
            + "costOfRevenue=%d%n" + "costofGoodsAndServicesSold=%d%n" + "operatingIncome=%d%n"
            + "sellingGeneralAndAdministrative=%d%n" + "researchAndDevelopment=%d%n"
            + "operatingExpenses=%d%n" + "investmentIncomeNet=%d%n" + "netInterestIncome=%d%n"
            + "interestIncome=%d%n" + "interestExpense=%d%n" + "nonInterestIncome=%d%n"
            + "otherNonOperatingIncome=%d%n" + "depreciation=%d%n"
            + "depreciationAndAmortization=%d%n" + "incomeBeforeTax=%d%n" + "incomeTaxExpense=%d%n"
            + "interestAndDebtExpense=%d%n" + "netIncomeFromContinuingOperations=%d%n"
            + "comprehensiveIncomeNetOfTax=%d%n" + "ebit=%d%n" + "ebitda=%d%n" + "netIncome=%d%n",
        symbol, fiscalDateEnding, reportedCurrency, grossProfit, totalRevenue, costOfRevenue,
        costofGoodsAndServicesSold, operatingIncome, sellingGeneralAndAdministrative,
        researchAndDevelopment, operatingExpenses, investmentIncomeNet, netInterestIncome,
        interestIncome, interestExpense, nonInterestIncome, otherNonOperatingIncome, depreciation,
        depreciationAndAmortization, incomeBeforeTax, incomeTaxExpense, interestAndDebtExpense,
        netIncomeFromContinuingOperations, comprehensiveIncomeNetOfTax, ebit, ebitda, netIncome
    );
  }
}