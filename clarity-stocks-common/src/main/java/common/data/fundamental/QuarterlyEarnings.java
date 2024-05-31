package common.data.fundamental;

/**
 * Class for storing quarterly earnings data.
 * <p>
 * This class holds data for quarterly earnings, including the fiscal date ending, reported date,
 * reported earnings per share (EPS), estimated EPS, surprise, and surprise percentage.
 * </p>
 *
 * <ul>
 *   <li>{@code symbol} - The stock symbol.</li>
 *   <li>{@code fiscalDateEnding} - The fiscal date ending for the earnings data.</li>
 *   <li>{@code reportedDate} - The date the earnings were reported.</li>
 *   <li>{@code reportedEPS} - The reported earnings per share (EPS).</li>
 *   <li>{@code estimatedEPS} - The estimated earnings per share (EPS).</li>
 *   <li>{@code surprise} - The surprise in earnings.</li>
 *   <li>{@code surprisePercentage} - The surprise in earnings as a percentage.</li>
 * </ul>
 *
 * @see EarningsData
 * @see AnnualEarnings
 * @see QuarterlyEarnings
 *
 * @author Joar Eliason
 */
public class QuarterlyEarnings {

  private final String symbol;
  private String fiscalDateEnding;
  private String reportedDate;
  private double reportedEPS;
  private double estimatedEPS;
  private double surprise;
  private double surprisePercentage;

  public QuarterlyEarnings(String symbol) {
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

  public String getReportedDate() {
    return reportedDate;
  }

  public void setReportedDate(String reportedDate) {
    this.reportedDate = reportedDate;
  }

  public double getReportedEPS() {
    return reportedEPS;
  }

  public void setReportedEPS(double reportedEPS) {
    this.reportedEPS = reportedEPS;
  }

  public double getEstimatedEPS() {
    return estimatedEPS;
  }

  public void setEstimatedEPS(double estimatedEPS) {
    this.estimatedEPS = estimatedEPS;
  }

  public double getSurprise() {
    return surprise;
  }

  public void setSurprise(double surprise) {
    this.surprise = surprise;
  }

  public double getSurprisePercentage() {
    return surprisePercentage;
  }

  public void setSurprisePercentage(double surprisePercentage) {
    this.surprisePercentage = surprisePercentage;
  }

  @Override
  public String toString() {
    return String.format(
        "Symbol: %s%n"
        + "Fiscal Date Ending: %s%n"
        + "Reported Date: %s%n"
        + "Reported EPS: %.2f%n"
        + "Estimated EPS: %.2f%n"
        + "Surprise: %.2f%n"
        + "Surprise Percentage: %.2f%n",
        symbol, fiscalDateEnding, reportedDate, reportedEPS, estimatedEPS, surprise, surprisePercentage
    );
  }
}
