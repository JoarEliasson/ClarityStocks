package analysis.graph;

import analysis.interfaces.TechnicalAnalysis;
import java.util.List;

/**
 * Class for evaluating the Golden Cross in stock trading.
 * <p>
 * The evaluation is based on the moving averages of 50 and 200 days.
 * A Golden Cross is when the 50-day moving average crosses above the 200-day moving average.
 * </p>
 * <p>
 * This class also includes potential Golden Cross events that have occurred on the graph.
 * </p>
 *
 * <ul>
 *   <li>{@code symbol} - The unique identifier for the company's stock.</li>
 *   <li>{@code movingAverage50} - The 50-day moving average of the company's stock.</li>
 *   <li>{@code movingAverage200} - The 200-day moving average of the company's stock.</li>
 *   <li>{@code percentageDifference} - The percentage difference between the two moving averages.</li>
 *   <li>{@code description} - A textual description of the evaluation.</li>
 *   <li>{@code goldenCrossEvents} - List of dates where Golden Cross events occurred.</li>
 * </ul>
 *
 * @see common.interfaces.RatingEvaluation
 * @see java.lang.String
 * @see java.util.List
 *
 * @author Olivia Svensson, Joar Eliason
 *
 */
public class GoldenCrossAnalysis implements TechnicalAnalysis {

  private final String symbol;
  private final double movingAverage50;
  private final double movingAverage200;
  private final List<String> goldenCrossEvents;
  private double percentageDifference;
  private String description;

  /**
   * Constructs a new {@code GoldenCrossAnalysis} instance.
   *
   * @param symbol the unique identifier for the company's stock
   * @param movingAverage50 the 50-day moving average of the company's stock
   * @param movingAverage200 the 200-day moving average of the company's stock
   * @param goldenCrossEvents list of dates where Golden Cross events occurred
   */
  public GoldenCrossAnalysis(String symbol, double movingAverage50, double movingAverage200,
      List<String> goldenCrossEvents) {
    this.symbol = symbol;
    this.movingAverage50 = movingAverage50;
    this.movingAverage200 = movingAverage200;
    this.goldenCrossEvents = goldenCrossEvents;
    analyze();
  }

  /**
   * Calculates the percentage difference between the 50-day and 200-day moving averages.
   */
  private void calculatePercentageDifference() {
    percentageDifference = (1 - (movingAverage50 / movingAverage200)) * 100;
    percentageDifference = Math.round(percentageDifference * 100.0) / 100.0;
  }

  /**
   * Analyzes the percentage difference and sets the description based on the evaluation.
   */
  @Override
  public void analyze() {
    calculatePercentageDifference();
    if (percentageDifference > 0) {
      description = String.format(
          "Stock traded %.2f%% higher than usual.%n"
              + "Consider being cautious if the high trading price has no reflection in "
              + "business indicators.",
          percentageDifference
      );
    } else if (percentageDifference < 0) {
      description = String.format(
          "Stock traded %.2f%% lower than usual.%n"
              + "Consider buying if business indicators are favorable.",
          Math.abs(percentageDifference)
      );
    } else {
      description = "Something went wrong with the evaluation of the percentage difference";
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
   * Gets the title of the analysis.
   *
   * <p>The title corresponds to the type of evaluation that is performed.
   *
   * @return the title of the analysis
   */
  @Override
  public String getAnalysisTitle() {
    return "Golden Cross";
  }

  /**
   * Gets the subtitle of the analysis.
   *
   * <p>The subtitle is a short description of the data that the analysis is based on.
   *
   * @return the subtitle of the analysis
   */
  @Override
  public String getGeneralAnalysisInfo() {
    return String.format(
        "Understanding Golden Cross:%n%n"
            + "The Golden Cross evaluates the current stock price based on historical trading patterns. "
            + "A Golden Cross is a chart pattern where a short-term moving average (like the 50-day MA) "
            + "crosses above a long-term moving average (like the 200-day MA). This is a bullish signal, "
            + "indicating that the stock's short-term momentum is strengthening relative to its longer-term trend.%n%n"
            + "The Golden Cross generally has three stages: %n"
            + "1. A downward trend bottoms out as buyers overpower sellers.%n"
            + "2. The shorter moving average crosses over the longer moving average, triggering a breakout and confirming a trend reversal.%n"
            + "3. An uptrend continues after the crossover.%n%n"
            + "The most commonly used moving averages are the MA50 and MA200. While the Golden Cross is a strong indicator, "
            + "it is important to consider other factors such as market conditions, fundamental analysis, and potential risks."
    );
  }

  /**
   * Gets the analysis method.
   *
   * <p>The evaluation method is a short description of how the analysis is performed.
   *
   * @return the analysis method
   */
  @Override
  public String getAnalysisMethodInfo() {
    return String.format(
        "Evaluation Method Explained:%n%n"
            + "The evaluation is based on the 50-day (MA50) and 200-day (MA200) moving averages. The percentage difference "
            + "between these averages is calculated by dividing the MA50 by the MA200 and converting it to a percentage. "
            + "A positive percentage indicates an uptrend (bull market), while a negative percentage indicates a downtrend (bear market).%n%n"
            + "This method helps identify potential Golden Cross events, which are significant bullish signals. Additionally, "
            + "the concept of a Death Cross, where the MA50 crosses below the MA200, indicating a potential bear market, is also explained."
    );
  }

  /**
   * Gets the detailed description of the analysis.
   *
   * @return the detailed description of the analysis
   */
  @Override
  public String getResultDescription() {
    StringBuilder detailedDescription = new StringBuilder(String.format(
        "MA50: %.2f%nMA200: %.2f%nPercentage Difference: %.2f%%%n%n",
        movingAverage50, movingAverage200, percentageDifference
    ));
    if (!goldenCrossEvents.isEmpty()) {
      detailedDescription.append("Golden Cross Events:%n");
      for (String event : goldenCrossEvents) {
        String eventString = String.format(" %s,", event);
        detailedDescription.append(eventString);
      }
    }
    detailedDescription.append(String.format(
        "%nCurrent Analysis:%nThe current percentage difference between the 50-day and 200-day moving averages for %s is %.2f%%. %s",
        symbol, percentageDifference, description
    ));
    return detailedDescription.toString();
  }

  public String getValue() {
    return String.format("%.2f%%", percentageDifference);
  }
}
