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
    return "Golden Cross";
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
    return "The golden cross evaluates the current stock price as to how the stock has been traded"
        + " historically. A golden cross is a chart pattern in which a relatively short-term moving"
        + " average crosses above a long-term moving average. The golden cross is a bullish"
        + " breakout pattern formed from a crossover involving a security’s short-term moving"
        + " average (such as the MA50 (50-day moving average)) crossing the long-term moving"
        + " average (such as the MA200 (200-day moving average)). As long-term indicators are more"
        + " trustworthy, the golden cross indicates the possibility of a long-term bull market"
        + " emerging. High trading volumes generally reinforce the indicator. The most commonly"
        + " used moving averages are the MA50 and the MA200. The golden cross is a momentum"
        + " indicator, meaning that the prices are continuously increasing/gaining momentum."
        + " Traders have changed their outlooks to bullish rather than bearish. The indicator"
        + " generally has three stages: 1 - A downward trend eventually bottoms up as buyers"
        + " overpower sellers. 2 - The shorter moving average crosses over the larger moving"
        + " average to trigger a breakout and confirms a downward trend reversal. 3 – Continuing"
        + " uptrend after the crossover. Day traders commonly use smaller periods like 5-day"
        + " and 15-day moving averages to trade intra-day. Different time periods can be used"
        + " depending on preferences. The opposite of the golden cross is the death cross,"
        + " which indicates a possible long-term bear market is approaching, where the"
        + " short-term moving average crosses above the long-term moving average.";
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
    return "\n"
        + "The evaluation is based on the two moving averages MA50 and MA200"
        + " (50-day moving average and 200-day moving average)."
        + " The evaluation of these is calculated by dividing the MA50 with the MA200"
        + " (the short term with the long-term moving average) in order to get the percentage"
        + " difference between these two. If the percentage difference is more than 100%,"
        + " the evaluation returns the percentage units difference in how much the stock has"
        + " traded higher than usual. If the difference is les than 100%, the evaluation returns"
        + " the percentage units difference in how much the stock has traded lower than usual."
        + " If the short-term moving average is higher than 100%, there is a current uptrend."
        + " If the short-term moving average is lower than 100%, there is a current downtrend. \n";
  }

  @Override
  public String getDescription() {
    return description;
  }
}
