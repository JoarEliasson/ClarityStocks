package alphaVantage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import common.data.fundamental.AnnualEarnings;
import common.data.fundamental.BalanceSheet;
import common.data.fundamental.CashFlowReport;
import common.data.fundamental.CompanyOverview;
import common.data.fundamental.EarningsData;
import common.data.fundamental.IncomeStatement;
import common.data.fundamental.QuarterlyEarnings;
import common.data.series.DailyDataPoint;
import common.data.series.TimeSeriesDaily;
import common.data.series.TimeSeriesMonthly;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import common.data.global.DailyTopLists;
import common.data.global.MarketStatus;
import common.data.global.TopListDataPoint;

/**
 * Class for parsing JSON responses from Alpha Vantage API (company overview and series data).
 */

public class AlphaVantageParser {

  public CompanyOverview parseFullStockOverview(String body) {
    ObjectMapper mapper = new ObjectMapper();
    CompanyOverview companyOverview = new CompanyOverview();
    try {
      JsonNode root = mapper.readTree(body);
      companyOverview.setSymbol(root.path(
          "Symbol").asText());
      companyOverview.setAssetType(root.path(
          "AssetType").asText());
      companyOverview.setName(root.path(
          "Name").asText());
      companyOverview.setDescription(root.path(
          "Description").asText());
      companyOverview.setCIK(root.path(
          "CIK").asText());
      companyOverview.setExchange(root.path(
          "Exchange").asText());
      companyOverview.setCurrency(root.path(
          "Currency").asText());
      companyOverview.setCountry(root.path(
          "Country").asText());
      companyOverview.setSector(root.path(
          "Sector").asText());
      companyOverview.setIndustry(root.path(
          "Industry").asText());
      companyOverview.setAddress(root.path(
          "Address").asText());
      companyOverview.setFiscalYearEnd(root.path(
          "FiscalYearEnd").asText());
      companyOverview.setLatestQuarter(root.path(
          "LatestQuarter").asText());
      companyOverview.setMarketCapitalization(root.path(
          "MarketCapitalization").asLong());
      companyOverview.setEBITDA(root.path(
          "EBITDA").asDouble());
      companyOverview.setPERatio(root.path(
          "PERatio").asDouble());
      companyOverview.setPEGRatio(root.path(
          "PEGRatio").asDouble());
      companyOverview.setBookValue(root.path(
          "BookValue").asDouble());
      companyOverview.setDividendPerShare(root.path(
          "DividendPerShare").asDouble());
      companyOverview.setDividendYield(root.path(
          "DividendYield").asDouble());
      companyOverview.setEPS(root.path(
          "EPS").asDouble());
      companyOverview.setRevenuePerShareTTM(root.path(
          "RevenuePerShareTTM").asDouble());
      companyOverview.setProfitMargin(root.path(
          "ProfitMargin").asDouble());
      companyOverview.setOperatingMarginTTM(root.path(
          "OperatingMarginTTM").asDouble());
      companyOverview.setReturnOnAssetsTTM(root.path(
          "ReturnOnAssetsTTM").asDouble());
      companyOverview.setReturnOnEquityTTM(root.path(
          "ReturnOnEquityTTM").asDouble());
      companyOverview.setRevenueTTM(root.path(
          "RevenueTTM").asLong());
      companyOverview.setGrossProfitTTM(root.path(
          "GrossProfitTTM").asLong());
      companyOverview.setDilutedEPSTTM(root.path(
          "DilutedEPSTTM").asDouble());
      companyOverview.setQuarterlyEarningsGrowthYOY(root.path(
          "QuarterlyEarningsGrowthYOY").asDouble());
      companyOverview.setQuarterlyRevenueGrowthYOY(root.path(
          "QuarterlyRevenueGrowthYOY").asDouble());
      companyOverview.setAnalystTargetPrice(root.path(
          "AnalystTargetPrice").asDouble());
      companyOverview.setAnalystRatingStrongBuy(root.path(
          "AnalystRatingStrongBuy").asInt());
      companyOverview.setAnalystRatingBuy(root.path(
          "AnalystRatingBuy").asInt());
      companyOverview.setAnalystRatingHold(root.path(
          "AnalystRatingHold").asInt());
      companyOverview.setAnalystRatingSell(root.path(
          "AnalystRatingSell").asInt());
      companyOverview.setAnalystRatingStrongSell(root.path(
          "AnalystRatingStrongSell").asInt());
      companyOverview.setTrailingPE(root.path(
          "TrailingPE").asDouble());
      companyOverview.setForwardPE(root.path(
          "ForwardPE").asDouble());
      companyOverview.setPriceToSalesRatioTTM(root.path(
          "PriceToSalesRatioTTM").asDouble());
      companyOverview.setPriceToBookRatio(root.path(
          "PriceToBookRatio").asDouble());
      companyOverview.setEVToRevenue(root.path(
          "EVToRevenue").asDouble());
      companyOverview.setEVToEBITDA(root.path(
          "EVToEBITDA").asDouble());
      companyOverview.setBeta(root.path(
          "Beta").asDouble());
      companyOverview.setWeek52High(root.path(
          "52WeekHigh").asDouble());
      companyOverview.setWeek52Low(root.path(
          "52WeekLow").asDouble());
      companyOverview.setMovingAverage50(root.path(
          "50DayMovingAverage").asDouble());
      companyOverview.setMovingAverage200(root.path(
          "200DayMovingAverage").asDouble());
      companyOverview.setSharesOutstanding(root.path(
          "SharesOutstanding").asLong());
      companyOverview.setDividendDate(root.path(
          "DividendDate").asText());
      companyOverview.setExDividendDate(root.path(
          "ExDividendDate").asText());
    } catch (IOException e) {
      e.printStackTrace();
    }
    return companyOverview;
  }

  public TimeSeriesDaily parseTimeSeriesDaily(String responseBody) {
    ObjectMapper mapper = new ObjectMapper();
    TimeSeriesDaily timeSeriesDaily = null;

    try {
      JsonNode metaNode = mapper.readTree(responseBody).path("Meta Data");
      String symbol = metaNode.path("2. Symbol").asText();
      String lastRefreshed = metaNode.path("3. Last Refreshed").asText();
      timeSeriesDaily = new TimeSeriesDaily(symbol);
      timeSeriesDaily.setLastRefreshed(lastRefreshed);

      JsonNode root = mapper.readTree(responseBody);
      JsonNode timeSeries = root.path("Time Series (Daily)");
      if (!timeSeries.isMissingNode()) {
        timeSeriesDaily.setDailyData(
            parseDailyDataPoints(timeSeries)
        );
      } else {
        System.out.println("No daily time series data found.");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return timeSeriesDaily;
  }


  private List<DailyDataPoint> parseDailyDataPoints(JsonNode timeSeries) {
    List<DailyDataPoint> timeSeriesData = new ArrayList<>();
    if (!timeSeries.isMissingNode()) {
      Iterator<Map.Entry<String, JsonNode>> fields = timeSeries.fields();
      while (fields.hasNext()) {
        Map.Entry<String, JsonNode> entry = fields.next();
        String date = entry.getKey();
        JsonNode data = entry.getValue();
        double open = data.path("1. open").asDouble();
        double high = data.path("2. high").asDouble();
        double low = data.path("3. low").asDouble();
        double close = data.path("4. close").asDouble();
        long volume = data.path("5. volume").asLong();
        DailyDataPoint dataPoint = new DailyDataPoint(date, open, high, low, close, volume);
        timeSeriesData.add(dataPoint);
      }
    } else {
      System.out.println("No daily data points found.");
    }
    return timeSeriesData;
  }

  public TimeSeriesMonthly parseTimeSeriesMonthly(String responseBody) {
    ObjectMapper mapper = new ObjectMapper();
    TimeSeriesMonthly timeSeriesMonthly = null;

    try {
      JsonNode metaNode = mapper.readTree(responseBody).path("Meta Data");
      String symbol = metaNode.path("2. Symbol").asText();
      String lastRefreshed = metaNode.path("3. Last Refreshed").asText();
      timeSeriesMonthly = new TimeSeriesMonthly(symbol);
      timeSeriesMonthly.setLastRefreshed(lastRefreshed);

      JsonNode root = mapper.readTree(responseBody);
      JsonNode timeSeries = root.path("Monthly Adjusted Time Series");
      if (!timeSeries.isMissingNode()) {
        timeSeriesMonthly.setMonthlyData(
            parseDailyAdjustedDataPoints(timeSeries)
        );
      } else {
        System.out.println("No monthly time series data found.");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return timeSeriesMonthly;
  }

  private List<DailyDataPoint> parseDailyAdjustedDataPoints(JsonNode timeSeries) {
    List<DailyDataPoint> timeSeriesData = new ArrayList<>();
    if (!timeSeries.isMissingNode()) {
      Iterator<Map.Entry<String, JsonNode>> fields = timeSeries.fields();
      while (fields.hasNext()) {
        Map.Entry<String, JsonNode> entry = fields.next();
        String date = entry.getKey();
        JsonNode data = entry.getValue();
        double open = data.path("1. open").asDouble();
        double high = data.path("2. high").asDouble();
        double low = data.path("3. low").asDouble();
        double close = data.path("4. close").asDouble();
        double adjustedClose = data.path("5. adjusted close").asDouble();
        long volume = data.path("6. volume").asLong();
        DailyDataPoint dataPoint = new DailyDataPoint(date, open, high, low, close, adjustedClose, volume);
        timeSeriesData.add(dataPoint);
      }
    } else {
      System.out.println("No daily data points found.");
    }
    return timeSeriesData;
  }

  public List<IncomeStatement> parseIncomeStatements(String body) {
    ObjectMapper mapper = new ObjectMapper();
    List<IncomeStatement> incomeStatements = new ArrayList<>();
    try {
      JsonNode symbolNode = mapper.readTree(body).path("symbol");
      String symbol = symbolNode.asText();
      JsonNode root = mapper.readTree(body);
      JsonNode incomeStatementsNode = root.path("annualReports");
      incomeStatements = parseIncomeStatementNode(symbol, incomeStatementsNode);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return incomeStatements;
  }

  public List<IncomeStatement> parseQuarterlyIncomeStatements(String body) {
    ObjectMapper mapper = new ObjectMapper();
    List<IncomeStatement> incomeStatements = new ArrayList<>();
    try {
      JsonNode symbolNode = mapper.readTree(body).path("symbol");
      String symbol = symbolNode.asText();
      JsonNode root = mapper.readTree(body);
      JsonNode incomeStatementsNode = root.path("quarterlyReports");
      incomeStatements = parseIncomeStatementNode(symbol, incomeStatementsNode);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return incomeStatements;
  }

  private List<IncomeStatement> parseIncomeStatementNode(String symbol, JsonNode incomeStatementsNode) {
    List<IncomeStatement> incomeStatements = new ArrayList<>();
    if (!incomeStatementsNode.isMissingNode()) {
      for (JsonNode incomeStatementNode : incomeStatementsNode) {
        IncomeStatement incomeStatement = new IncomeStatement(symbol);
        incomeStatement.setFiscalDateEnding(incomeStatementNode.path(
            "fiscalDateEnding").asText());
        incomeStatement.setReportedCurrency(incomeStatementNode.path(
            "reportedCurrency").asText());
        incomeStatement.setGrossProfit(incomeStatementNode.path(
            "grossProfit").asLong());
        incomeStatement.setTotalRevenue(incomeStatementNode.path(
            "totalRevenue").asLong());
        incomeStatement.setCostOfRevenue(incomeStatementNode.path(
            "costOfRevenue").asLong());
        incomeStatement.setCostOfGoodsAndServicesSold(incomeStatementNode.path(
            "costofGoodsAndServicesSold").asLong());
        incomeStatement.setOperatingIncome(incomeStatementNode.path(
            "operatingIncome").asLong());
        incomeStatement.setSellingGeneralAndAdministrative(incomeStatementNode.path(
            "sellingGeneralAndAdministrative").asLong());
        incomeStatement.setResearchAndDevelopment(incomeStatementNode.path(
            "researchAndDevelopment").asLong());
        incomeStatement.setOperatingExpenses(incomeStatementNode.path(
            "operatingExpenses").asLong());
        incomeStatement.setInvestmentIncomeNet(incomeStatementNode.path(
            "investmentIncomeNet").asLong());
        incomeStatement.setNetInterestIncome(incomeStatementNode.path(
            "netInterestIncome").asLong());
        incomeStatement.setInterestIncome(incomeStatementNode.path(
            "interestIncome").asLong());
        incomeStatement.setInterestExpense(incomeStatementNode.path(
            "interestExpense").asLong());
        incomeStatement.setNonInterestIncome(incomeStatementNode.path(
            "nonInterestIncome").asLong());
        incomeStatement.setOtherNonOperatingIncome(incomeStatementNode.path(
            "otherNonOperatingIncome").asLong());
        incomeStatement.setDepreciation(incomeStatementNode.path(
            "depreciation").asLong());
        incomeStatement.setDepreciationAndAmortization(incomeStatementNode.path(
            "depreciationAndAmortization").asLong());
        incomeStatement.setIncomeBeforeTax(incomeStatementNode.path(
            "incomeBeforeTax").asLong());
        incomeStatement.setIncomeTaxExpense(incomeStatementNode.path(
            "incomeTaxExpense").asLong());
        incomeStatement.setInterestAndDebtExpense(incomeStatementNode.path(
            "interestAndDebtExpense").asLong());
        incomeStatement.setNetIncomeFromContinuingOperations(incomeStatementNode.path(
            "netIncomeFromContinuingOperations").asLong());
        incomeStatement.setComprehensiveIncomeNetOfTax(incomeStatementNode.path(
            "comprehensiveIncomeNetOfTax").asLong());
        incomeStatement.setEBIT(incomeStatementNode.path(
            "ebit").asLong());
        incomeStatement.setEBITDA(incomeStatementNode.path(
            "ebitda").asLong());
        incomeStatement.setNetIncome(incomeStatementNode.path(
            "netIncome").asLong());
        incomeStatements.add(incomeStatement);
      }
    } else {
      System.out.println("No income statement data found.");
    }
    return incomeStatements;
  }

  public List<BalanceSheet> parseBalanceSheets(String body) {
    ObjectMapper mapper = new ObjectMapper();
    List<BalanceSheet> balanceSheets = new ArrayList<>();
    try {
      JsonNode symbolNode = mapper.readTree(body).path("symbol");
      String symbol = symbolNode.asText();
      JsonNode root = mapper.readTree(body);
      JsonNode balanceSheetsNode = root.path("annualReports");
      balanceSheets = parseBalanceSheetNode(symbol, balanceSheetsNode);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return balanceSheets;
  }

  public List<BalanceSheet> parseQuarterlyBalanceSheets(String body) {
    ObjectMapper mapper = new ObjectMapper();
    List<BalanceSheet> balanceSheets = new ArrayList<>();
    try {
      JsonNode symbolNode = mapper.readTree(body).path("symbol");
      String symbol = symbolNode.asText();
      JsonNode root = mapper.readTree(body);
      JsonNode balanceSheetsNode = root.path("quarterlyReports");
      balanceSheets = parseBalanceSheetNode(symbol, balanceSheetsNode);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return balanceSheets;
  }

  private List<BalanceSheet> parseBalanceSheetNode(String symbol, JsonNode balanceSheetsNode) {
    List<BalanceSheet> balanceSheets = new ArrayList<>();
    if (!balanceSheetsNode.isMissingNode()) {
      for (JsonNode balanceSheetNode : balanceSheetsNode) {
        BalanceSheet balanceSheet = new BalanceSheet(symbol);
        balanceSheet.setFiscalDateEnding(balanceSheetNode.path(
            "fiscalDateEnding").asText());
        balanceSheet.setReportedCurrency(balanceSheetNode.path(
            "reportedCurrency").asText());
        balanceSheet.setTotalAssets(balanceSheetNode.path(
            "totalAssets").asLong());
        balanceSheet.setTotalCurrentAssets(balanceSheetNode.path(
            "totalCurrentAssets").asLong());
        balanceSheet.setCashAndCashEquivalentsAtCarryingValue(balanceSheetNode.path(
            "cashAndCashEquivalentsAtCarryingValue").asLong());
        balanceSheet.setCashAndShortTermInvestments(balanceSheetNode.path(
            "cashAndShortTermInvestments").asLong());
        balanceSheet.setInventory(balanceSheetNode.path(
            "inventory").asLong());
        balanceSheet.setCurrentNetReceivables(balanceSheetNode.path(
            "currentNetReceivables").asLong());
        balanceSheet.setTotalNonCurrentAssets(balanceSheetNode.path(
            "totalNonCurrentAssets").asLong());
        balanceSheet.setPropertyPlantEquipment(balanceSheetNode.path(
            "propertyPlantEquipment").asLong());
        balanceSheet.setAccumulatedDepreciationAmortizationPPE(balanceSheetNode.path(
            "accumulatedDepreciationAmortizationPPE").asLong());
        balanceSheet.setIntangibleAssets(balanceSheetNode.path(
            "intangibleAssets").asLong());
        balanceSheet.setIntangibleAssetsExcludingGoodwill(balanceSheetNode.path(
            "intangibleAssetsExcludingGoodwill").asLong());
        balanceSheet.setGoodwill(balanceSheetNode.path(
            "goodwill").asLong());
        balanceSheet.setInvestments(balanceSheetNode.path(
            "investments").asLong());
        balanceSheet.setLongTermInvestments(balanceSheetNode.path(
            "longTermInvestments").asLong());
        balanceSheet.setShortTermInvestments(balanceSheetNode.path(
            "shortTermInvestments").asLong());
        balanceSheet.setOtherCurrentAssets(balanceSheetNode.path(
            "otherCurrentAssets").asLong());
        balanceSheet.setOtherNonCurrentAssets(balanceSheetNode.path(
            "otherNonCurrentAssets").asLong());
        balanceSheet.setTotalLiabilities(balanceSheetNode.path(
            "totalLiabilities").asLong());
        balanceSheet.setTotalCurrentLiabilities(balanceSheetNode.path(
            "totalCurrentLiabilities").asLong());
        balanceSheet.setCurrentAccountsPayable(balanceSheetNode.path(
            "currentAccountsPayable").asLong());
        balanceSheet.setDeferredRevenue(balanceSheetNode.path(
            "deferredRevenue").asLong());
        balanceSheet.setCurrentDebt(balanceSheetNode.path(
            "currentDebt").asLong());
        balanceSheet.setShortTermDebt(balanceSheetNode.path(
            "shortTermDebt").asLong());
        balanceSheet.setTotalNonCurrentLiabilities(balanceSheetNode.path(
            "totalNonCurrentLiabilities").asLong());
        balanceSheet.setCapitalLeaseObligations(balanceSheetNode.path(
            "capitalLeaseObligations").asLong());
        balanceSheet.setLongTermDebt(balanceSheetNode.path(
            "longTermDebt").asLong());
        balanceSheet.setCurrentLongTermDebt(balanceSheetNode.path(
            "currentLongTermDebt").asLong());
        balanceSheet.setLongTermDebtNoncurrent(balanceSheetNode.path(
            "longTermDebtNoncurrent").asLong());
        balanceSheet.setShortLongTermDebtTotal(balanceSheetNode.path(
            "shortLongTermDebtTotal").asLong());
        balanceSheet.setOtherCurrentLiabilities(balanceSheetNode.path(
            "otherCurrentLiabilities").asLong());
        balanceSheet.setOtherNonCurrentLiabilities(balanceSheetNode.path(
            "otherNonCurrentLiabilities").asLong());
        balanceSheet.setTotalShareholderEquity(balanceSheetNode.path(
            "totalShareholderEquity").asLong());
        balanceSheet.setTreasuryStock(balanceSheetNode.path(
            "treasuryStock").asLong());
        balanceSheet.setRetainedEarnings(balanceSheetNode.path(
            "retainedEarnings").asLong());
        balanceSheet.setCommonStock(balanceSheetNode.path(
            "commonStock").asLong());
        balanceSheet.setCommonStockSharesOutstanding(balanceSheetNode.path(
            "commonStockSharesOutstanding").asLong());
        balanceSheets.add(balanceSheet);
      }
    } else {
      System.out.println("No balance sheet data found.");
    }
    return balanceSheets;
  }

  public List<CashFlowReport> parseCashFlowReports(String body) {
    ObjectMapper mapper = new ObjectMapper();
    List<CashFlowReport> cashFlowReports = new ArrayList<>();
    try {
      JsonNode symbolNode = mapper.readTree(body).path("symbol");
      String symbol = symbolNode.asText();
      JsonNode root = mapper.readTree(body);
      JsonNode cashFlowReportsNode = root.path("annualReports");
      cashFlowReports = parseCashFlowReportNode(symbol, cashFlowReportsNode);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return cashFlowReports;
  }

  public List<CashFlowReport> parseQuarterlyCashFlowReports(String body) {
    ObjectMapper mapper = new ObjectMapper();
    List<CashFlowReport> cashFlowReports = new ArrayList<>();
    try {
      JsonNode symbolNode = mapper.readTree(body).path("symbol");
      String symbol = symbolNode.asText();
      JsonNode root = mapper.readTree(body);
      JsonNode cashFlowReportsNode = root.path("quarterlyReports");
      cashFlowReports = parseCashFlowReportNode(symbol, cashFlowReportsNode);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return cashFlowReports;
  }

  private List<CashFlowReport> parseCashFlowReportNode(String symbol, JsonNode cashFlowReportsNode) {
    List<CashFlowReport> cashFlowReports = new ArrayList<>();
    if (!cashFlowReportsNode.isMissingNode()) {
      for (JsonNode cashFlowReportNode : cashFlowReportsNode) {
        CashFlowReport cashFlowReport = new CashFlowReport(symbol);
        cashFlowReport.setFiscalDateEnding(cashFlowReportNode.path(
            "fiscalDateEnding").asText());
        cashFlowReport.setReportedCurrency(cashFlowReportNode.path(
            "reportedCurrency").asText());
        cashFlowReport.setOperatingCashflow(cashFlowReportNode.path(
            "operatingCashflow").asLong());
        cashFlowReport.setPaymentsForOperatingActivities(cashFlowReportNode.path(
            "paymentsForOperatingActivities").asLong());
        cashFlowReport.setProceedsFromOperatingActivities(cashFlowReportNode.path(
            "proceedsFromOperatingActivities").asLong());
        cashFlowReport.setChangeInOperatingLiabilities(cashFlowReportNode.path(
            "changeInOperatingLiabilities").asLong());
        cashFlowReport.setChangeInOperatingAssets(cashFlowReportNode.path(
            "changeInOperatingAssets").asLong());
        cashFlowReport.setDepreciationDepletionAndAmortization(cashFlowReportNode.path(
            "depreciationDepletionAndAmortization").asLong());
        cashFlowReport.setCapitalExpenditures(cashFlowReportNode.path(
            "capitalExpenditures").asLong());
        cashFlowReport.setChangeInReceivables(cashFlowReportNode.path(
            "changeInReceivables").asLong());
        cashFlowReport.setChangeInInventory(cashFlowReportNode.path(
            "changeInInventory").asLong());
        cashFlowReport.setProfitLoss(cashFlowReportNode.path(
            "profitLoss").asLong());
        cashFlowReport.setCashflowFromInvestment(cashFlowReportNode.path(
            "cashflowFromInvestment").asLong());
        cashFlowReport.setCashflowFromFinancing(cashFlowReportNode.path(
            "cashflowFromFinancing").asLong());
        cashFlowReport.setProceedsFromRepaymentsOfShortTermDebt(cashFlowReportNode.path(
            "proceedsFromRepaymentsOfShortTermDebt").asLong());
        cashFlowReport.setPaymentsForRepurchaseOfCommonStock(cashFlowReportNode.path(
            "paymentsForRepurchaseOfCommonStock").asLong());
        cashFlowReport.setPaymentsForRepurchaseOfEquity(cashFlowReportNode.path(
            "paymentsForRepurchaseOfEquity").asLong());
        cashFlowReport.setPaymentsForRepurchaseOfPreferredStock(cashFlowReportNode.path(
            "paymentsForRepurchaseOfPreferredStock").asLong());
        cashFlowReport.setDividendPayout(cashFlowReportNode.path(
            "dividendPayout").asLong());
        cashFlowReport.setDividendPayoutCommonStock(cashFlowReportNode.path(
            "dividendPayoutCommonStock").asLong());
        cashFlowReport.setDividendPayoutPreferredStock(cashFlowReportNode.path(
            "dividendPayoutPreferredStock").asLong());
        cashFlowReport.setProceedsFromIssuanceOfCommonStock(cashFlowReportNode.path(
            "proceedsFromIssuanceOfCommonStock").asLong());
        cashFlowReport.setProceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet(
            cashFlowReportNode.path(
            "proceedsFromIssuanceOfLongTermDebtAndCapitalSecuritiesNet").asLong());
        cashFlowReport.setProceedsFromIssuanceOfPreferredStock(cashFlowReportNode.path(
            "proceedsFromIssuanceOfPreferredStock").asLong());
        cashFlowReport.setProceedsFromRepurchaseOfEquity(cashFlowReportNode.path(
            "proceedsFromRepurchaseOfEquity").asLong());
        cashFlowReport.setProceedsFromSaleOfTreasuryStock(cashFlowReportNode.path(
            "proceedsFromSaleOfTreasuryStock").asLong());
        cashFlowReport.setChangeInCashAndCashEquivalents(cashFlowReportNode.path(
            "changeInCashAndCashEquivalents").asLong());
        cashFlowReport.setChangeInExchangeRate(cashFlowReportNode.path(
            "changeInExchangeRate").asLong());
        cashFlowReport.setNetIncome(cashFlowReportNode.path(
            "netIncome").asLong());
        cashFlowReports.add(cashFlowReport);
      }
    } else {
      System.out.println("No cash flow report data found.");
    }
    return cashFlowReports;
  }

  public EarningsData parseEarningsData(String body) {
    ObjectMapper mapper = new ObjectMapper();
    EarningsData earningsData = new EarningsData();

    List<AnnualEarnings> annualEarningsList = new ArrayList<>();
    List<QuarterlyEarnings> quarterlyEarningsList = new ArrayList<>();
    try {
      JsonNode symbolNode = mapper.readTree(body).path("symbol");
      String symbol = symbolNode.asText();
      JsonNode root = mapper.readTree(body);
      JsonNode annualEarningsNode = root.path("annualEarnings");
      if (!annualEarningsNode.isMissingNode()) {
        earningsData.setAnnualEarnings(
            parseAnnualEarningsNode(symbol, annualEarningsNode)
        );
      } else {
        System.out.println("No annual earnings data found.");
      }
      JsonNode quarterlyEarningsNode = root.path("quarterlyEarnings");
      if (!quarterlyEarningsNode.isMissingNode()) {
        earningsData.setQuarterlyEarnings(
            parseQuarterlyEarningsNode(symbol, quarterlyEarningsNode)
        );
      } else {
        System.out.println("No quarterly earnings data found.");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return earningsData;
  }

  public List<AnnualEarnings> parseAnnualEarningsNode(String symbol, JsonNode annualEarningsNode) {
    List<AnnualEarnings> annualEarningsList = new ArrayList<>();
    if (!annualEarningsNode.isMissingNode()) {
      for (JsonNode annualEarnings : annualEarningsNode) {
        AnnualEarnings annualEarningsData = new AnnualEarnings(symbol);
        annualEarningsData.setFiscalDateEnding(annualEarnings.path(
            "fiscalDateEnding").asText());
        annualEarningsData.setReportedEPS(annualEarnings.path(
            "reportedEPS").asDouble());
        annualEarningsList.add(annualEarningsData);
      }
    } else {
      System.out.println("No annual earnings data found.");
    }
    return annualEarningsList;
  }

  public List<QuarterlyEarnings> parseQuarterlyEarningsNode(String symbol, JsonNode quarterlyEarningsNode) {
    List<QuarterlyEarnings> quarterlyEarningsList = new ArrayList<>();
    if (!quarterlyEarningsNode.isMissingNode()) {
      for (JsonNode quarterlyEarnings : quarterlyEarningsNode) {
        QuarterlyEarnings quarterlyEarningsData = new QuarterlyEarnings(symbol);
        quarterlyEarningsData.setFiscalDateEnding(quarterlyEarnings.path(
            "fiscalDateEnding").asText());
        quarterlyEarningsData.setReportedDate(quarterlyEarnings.path(
            "reportedDate").asText());
        quarterlyEarningsData.setReportedEPS(quarterlyEarnings.path(
            "reportedEPS").asDouble());
        quarterlyEarningsData.setEstimatedEPS(quarterlyEarnings.path(
            "estimatedEPS").asDouble());
        quarterlyEarningsData.setSurprise(quarterlyEarnings.path(
            "surprise").asDouble());
        quarterlyEarningsData.setSurprisePercentage(quarterlyEarnings.path(
            "surprisePercentage").asDouble());
        quarterlyEarningsList.add(quarterlyEarningsData);
      }
    } else {
      System.out.println("No quarterly earnings data found.");
    }
    return quarterlyEarningsList;
  }

  public DailyTopLists parseDailyTopLists(String body) {
    ObjectMapper mapper = new ObjectMapper();
    DailyTopLists dailyTopLists = new DailyTopLists();
    try {
      JsonNode root = mapper.readTree(body);
      dailyTopLists.setInformation(root.path("metadata").asText());
      dailyTopLists.setLastUpdated(root.path("last_updated").asText());
      dailyTopLists.setTopGainers(parseTopListDataPoints(root.path("top_gainers")));
      dailyTopLists.setTopLosers(parseTopListDataPoints(root.path("top_losers")));
      dailyTopLists.setMostTraded(parseTopListDataPoints(root.path("most_actively_traded")));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return dailyTopLists;
  }

  private List<TopListDataPoint> parseTopListDataPoints(JsonNode topList) {
    List<TopListDataPoint> topListDataPoints = new ArrayList<>();
    for (JsonNode entry : topList) {
      TopListDataPoint topListDataPoint = new TopListDataPoint(entry.path("ticker").asText());
      topListDataPoint.setCurrentPrice(entry.path("price").asDouble());
      topListDataPoint.setChangeAmount(entry.path("change_amount").asDouble());
      topListDataPoint.setChangePercentage(entry.path("change_percentage").asText());
      topListDataPoint.setTradingVolume(entry.path("volume").asLong());
      topListDataPoints.add(topListDataPoint);
    }
    return topListDataPoints;
  }

  public List<MarketStatus> parseMarketStatus(String body) {
    ObjectMapper mapper = new ObjectMapper();
    List<MarketStatus> marketStatusList = new ArrayList<>();
    try {
      JsonNode root = mapper.readTree(body);
      for (JsonNode entry : root) {
        MarketStatus marketStatus = new MarketStatus();
        marketStatus.setMarketType(entry.path("market_type").asText());
        marketStatus.setRegion(entry.path("region").asText());
        marketStatus.setPrimaryExchanges(entry.path("primary_exchanges").asText());
        marketStatus.setLocalOpeningTime(entry.path("local_open").asText());
        marketStatus.setLocalClosingTime(entry.path("local_close").asText());
        marketStatus.setCurrentStatus(entry.path("current_status").asText());
        marketStatusList.add(marketStatus);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return marketStatusList;
  }
}
