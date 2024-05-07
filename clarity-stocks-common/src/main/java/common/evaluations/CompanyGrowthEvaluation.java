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
    } else if (quarterlyRevenueGrowthYOY < 5 && quarterlyRevenueGrowthYOY > 0) {
        description = String.format(
            "The company (%s) is experiencing a small growth (%s).%nThis is a sign for caution"
                + " depending on sector, industry and company size.",
            symbol, quarterlyRevenueGrowthYOY
        );
        rating = 1;
    } else if (quarterlyRevenueGrowthYOY < 15 && quarterlyRevenueGrowthYOY > 5) {
        description = String.format(
            "The company (%s) is experiencing stable growth (%s).%nThis is a neutral or positive"
                + " sign depending on sector, industry and company size.",
            symbol, quarterlyRevenueGrowthYOY
        );
        rating = 2;
    } else if (quarterlyRevenueGrowthYOY < 25 && quarterlyRevenueGrowthYOY > 15) {
        description = String.format(
            "The company (%s) is experiencing rapid growth (%s).%nThis is a positive sign for "
            + "the company and its stock. A company with strong growth is likely to be a good "
            + "investment.",
            symbol, quarterlyRevenueGrowthYOY
        );
        rating = 3;
    }
    else if (quarterlyRevenueGrowthYOY < 50 && quarterlyRevenueGrowthYOY > 25) {
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
    return "Company Growth";
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
    return "The company growth evaluation is based on the stock company’s growth, which is based on"
        + " the quarterly revenue growth year over year. Quarterly revenue growth refers to an"
        + " increase in the company’s sales from one quarter to the next. The year over year basis"
        + " is when the sales figures for Q4 (fourth quarter of the year) for year 1 is compares to"
        + " the Q4 of year 2. The quarterly revenue growth provides a percentage rate of the"
        + " increase in a company’s sales over a fixed period. If the growth rate continues for"
        + " the company, it is an indicator of a good investment. Companies are required to report"
        + " earnings every quarter, which contributes to transparency and integrity of public"
        + " markets. If companies were to report less frequent such as annually, there would be a"
        + " greater scope for earnings management as the management can pick and choose certain"
        + " values to portray financial well-being. The quarterly revenue growth is not to only be"
        + " looked at, as this is only a short-term indication of success. Having a few quarters of"
        + " good company growth doesn’t necessarily mean that the company is going to continue"
        + " having success in the long run. Fluctuation is common as there might be short-term"
        + " changes in the company, as well as the economy. Some companies are seasonal businesses,"
        + " such as retail, which might experience high quarterly growth during certain times of"
        + " the year. It is important to not only look at the quarterly revenue growth, but to"
        + " combine it with other growth metrics.";
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
    return "The evaluation of the company growth is based on the value of the quarterly revenue"
        + " year over year. The grading has been divided into six categories. If the growth is less"
        + " than 0, it means that the company is experiencing a decline in growth."
        + " If the growth is less than 5%, the company is experiencing a small growth."
        + " If the growth is less than 15%, it is an indication that the company is experiencing"
        + " a stable growth."
        + " If the growth is less than 25%, the company is experiencing rapid growth."
        + " If the growth is less than 50%, the company is experiencing very rapid growth."
        + " If the growth is more than 50%, the company is experiencing extremely rapid growth."
        + " It is important to note that the higher the value of the growth is, does not correlate"
        + " with how well the company is performing historically or how it will perform in the"
        + " future. If the company has a very high growth rate, it is very unlikely that it will"
        + " continue in the future. The company growth is only evaluated on quarters, meaning that"
        + " it is not a long term measurement of the company’s performance. ";
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
