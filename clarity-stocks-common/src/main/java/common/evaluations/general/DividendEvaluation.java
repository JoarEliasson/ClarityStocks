package common.evaluations.general;

import common.data.fundamental.CashFlowReport;
import common.data.fundamental.CompanyOverview;
import common.interfaces.Evaluation;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class for evaluating the dividend yield and historical dividend payout.
 * <p>
 * This class calculates the dividend yield for the fiscal year and analyzes historical
 * dividends to provide a more detailed evaluation.
 * </p>
 *
 * <ul>
 *   <li>{@code symbol} - The unique identifier for the company's stock.</li>
 *   <li>{@code dividendPerShare} - The dividend paid per share.</li>
 *   <li>{@code dividendYield} - The dividend yield as a percentage.</li>
 *   <li>{@code fiscalYearEnd} - The fiscal year end month.</li>
 *   <li>{@code currentYear} - The current year.</li>
 *   <li>{@code cashFlowReports} - List of historical cash flow reports.</li>
 * </ul>
 *
 * @see Evaluation
 * @see CashFlowReport
 * @see HistoricalDividend
 * @see CompanyOverview
 *
 * @author Joar Eliason
 * @author Olivia Svensson
 */
public class DividendEvaluation implements Evaluation {

  private final String symbol;
  private final double dividendPerShare;
  private final double dividendYield;
  private final String fiscalYearEnd;
  private final int currentYear;
  private final List<CashFlowReport> cashFlowReports;
  private double averageDividendYield;
  private double averageDividendGrowth;
  private String description;
  private List<Long> lastFiveYearsDividends;
  private List<HistoricalDividend> historicalDividends;

  /**
   * Constructs a new {@code DividendEvaluation} instance.
   *
   * @param symbol the unique identifier for the company's stock
   * @param dividendPerShare the dividend paid per share
   * @param dividendYield the dividend yield as a percentage
   * @param fiscalYearEnd the fiscal year-end month
   * @param cashFlowReports the list of historical cash flow reports
   */
  public DividendEvaluation(String symbol, double dividendPerShare,
      double dividendYield, String fiscalYearEnd, List<CashFlowReport> cashFlowReports) {
    this.symbol = symbol;
    this.dividendPerShare = dividendPerShare;
    this.dividendYield = dividendYield;
    this.fiscalYearEnd = fiscalYearEnd;
    this.currentYear = LocalDate.now().getYear();
    this.cashFlowReports = cashFlowReports;
    evaluate();
  }

  /**
   * Method for evaluating the dividend yield and historical dividend payouts.
   * <p>
   * This method calculates the average dividend yield and growth over the historical period.
   * </p>
   */
  @Override
  public void evaluate() {
    calculateAverageDividendYield();
    calculateAverageDividendGrowth();
    collectLastFiveYearsDividends();

    int month = getMonthNumber(fiscalYearEnd);
    int currentMonth = LocalDate.now().getMonthValue();

    if (month == currentMonth) {
      description = String.format(
          "The dividend for fiscal year %d has been paid out in %s.%n"
              + "The dividend yield for fiscal year %d was %.2f%%.%n"
              + "The dividend per share was %.2f.%n"
              + "The average dividend yield over the past years is %.2f%%.%n"
              + "The average dividend growth over the past years is %.2f%%.%n"
              + "Dividend payouts for the last %d years: %s.%n",
          currentYear, fiscalYearEnd, currentYear, dividendYield, dividendPerShare,
          averageDividendYield, averageDividendGrowth, lastFiveYearsDividends.size(),
          lastFiveYearsDividends.stream().map(String::valueOf).collect(Collectors.joining(", "))
      );
    } else {
      int monthDifference = month - currentMonth;
      String monthString = monthDifference == 1 ? "month" : "months";
      description = String.format(
          "The dividend for fiscal year %d has not been paid out yet.%n"
              + "The dividend yield for last year was %.2f%%.%n"
              + "The dividend per share was %.2f.%n"
              + "The dividend will be paid out in %d %s.%n"
              + "The average dividend yield over the past years is %.2f%%.%n"
              + "The average dividend growth over the past years is %.2f%%.%n"
              + "Dividend payouts for the last %d years: %s.",
          currentYear, dividendYield, dividendPerShare, monthDifference, monthString,
          averageDividendYield, averageDividendGrowth, lastFiveYearsDividends.size(),
          lastFiveYearsDividends.stream().map(String::valueOf).collect(Collectors.joining(", "))
      );
    }
  }

  /**
   * Calculates the average dividend yield from the historical data.
   */
  private void calculateAverageDividendYield() {
    double totalYield = 0;
    int count = 0;

    for (CashFlowReport report : cashFlowReports) {
      if (report.getDividendPayout() > 0 && report.getOperatingCashFlow() > 0) {
        double yield = (double) report.getDividendPayout() / report.getOperatingCashFlow() * 100;
        totalYield += yield;
        count++;
      }
    }

    if (count > 0) {
      averageDividendYield = totalYield / count;
    } else {
      averageDividendYield = 0;
    }
  }

  /**
   * Calculates the average dividend growth from the historical data.
   */
  private void calculateAverageDividendGrowth() {
    double totalGrowth = 0;
    int count = 0;
    Long previousDividend = null;

    for (CashFlowReport report : cashFlowReports) {
      long currentDividend = report.getDividendPayout();

      if (previousDividend != null && currentDividend > 0) {
        double growth = (double) (currentDividend - previousDividend) / previousDividend * 100;
        totalGrowth += growth;
        count++;
      }

      if (currentDividend > 0) {
        previousDividend = currentDividend;
      }
    }

    if (count > 0) {
      averageDividendGrowth = totalGrowth / count;
    } else {
      averageDividendGrowth = 0;
    }
  }

  /**
   * Collects the dividend payouts for the last five years, if available.
   */
  private void collectLastFiveYearsDividends() {
    lastFiveYearsDividends = cashFlowReports.stream()
        .filter(report -> report.getDividendPayout() > 0)
        .sorted((r1, r2) -> r2.getFiscalDateEnding().compareTo(r1.getFiscalDateEnding()))
        .limit(5)
        .map(CashFlowReport::getDividendPayout)
        .collect(Collectors.toList());
    historicalDividends = new ArrayList<>();
    for (int i = 0; i < lastFiveYearsDividends.size(); i++) {
      historicalDividends.add(new HistoricalDividend(
          cashFlowReports.get(i).getFiscalDateEnding(), lastFiveYearsDividends.get(i)));
    }
    // Sort the historical dividends by year so that the most recent year is last
    historicalDividends.sort(Comparator.comparing(HistoricalDividend::getYear));
    for (HistoricalDividend historicalDividend : historicalDividends) {
      System.out.println(historicalDividend.getYear() + " " + historicalDividend.getDividend());
    }
  }

  /**
   * Gets the stock symbol.
   *
   * @return the stock symbol
   */
  @Override
  public String getSymbol() {
    return symbol;
  }

  /**
   * Gets the title of the evaluation.
   * <p>
   * The title corresponds to the type of evaluation that is performed.
   *
   * @return the title of the evaluation
   */
  @Override
  public String getEvaluationTitle() {
    return "Dividend Evaluation";
  }

  /**
   * Gets the subtitle of the evaluation.
   * <p>
   * The subtitle is a short description of the data that the evaluation is based on.
   *
   * @return the subtitle of the evaluation
   */
  @Override
  public String getGeneralEvaluationInfo() {
    return String.format(
        "Understanding Dividend Evaluation:%n%n"
            + "The dividend evaluation analyzes the company's dividend yield and payout history. "
            + "Dividend yield is a financial ratio that shows how much a company pays out in dividends "
            + "each year relative to its stock price. This evaluation uses historical data to assess "
            + "the stability and growth of the company's dividend payouts.%n%n"
            + "Historical dividend data helps in understanding the company's long-term financial health "
            + "and commitment to returning value to shareholders. The evaluation considers both the "
            + "current year's dividend metrics and trends over multiple years."
    );
  }

  /**
   * Gets the evaluation method.
   * <p>
   * The evaluation method is a short description of how the evaluation is performed.
   *
   * @return the evaluation method
   */
  @Override
  public String getEvaluationMethodInfo() {
    return String.format(
        "Evaluation Method Explained:%n%n"
            + "The dividend evaluation is based on the company's historical dividend payouts and the "
            + "current year's dividend yield. The analysis includes calculating the average dividend "
            + "yield and growth over several years. This involves examining the dividend payout ratio "
            + "from the company's cash flow statements and assessing year-over-year changes.%n%n"
            + "The evaluation grades the company's dividend performance based on the stability and "
            + "growth of its payouts. A consistent and growing dividend is a positive indicator of the "
            + "company's financial health and its ability to generate cash flow. Investors should "
            + "consider these metrics alongside other financial indicators when making investment decisions."
    );
  }

  /**
   * Gets the detailed description of the evaluation.
   *
   * @return the detailed description of the evaluation
   */
  @Override
  public String getResultDescription() {
    return description;
  }

  public List<HistoricalDividend> getHistoricalDividends() {
    return historicalDividends;
  }

  /**
   * Gets the month number from the month name.
   *
   * @param month the month name
   * @return the month number
   */
  private int getMonthNumber(String month) {
    return switch (month) {
      case "January" -> 1;
      case "February" -> 2;
      case "March" -> 3;
      case "April" -> 4;
      case "May" -> 5;
      case "June" -> 6;
      case "July" -> 7;
      case "August" -> 8;
      case "September" -> 9;
      case "October" -> 10;
      case "November" -> 11;
      case "December" -> 12;
      default -> 0;
    };
  }

  /**
   * Class for storing historical dividend payouts.
   * <p>
   * This class stores the dividend payout for a specific year.
   * </p>
   */
  public class HistoricalDividend {

    private final String dividendYear;
    private final long dividendAmount;

    public HistoricalDividend(String year, long dividend) {
      this.dividendYear = year;
      this.dividendAmount = dividend;
    }

    public String getYear() {
      return dividendYear;
    }

    public long getDividend() {
      return dividendAmount;
    }
  }
}
