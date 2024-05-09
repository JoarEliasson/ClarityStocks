package model.stock;

import common.evaluations.BusinessPerformanceEvaluation;
import common.evaluations.CompanyGrowthEvaluation;
import common.evaluations.CompanySizeEvaluation;
import common.evaluations.DividendEvaluation;
import common.evaluations.GoldenCrossEvaluation;
import common.evaluations.HighAndLow;
import common.evaluations.PERatioEvaluation;
import analysis.regression.RegressionAnalysis;
import common.data.fundamental.BalanceSheet;
import common.data.fundamental.CashFlowReport;
import common.data.fundamental.CompanyOverview;
import common.data.fundamental.EarningsData;
import common.data.fundamental.IncomeStatement;
import common.data.series.TimeSeriesDaily;
import common.data.series.TimeSeriesMonthly;
import java.util.Comparator;
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
  private HighAndLow highAndLow;
  private PERatioEvaluation peRatioEvaluation;
  private CompanySizeEvaluation companySizeEvaluation;
  private CompanyGrowthEvaluation companyGrowthEvaluation;
  private BusinessPerformanceEvaluation businessPerformanceEvaluation;
  private DividendEvaluation dividendEvaluation;
  private GoldenCrossEvaluation goldenCrossEvaluation;

  /**
   * Analysis variables.
   */
  private RegressionAnalysis regressionAnalysis;

  /**
   * This method runs all evaluations on the retrieved stock data. The evaluations are stored as
   * instance variables in the class.
   */
  public void runEvaluations() {
    evaluatePERatio();
    evaluateCompanySize();
    evaluateCompanyGrowth();
    evaluateBusinessPerformance();
    evaluateDividend();
    evaluateGoldenCross();
    evaluateHighAndLow();
  }

  public void runAnalyses() {
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
          companyOverview.getRevenueTTM()
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
        companyOverview.getFiscalYearEnd()
    );
  }

  /**
   * This method creates a new instance of {@code GoldenCrossEvaluation} representing the evaluation.
   * The result is stored as an instance variable.
   * @see GoldenCrossEvaluation for the evaluation details.
   */
  private void evaluateGoldenCross() {
    goldenCrossEvaluation = new GoldenCrossEvaluation(
        companyOverview.getSymbol(),
        companyOverview.getMovingAverage50(),
        companyOverview.getMovingAverage200()
    );
  }

  /**
   * This method creates a new instance of {@code HighAndLow} representing the evaluation. The result
   * is stored as an instance variable.
   * @see HighAndLow for the evaluation details.
   */
  private void evaluateHighAndLow() {
    highAndLow = new HighAndLow(
        companyOverview.getSymbol(),
        companyOverview.getWeek52High(),
        companyOverview.getWeek52Low()
    );
  }

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

  public HighAndLow getHighAndLow() {
    return highAndLow;
  }

  public PERatioEvaluation getPeRatioEvaluation() {
    return peRatioEvaluation;
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

  public GoldenCrossEvaluation getGoldenCrossEvaluation() {
    return goldenCrossEvaluation;
  }

  @Override
  public String toString() {
    return String.format(
        "Company Overview: %s%nTime Series Daily: %s%nPE Ratio Evaluation: %s%n"
        + "Business Performance Evaluation: %s%nDividend Evaluation: %s%n"
        + "Golden Cross Evaluation: %s%nHigh and Low: %s%nEarnings Data: %s%n"
        + "Cash Flow Reports: %s%nBalance Sheets: %s%nIncome Statements: %s%n",
        companyOverview, timeSeriesDaily, peRatioEvaluation, businessPerformanceEvaluation,
        dividendEvaluation, goldenCrossEvaluation, highAndLow, earningsData, cashFlowReports,
        balanceSheets, incomeStatements
    );
  }
}
