package common.data.fundamental;

/**
 * Class for storing cash flow reports.
 * <p>
 * This class holds data for a cash flow report, including the symbol of the company, the fiscal
 * date ending of the report, the reported currency, and various cash flow values.
 * </p>
 *
 * <ul>
 *   <li>{@code symbol} - The symbol of the company.</li>
 *   <li>{@code fiscalDateEnding} - The fiscal date ending of the report.</li>
 *   <li>{@code reportedCurrency} - The currency in which the report is reported.</li>
 *   <li>{@code operatingCashFlow} - The operating cash flow.</li>
 *   <li>{@code paymentsForOperatingActivities} - The payments for operating activities.</li>
 *   <li>{@code proceedsFromOperatingActivities} - The proceeds from operating activities.</li>
 *   <li>{@code changeInOperatingLiabilities} - The change in operating liabilities.</li>
 *   <li>{@code changeInOperatingAssets} - The change in operating assets.</li>
 *   <li>{@code depreciationDepletionAndAmortization} - The depreciation, depletion, and
 *   amortization.</li>
 *   <li>{@code capitalExpenditures} - The capital expenditures.</li>
 *   <li>{@code changeInReceivables} - The change in receivables.</li>
 *   <li>{@code changeInInventory} - The change in inventory.</li>
 *   <li>{@code profitLoss} - The profit or loss.</li>
 *   <li>{@code cashFlowFromInvestment} - The cash flow from investment.</li>
 *   <li>{@code cashFlowFromFinancing} - The cash flow from financing.</li>
 *   <li>{@code proceedsFromRepaymentsOfShortTermDebt} - The proceeds from repayments of short-term
 *   debt.</li>
 *   <li>{@code paymentsForRepurchaseOfCommonStock} - The payments for repurchase of common
 *   stock.</li>
 *   <li>{@code paymentsForRepurchaseOfEquity} - The payments for repurchase of equity.</li>
 *   <li>{@code paymentsForRepurchaseOfPreferredStock} - The payments for repurchase of preferred
 *   stock.</li>
 *   <li>{@code dividendPayout} - The dividend payout.</li>
 *   <li>{@code dividendPayoutCommonStock} - The dividend payout for common stock.</li>
 *   <li>{@code dividendPayoutPreferredStock} - The dividend payout for preferred stock.</li>
 *   <li>{@code proceedsFromIssuanceOfCommonStock} - The proceeds from issuance of common
 *   stock.</li>
 *   <li>{@code proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet} - The proceeds from
 *   issuance of long-term debt and capital securities net.</li>
 *   <li>{@code proceedsFromIssuanceOfPreferredStock} - The proceeds from issuance of preferred
 *   stock.</li>
 *   <li>{@code proceedsFromRepurchaseOfEquity} - The proceeds from repurchase of equity.</li>
 *   <li>{@code proceedsFromSaleOfTreasuryStock} - The proceeds from sale of treasury stock.</li>
 *   <li>{@code changeInCashAndCashEquivalents} - The change in cash and cash equivalents.</li>
 *   <li>{@code changeInExchangeRate} - The change in exchange rate.</li>
 * </ul>
 *
 * @author Joar Eliason
 */
public class CashFlowReport {

  private final String symbol;
  private String fiscalDateEnding;
  private String reportedCurrency;
  private long operatingCashFlow;
  private long paymentsForOperatingActivities;
  private long proceedsFromOperatingActivities;
  private long changeInOperatingLiabilities;
  private long changeInOperatingAssets;
  private long depreciationDepletionAndAmortization;
  private long capitalExpenditures;
  private long changeInReceivables;
  private long changeInInventory;
  private long profitLoss;
  private long cashFlowFromInvestment;
  private long cashFlowFromFinancing;
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

  public long getOperatingCashFlow() {
    return operatingCashFlow;
  }

  public void setOperatingCashFlow(long operatingCashFlow) {
    this.operatingCashFlow = operatingCashFlow;
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

  public long getCashFlowFromInvestment() {
    return cashFlowFromInvestment;
  }

  public void setCashFlowFromInvestment(long cashFlowFromInvestment) {
    this.cashFlowFromInvestment = cashFlowFromInvestment;
  }

  public long getCashFlowFromFinancing() {
    return cashFlowFromFinancing;
  }

  public void setCashFlowFromFinancing(long cashFlowFromFinancing) {
    this.cashFlowFromFinancing = cashFlowFromFinancing;
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
            + "reportedCurrency=%s%n" + "operatingCashFlow=%d%n"
            + "paymentsForOperatingActivities=%d%n" + "proceedsFromOperatingActivities=%d%n"
            + "changeInOperatingLiabilities=%d%n" + "changeInOperatingAssets=%d%n"
            + "depreciationDepletionAndAmortization=%d%n" + "capitalExpenditures=%d%n"
            + "changeInReceivables=%d%n" + "changeInInventory=%d%n" + "profitLoss=%d%n"
            + "cashFlowFromInvestment=%d%n" + "cashFlowFromFinancing=%d%n"
            + "proceedsFromRepaymentsOfShortTermDebt=%d%n"
            + "paymentsForRepurchaseOfCommonStock=%d%n" + "paymentsForRepurchaseOfEquity=%d%n"
            + "paymentsForRepurchaseOfPreferredStock=%d%n" + "dividendPayout=%d%n"
            + "dividendPayoutCommonStock=%d%n" + "dividendPayoutPreferredStock=%d%n"
            + "proceedsFromIssuanceOfCommonStock=%d%n"
            + "proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet=%d%n"
            + "proceedsFromIssuanceOfPreferredStock=%d%n" + "proceedsFromRepurchaseOfEquity=%d%n"
            + "proceedsFromSaleOfTreasuryStock=%d%n" + "changeInCashAndCashEquivalents=%d%n"
            + "changeInExchangeRate=%d%n" + "netIncome=%d%n",
        symbol, fiscalDateEnding, reportedCurrency, operatingCashFlow,
        paymentsForOperatingActivities, proceedsFromOperatingActivities,
        changeInOperatingLiabilities, changeInOperatingAssets,
        depreciationDepletionAndAmortization, capitalExpenditures, changeInReceivables,
        changeInInventory, profitLoss, cashFlowFromInvestment, cashFlowFromFinancing,
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
