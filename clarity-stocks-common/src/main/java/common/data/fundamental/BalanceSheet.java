package common.data.fundamental;

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
  private long longTermDebtNoncurrent;
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

  public long getLongTermDebtNoncurrent() {
    return longTermDebtNoncurrent;
  }

  public void setLongTermDebtNoncurrent(long longTermDebtNoncurrent) {
    this.longTermDebtNoncurrent = longTermDebtNoncurrent;
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
            + "longTermDebt=%d%n" + "currentLongTermDebt=%d%n" + "longTermDebtNoncurrent=%d%n"
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
        capitalLeaseObligations, longTermDebt, currentLongTermDebt, longTermDebtNoncurrent,
        shortLongTermDebtTotal, otherCurrentLiabilities, otherNonCurrentLiabilities,
        totalShareholderEquity, treasuryStock, retainedEarnings, commonStock,
        commonStockSharesOutstanding
    );
  }
}
