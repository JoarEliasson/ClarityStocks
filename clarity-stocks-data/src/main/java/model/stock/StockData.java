package model.stock;

import analysis.regression.RegressionAnalysis;
import common.data.fundamental.BalanceSheet;
import common.data.fundamental.CashFlowReport;
import common.data.fundamental.CompanyOverview;
import common.data.fundamental.EarningsData;
import common.data.fundamental.IncomeStatement;
import common.data.series.TimeSeriesDaily;
import common.data.series.TimeSeriesMonthly;
import common.evaluations.rating.AnalystPredictionEvaluation;
import common.evaluations.rating.BusinessPerformanceEvaluation;
import common.evaluations.rating.CompanyGrowthEvaluation;
import common.evaluations.rating.CompanySizeEvaluation;
import common.evaluations.DividendEvaluation;
import analysis.graph.GoldenCrossAnalysis;
import common.evaluations.rating.HighAndLowEvaluation;
import common.evaluations.rating.PERatioEvaluation;
import common.evaluations.rating.PriceToPerformance;
import java.util.List;


/**
 * This class represents a company's stock data, including evaluations, time series data, general
 * information, and fundamental data. Objects of this class contain all necessary data used by the
 * GUI to display information about a stock.
 *
 * @author Joar Eliasson
 */
public class StockData {

  /**
   * Stock data variables.
   */
  private CompanyOverview companyOverview;
  private TimeSeriesDaily timeSeriesDaily;
  private TimeSeriesMonthly timeSeriesMonthly;
  private EarningsData earningsData;
  private List<IncomeStatement> incomeStatements;
  private List<BalanceSheet> balanceSheets;
  private List<CashFlowReport> cashFlowReports;

  /**
   * Evaluation variables.
   */
  private HighAndLowEvaluation highAndLowEvaluation;
  private PERatioEvaluation peRatioEvaluation;
  private PriceToPerformance priceToPerformance;
  private CompanySizeEvaluation companySizeEvaluation;
  private CompanyGrowthEvaluation companyGrowthEvaluation;
  private BusinessPerformanceEvaluation businessPerformanceEvaluation;
  private DividendEvaluation dividendEvaluation;
  private AnalystPredictionEvaluation analystPredictionEvaluation;

  /**
   * Analysis variables.
   */
  private GoldenCrossAnalysis goldenCrossAnalysis;
  private RegressionAnalysis regressionAnalysis;

  /**
   * This method runs all evaluations on the retrieved stock data. The evaluations are stored as
   * instance variables in the class.
   */
  public void runEvaluations() {
    evaluatePERatio();
    evaluatePriceToPerformance();
    evaluateCompanySize();
    evaluateCompanyGrowth();
    evaluateBusinessPerformance();
    evaluateDividend();
    runGoldenCrossAnalysis();
    evaluateHighAndLow();
    evaluateAnalystsPredictions();
  }

  public void runAnalyses() {
    runGoldenCrossAnalysis();
    runRegressionAnalysis();
  }

  /**
   * This method creates a new instance of {@code PERatioEvaluation} representing the evaluation.
   * The result is stored as an instance variable.
   * @see PERatioEvaluation for the evaluation details.
   */
  private void evaluatePERatio() {
    peRatioEvaluation = new PERatioEvaluation(
        companyOverview.getSymbol(), companyOverview.getPERatio()
    );
  }

  /**
   * This method creates a new instance of {@code PriceToPerformance} representing the evaluation.
   * The result is stored as an instance variable.
   * @see PriceToPerformance for the evaluation details.
   */
  private void evaluatePriceToPerformance() {
    priceToPerformance = new PriceToPerformance(
        peRatioEvaluation, companyOverview.getSector()
    );
  }

  /**
   * This method creates a new instance of {@code BusinessPerformanceEvaluation} representing the
   * evaluation. The result is stored as an instance variable.
   * @see BusinessPerformanceEvaluation for the evaluation details.
   */
  private void evaluateBusinessPerformance() {
    businessPerformanceEvaluation = new BusinessPerformanceEvaluation(
        companyOverview.getSymbol(),
        companyOverview.getEBITDA(),
        companyOverview.getRevenueTTM()
    );
  }

  /**
   * This method creates a new instance of {@code CompanyGrowthEvaluation} representing the evaluation. The
   * result is stored as an instance variable.
   * @see CompanyGrowthEvaluation for the evaluation details.
   */
  private void evaluateCompanyGrowth() {
    companyGrowthEvaluation = new CompanyGrowthEvaluation(
            companyOverview.getSymbol(),
            companyOverview.getQuarterlyRevenueGrowthYOY()
    );
  }

  /**
   * This method creates a new instance of {@code CompanySizeEvaluation} representing the evaluation. The
   * result is stored as an instance variable.
   * @see CompanySizeEvaluation for the evaluation details.
   */
  private void evaluateCompanySize() {
    companySizeEvaluation = new CompanySizeEvaluation(
          companyOverview.getSymbol(),
          companyOverview.getRevenueTTM(),
          companyOverview.getMarketCapitalization()
    );
  }

  /**
   * This method creates a new instance of {@code DividendEvaluation} representing the evaluation.
   * The result is stored as an instance variable.
   * @see DividendEvaluation for the evaluation details.
   */
  private void evaluateDividend() {
    dividendEvaluation = new DividendEvaluation(
        companyOverview.getSymbol(),
        companyOverview.getDividendPerShare(),
        companyOverview.getDividendYield(),
        companyOverview.getFiscalYearEnd(),
        cashFlowReports
    );
  }

  /**
   * This method creates a new instance of {@code HighAndLow} representing the evaluation. The result
   * is stored as an instance variable.
   * @see HighAndLowEvaluation for the evaluation details.
   */
  private void evaluateHighAndLow() {
    highAndLowEvaluation = new HighAndLowEvaluation(
        companyOverview.getSymbol(),
        companyOverview.getWeek52High(),
        companyOverview.getWeek52Low(),
        timeSeriesDaily.getLatestClose()
    );
  }

  /**
   * This method creates a new instance of {@code AnalystPredictionEvaluation} representing the
   * evaluation. The result is stored as an instance variable.
   * @see AnalystPredictionEvaluation for the evaluation details.
   */
  private void evaluateAnalystsPredictions() {
    analystPredictionEvaluation = new AnalystPredictionEvaluation(
        companyOverview.getSymbol(),
        timeSeriesDaily.getLatestClose(),
        companyOverview.getAnalystTargetPrice(),
        companyOverview.getAnalystRatingStrongBuy(),
        companyOverview.getAnalystRatingBuy(),
        companyOverview.getAnalystRatingHold(),
        companyOverview.getAnalystRatingSell(),
        companyOverview.getAnalystRatingStrongSell()
    );
  }

  /**
   * This method creates a new instance of {@code GoldenCrossEvaluation} representing the evaluation.
   * The result is stored as an instance variable.
   * @see GoldenCrossAnalysis for the evaluation details.
   */
  private void runGoldenCrossAnalysis() {
    goldenCrossAnalysis = new GoldenCrossAnalysis(
        companyOverview.getSymbol(),
        companyOverview.getMovingAverage50(),
        companyOverview.getMovingAverage200(),
        timeSeriesDaily.getGoldenCrossEvents()
    );
  }

  /**
   * This method creates a new instance of {@code RegressionAnalysis} representing the analysis.
   * The result is stored as an instance variable.
   * @see RegressionAnalysis for the analysis details.
   */
  private void runRegressionAnalysis() {
    regressionAnalysis = new RegressionAnalysis(
        companyOverview.getSymbol(),
        incomeStatements,
        timeSeriesMonthly
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

  public TimeSeriesMonthly getTimeSeriesMonthly() {
    return timeSeriesMonthly;
  }

  public void setTimeSeriesMonthly(TimeSeriesMonthly timeSeriesMonthly) {
    this.timeSeriesMonthly = timeSeriesMonthly;
  }

  public EarningsData getEarningsData() {
    return earningsData;
  }

  public void setEarningsData(EarningsData earningsData) {
    this.earningsData = earningsData;
  }

  public List<IncomeStatement> getIncomeStatements() {
    return incomeStatements;
  }

  public void setIncomeStatements(
      List<IncomeStatement> incomeStatements) {
    this.incomeStatements = incomeStatements;
  }

  public List<BalanceSheet> getBalanceSheets() {
    return balanceSheets;
  }

  public void setBalanceSheets(
      List<BalanceSheet> balanceSheets) {
    this.balanceSheets = balanceSheets;
  }

  public List<CashFlowReport> getCashFlowReports() {
    return cashFlowReports;
  }

  public void setCashFlowReports(
      List<CashFlowReport> cashFlowReports) {
    this.cashFlowReports = cashFlowReports;
  }

  public HighAndLowEvaluation getHighAndLowEvaluation() {
    return highAndLowEvaluation;
  }

  public PERatioEvaluation getPeRatioEvaluation() {
    return peRatioEvaluation;
  }

  public PriceToPerformance getPriceToPerformance() {
    return priceToPerformance;
  }

  public CompanySizeEvaluation getCompanySizeEvaluation() {
    return companySizeEvaluation;
  }

  public CompanyGrowthEvaluation getCompanyGrowthEvaluation() {
    return companyGrowthEvaluation;
  }

  public BusinessPerformanceEvaluation getBusinessPerformanceEvaluation() {
    return businessPerformanceEvaluation;
  }

  public DividendEvaluation getDividendEvaluation() {
    return dividendEvaluation;
  }

  public AnalystPredictionEvaluation getAnalystPredictionEvaluation() {
    return analystPredictionEvaluation;
  }

  public GoldenCrossAnalysis getGoldenCrossAnalysis() {
    return goldenCrossAnalysis;
  }

  public RegressionAnalysis getRegressionAnalysis() {
    return regressionAnalysis;
  }

  @Override
  public String toString() {
    return String.format(
        "Company Overview: %s%nTime Series Daily: %s%nPE Ratio Evaluation: %s%n"
        + "Business Performance Evaluation: %s%nDividend Evaluation: %s%n"
        + "Golden Cross Evaluation: %s%nHigh and Low: %s%nEarnings Data: %s%n"
        + "Cash Flow Reports: %s%nBalance Sheets: %s%nIncome Statements: %s%n",
        companyOverview, timeSeriesDaily, peRatioEvaluation, businessPerformanceEvaluation,
        dividendEvaluation, goldenCrossAnalysis, highAndLowEvaluation, earningsData, cashFlowReports,
        balanceSheets, incomeStatements
    );
  }
}
