package common.evaluations.rating;

import common.data.fundamental.CompanyOverview;
import common.data.fundamental.IncomeStatement;
import common.interfaces.RatingEvaluation;

/**
 * This class evaluates the company's growth based on the quarterly revenue growth year over year.
 * <p>
 * The evaluation is based on the increase in the company's sales from one quarter to the next,
 * compared on a year-over-year basis.
 * </p>
 *
 * <ul>
 *   <li>{@code symbol} - The unique identifier for the company's stock.</li>
 *   <li>{@code quarterlyRevenueGrowthYOY} - The percentage increase in the company's revenue
 *       from one quarter to the same quarter the previous year.</li>
 *   <li>{@code rating} - The calculated rating based on the quarterly revenue growth.</li>
 *   <li>{@code ratingDescription} - A textual description of the rating.</li>
 *   <li>{@code description} - A detailed description of the evaluation.</li>
 * </ul>
 *
 * @see RatingEvaluation
 * @see IncomeStatement
 * @see CompanyOverview
 *
 * @author Joar Eliason
 * @author Olivia Svensson
 */
public class CompanyGrowthEvaluation implements RatingEvaluation {

  private final String symbol;
  private final double quarterlyRevenueGrowthYOY;
  private double rating;
  private String ratingDescription;

  /**
   * Constructs a new {@code CompanyGrowthEvaluation} instance.
   *
   * @param symbol the unique identifier for the company's stock
   * @param quarterlyRevenueGrowthYOY the percentage increase in the company's revenue from one
   *        quarter to the same quarter the previous year
   */
  public CompanyGrowthEvaluation(String symbol, double quarterlyRevenueGrowthYOY) {
    this.symbol = symbol;
    this.quarterlyRevenueGrowthYOY = quarterlyRevenueGrowthYOY;
    evaluate();
  }

  /**
   * Generates a rating description based on predefined ranges.
   *
   * @return the rating description
   */
  @Override
  public String getRatingDescription() {
    if (quarterlyRevenueGrowthYOY < 0) {
      return "Decline in Growth";
    } else if (quarterlyRevenueGrowthYOY < 5) {
      return "Small Growth";
    } else if (quarterlyRevenueGrowthYOY < 15) {
      return "Stable Growth";
    } else if (quarterlyRevenueGrowthYOY < 25) {
      return "Rapid Growth";
    } else if (quarterlyRevenueGrowthYOY < 50) {
      return "Very Rapid Growth";
    } else {
      return "Extremely Rapid Growth";
    }
  }

  /**
   * Evaluates the company's growth by calculating the rating and generating its description.
   */
  @Override
  public void evaluate() {
    rating = quarterlyRevenueGrowthYOY / 100.0;
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
    return "Company Growth";
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
        "Understanding Company Growth:%n%n"
            + "The company growth evaluation is based on the company's growth, measured by the quarterly "
            + "revenue growth year over year. Quarterly revenue growth refers to the increase in the company's "
            + "sales from one quarter to the next, compared on a year-over-year basis. For example, the revenue "
            + "for Q4 (fourth quarter of the fiscal year) of year 1 is compared to Q4 of year 2.%n%n"
            + "This growth metric provides a percentage rate of the increase in a company's sales over a fixed period. "
            + "Consistent growth is an indicator of a potentially good investment. Companies report earnings every quarter, "
            + "which contributes to the transparency and integrity of public markets. However, it's important to combine this "
            + "metric with other growth indicators to get a comprehensive view of the company's performance."
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
            + "The evaluation of the company growth is based on the value of the quarterly revenue year over year. "
            + "The growth is categorized into six levels:%n"
            + "Less than 0%%: Decline in growth%n"
            + "Less than 5%%: Small growth%n"
            + "Less than 15%%: Stable growth%n"
            + "Less than 25%%: Rapid growth%n"
            + "Less than 50%%: Very rapid growth%n"
            + "More than 50%%: Extremely rapid growth%n%n"
            + "While a high growth rate is positive, it does not guarantee long-term success. Seasonal fluctuations and "
            + "short-term economic changes can affect quarterly growth. It is important to consider the sustainability of the growth rate "
            + "and other financial metrics when evaluating a company's long-term performance."
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
        "Quarterly Revenue Growth YOY: %.2f%%%n"
            + "Market Comparison: %s%n%n"
            + "The current quarterly revenue growth year over year for %s stands at %.2f%%, which corresponds to a "
            + "market comparison rating of '%s'. This indicates that the company's growth rate is %s relative to "
            + "industry standards.%n%n"
            + "In summary, %s's quarterly revenue growth of %.2f%% results in a '%s' rating, suggesting that the company is %s "
            + "relative to market expectations. Investors should consider this rating alongside other financial metrics and "
            + "the company's overall financial health when making investment decisions.",
        quarterlyRevenueGrowthYOY, ratingDescription, symbol, quarterlyRevenueGrowthYOY,
        ratingDescription, ratingDescription.toLowerCase(),
        symbol, quarterlyRevenueGrowthYOY, ratingDescription, ratingDescription.toLowerCase()
    );
  }

  /**
   * Gets the calculated rating of the company's quarterly revenue growth.
   *
   * @return the calculated rating
   */
  @Override
  public double getRating() {
    return rating;
  }

  /**
   * Gets the quarterly revenue growth year over year of the company's stock.
   *
   * @return the quarterly revenue growth year over year
   */
  @Override
  public String getValue() {
    return String.format("%.2f%%", quarterlyRevenueGrowthYOY);
  }
}
