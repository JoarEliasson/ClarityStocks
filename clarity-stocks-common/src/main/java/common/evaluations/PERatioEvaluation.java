package common.evaluations;

import common.interfaces.RatingEvaluation;

/**
 * Class for evaluating the Price Earnings ratio of a company.
 * The Price Earnings ratio is calculated by dividing the current stock price by the earnings per
 * share.
 * <p>
 *   The class evaluates the Price Earnings ratio and returns a rating and a description of the
 *   evaluation.
 * <p>
 *   As of december 2023 the average Price Earnings ratio for the S&P 500 was 24.79 and will serve
 *   as a reference point for the evaluation.
 *
 * @author Joar Eliasson
 */
public class PERatioEvaluation implements RatingEvaluation {

  private static final double MARKET_AVERAGE_PE = 24.79;
  private final String symbol;
  private final double peRatio;
  private double rating;
  private String ratingDescription;
  private String description;

  public PERatioEvaluation(String symbol, double peRatio) {
    this.symbol = symbol;
    this.peRatio = peRatio;
    evaluate();
  }

  private double calculateRating() {
    return peRatio / MARKET_AVERAGE_PE;
  }

  private String generateRatingDescription() {
    if (rating < 0.25) {
      return "Very Low";
    } else if (rating < 0.5) {
      return "Low";
    } else if (rating < 0.75) {
      return "Below Average";
    } else if (rating < 1.0) {
      return "Average";
    } else if (rating < 1.25) {
      return "Above Average";
    } else if (rating < 1.5) {
      return "High";
    } else if (rating < 1.75) {
      return "Very High";
    } else if (rating < 2.0) {
      return "Extremely High";
    } else {
      return "Sky High";
    }
  }

  @Override
  public void evaluate() {
    rating = calculateRating();
    ratingDescription = generateRatingDescription();
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
    return "Price-to-Earnings Ratio";
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
    return String.format(
        "Understanding P/E Ratio:%n%n"

        + "The Price-to-Earnings (P/E) ratio is a commonly used metric in financial markets to "
        + "evaluate the valuation of a company's stock. It is calculated by dividing the current "
        + "market price of a stock by its earnings per share (EPS). The P/E ratio provides insight "
        + "into how much investors are willing to pay per dollar of earnings, indicating the "
        + "market's expectations of a company's future financial performance.%n%n"

        + "For example, a high P/E ratio may suggest that the market expects strong future growth "
        + "and earnings, while a low P/E ratio could imply that the stock is undervalued or that "
        + "the company is facing challenges. It is important for investors to compare the P/E ratio"
        + " of a stock with the average P/E ratio of the market or the industry to make informed "
        + "investment decisions."
    );
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
    return String.format(
        "Evaluation Method Explained:%n%n"
        + "The evaluation method for determining a stock's rating based on its Price-to-Earnings "
        + "(P/E) ratio involves comparing the stock's P/E ratio to the average P/E ratio of the US "
        + "stock market. This method is a standardized approach to gauge a stock's relative "
        + "valuation and potential performance. The P/E ratio of a stock is divided by the average "
        + "market P/E ratio, yielding a rating that reflects whether the stock is overvalued or "
        + "undervalued relative to the market average.%n%n"

        + "For instance, a rating of 1 indicates that the stock's P/E ratio is exactly the same as "
        + "the market average, suggesting a neutral valuation. Ratings above 1 indicate a higher "
        + "P/E ratio, implying that the stock is considered overvalued relative to the market, "
        + "potentially reflecting strong growth expectations. Conversely, ratings below 1 suggest "
        + "a lower P/E ratio, indicating the stock may be undervalued or that the market has lower "
        + "expectations for its growth.%n%n"

        + "This method provides a clear and objective measure to compare individual stocks against "
        + "a broad market benchmark, thereby facilitating informed investment decisions.%n%n"

    );
  }

  @Override
  public String getDescription() {
    return String.format(
        "P/E Ratio: %.2f%n" +
        "Market Comparison: %s%n%n" +
        "The current Price-to-Earnings (P/E) ratio for %s stands at %.2f, which corresponds to a market comparison " +
        "rating of '%s'. This indicates that the stock's valuation is %s relative to the average P/E ratio of the US " +
        "stock market, which is %.2f.%n%n" +
        "In summary, %s's P/E ratio of %.2f results in a '%s' rating, suggesting that the stock is %s relative to market " +
        "expectations. Investors should consider this rating alongside other financial metrics and the company's overall " +
        "financial health when making investment decisions.",
        peRatio, ratingDescription, symbol, peRatio, ratingDescription,
        ratingDescription.toLowerCase(), MARKET_AVERAGE_PE,
        symbol, peRatio, ratingDescription, ratingDescription.toLowerCase()
    );
  }

  @Override
  public double getRating() {
    return rating;
  }

  @Override
  public String getValue() {
    return String.valueOf(peRatio);
  }

}
