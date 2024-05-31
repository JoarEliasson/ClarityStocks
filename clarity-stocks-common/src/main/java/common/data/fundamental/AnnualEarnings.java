package common.data.fundamental;

/**
 * Class for storing annual earnings data.
 * <p>
 * This class is used to store annual earnings data for a company.
 * The class includes the symbol of the company, the fiscal date ending, and the reported earnings
 * per share.
 * </p>
 *
 * <ul>
 *   <li>{@code symbol} - The unique identifier for the company's stock.</li>
 *   <li>{@code fiscalDateEnding} - The fiscal date ending for the earnings data.</li>
 *   <li>{@code reportedEPS} - The reported earnings per share.</li>
 * </ul>
 *
 * @see EarningsData
 * @see QuarterlyEarnings
 *
 * @author Joar Eliason
 */
public class AnnualEarnings {

  private final String symbol;
  private String fiscalDateEnding;
  private double reportedEPS;

  public AnnualEarnings(String symbol) {
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

  public double getReportedEPS() {
    return reportedEPS;
  }

  public void setReportedEPS(double reportedEPS) {
    this.reportedEPS = reportedEPS;
  }

  @Override
  public String toString() {
    return String.format(
        "Annual Earnings for %s on %s: %.2f",
        symbol, fiscalDateEnding, reportedEPS
    );
  }
}
