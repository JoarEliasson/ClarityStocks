package common.data.fundamental;

public class CashFlowReport {

  private final String symbol;
  private String fiscalDateEnding;
  private String reportedCurrency;
  private long operatingCashflow;
  private long paymentsForOperatingActivities;
  private long proceedsFromOperatingActivities;
  private long changeInOperatingLiabilities;
  private long changeInOperatingAssets;
  private long depreciationDepletionAndAmortization;
  private long capitalExpenditures;
  private long changeInReceivables;
  private long changeInInventory;
  private long profitLoss;
  private long cashflowFromInvestment;
  private long cashflowFromFinancing;
  private long proceedsFromRepaymentsOfShortTermDebt;
  private long paymentsForRepurchaseOfCommonStock;
  private long paymentsForRepurchaseOfEquity;
  private long paymentsForRepurchaseOfPreferredStock;
  private long dividendPayout;
  private long dividendPayoutCommonStock;
  private long dividendPayoutPreferredStock;
  private long proceedsFromIssuanceOfCommonStock;
  private long proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;
  private long proceedsFromIssuanceOfPreferredStock;
  private long proceedsFromRepurchaseOfEquity;
  private long proceedsFromSaleOfTreasuryStock;
  private long changeInCashAndCashEquivalents;
  private long changeInExchangeRate;
  private long netIncome;

  public CashFlowReport(String symbol) {
    this.symbol = symbol;
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

  public long getOperatingCashflow() {
    return operatingCashflow;
  }

  public void setOperatingCashflow(long operatingCashflow) {
    this.operatingCashflow = operatingCashflow;
  }

  public long getPaymentsForOperatingActivities() {
    return paymentsForOperatingActivities;
  }

  public void setPaymentsForOperatingActivities(long paymentsForOperatingActivities) {
    this.paymentsForOperatingActivities = paymentsForOperatingActivities;
  }

  public long getProceedsFromOperatingActivities() {
    return proceedsFromOperatingActivities;
  }

  public void setProceedsFromOperatingActivities(long proceedsFromOperatingActivities) {
    this.proceedsFromOperatingActivities = proceedsFromOperatingActivities;
  }

  public long getChangeInOperatingLiabilities() {
    return changeInOperatingLiabilities;
  }

  public void setChangeInOperatingLiabilities(long changeInOperatingLiabilities) {
    this.changeInOperatingLiabilities = changeInOperatingLiabilities;
  }

  public long getChangeInOperatingAssets() {
    return changeInOperatingAssets;
  }

  public void setChangeInOperatingAssets(long changeInOperatingAssets) {
    this.changeInOperatingAssets = changeInOperatingAssets;
  }

  public long getDepreciationDepletionAndAmortization() {
    return depreciationDepletionAndAmortization;
  }

  public void setDepreciationDepletionAndAmortization(long depreciationDepletionAndAmortization) {
    this.depreciationDepletionAndAmortization = depreciationDepletionAndAmortization;
  }

  public long getCapitalExpenditures() {
    return capitalExpenditures;
  }

  public void setCapitalExpenditures(long capitalExpenditures) {
    this.capitalExpenditures = capitalExpenditures;
  }

  public long getChangeInReceivables() {
    return changeInReceivables;
  }

  public void setChangeInReceivables(long changeInReceivables) {
    this.changeInReceivables = changeInReceivables;
  }

  public long getChangeInInventory() {
    return changeInInventory;
  }

  public void setChangeInInventory(long changeInInventory) {
    this.changeInInventory = changeInInventory;
  }

  public long getProfitLoss() {
    return profitLoss;
  }

  public void setProfitLoss(long profitLoss) {
    this.profitLoss = profitLoss;
  }

  public long getCashflowFromInvestment() {
    return cashflowFromInvestment;
  }

  public void setCashflowFromInvestment(long cashflowFromInvestment) {
    this.cashflowFromInvestment = cashflowFromInvestment;
  }

  public long getCashflowFromFinancing() {
    return cashflowFromFinancing;
  }

  public void setCashflowFromFinancing(long cashflowFromFinancing) {
    this.cashflowFromFinancing = cashflowFromFinancing;
  }

  public long getProceedsFromRepaymentsOfShortTermDebt() {
    return proceedsFromRepaymentsOfShortTermDebt;
  }

  public void setProceedsFromRepaymentsOfShortTermDebt(long proceedsFromRepaymentsOfShortTermDebt) {
    this.proceedsFromRepaymentsOfShortTermDebt = proceedsFromRepaymentsOfShortTermDebt;
  }

  public long getPaymentsForRepurchaseOfCommonStock() {
    return paymentsForRepurchaseOfCommonStock;
  }

  public void setPaymentsForRepurchaseOfCommonStock(long paymentsForRepurchaseOfCommonStock) {
    this.paymentsForRepurchaseOfCommonStock = paymentsForRepurchaseOfCommonStock;
  }

  public long getPaymentsForRepurchaseOfEquity() {
    return paymentsForRepurchaseOfEquity;
  }

  public void setPaymentsForRepurchaseOfEquity(long paymentsForRepurchaseOfEquity) {
    this.paymentsForRepurchaseOfEquity = paymentsForRepurchaseOfEquity;
  }

  public long getPaymentsForRepurchaseOfPreferredStock() {
    return paymentsForRepurchaseOfPreferredStock;
  }

  public void setPaymentsForRepurchaseOfPreferredStock(long paymentsForRepurchaseOfPreferredStock) {
    this.paymentsForRepurchaseOfPreferredStock = paymentsForRepurchaseOfPreferredStock;
  }

  public long getDividendPayout() {
    return dividendPayout;
  }

  public void setDividendPayout(long dividendPayout) {
    this.dividendPayout = dividendPayout;
  }

  public long getDividendPayoutCommonStock() {
    return dividendPayoutCommonStock;
  }

  public void setDividendPayoutCommonStock(long dividendPayoutCommonStock) {
    this.dividendPayoutCommonStock = dividendPayoutCommonStock;
  }

  public long getDividendPayoutPreferredStock() {
    return dividendPayoutPreferredStock;
  }

  public void setDividendPayoutPreferredStock(long dividendPayoutPreferredStock) {
    this.dividendPayoutPreferredStock = dividendPayoutPreferredStock;
  }

  public long getProceedsFromIssuanceOfCommonStock() {
    return proceedsFromIssuanceOfCommonStock;
  }

  public void setProceedsFromIssuanceOfCommonStock(long proceedsFromIssuanceOfCommonStock) {
    this.proceedsFromIssuanceOfCommonStock = proceedsFromIssuanceOfCommonStock;
  }

  public long getProceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet() {
    return proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;
  }

  public void setProceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet(
      long proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet) {
    this.proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet = proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet;
  }

  public long getProceedsFromIssuanceOfPreferredStock() {
    return proceedsFromIssuanceOfPreferredStock;
  }

  public void setProceedsFromIssuanceOfPreferredStock(long proceedsFromIssuanceOfPreferredStock) {
    this.proceedsFromIssuanceOfPreferredStock = proceedsFromIssuanceOfPreferredStock;
  }

  public long getProceedsFromRepurchaseOfEquity() {
    return proceedsFromRepurchaseOfEquity;
  }

  public void setProceedsFromRepurchaseOfEquity(long proceedsFromRepurchaseOfEquity) {
    this.proceedsFromRepurchaseOfEquity = proceedsFromRepurchaseOfEquity;
  }

  public long getProceedsFromSaleOfTreasuryStock() {
    return proceedsFromSaleOfTreasuryStock;
  }

  public void setProceedsFromSaleOfTreasuryStock(long proceedsFromSaleOfTreasuryStock) {
    this.proceedsFromSaleOfTreasuryStock = proceedsFromSaleOfTreasuryStock;
  }

  public long getChangeInCashAndCashEquivalents() {
    return changeInCashAndCashEquivalents;
  }

  public void setChangeInCashAndCashEquivalents(long changeInCashAndCashEquivalents) {
    this.changeInCashAndCashEquivalents = changeInCashAndCashEquivalents;
  }

  public long getChangeInExchangeRate() {
    return changeInExchangeRate;
  }

  public void setChangeInExchangeRate(long changeInExchangeRate) {
    this.changeInExchangeRate = changeInExchangeRate;
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
        "[CashFlow (%s) fiscalDateEnding=%s]%n"
            + "reportedCurrency=%s%n" + "operatingCashflow=%d%n"
            + "paymentsForOperatingActivities=%d%n" + "proceedsFromOperatingActivities=%d%n"
            + "changeInOperatingLiabilities=%d%n" + "changeInOperatingAssets=%d%n"
            + "depreciationDepletionAndAmortization=%d%n" + "capitalExpenditures=%d%n"
            + "changeInReceivables=%d%n" + "changeInInventory=%d%n" + "profitLoss=%d%n"
            + "cashflowFromInvestment=%d%n" + "cashflowFromFinancing=%d%n"
            + "proceedsFromRepaymentsOfShortTermDebt=%d%n"
            + "paymentsForRepurchaseOfCommonStock=%d%n" + "paymentsForRepurchaseOfEquity=%d%n"
            + "paymentsForRepurchaseOfPreferredStock=%d%n" + "dividendPayout=%d%n"
            + "dividendPayoutCommonStock=%d%n" + "dividendPayoutPreferredStock=%d%n"
            + "proceedsFromIssuanceOfCommonStock=%d%n"
            + "proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet=%d%n"
            + "proceedsFromIssuanceOfPreferredStock=%d%n" + "proceedsFromRepurchaseOfEquity=%d%n"
            + "proceedsFromSaleOfTreasuryStock=%d%n" + "changeInCashAndCashEquivalents=%d%n"
            + "changeInExchangeRate=%d%n" + "netIncome=%d%n",
        symbol, fiscalDateEnding, reportedCurrency, operatingCashflow,
        paymentsForOperatingActivities, proceedsFromOperatingActivities,
        changeInOperatingLiabilities, changeInOperatingAssets,
        depreciationDepletionAndAmortization, capitalExpenditures, changeInReceivables,
        changeInInventory, profitLoss, cashflowFromInvestment, cashflowFromFinancing,
        proceedsFromRepaymentsOfShortTermDebt, paymentsForRepurchaseOfCommonStock,
        paymentsForRepurchaseOfEquity, paymentsForRepurchaseOfPreferredStock, dividendPayout,
        dividendPayoutCommonStock, dividendPayoutPreferredStock, proceedsFromIssuanceOfCommonStock,
        proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet,
        proceedsFromIssuanceOfPreferredStock, proceedsFromRepurchaseOfEquity,
        proceedsFromSaleOfTreasuryStock, changeInCashAndCashEquivalents, changeInExchangeRate,
        netIncome
    );
  }
}
