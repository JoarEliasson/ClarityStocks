package common.evaluations;

import common.interfaces.RatingEvaluation;

/**
 * Class for evaluating the Price Earnings ratio of a company.
 * The Price Earnings ratio is calculated by dividing the current stock price by the earnings per
 * share.
 * <p>
 *   The class evaluates the Price Earnings ratio and returns a rating and a description of the
 *   evaluation.
 * </p>
 *
 * @author Joar Eliasson
 */
public class PERatioEvaluation implements RatingEvaluation {

  private final String symbol;
  private final double peRatio;
  private int rating;
  private String description;

  public PERatioEvaluation(String symbol, double peRatio) {
    this.symbol = symbol;
    this.peRatio = peRatio;
    evaluate();
  }

  @Override
  public void evaluate() {
    if (peRatio < 0) {
      description = "Price Earnings ratio is negative, which is a warning sign. The company is "
      + "likely losing money and has negative earnings.";
      rating = 0;
    } else if (peRatio < 10) {
      description = "Price Earnings ratio is less than 10, which is low. This is could indicate "
      + "that the company is undervalued or the prospect for the future of the company is "
      + "uncertain. Compare with the other stock-parameters.";
      rating = 5;
    } else if (peRatio < 15) {
      description = "Price Earnings ratio is between 10 and 15, which from a long-term historical "
      + "perspective is normal. It is lower than the market average, which is a positive sign.";
      rating = 4;
    } else if (peRatio < 20) {
      description = "Price Earnings ratio is between 15 and 20, which is normal within the last 10 "
      + "years. It is slightly lower than the market average, which is a positive sign. Compare"
      + " with the other stock-parameters.";
      rating = 3;
    } else if (peRatio < 25) {
      description = "Price Earnings ratio is between 20 and 25, which is a high value from a "
      + "historical perspective. A value this high should be based on strengths in other "
      + "performance indicators.";
      rating = 2;
    } else if (peRatio < 30) {
      description = "Price Earnings ratio is between 25 and 30, which is high. This is potentially "
      + "a negative sign, indicating that there is a risk that the company is overvalued. "
      + "Compare with the other stock-parameters.";
      rating = 1;
    } else if (peRatio < 40) {
      description = "Price Earnings ratio is greater than 30, which is high. Caution is advised "
      + "since there is a notable risk that the company is overvalued. Compare with the other "
      + "stock-parameters.";
      rating = 0;
    } else {
      description = "Price Earnings ratio is greater than 40, which is very high. This is a "
      + "negative sign, indicating that the price might be inflated by hype and is not based on"
      + " the company's earnings.";
      rating = 0;
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
    return String.valueOf(peRatio);
  }
}
