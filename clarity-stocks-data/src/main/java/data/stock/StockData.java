package data.stock;

import analysis.graph.GoldenCrossAnalysis;
import analysis.regression.RegressionAnalysis;
import common.data.fundamental.BalanceSheet;
import common.data.fundamental.CashFlowReport;
import common.data.fundamental.CompanyOverview;
import common.data.fundamental.EarningsData;
import common.data.fundamental.IncomeStatement;
import common.data.series.TimeSeriesDaily;
import common.data.series.TimeSeriesMonthly;
import common.evaluations.general.DividendEvaluation;
import common.evaluations.rating.AnalystPredictionEvaluation;
import common.evaluations.rating.BusinessPerformanceEvaluation;
import common.evaluations.rating.CompanyGrowthEvaluation;
import common.evaluations.rating.CompanySizeEvaluation;
import common.evaluations.rating.HighAndLowEvaluation;
import common.evaluations.rating.PERatioEvaluation;
import common.evaluations.rating.PriceToPerformanceEvaluation;
import java.util.Comparator;
import java.util.List;

/**
 * StockData class represents the data for a stock.
 * <p>
 * This class contains all the data for a stock, including company overview, time series data, and
 * fundamental data. It also contains evaluations and analyses of the stock data.
 * </p>
 *
 * <p>
 * The class contains methods for running evaluations and analyses on the stock data.
 * </p>
 *
 * @see CompanyOverview
 * @see TimeSeriesDaily
 * @see TimeSeriesMonthly
 * @see EarningsData
 * @see IncomeStatement
 * @see BalanceSheet
 * @see CashFlowReport
 * @see HighAndLowEvaluation
 * @see PERatioEvaluation
 * @see PriceToPerformanceEvaluation
 * @see CompanySizeEvaluation
 * @see CompanyGrowthEvaluation
 * @see BusinessPerformanceEvaluation
 * @see DividendEvaluation
 * @see AnalystPredictionEvaluation
 * @see GoldenCrossAnalysis
 * @see RegressionAnalysis
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
  private HighAndLowEvaluation highAndLow;
  private PERatioEvaluation peRatioEvaluation;
  private PriceToPerformanceEvaluation priceToPerformance;
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
    evaluateHighAndLow();
    evaluatePriceToPerformance();
    runGoldenCrossAnalysis();
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
   * @see PriceToPerformanceEvaluation for the evaluation details.
   */
  private void evaluatePriceToPerformance() {
    priceToPerformance = new PriceToPerformanceEvaluation(
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
    if (timeSeriesDaily != null) {
      highAndLow = new HighAndLowEvaluation(
          companyOverview.getSymbol(),
          companyOverview.getWeek52High(),
          companyOverview.getWeek52Low(),
          timeSeriesDaily.getLatestClose()
      );
    }
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
        getOrderedIncomeStatementData(),
        timeSeriesMonthly.getPriceDataMatching(
            incomeStatements.getFirst().getFiscalDateEnding(),
            incomeStatements.size()
        )
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

  /**
   * This method returns a list of {@code IncomeStatement} objects sorted in chronological order.
   * @return A {@code List} of {@code IncomeStatement} objects.
   * @see IncomeStatement
   *
   * @author Joar Eliasson
   */
  public List<IncomeStatement> getOrderedIncomeStatementData() {
    List<IncomeStatement> chronologicalIncomeStatements = incomeStatements;
    chronologicalIncomeStatements.sort(Comparator.comparing(IncomeStatement::getFiscalDateEnding));
    return chronologicalIncomeStatements;
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

  public HighAndLowEvaluation getHighAndLow() {
    return highAndLow;
  }

  public PERatioEvaluation getPeRatioEvaluation() {
    return peRatioEvaluation;
  }

  public PriceToPerformanceEvaluation getPriceToPerformance() {
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
        dividendEvaluation, goldenCrossAnalysis, highAndLow, earningsData, cashFlowReports,
        balanceSheets, incomeStatements
    );
  }
}
