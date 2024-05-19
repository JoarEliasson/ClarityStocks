package common.evaluations;

import common.interfaces.RatingEvaluation;

/**
 * Class for evaluating the size of the company.
 * <p>
 * Company size evaluations are based on the revenue for the past twelve months and their market
 * capitalization.
 * </p>
 *
 * <ul>
 *   <li>{@code symbol} - The unique identifier for the company's stock.</li>
 *   <li>{@code revenueTTM} - The total revenue of the company for the past twelve months.</li>
 *   <li>{@code marketCap} - The market capitalization of the company.</li>
 *   <li>{@code rating} - The calculated rating based on the company's size.</li>
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
public class CompanySizeEvaluation implements RatingEvaluation {

  private final String symbol;
  private final long revenueTTM;
  private final long marketCap;
  private double rating;
  private String ratingDescription;

  /**
   * Constructs a new {@code CompanySizeEvaluation} instance.
   *
   * @param symbol the unique identifier for the company's stock
   * @param revenueTTM the total revenue of the company for the past twelve months
   * @param marketCap the market capitalization of the company
   */
  public CompanySizeEvaluation(String symbol, long revenueTTM, long marketCap) {
    this.symbol = symbol;
    this.revenueTTM = revenueTTM;
    this.marketCap = marketCap;
    evaluate();
  }

  /**
   * Generates a rating description based on predefined ranges.
   *
   * @return the rating description
   */
  @Override
  public String getRatingDescription() {
    if (marketCap < 50000000L) {
      return "Nano-Cap (Very Small Company)";
    } else if (marketCap < 250000000L) {
      return "Micro-Cap (Small Company)";
    } else if (marketCap < 2000000000L) {
      return "Mid-Cap (Medium-Sized Company)";
    } else if (marketCap < 10000000000L) {
      return "Big-Cap (Large Company)";
    } else if (marketCap < 200000000000L) {
      return "Mega-Cap (Very Large Company)";
    } else {
      return "Ultra-Cap (Extremely Large Company)";
    }
  }

  /**
   * Evaluates the company's size by calculating the rating and generating its description.
   */
  @Override
  public void evaluate() {
    if (marketCap < 50000000L) {
      rating = 0.0;
    } else if (marketCap < 250000000L) {
      rating = 1.0;
    } else if (marketCap < 2000000000L) {
      rating = 2.0;
    } else if (marketCap < 10000000000L) {
      rating = 3.0;
    } else if (marketCap < 200000000000L) {
      rating = 4.0;
    } else {
      rating = 5.0;
    }
    ratingDescription = getRatingDescription();
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
    return "Company Size";
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
        "Understanding Company Size:%n%n"
            + "The company size evaluation is based on the revenue of the company for the past twelve "
            + "months (TTM - trailing twelve months) and their market capitalization. Revenue is the "
            + "total amount of money generated from a business’s primary operations, also known as "
            + "gross sales. Revenue is calculated by multiplying a company’s average sales price by the "
            + "number of units sold. It differs from the company’s income as it does not include expenses. "
            + "Revenue provides insight into how effective the company is at generating sales.%n%n"
            + "Market capitalization is the total market value of a company’s outstanding shares of stock. "
            + "It is calculated by multiplying the company’s share price by its total number of outstanding shares."
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
            + "The evaluation of the company’s size is done by comparing the revenue of the past twelve months "
            + "with different thresholds for market capitalization categories. There are six categories: %n"
            + "Nano-Cap: Below $50 million%n"
            + "Micro-Cap: $50 million to $250 million%n"
            + "Mid-Cap: $250 million to $2 billion%n"
            + "Big-Cap: $2 billion to $10 billion%n"
            + "Mega-Cap: $10 billion to $200 billion%n"
            + "Ultra-Cap: Above $200 billion%n%n"
            + "These categories help investors understand the company's size and relative market position. "
            + "Company size can affect the level of risk and growth potential. Smaller companies may have higher "
            + "growth potential but also higher risk, while larger companies often provide more stability but less "
            + "growth potential."
    );
  }

  /**
   * Gets the detailed description of the evaluation.
   *
   * @return the detailed description of the evaluation
   */
  @Override
  public String getResultDescription() {
    return String.format(
        "Revenue (TTM): $%,d%n"
            + "Market Capitalization: $%,d%n%n"
            + "The company (%s) is considered a %s company, judging by the revenue for the past twelve "
            + "months and its market capitalization. This indicates that the company is %s relative to industry standards.%n%n"
            + "In summary, %s's revenue of $%,d and market capitalization of $%,d result in a '%s' rating, suggesting that the "
            + "company is %s relative to market expectations. Investors should consider this rating alongside other financial "
            + "metrics and the company's overall financial health when making investment decisions.",
        revenueTTM, marketCap, symbol, ratingDescription, ratingDescription.toLowerCase(),
        symbol, revenueTTM, marketCap, ratingDescription, ratingDescription.toLowerCase()
    );
  }

  /**
   * Gets the calculated rating of the company's size.
   *
   * @return the calculated rating
   */
  @Override
  public double getRating() {
    return rating;
  }

  /**
   * Gets the revenue and market capitalization values of the company's stock.
   *
   * @return the revenue and market capitalization values
   */
  @Override
  public String getValue() {
    return String.format("Revenue (TTM): $%,d, Market Cap: $%,d", revenueTTM, marketCap);
  }
}
