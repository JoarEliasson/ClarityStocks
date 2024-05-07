package common.evaluations;

import common.interfaces.RatingEvaluation;

/**
 * This class evaluates the company's growth based on the quarterly revenue growth year over year.
 *
 * @author Olivia Svensson
 */
public class CompanyGrowthEvaluation implements RatingEvaluation {

  private final String symbol;
  private final double quarterlyRevenueGrowthYOY;
  private int rating;
  private String description;

  public CompanyGrowthEvaluation(String symbol, double quarterlyRevenueGrowthYOY) {
    this.symbol = symbol;
    this.quarterlyRevenueGrowthYOY = quarterlyRevenueGrowthYOY;
    evaluate();
  }

  @Override
  public void evaluate() {
    if (quarterlyRevenueGrowthYOY < 0) {
      description = String.format(
          "The company (%s) is experiencing a decline in growth (%s).%n"
          + "This is a warning signal for any stock and company.",
          symbol, quarterlyRevenueGrowthYOY
      );
      rating = 0;
    } else if (quarterlyRevenueGrowthYOY < 5) {
        description = String.format(
            "The company (%s) is experiencing a small growth (%s).%nThis is a sign for caution"
                + " depending on sector, industry and company size.",
            symbol, quarterlyRevenueGrowthYOY
        );
        rating = 1;
    } else if (quarterlyRevenueGrowthYOY < 15) {
        description = String.format(
            "The company (%s) is experiencing stable growth (%s).%nThis is a neutral or positive"
                + " sign depending on sector, industry and company size.",
            symbol, quarterlyRevenueGrowthYOY
        );
        rating = 2;
    } else if (quarterlyRevenueGrowthYOY < 25) {
        description = String.format(
            "The company (%s) is experiencing rapid growth (%s).%nThis is a positive sign for "
            + "the company and its stock. A company with strong growth is likely to be a good "
            + "investment.",
            symbol, quarterlyRevenueGrowthYOY
        );
        rating = 3;
    }
    else if (quarterlyRevenueGrowthYOY < 50) {
        description = String.format(
            "The company (%s) is experiencing very rapid growth (%s).%nThis is a very positive sign"
            + " for the company and its stock. A company with strong growth is likely to be a good"
            + " investment.",
            symbol, quarterlyRevenueGrowthYOY
        );
        rating = 4;
    } else {
        description = String.format(
            "The company (%s) is experiencing extremely rapid growth (%s).%nThis is an extremely "
            + "high growth rate and is should be considered carefully. A company with strong growth"
            + " is likely to be a good investment. Depending on the price of the stock, it might be"
            + " wise to consider if the growth is sustainable in the long run.",
            symbol, quarterlyRevenueGrowthYOY
        );
        rating = 5;
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

  @Override
  public int getRating() {
    return rating;
  }

  @Override
  public String getValue() {
    return String.format("%.2f %s", quarterlyRevenueGrowthYOY, "%");
  }
}
