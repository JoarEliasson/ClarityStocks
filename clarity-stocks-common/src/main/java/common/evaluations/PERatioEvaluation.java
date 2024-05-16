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
  private String description;

  public PERatioEvaluation(String symbol, double peRatio) {
    this.symbol = symbol;
    this.peRatio = peRatio;
    evaluate();
  }

  @Override
  public void evaluate() {
    rating = peRatio / MARKET_AVERAGE_PE;
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
        + "a broad market benchmark, thereby facilitating informed investment decisions.%n%n" +

        "Rating Interpretation:%n" +
        "1. **Very Low (Rating < 0.25)**: "
        + "Significantly undervalued, potential buy if fundamentals are strong.%n" +
        "2. **Low (Rating 0.25 - 0.5)**: "
        + "Lower valuation, further investigation needed.%n" +
        "3. **Below Average (Rating 0.5 - 0.75)**: "
        + "Valued below market, could be a bargain or facing challenges.%n" +
        "4. **Average (Rating 0.75 - 1.0)**: "
        + "Valuation aligns with market average, neutral outlook.%n" +
        "5. **Above Average (Rating 1.0 - 1.25)**: "
        + "Slightly overvalued, moderate growth expected.%n" +
        "6. **High (Rating 1.25 - 1.5)**: "
        + "Higher valuation expectations, strong growth potential.%n" +
        "7. **Very High (Rating 1.5 - 1.75)**: "
        + "Considerably overvalued, high growth expectations.%n" +
        "8. **Extremely High (Rating 1.75 - 2.0)**: "
        + "Very high market expectations, ensure growth justifies valuation.%n" +
        "9. **Sky High (Rating > 2.0)**: "
        + "Highly overvalued, strong growth or speculative trading."
    );
  }

  @Override
  public String getDescription() {
    return description;
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
