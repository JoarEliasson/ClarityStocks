package common.data.fundamental;

/**
 * Class for storing balance sheet data.
 * <p>
 * This class is used to store balance sheet data for a company.
 * The class includes the symbol of the company, the fiscal date ending, the reported currency, and
 * various balance sheet data points.
 * </p>
 *
 * <ul>
 *   <li>{@code symbol} - The unique identifier for the company's stock.</li>
 *   <li>{@code fiscalDateEnding} - The fiscal date ending for the balance sheet data.</li>
 *   <li>{@code reportedCurrency} - The reported currency for the balance sheet data.</li>
 *   <li>{@code totalAssets} - The total assets of the company.</li>
 *   <li>{@code totalCurrentAssets} - The total current assets of the company.</li>
 *   <li>{@code cashAndCashEquivalentsAtCarryingValue} - The cash and cash equivalents at carrying
 *   value of the company.</li>
 *   <li>{@code cashAndShortTermInvestments} - The cash and short-term investments of the
 *   company.</li>
 *   <li>{@code inventory} - The inventory of the company.</li>
 *   <li>{@code currentNetReceivables} - The current net receivables of the company.</li>
 *   <li>{@code totalNonCurrentAssets} - The total non-current assets of the company.</li>
 *   <li>{@code propertyPlantEquipment} - The property, plant, and equipment of the company.</li>
 *   <li>{@code accumulatedDepreciationAmortizationPPE} - The accumulated depreciation and
 *   amortization of the company.</li>
 *   <li>{@code intangibleAssets} - The intangible assets of the company.</li>
 *   <li>{@code intangibleAssetsExcludingGoodwill} - The intangible assets excluding goodwill of the
 *   company.</li>
 *   <li>{@code goodwill} - The goodwill of the company.</li>
 *   <li>{@code investments} - The investments of the company.</li>
 *   <li>{@code longTermInvestments} - The long-term investments of the company.</li>
 *   <li>{@code shortTermInvestments} - The short-term investments of the company.</li>
 *   <li>{@code otherCurrentAssets} - The other current assets of the company.</li>
 *   <li>{@code otherNonCurrentAssets} - The other non-current assets of the company.</li>
 *   <li>{@code totalLiabilities} - The total liabilities of the company.</li>
 *   <li>{@code totalCurrentLiabilities} - The total current liabilities of the company.</li>
 *   <li>{@code currentAccountsPayable} - The current accounts payable of the company.</li>
 *   <li>{@code deferredRevenue} - The deferred revenue of the company.</li>
 *   <li>{@code currentDebt} - The current debt of the company.</li>
 *   <li>{@code shortTermDebt} - The short-term debt of the company.</li>
 *   <li>{@code totalNonCurrentLiabilities} - The total non-current liabilities of the company.</li>
 *   <li>{@code capitalLeaseObligations} - The capital lease obligations of the company.</li>
 *   <li>{@code longTermDebt} - The long-term debt of the company.</li>
 *   <li>{@code currentLongTermDebt} - The current long-term debt of the company.</li>
 *   <li>{@code longTermDebtNonCurrent} - The non-current long-term debt of the company.</li>
 *   <li>{@code shortLongTermDebtTotal} - The total short long-term debt of the company.</li>
 *   <li>{@code otherCurrentLiabilities} - The other current liabilities of the company.</li>
 *   <li>{@code otherNonCurrentLiabilities} - The other non-current liabilities of the company.</li>
 *   <li>{@code totalShareholderEquity} - The total shareholder equity of the company.</li>
 *   <li>{@code treasuryStock} - The treasury stock of the company.</li>
 *   <li>{@code retainedEarnings} - The retained earnings of the company.</li>
 *   <li>{@code commonStock} - The common stock of the company.</li>
 *   <li>{@code commonStockSharesOutstanding} - The common stock shares outstanding of the
 *   company.</li>
 * </ul>
 *
 * @author Joar Eliason
 */
public class BalanceSheet {

  private String symbol;
  private String fiscalDateEnding;
  private String reportedCurrency;
  private long totalAssets;
  private long totalCurrentAssets;
  private long cashAndCashEquivalentsAtCarryingValue;
  private long cashAndShortTermInvestments;
  private long inventory;
  private long currentNetReceivables;
  private long totalNonCurrentAssets;
  private long propertyPlantEquipment;
  private long accumulatedDepreciationAmortizationPPE;
  private long intangibleAssets;
  private long intangibleAssetsExcludingGoodwill;
  private long goodwill;
  private long investments;
  private long longTermInvestments;
  private long shortTermInvestments;
  private long otherCurrentAssets;
  private long otherNonCurrentAssets;
  private long totalLiabilities;
  private long totalCurrentLiabilities;
  private long currentAccountsPayable;
  private long deferredRevenue;
  private long currentDebt;
  private long shortTermDebt;
  private long totalNonCurrentLiabilities;
  private long capitalLeaseObligations;
  private long longTermDebt;
  private long currentLongTermDebt;
  private long longTermDebtNonCurrent;
  private long shortLongTermDebtTotal;
  private long otherCurrentLiabilities;
  private long otherNonCurrentLiabilities;
  private long totalShareholderEquity;
  private long treasuryStock;
  private long retainedEarnings;
  private long commonStock;
  private long commonStockSharesOutstanding;

  public BalanceSheet(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
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

  public long getTotalAssets() {
    return totalAssets;
  }

  public void setTotalAssets(long totalAssets) {
    this.totalAssets = totalAssets;
  }

  public long getTotalCurrentAssets() {
    return totalCurrentAssets;
  }

  public void setTotalCurrentAssets(long totalCurrentAssets) {
    this.totalCurrentAssets = totalCurrentAssets;
  }

  public long getCashAndCashEquivalentsAtCarryingValue() {
    return cashAndCashEquivalentsAtCarryingValue;
  }

  public void setCashAndCashEquivalentsAtCarryingValue(long cashAndCashEquivalentsAtCarryingValue) {
    this.cashAndCashEquivalentsAtCarryingValue = cashAndCashEquivalentsAtCarryingValue;
  }

  public long getCashAndShortTermInvestments() {
    return cashAndShortTermInvestments;
  }

  public void setCashAndShortTermInvestments(long cashAndShortTermInvestments) {
    this.cashAndShortTermInvestments = cashAndShortTermInvestments;
  }

  public long getInventory() {
    return inventory;
  }

  public void setInventory(long inventory) {
    this.inventory = inventory;
  }

  public long getCurrentNetReceivables() {
    return currentNetReceivables;
  }

  public void setCurrentNetReceivables(long currentNetReceivables) {
    this.currentNetReceivables = currentNetReceivables;
  }

  public long getTotalNonCurrentAssets() {
    return totalNonCurrentAssets;
  }

  public void setTotalNonCurrentAssets(long totalNonCurrentAssets) {
    this.totalNonCurrentAssets = totalNonCurrentAssets;
  }

  public long getPropertyPlantEquipment() {
    return propertyPlantEquipment;
  }

  public void setPropertyPlantEquipment(long propertyPlantEquipment) {
    this.propertyPlantEquipment = propertyPlantEquipment;
  }

  public long getAccumulatedDepreciationAmortizationPPE() {
    return accumulatedDepreciationAmortizationPPE;
  }

  public void setAccumulatedDepreciationAmortizationPPE(
      long accumulatedDepreciationAmortizationPPE) {
    this.accumulatedDepreciationAmortizationPPE = accumulatedDepreciationAmortizationPPE;
  }

  public long getIntangibleAssets() {
    return intangibleAssets;
  }

  public void setIntangibleAssets(long intangibleAssets) {
    this.intangibleAssets = intangibleAssets;
  }

  public long getIntangibleAssetsExcludingGoodwill() {
    return intangibleAssetsExcludingGoodwill;
  }

  public void setIntangibleAssetsExcludingGoodwill(long intangibleAssetsExcludingGoodwill) {
    this.intangibleAssetsExcludingGoodwill = intangibleAssetsExcludingGoodwill;
  }

  public long getGoodwill() {
    return goodwill;
  }

  public void setGoodwill(long goodwill) {
    this.goodwill = goodwill;
  }

  public long getInvestments() {
    return investments;
  }

  public void setInvestments(long investments) {
    this.investments = investments;
  }

  public long getLongTermInvestments() {
    return longTermInvestments;
  }

  public void setLongTermInvestments(long longTermInvestments) {
    this.longTermInvestments = longTermInvestments;
  }

  public long getShortTermInvestments() {
    return shortTermInvestments;
  }

  public void setShortTermInvestments(long shortTermInvestments) {
    this.shortTermInvestments = shortTermInvestments;
  }

  public long getOtherCurrentAssets() {
    return otherCurrentAssets;
  }

  public void setOtherCurrentAssets(long otherCurrentAssets) {
    this.otherCurrentAssets = otherCurrentAssets;
  }

  public long getOtherNonCurrentAssets() {
    return otherNonCurrentAssets;
  }

  public void setOtherNonCurrentAssets(long otherNonCurrentAssets) {
    this.otherNonCurrentAssets = otherNonCurrentAssets;
  }

  public long getTotalLiabilities() {
    return totalLiabilities;
  }

  public void setTotalLiabilities(long totalLiabilities) {
    this.totalLiabilities = totalLiabilities;
  }

  public long getTotalCurrentLiabilities() {
    return totalCurrentLiabilities;
  }

  public void setTotalCurrentLiabilities(long totalCurrentLiabilities) {
    this.totalCurrentLiabilities = totalCurrentLiabilities;
  }

  public long getCurrentAccountsPayable() {
    return currentAccountsPayable;
  }

  public void setCurrentAccountsPayable(long currentAccountsPayable) {
    this.currentAccountsPayable = currentAccountsPayable;
  }

  public long getDeferredRevenue() {
    return deferredRevenue;
  }

  public void setDeferredRevenue(long deferredRevenue) {
    this.deferredRevenue = deferredRevenue;
  }

  public long getCurrentDebt() {
    return currentDebt;
  }

  public void setCurrentDebt(long currentDebt) {
    this.currentDebt = currentDebt;
  }

  public long getShortTermDebt() {
    return shortTermDebt;
  }

  public void setShortTermDebt(long shortTermDebt) {
    this.shortTermDebt = shortTermDebt;
  }

  public long getTotalNonCurrentLiabilities() {
    return totalNonCurrentLiabilities;
  }

  public void setTotalNonCurrentLiabilities(long totalNonCurrentLiabilities) {
    this.totalNonCurrentLiabilities = totalNonCurrentLiabilities;
  }

  public long getCapitalLeaseObligations() {
    return capitalLeaseObligations;
  }

  public void setCapitalLeaseObligations(long capitalLeaseObligations) {
    this.capitalLeaseObligations = capitalLeaseObligations;
  }

  public long getLongTermDebt() {
    return longTermDebt;
  }

  public void setLongTermDebt(long longTermDebt) {
    this.longTermDebt = longTermDebt;
  }

  public long getCurrentLongTermDebt() {
    return currentLongTermDebt;
  }

  public void setCurrentLongTermDebt(long currentLongTermDebt) {
    this.currentLongTermDebt = currentLongTermDebt;
  }

  public long getLongTermDebtNonCurrent() {
    return longTermDebtNonCurrent;
  }

  public void setLongTermDebtNonCurrent(long longTermDebtNonCurrent) {
    this.longTermDebtNonCurrent = longTermDebtNonCurrent;
  }

  public long getShortLongTermDebtTotal() {
    return shortLongTermDebtTotal;
  }

  public void setShortLongTermDebtTotal(long shortLongTermDebtTotal) {
    this.shortLongTermDebtTotal = shortLongTermDebtTotal;
  }

  public long getOtherCurrentLiabilities() {
    return otherCurrentLiabilities;
  }

  public void setOtherCurrentLiabilities(long otherCurrentLiabilities) {
    this.otherCurrentLiabilities = otherCurrentLiabilities;
  }

  public long getOtherNonCurrentLiabilities() {
    return otherNonCurrentLiabilities;
  }

  public void setOtherNonCurrentLiabilities(long otherNonCurrentLiabilities) {
    this.otherNonCurrentLiabilities = otherNonCurrentLiabilities;
  }

  public long getTotalShareholderEquity() {
    return totalShareholderEquity;
  }

  public void setTotalShareholderEquity(long totalShareholderEquity) {
    this.totalShareholderEquity = totalShareholderEquity;
  }

  public long getTreasuryStock() {
    return treasuryStock;
  }

  public void setTreasuryStock(long treasuryStock) {
    this.treasuryStock = treasuryStock;
  }

  public long getRetainedEarnings() {
    return retainedEarnings;
  }

  public void setRetainedEarnings(long retainedEarnings) {
    this.retainedEarnings = retainedEarnings;
  }

  public long getCommonStock() {
    return commonStock;
  }

  public void setCommonStock(long commonStock) {
    this.commonStock = commonStock;
  }

  public long getCommonStockSharesOutstanding() {
    return commonStockSharesOutstanding;
  }

  public void setCommonStockSharesOutstanding(long commonStockSharesOutstanding) {
    this.commonStockSharesOutstanding = commonStockSharesOutstanding;
  }

  @Override
  public String toString() {
    return String.format(
        "[BalanceSheet (%s) fiscalDateEnding=%s]%n"
            + "reportedCurrency=%s%n" + "totalAssets=%d%n" + "totalCurrentAssets=%d%n"
            + "cashAndCashEquivalentsAtCarryingValue=%d%n" + "cashAndShortTermInvestments=%d%n"
            + "inventory=%d%n" + "currentNetReceivables=%d%n" + "totalNonCurrentAssets=%d%n"
            + "propertyPlantEquipment=%d%n" + "accumulatedDepreciationAmortizationPPE=%d%n"
            + "intangibleAssets=%d%n" + "intangibleAssetsExcludingGoodwill=%d%n" + "goodwill=%d%n"
            + "investments=%d%n" + "longTermInvestments=%d%n" + "shortTermInvestments=%d%n"
            + "otherCurrentAssets=%d%n" + "otherNonCurrentAssets=%d%n" + "totalLiabilities=%d%n"
            + "totalCurrentLiabilities=%d%n" + "currentAccountsPayable=%d%n"
            + "deferredRevenue=%d%n" + "currentDebt=%d%n" + "shortTermDebt=%d%n"
            + "totalNonCurrentLiabilities=%d%n" + "capitalLeaseObligations=%d%n"
            + "longTermDebt=%d%n" + "currentLongTermDebt=%d%n" + "longTermDebtNon-current=%d%n"
            + "shortLongTermDebtTotal=%d%n" + "otherCurrentLiabilities=%d%n"
            + "otherNonCurrentLiabilities=%d%n" + "totalShareholderEquity=%d%n"
            + "treasuryStock=%d%n" + "retainedEarnings=%d%n" + "commonStock=%d%n"
            + "commonStockSharesOutstanding=%d%n",
        symbol, fiscalDateEnding, reportedCurrency, totalAssets, totalCurrentAssets,
        cashAndCashEquivalentsAtCarryingValue, cashAndShortTermInvestments, inventory,
        currentNetReceivables, totalNonCurrentAssets, propertyPlantEquipment,
        accumulatedDepreciationAmortizationPPE, intangibleAssets, intangibleAssetsExcludingGoodwill,
        goodwill, investments, longTermInvestments, shortTermInvestments, otherCurrentAssets,
        otherNonCurrentAssets, totalLiabilities, totalCurrentLiabilities, currentAccountsPayable,
        deferredRevenue, currentDebt, shortTermDebt, totalNonCurrentLiabilities,
        capitalLeaseObligations, longTermDebt, currentLongTermDebt, longTermDebtNonCurrent,
        shortLongTermDebtTotal, otherCurrentLiabilities, otherNonCurrentLiabilities,
        totalShareholderEquity, treasuryStock, retainedEarnings, commonStock,
        commonStockSharesOutstanding
    );
  }
}
