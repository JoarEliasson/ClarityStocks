package common.evaluations.rating;

import common.data.fundamental.CompanyOverview;
import common.enums.Sectors;
import common.interfaces.RatingEvaluation;

/**
 * Class for evaluating the stock price in relation to how the company is performing.
 * <p>
 * This evaluation assesses the annual earnings per share related to the share price using the
 * price-to-earnings (P/E) ratio. The P/E ratio is compared to the average P/E ratio of the
 * company's sector to determine the relative valuation.
 * </p>
 *
 * <ul>
 *   <li>{@code symbol} - The unique identifier for the company's stock.</li>
 *   <li>{@code peRatio} - The price-to-earnings ratio of the company's stock.</li>
 *   <li>{@code sector} - The sector to which the company belongs.</li>
 *   <li>{@code rating} - The calculated rating based on the company's P/E ratio relative to its sector.</li>
 *   <li>{@code ratingDescription} - A textual description of the rating.</li>
 *   <li>{@code description} - A detailed description of the evaluation.</li>
 * </ul>
 *
 * @see RatingEvaluation
 * @see PERatioEvaluation
 * @see Sectors
 * @see CompanyOverview
 *
 * @author Joar Eliason
 * @author Olivia Svensson
 */
public class PriceToPerformanceEvaluation implements RatingEvaluation {

  private final PERatioEvaluation peEvaluation;
  private final String symbol;
  private final double peRatio;
  private final String sector;
  private double rating;
  private String ratingDescription;
  private String description;

  /**
   * Constructs a new {@code PriceToPerformance} instance.
   *
   * @param peRatioEvaluation the P/E ratio evaluation of the company
   * @param sector the sector to which the company belongs
   */
  public PriceToPerformanceEvaluation(PERatioEvaluation peRatioEvaluation, String sector) {
    this.peEvaluation = peRatioEvaluation;
    this.symbol = peEvaluation.getSymbol();
    this.peRatio = Double.parseDouble(peEvaluation.getValue());
    this.sector = sector.toLowerCase();

    evaluate();
  }

  /**
   * Generates a rating description based on predefined ranges.
   *
   * @return the rating description
   */
  @Override
  public String getRatingDescription() {
    return peEvaluation.generateRatingDescription(rating);
  }

  public double getGaugeValue() {
    if (rating < 0.25) {
      return 2.5;
    } else if (rating < 0.5) {
      return 2.25;
    } else if (rating < 0.75) {
      return 2.0;
    } else if (rating < 0.9) {
      return 1.75;
    } else if (rating <= 1.1) {
      return 1.5;
    } else if (rating < 1.25) {
      return 1.25;
    } else if (rating < 1.5) {
      return 1.0;
    } else if (rating < 1.75) {
      return 0.75;
    } else if (rating < 2.0) {
      return 0.5;
    } else {
      return 0.25;
    }
  }

  /**
   * Evaluates the company's P/E ratio relative to its sector.
   */
  @Override
  public void evaluate() {
    double sectorAveragePE = getSectorAveragePE(sector);
    if (sectorAveragePE == 0.0) {
      description = "Could not find sector for stock " + symbol;
      rating = 0.0;
      ratingDescription = "Unknown";
    } else {
      rating = peRatio / sectorAveragePE;
      ratingDescription = getRatingDescription();
      generateDescription(rating);
    }
  }

  /**
   * Gets the average P/E ratio for the specified sector.
   *
   * @param sector the sector to which the company belongs
   * @return the average P/E ratio for the sector
   */
  private double getSectorAveragePE(String sector) {
    return switch (sector) {
      case "energy" -> Sectors.ENERGY.getPriceToEarnings();
      case "technology" -> Sectors.TECHNOLOGY.getPriceToEarnings();
      case "telecom" -> Sectors.TELECOM.getPriceToEarnings();
      case "industry" -> Sectors.INDUSTRY.getPriceToEarnings();
      case "finance" -> Sectors.FINANCE.getPriceToEarnings();
      case "software" -> Sectors.SOFTWARE.getPriceToEarnings();
      case "consumer staples" -> Sectors.CONSUMER_STAPLES.getPriceToEarnings();
      case "healthcare" -> Sectors.HEALTHCARE.getPriceToEarnings();
      case "materials" -> Sectors.MATERIALS.getPriceToEarnings();
      case "real estate" -> Sectors.REAL_ESTATE.getPriceToEarnings();
      case "utilities" -> Sectors.UTILITIES.getPriceToEarnings();
      default -> 0.0;
    };
  }

  /**
   * Generates the detailed description of the evaluation.
   *
   * @param sectorAveragePE the average P/E ratio for the sector
   */
  private void generateDescription(double sectorAveragePE) {
    description = String.format(
        "P/E Ratio: %.2f%n"
            + "Sector Average P/E Ratio: %.2f%n"
            + "P/E Percentage: %.2f%%%n%n"
            + "The current P/E ratio for %s stands at %.2f, which is %.2f%% of the average P/E ratio "
            + "for the %s sector. This indicates that the stock's valuation is %s relative to its sector.%n%n"
            + "In summary, %s's P/E ratio of %.2f results in a '%s' rating, suggesting that the stock is %s "
            + "relative to sector expectations. Investors should consider this rating alongside other financial "
            + "metrics and the company's overall financial health when making investment decisions.",
        peRatio, sectorAveragePE, rating * 100,
        symbol, peRatio, rating * 100, sector, ratingDescription.toLowerCase(),
        symbol, peRatio, ratingDescription, ratingDescription.toLowerCase()
    );
  }

  public PERatioEvaluation getPeEvaluation() {
    return peEvaluation;
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
    return "Price to Performance";
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
        "Understanding Price to Performance:%n%n"
            + "The price to performance evaluation evaluates the price of the stock in relation to how well the "
            + "company is performing. It evaluates how the annual earnings per share are related to the share price, "
            + "in other words, the price-to-earnings (P/E) ratio. The P/E ratio is one of the most widely used metrics "
            + "for both investors and analysts to determine the value of a stock. It indicates whether a company's stock "
            + "price is overvalued or undervalued and can reveal how a stock's valuation compares to its sector or a benchmark "
            + "like the S&P 500.%n%n"
            + "The P/E ratio is calculated by dividing the stock's current price by its latest earnings per share. The P/E ratio "
            + "varies across different sectors, and it is not always an accurate indicator of the company's performance. A good "
            + "P/E ratio for one sector could be a poor P/E ratio for another sector. Companies that grow faster than average, such "
            + "as technology companies, typically have higher P/E ratios. The P/E ratio can also be used to determine the company's "
            + "future earnings growth. For example, investors might expect the company to increase its dividends if earnings are expected "
            + "to rise. Higher earnings and higher dividends typically lead to a higher stock price."
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
            + "The evaluations are made by comparing the P/E ratio of the stock to the average P/E ratio of the sector the company belongs to. "
            + "This is important as the P/E ratios for different sectors vary significantly. The P/E ratio percentage is calculated by dividing "
            + "the P/E ratio of the stock by the average P/E ratio of the sector. The value is then graded based on ten categories: %n"
            + "Lower than 25%%: Very Low%n"
            + "Higher than 25%% but lower than 50%%: Low%n"
            + "Higher than 50%% but lower than 75%%: Below Average%n"
            + "Higher than 75%% but lower than 90%%: Slightly Below Average%n"
            + "Higher than 90%% but lower than 110%%: Average%n"
            + "Higher than 110%% but lower than 125%%: Slightly Above Average%n"
            + "Higher than 125%% but lower than 150%%: Above Average%n"
            + "Higher than 150%% but lower than 175%%: High%n"
            + "Higher than 175%% but lower than 200%%: Very High%n"
            + "Higher than 200%%: Extremely High%n%n"
            + "If the P/E ratio is considered high, it is possible that the stock is overvalued, which might indicate that the price may fall in the future. "
            + "If the P/E ratio is considered low, it is possible that the stock is undervalued, suggesting that the stock's price may rise in the future."
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
   * Gets the calculated rating of the company's P/E ratio.
   *
   * @return the calculated rating
   */
  @Override
  public double getRating() {
    return rating;
  }

  /**
   * Gets the P/E ratio of the company's stock.
   *
   * @return the P/E ratio
   */
  @Override
  public String getValue() {
    return String.valueOf(peRatio);
  }
}