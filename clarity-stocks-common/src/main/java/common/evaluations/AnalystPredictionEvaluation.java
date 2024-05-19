package common.evaluations;

import common.interfaces.RatingEvaluation;

/**
 * Class for evaluating the analyst prediction of a stock.
 *
 * <p>
 * The evaluation is based on the comparison of the current price and the target price provided by
 * analysts, as well as the analysts' ratings.
 * <p>
 * This class evaluates the analysts' predictions and returns a rating and a description of the
 * evaluation.
 *
 * <ul>
 *   <li>{@code symbol} - The unique identifier for the company's stock.</li>
 *   <li>{@code currentPrice} - The current market price of the stock.</li>
 *   <li>{@code targetPrice} - The target price estimated by analysts.</li>
 *   <li>{@code ratingStrongBuy} - The number of analysts rating the stock as "strong buy".</li>
 *   <li>{@code ratingBuy} - The number of analysts rating the stock as "buy".</li>
 *   <li>{@code ratingHold} - The number of analysts rating the stock as "hold".</li>
 *   <li>{@code ratingSell} - The number of analysts rating the stock as "sell".</li>
 *   <li>{@code ratingStrongSell} - The number of analysts rating the stock as "strong sell".</li>
 *   <li>{@code rating} - The calculated rating based on the analysts' predictions.</li>
 *   <li>{@code ratingDescription} - A textual description of the rating.</li>
 * </ul>
 *
 * @see common.interfaces.RatingEvaluation
 * @see java.lang.String
 * @see java.lang.Math
 *
 * @author Olivia Svensson, Joar Eliasson
 */
public class AnalystPredictionEvaluation implements RatingEvaluation {

  private final String symbol;
  private final double currentPrice;
  private final double targetPrice;
  private final int ratingStrongBuy;
  private final int ratingBuy;
  private final int ratingHold;
  private final int ratingSell;
  private final int ratingStrongSell;
  private double percentage;
  private double rating;
  private String ratingDescription;
  private String description;

  /**
   * Constructs a new {@code AnalystPredictionEvaluation} instance.
   *
   * @param symbol the unique identifier for the company's stock
   * @param currentPrice the current market price of the stock
   * @param targetPrice the target price estimated by analysts
   * @param ratingStrongBuy the number of analysts rating the stock as "strong buy"
   * @param ratingBuy the number of analysts rating the stock as "buy"
   * @param ratingHold the number of analysts rating the stock as "hold"
   * @param ratingSell the number of analysts rating the stock as "sell"
   * @param ratingStrongSell the number of analysts rating the stock as "strong sell"
   */
  public AnalystPredictionEvaluation(String symbol, double currentPrice, double targetPrice,
      int ratingStrongBuy, int ratingBuy, int ratingHold, int ratingSell, int ratingStrongSell) {
    this.symbol = symbol;
    this.currentPrice = currentPrice;
    this.targetPrice = targetPrice;
    this.ratingStrongBuy = ratingStrongBuy;
    this.ratingBuy = ratingBuy;
    this.ratingHold = ratingHold;
    this.ratingSell = ratingSell;
    this.ratingStrongSell = ratingStrongSell;
    evaluate();
  }

  /**
   * Calculates the percentage difference between the current price and the target price.
   *
   * @param targetPrice the target price estimated by analysts
   * @param currentPrice the current market price of the stock
   */
  private void calculatePercentage(double targetPrice, double currentPrice) {
    percentage = (currentPrice / targetPrice) * 100;
  }

  /**
   * Generates a rating description based on the percentage difference.
   *
   * @return the rating description
   */
  @Override
  public String getRatingDescription() {
    if (percentage < 75.0) {
      return "Significantly Undervalued";
    } else if (percentage < 90.0) {
      return "Undervalued";
    } else if (percentage < 95.0) {
      return "Moderately Undervalued";
    } else if (percentage < 97.5) {
      return "Slightly Undervalued";
    } else if (percentage < 100.0) {
      return "Almost Fairly Priced";
    } else if (percentage < 102.5) {
      return "Fairly Priced";
    } else if (percentage < 105.0) {
      return "Slightly Overvalued";
    } else if (percentage < 110.0) {
      return "Moderately Overvalued";
    } else if (percentage < 115.0) {
      return "Overvalued";
    } else if (percentage < 120.0) {
      return "Highly Overvalued";
    } else {
      return "Extremely Overvalued";
    }
  }

  /**
   * Evaluates the analyst predictions by calculating the rating and generating its description.
   */
  @Override
  public void evaluate() {
    calculatePercentage(targetPrice, currentPrice);
    rating = percentage / 100;
    ratingDescription = getRatingDescription();
    description = String.format(
        "The current price of %s is %.2f, which is %.2f%% of the target price of %.2f.%n"
            + "Analyst Ratings:%n"
            + "Strong Buy: %d%n"
            + "Buy: %d%n"
            + "Hold: %d%n"
            + "Sell: %d%n"
            + "Strong Sell: %d%n"
            + "This places the stock in the '%s' category based on the analysts' predictions.",
        symbol, currentPrice, percentage, targetPrice, ratingStrongBuy, ratingBuy, ratingHold,
        ratingSell, ratingStrongSell, ratingDescription
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
    return "Analyst Prediction";
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
        "Understanding Analyst Predictions:%n%n"
            + "The analyst prediction evaluation is based on analysts’ ratings of the stock. Analysts conduct thorough "
            + "analyses of the company, industry, and macroeconomic environment to estimate the fair value of the stock. "
            + "They use techniques such as discounted cash flow analysis, comparable company analysis, and precedent "
            + "transactions analysis. Analysts also assess risks specific to the company and external risks to determine "
            + "whether the stock is a 'strong buy', 'buy', 'hold', 'sell', or 'strong sell'.%n%n"
            + "These ratings are independent and meant to complement other financial metrics, not to be used in isolation. "
            + "The ratings provide a target price, which is an estimation of the stock's future value."
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
            + "The evaluation of the analyst prediction is based on comparing the target price with the current price of the "
            + "stock. This is done by dividing the current price by the target price and multiplying by 100 to get a percentage. "
            + "If the current price is less than 100%% of the target price, the stock is considered undervalued. If it is more "
            + "than 100%%, the stock is considered overvalued.%n%n"
            + "The ratings provided by analysts are on a scale of 'strong buy', 'buy', 'hold', 'sell', and 'strong sell'. "
            + "The evaluation also takes into account the number of analysts with each rating to provide a comprehensive analysis. "
            + "The current price is evaluated in the context of these ratings to determine if the stock is undervalued or overvalued."
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
   * Gets the calculated rating of the analyst prediction.
   *
   * @return the calculated rating
   */
  @Override
  public double getRating() {
    return rating;
  }

  /**
   * Gets the current and target prices of the company's stock.
   *
   * @return the current and target prices
   */
  @Override
  public String getValue() {
    return String.format("Current Price: %.2f, Target Price: %.2f", currentPrice, targetPrice);
  }
}