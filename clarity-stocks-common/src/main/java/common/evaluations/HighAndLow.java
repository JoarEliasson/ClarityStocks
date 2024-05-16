package common.evaluations;

import common.interfaces.Evaluation;

/**
 * Class for evaluating the 52-week high and low of a stock.
 *
 * @author Olivia Svensson
 */

public class HighAndLow implements Evaluation {

  private final String symbol;
  private final double high;
  private final double low;
  private String description;

  public HighAndLow(String symbol, double high, double low) {
    this.symbol = symbol;
    this.high = high;
    this.low = low;
  }

  @Override
  public void evaluate() {
    description = String.format(
    "The 52-week high for %s is %.2f.%n"
    + "The 52-week low for %s is %.2f",
    symbol, high, symbol, low
    );
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
    return "52-Week High and Low";
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
    return "The High and Low evaluates the 52-week high and the 52-week low of a stock’s price."
        + " The 52-week high is the highest price at which a stock has been traded over the"
        + " previous 52 weeks. The 52-week low is the lowest price at which a stock has been"
        + " traded over the previous 52 weeks. This information is important to some investors,"
        + " who might see it as an indicator that they use as part of their investment strategy."
        + " The investor can compare the two values with the current price of the stock to judge"
        + " if the price of the stock is considered high or low. If the price is considered low,"
        + " then the investor might consider it a buying opportunity. These metrics however are"
        + " not only to be considered, as many things could happen to a company in 52 weeks."
        + " Just because a stock has traded high before, or low, doesn’t mean that it will"
        + " necessarily do so again. There could be factors which makes the stock worth more or"
        + " less than before, so solely judging a stock’s performance on its price is not wise.";
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
    return "The evaluation simply returns the two values for the 52-week high and low of the price"
        + " of the stock. The investor can make judgements about the current stock price based on"
        + " these metrics, however it is not wise to solely base the judgements on these as many"
        + " other factors are to be considered as well. Just because a stock has historically"
        + " traded high, doesn't mean that it will do so again. Historical prices does not"
        + " guarantee future prices corresponding to these.";
  }

  @Override
  public String getDescription() {
    return description;
  }
}
