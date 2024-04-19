package analysis.model.evaluations;

import analysis.model.interfaces.Evaluation;

/**
 * Class for evaluating the 52-week high and low of a stock.
 *
 * @author Olivia Svensson
 */

public class HighAndLow implements Evaluation {

  private final String symbol;
  private final double high;
  private final double low;
  private String description;

  public HighAndLow(String symbol, double high, double low) {
    this.symbol = symbol;
    this.high = high;
    this.low = low;
  }

  @Override
  public void evaluate() {
    description = String.format(
        "The 52-week high for %s is %.2f.%n"
        + "The 52-week low for %s is %.2f",
        symbol, high, symbol, low
    );
  }

  @Override
  public String getSymbol() {
    return symbol;
  }

  @Override
  public String getDescription() {
    return description;
  }
}
