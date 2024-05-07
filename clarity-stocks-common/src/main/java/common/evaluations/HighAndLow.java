package common.evaluations;

import common.interfaces.Evaluation;

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

  /**
   * Method for getting the title of the evaluation.
   * <p>
   * The title corresponds to the type of evaluation that is performed.
   *
   * @return the title of the evaluation.
   */
  @Override
  public String getTitle() {
    return "";
  }

  /**
   * Method for getting the subtitle of the evaluation.
   * <p>
   * The subtitle is a short description of the data that the evaluation is based on.
   *
   * @return the subtitle of the evaluation.
   */
  @Override
  public String getSubtitle() {
    return "";
  }

  /**
   * Method for getting the evaluation method.
   * <p>
   * The evaluation method is a short description of how the evaluation is performed.
   *
   * @return the evaluation method.
   */
  @Override
  public String getEvaluationMethod() {
    return "";
  }

  @Override
  public String getDescription() {
    return description;
  }
}
