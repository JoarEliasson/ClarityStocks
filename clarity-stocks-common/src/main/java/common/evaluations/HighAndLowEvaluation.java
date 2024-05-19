package common.evaluations;

import common.interfaces.RatingEvaluation;

/**
 * Class for evaluating the 52-week high and low of a stock.
 *
 * <p>The evaluation is based on the highest and lowest prices at which a stock has been traded
 * over the previous 52 weeks. This information can be used to assess the stock's volatility
 * and potential investment opportunities.
 *
 * <ul>
 *   <li>{@code symbol} - The unique identifier for the company's stock.</li>
 *   <li>{@code high52Week} - The highest price at which the stock has been traded over the
 *       previous 52 weeks.</li>
 *   <li>{@code low52Week} - The lowest price at which the stock has been traded over the
 *       previous 52 weeks.</li>
 *   <li>{@code currentPrice} - The current price of the stock.</li>
 *   <li>{@code rating} - The calculated rating based on the current price's position relative
 *       to the 52-week high and low.</li>
 *   <li>{@code ratingDescription} - A textual description of the rating.</li>
 *   <li>{@code description} - A detailed description of the evaluation.</li>
 * </ul>
 *
 * @see common.interfaces.RatingEvaluation
 * @see java.lang.String
 * @see java.lang.Math
 *
 * @author Olivia Svensson, Joar Eliasson
 */
public class HighAndLowEvaluation implements RatingEvaluation {

  private final String symbol;
  private final double high52Week;
  private final double low52Week;
  private final double currentPrice;
  private double rating;
  private String ratingDescription;
  private String description;

  /**
   * Constructs a new {@code HighAndLowEvaluation} instance.
   *
   * @param symbol the unique identifier for the company's stock
   * @param high52Week the highest price at which the stock has been traded over the previous
   *        52 weeks
   * @param low52Week the lowest price at which the stock has been traded over the previous
   *        52 weeks
   * @param currentPrice the current price of the stock
   */
  public HighAndLowEvaluation(String symbol, double high52Week, double low52Week, double currentPrice) {
    this.symbol = symbol;
    this.high52Week = high52Week;
    this.low52Week = low52Week;
    this.currentPrice = currentPrice;
    evaluate();
  }

  /**
   * Generates a rating description based on the current price's position relative to the
   * 52-week high and low prices.
   *
   * @return the rating description
   */
  @Override
  public String getRatingDescription() {
    double range = high52Week - low52Week;
    double position = (currentPrice - low52Week) / range;

    if (position < 0.2) {
      return "Near 52-Week Low";
    } else if (position < 0.4) {
      return "Below Average";
    } else if (position < 0.6) {
      return "Average";
    } else if (position < 0.8) {
      return "Above Average";
    } else {
      return "Near 52-Week High";
    }
  }

  /**
   * Evaluates the stock's current price relative to its 52-week high and low prices.
   */
  @Override
  public void evaluate() {
    double range = high52Week - low52Week;
    rating = (currentPrice - low52Week) / range;
    ratingDescription = getRatingDescription();
    description = String.format(
        "The 52-week high for %s is %.2f.%n"
            + "The 52-week low for %s is %.2f.%n"
            + "The current price for %s is %.2f.%n"
            + "This places the current price %s within the 52-week range.",
        symbol, high52Week, symbol, low52Week, symbol, currentPrice, ratingDescription.toLowerCase()
    );
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
   *
   * <p>The title corresponds to the type of evaluation that is performed.
   *
   * @return the title of the evaluation
   */
  @Override
  public String getEvaluationTitle() {
    return "52-Week High and Low";
  }

  /**
   * Gets the subtitle of the evaluation.
   *
   * <p>The subtitle is a short description of the data that the evaluation is based on.
   *
   * @return the subtitle of the evaluation
   */
  @Override
  public String getGeneralEvaluationInfo() {
    return String.format(
        "Understanding 52-Week High and Low:%n%n"
            + "The 52-week high is the highest price at which a stock has been traded over the previous "
            + "52 weeks, and the 52-week low is the lowest price at which a stock has been traded over "
            + "the previous 52 weeks. This information is important to some investors as part of their "
            + "investment strategy. Comparing these values with the current price of the stock helps "
            + "determine if the price is considered high or low. A low price might be seen as a buying "
            + "opportunity, while a high price might indicate a time to sell.%n%n"
            + "It is important to consider that many factors can affect a company's stock price over 52 weeks, "
            + "and historical prices do not guarantee future performance. Investors should use these metrics "
            + "in conjunction with other financial analyses."
    );
  }

  /**
   * Gets the evaluation method.
   *
   * <p>The evaluation method is a short description of how the evaluation is performed.
   *
   * @return the evaluation method
   */
  @Override
  public String getEvaluationMethodInfo() {
    return String.format(
        "Evaluation Method Explained:%n%n"
            + "This evaluation uses the 52-week high and low prices to assess the stock's current price. "
            + "The 52-week high is the highest price the stock has traded at in the past 52 weeks, and the "
            + "52-week low is the lowest price during the same period. By comparing the current price to these "
            + "extremes, investors can gauge whether the stock is trading near its peak or trough.%n%n"
            + "The rating is determined by the current price's position within the 52-week range. If the current "
            + "price is near the 52-week low, it may indicate a potential buying opportunity. Conversely, if the "
            + "current price is near the 52-week high, it may indicate a potential selling opportunity. However, "
            + "it is crucial to consider other factors and not rely solely on these metrics for investment decisions."
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

  /**
   * Gets the calculated rating of the stock's current price relative to its 52-week high and low.
   *
   * @return the calculated rating
   */
  @Override
  public double getRating() {
    return rating;
  }

  /**
   * Gets the 52-week high and low prices and the current price of the stock.
   *
   * @return the 52-week high and low prices and the current price
   */
  @Override
  public String getValue() {
    return String.format(
        "52-Week High: %.2f, 52-Week Low: %.2f, Current Price: %.2f",
        high52Week, low52Week, currentPrice
    );
  }
}
