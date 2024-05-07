package common.evaluations;

import common.interfaces.RatingEvaluation;

/**
 * Class for evaluating the size of the company. Company size evaluations are based on the revenue
 * for the past twelve months and their market capitalization.
 *
 * @author Olivia Svensson, Joar Eliasson
 */
public class CompanySizeEvaluation implements RatingEvaluation {

  private final String symbol;
  private final long revenueTTM;
  private int rating;
  private String description;

  public CompanySizeEvaluation(String symbol, long revenueTTM) {
    this.symbol = symbol;
    this.revenueTTM = revenueTTM;
    evaluate();
  }

  /**
   * Evaluates the company size based on the revenue for the past twelve months. The evaluation is
   * based on the following thresholds:
   * <p>
   * Nano-cap: Below 50 million $
   * <p>
   * Micro-cap: Between 50 million $ and 250 million $
   * <p>
   * Mid-cap: Between 250 million $ and 2 billion $
   * <p>
   * Big-cap: Between 2 billion $ and 10 billion $
   * <p>
   * Mega-cap: Between 10 billion $ and 200 billion $
   */
  @Override
  public void evaluate() {
    long nanoCapThreshold = 50000000L;
    long microCapThreshold = 250000000L;
    long midCapThreshold = 2000000000L;
    long bigCapThreshold = 10000000000L;
    long megaCapThreshold = 200000000000L;

    if (revenueTTM < nanoCapThreshold) {
      description = String.format(
          "The Company (%s) is considered a very small company, a Nano-cap company "
          + "(below 50 million $) , judging by the revenue for the past twelve months "
          + "(%s $). A small company is often considered riskier than larger companies, but "
          + "it also has the potential for high growth.",
          symbol, revenueTTM
      );
      rating = 0;
    } else if (revenueTTM < microCapThreshold && revenueTTM > nanoCapThreshold) {
      description = String.format(
          "The Company (%s) is considered a small company, a Micro-cap company "
          + "(between 50 million $ and 250 million $) , judging by the revenue for the past "
          + "twelve months (%s $). A small company is often considered riskier than a large "
          + "company, but it also has the potential for high growth.",
          symbol, revenueTTM
      );
      rating = 1;
    } else if (revenueTTM < midCapThreshold && revenueTTM > microCapThreshold) {
      description = String.format(
          "The Company (%s) is considered a medium-sized company, a Mid-cap company "
          + "(between 250 million $ and 2 billion $) , judging by the revenue for the past "
          + "twelve months (%s $). A medium-sized company is often considered less risky "
          + "than a small company, but it also has less growth potential. This is due to "
          + "the company's size and market position.",
          symbol, revenueTTM
      );
      rating = 2;
    } else if (revenueTTM < bigCapThreshold && revenueTTM > midCapThreshold) {
      description = String.format(
          "The Company (%s) is considered a large company, a Big-cap company "
          + "(between 2 billion $ and 10 billion $) , judging by the revenue for the past "
          + "twelve months (%s $). A large company often brings less risk and more stability "
          + "than smaller companies. It is also less likely to experience high growth. This "
          + "is due to the company's size and market position.",
          symbol, revenueTTM
      );
      rating = 3;
    } else if (revenueTTM < megaCapThreshold && revenueTTM > bigCapThreshold) {
      description = String.format(
          "The Company (%s) is considered a very large company, a Mega-cap company "
          + "(between 10 billion $ and 200 billion $) , judging by the revenue for the past"
          + " twelve months (%s $). Companies of this size are often big players in their "
          + "market and are considered less risky than smaller companies.",
          symbol, revenueTTM
      );
      rating = 4;
    } else {
      description = String.format(
          "The Company (%s) is considered an extremely large company, a Mega-cap company "
          + "(above 200 billion $) , judging by the revenue for the past twelve months "
          + "(%s $). Few companies are this large, and they are often considered less risky"
          + " than smaller companies, since they most likely hold a dominant position in"
          + "their market.",
          symbol, revenueTTM
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
    return "Company Size";
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
    return "The company size evaluation is based on the revenue of the company for the past twelve"
        + " months, as well as their market capitalization. Revenue is the total amount of money"
        + " generated from a business’s primary operations. It is also known as gross sales."
        + " The revenue is calculated by multiplying a company’s average sales price by the"
        + " number of units sold. Revenue differs from the company’s income as the income is the"
        + " company’s total earnings after all expenses, and earnings not counted as revenue are"
        + " deducted. Revenue is in other words the total amount of money generated by the"
        + " company’s sale of goods and services related to the company’s primary operations."
        + " Expenses are not counted in the revenue. The revenue of the company only indicates"
        + " how effective the company is at generating sales. It does not take into consideration"
        + " operating efficiencies, which could have a big impact on the company’s bottom line"
        + " (earnings, profit, net income, or earnings per share). Revenue can come from a variety"
        + " of sources such as the sale of goods, services and assets, advertising, licensing"
        + " agreements, fees and service charges, subscriptions, and rental income."
        + " Companies recognize and record revenue differently, and it is not the same even"
        + " if they are part of the same sector. ";
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
    return "The evaluation of the company’s size is done by comparing the revenue of the past"
        + " twelve months with the different thresholds for market capitalization categories."
        + " There are six categories: nano-cap (below 50 million dollars), micro-cap (between 50"
        + " million dollars and 250 million dollars), mid-cap (between 250 million dollars and 2"
        + " billion dollars), big-cap (between 2 billion dollars and 10 billion dollars), as well"
        + " as mega-cap (between 10 billion dollars and 200 billion dollars). The order of the"
        + " categories goes from smaller companies to bigger. Nano-cap = very small company,"
        + " micro-cap = small company, mid-cap = medium-sized company, big-cap = larger company,"
        + " and mega-cap = extremely large company. Note that these statistics are for the American"
        + " stock exchanges, as the American companies are much bigger than many other countries."
        + " What is considered big-cap or small-cap is largely dependent on which market you are"
        + " in. ";
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
    return String.valueOf(revenueTTM);
  }
}
