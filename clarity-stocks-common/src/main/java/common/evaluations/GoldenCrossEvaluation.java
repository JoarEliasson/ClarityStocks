package common.evaluations;

import common.interfaces.Evaluation;

/**
 * Class for comparing the stock price currently as to how it has been traded historically.
 * <p>
 *   The evaluation is based on the moving averages of 50 and 200 days.
 *   A golden cross is when the moving average of 50 days is higher than the moving average of 200
 *   days.
 * </p>
 *
 * @author Olivia Svensson
 */
public class GoldenCrossEvaluation implements Evaluation {

  private final String symbol;
  private final double movingAverage50;
  private final double movingAverage200;
  private double percentageDifference;
  private String description;

  public GoldenCrossEvaluation(String symbol, double movingAverage50, double movingAverage200) {
    this.symbol = symbol;
    this.movingAverage50 = movingAverage50;
    this.movingAverage200 = movingAverage200;
    evaluate();
  }

  /**
   * Method for calculating the percentage difference between ma50 and ma200.
   *
   * @author Olivia Svensson
   */
  private void calculatePercentageDifference() {
    percentageDifference = (1 - (movingAverage50 / movingAverage200)) * 100;
    percentageDifference = Math.round(percentageDifference);
  }

  /**
   * Method for evaluating the percentage difference. Returns a string reflecting on the
   * considerations to be made when trading the stock.
   *
   * @author Olivia Svensson
   */
  @Override
  public void evaluate() {
    calculatePercentageDifference();
    if (percentageDifference > 0) {
      description = String.format(
      "Stock traded %.2f higher than usual.%n"
      + "Consider being a bit cautious if the high trading price has no reflection in "
      + "business indicators.",
      percentageDifference
      );
    } else if (percentageDifference < 0) {
      description = String.format(
      "Stock traded %.2f  lower than usual. %n"
      + "Consider buying if business indicators are favorable.",
      Math.abs(percentageDifference)
      );
    } else {
      description = "Something went wrong with the evaluation of the percentage difference";
    }
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
