package common.data.fundamental;

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
