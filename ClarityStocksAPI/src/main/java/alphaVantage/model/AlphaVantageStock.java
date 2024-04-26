package alphaVantage.model;

import alphaVantage.model.data.fundamental.BalanceSheet;
import alphaVantage.model.data.fundamental.CashFlowReport;
import alphaVantage.model.data.fundamental.CompanyOverview;
import alphaVantage.model.data.fundamental.EarningsData;
import alphaVantage.model.data.fundamental.IncomeStatement;
import alphaVantage.model.data.series.TimeSeriesDaily;
import analysis.model.evaluations.*;
import analysis.model.unfinished.PriceToPerformance;
import java.util.List;

/**
 * Class for the Alpha vantage stock. Runs several evaluations of the stock object.
 * @author Joar Eliasson, Olivia Svensson
 * */
public class AlphaVantageStock {

  private CompanyOverview companyOverview;
  private TimeSeriesDaily timeSeriesDaily;
  private PERatioEvaluation peRatioEvaluation;
  private BusinessPerformanceEvaluation businessPerformanceEvaluation;
  private DividendEvaluation dividendEvaluation;
  private GoldenCrossEvaluation goldenCrossEvaluation;
  private HighAndLow highAndLow;
  private PriceToPerformance priceToPerformance;
  private List<IncomeStatement> incomeStatements;
  private List<BalanceSheet> balanceSheets;
  private List<CashFlowReport> cashFlowReports;
  private EarningsData earningsData;
  private CompanyGrowth companyGrowth;
  private CompanySize companySize;


  public void runEvaluations() {
    evaluatePERatio();
    evaluateBusinessPerformance();
    evaluateDividend();
    evaluateGoldenCross();
    evaluateHighAndLow();
    evaluatePriceToPerformance();
    evaluateCompanyGrowth();
    evaluateCompanySize();
  }

  private void evaluatePERatio() {
    peRatioEvaluation = new PERatioEvaluation(
        companyOverview.getSymbol(), companyOverview.getPERatio()
    );
  }

  private void evaluateBusinessPerformance() {
    businessPerformanceEvaluation = new BusinessPerformanceEvaluation(
        companyOverview.getSymbol(),
        companyOverview.getEBITDA(),
        companyOverview.getRevenueTTM()
    );
  }


  private void evaluateCompanyGrowth() {
    companyGrowth = new CompanyGrowth(
            companyGrowth.getSymbol(),
            companyOverview.getQuarterlyRevenueGrowthYOY()

  private void evaluateCompanySize() {
    companySize = new CompanySize(
          companyOverview.getSymbol(),
          companyOverview.getRevenueTTM()

    );
  }

  private void evaluateDividend() {
    dividendEvaluation = new DividendEvaluation(
        companyOverview.getSymbol(),
        companyOverview.getDividendPerShare(),
        companyOverview.getDividendYield(),
        companyOverview.getFiscalYearEnd()
    );
  }

  private void evaluateGoldenCross() {
    goldenCrossEvaluation = new GoldenCrossEvaluation(
        companyOverview.getSymbol(),
        companyOverview.getMovingAverage50(),
        companyOverview.getMovingAverage200()
    );
  }

  private void evaluateHighAndLow() {
    highAndLow = new HighAndLow(
        companyOverview.getSymbol(),
        companyOverview.getWeek52High(),
        companyOverview.getWeek52Low()
    );
  }

  private void evaluatePriceToPerformance() {
    priceToPerformance = new PriceToPerformance(
        companyOverview.getSymbol(),
        companyOverview.getPERatio(),
        companyOverview.getSector()
    );
  }

  public CompanyOverview getCompanyOverview() {
    return companyOverview;
  }

  public void setCompanyOverview(CompanyOverview companyOverview) {
    this.companyOverview = companyOverview;
  }

  public TimeSeriesDaily getTimeSeriesDaily() {
    return timeSeriesDaily;
  }

  public void setTimeSeriesDaily(TimeSeriesDaily timeSeriesDaily) {
    this.timeSeriesDaily = timeSeriesDaily;
  }

  public PERatioEvaluation getPeRatioEvaluation() {
    return peRatioEvaluation;
  }

  public BusinessPerformanceEvaluation getBusinessPerformanceEvaluation() {
    return businessPerformanceEvaluation;
  }

  public DividendEvaluation getDividendEvaluation() {
    return dividendEvaluation;
  }

  public GoldenCrossEvaluation getGoldenCrossEvaluation() {
    return goldenCrossEvaluation;
  }

  public HighAndLow getHighAndLow() {
    return highAndLow;
  }

  public PriceToPerformance getPriceToPerformance() {
    return priceToPerformance;
  }

  public EarningsData getEarningsData() {
    return earningsData;
  }

  public void setEarningsData(EarningsData earningsData) {
    this.earningsData = earningsData;
  }

  public List<CashFlowReport> getCashFlowReports() {
    return cashFlowReports;
  }

  public void setCashFlowReports(List<CashFlowReport> cashFlowReports) {
    this.cashFlowReports = cashFlowReports;
  }

  public List<BalanceSheet> getBalanceSheets() {
    return balanceSheets;
  }

  public void setBalanceSheets(List<BalanceSheet> balanceSheets) {
    this.balanceSheets = balanceSheets;
  }

  public List<IncomeStatement> getIncomeStatements() {
    return incomeStatements;
  }

  public void setIncomeStatements(List<IncomeStatement> incomeStatements) {
    this.incomeStatements = incomeStatements;
  }

  @Override
  public String toString() {
    return String.format(
        "Company Overview: %s%nTime Series Daily: %s%nPE Ratio Evaluation: %s%n"
        + "Business Performance Evaluation: %s%nDividend Evaluation: %s%n"
        + "Golden Cross Evaluation: %s%nHigh and Low: %s%nPrice to Performance: %s%n"
        + "Earnings Data: %s%nCash Flow Reports: %s%nBalance Sheets: %s%n"
        + "Income Statements: %s%n",
        companyOverview, timeSeriesDaily, peRatioEvaluation, businessPerformanceEvaluation,
        dividendEvaluation, goldenCrossEvaluation, highAndLow, priceToPerformance, earningsData,
        cashFlowReports, balanceSheets, incomeStatements
    );
  }
}
