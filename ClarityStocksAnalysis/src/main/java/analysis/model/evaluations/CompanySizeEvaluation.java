package analysis.model.evaluations;

import analysis.model.interfaces.RatingEvaluation;

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
          + "(%s $). A small company is often considered riskier than larger companies, but"
          + "it also has the potential for high growth.",
          symbol, revenueTTM
      );
      rating = 0;
    } else if (revenueTTM < microCapThreshold) {
      description = String.format(
          "The Company (%s) is considered a small company, a Micro-cap company "
          + "(between 50 million $ and 250 million $) , judging by the revenue for the past"
          + "twelve months (%s $). A small company is often considered riskier than a large"
          + "company, but it also has the potential for high growth.",
          symbol, revenueTTM
      );
      rating = 1;
    } else if (revenueTTM < midCapThreshold) {
      description = String.format(
          "The Company (%s) is considered a medium-sized company, a Mid-cap company "
          + "(between 250 million $ and 2 billion $) , judging by the revenue for the past"
          + "twelve months (%s $). A medium-sized company is often considered less risky "
          + "than a small company, but it also has less growth potential. This is due to "
          + "the company's size and market position.",
          symbol, revenueTTM
      );
      rating = 2;
    } else if (revenueTTM < bigCapThreshold) {
      description = String.format(
          "The Company (%s) is considered a large company, a Big-cap company "
          + "(between 2 billion $ and 10 billion $) , judging by the revenue for the past"
          + "twelve months (%s $). A large company often brings less risk and more stability"
          + "than smaller companies. It is also less likely to experience high growth. This "
          + "is due to the company's size and market position.",
          symbol, revenueTTM
      );
      rating = 3;
    } else if (revenueTTM < megaCapThreshold) {
      description = String.format(
          "The Company (%s) is considered a very large company, a Mega-cap company "
          + "(between 10 billion $ and 200 billion $) , judging by the revenue for the past"
          + "twelve months (%s $). Companies of this size are often big players in their "
          + "market and are considered less risky than smaller companies.",
          symbol, revenueTTM
      );
      rating = 4;
    } else {
      description = String.format(
          "The Company (%s) is considered an extremely large company, a Mega-cap company "
          + "(above 200 billion $) , judging by the revenue for the past twelve months "
          + "(%s $). Few companies are this large, and they are often considered less risky"
          + "than smaller companies, since they most likely hold a dominant position in"
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
