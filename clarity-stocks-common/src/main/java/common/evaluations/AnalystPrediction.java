package common.evaluations;

import common.interfaces.Evaluation;

/**
 * Class for evaluating the analyst prediction of a stock. The analyst grades the evaluations on a
 * scale of 0 to 10, 0 being the lowest indication of an analyst rating, and 10 being the highest.
 *
 * @author Olivia Svensson
 */
public class AnalystPrediction implements Evaluation {

  private String symbol;
  private String description;
  private double targetPrice;
  private double currentPrice;
  private int ratingStrongBuy;
  private int ratingBuy;
  private int ratingHold;
  private int ratingSell;
  private int ratingStrongSell;
  private String descriptionPrice;
  private double percentage;
  private String percentageDescription;
  private double percentageCompared;
  private String analystDescription;

  /*
   * Constructor for the AnalystPrediction. Passes in values from the Alpha Vantage API and sets the
   *  instance variables of the class to the ones passed in from the method parameters.
   * Also calls the evaluate method.
   * */
  public AnalystPrediction(String symbol, double currentPrice, double analystTargetPrice,
    int analystRatingStrongBuy, int analystRatingBuy, int analystRatingHold,
    int analystRatingSell, int analystRatingStrongSell) {
    this.symbol = symbol;
    this.currentPrice = currentPrice;
    this.ratingBuy = analystRatingBuy;
    this.ratingStrongBuy = analystRatingStrongBuy;
    this.ratingHold = analystRatingHold;
    this.ratingSell = analystRatingSell;
    this.ratingStrongSell = analystRatingStrongSell;
    this.targetPrice = analystTargetPrice;
    evaluate();
  }

  /*
   * Method which returns a simple description of several analyst ratings.
   * @author Olivia Svensson, Joar Eliasson
   * */
  public String getDescription(String symbol, double currentPrice, double targetPrice,
      int ratingStrongBuy, int ratingBuy, int ratingHold, int ratingSell, int ratingStrongSell) {
    return description =
    "Analyst target price for " + symbol + ": " + targetPrice + ". Current price is "
    + currentPrice + "." +
    "\nAnalyst rating buy: " + ratingBuy + "." +
    "\nAnalyst rating strong buy: " + ratingStrongBuy + "." +
    "\nAnalyst rating hold: " + ratingHold + "." +
    "\nAnalyst rating sell: " + ratingSell + "." +
    "\nAnalyst rating strong sell: " + ratingStrongSell + ".";
  }

  /*
   * Method for getting a description of analysts stock price rating.
   * @return description in a String
   * */

  private String getDescriptionPrice() {
    if (percentage < 100.0) {
      return descriptionPrice = "There is an indication that the stock is undervalued.";
    } else if (percentage > 100.0) {
      return descriptionPrice = "There is an indication that the stock is overvalued.";
    } else if (percentage == 100.0) {
      return descriptionPrice = "There is an indication that the stock is at a fair price.";
    } else {
      return descriptionPrice = "Something went wrong with the evaluation of the analyst target"
          + " price compared to the current price.";
    }
  }

  public String getPercentageDescription() {
    percentageCompared = percentage - 100;
    if (percentageCompared < 0) {
      percentageCompared = percentageCompared * (-1);
      return percentageDescription = "the current price of the stock is " + percentage + "% lower" +
      " than the target price";
    } else {
      return percentageDescription = "the current price of the stock is " + percentage +
      "% higher than the target price";
    }
  }

  /*
   * Method for evaluating the analyst ratings depending on what is the most favored.
   * @return String with the two most favored analyst ratings.
   * */
  private String getAnalystDescription() {
    if ((ratingBuy + ratingStrongBuy) > (ratingSell + ratingStrongSell)) {
      if (ratingHold > ratingStrongBuy) {
        return analystDescription = "The analysts seem to favor a BUY/HOLD";
      } else {
        return analystDescription = "The analysts seem to favor a BUY/STRONG BUY";
      }
    } else {
      if (ratingHold > ratingStrongSell) {
        return analystDescription = "The analysts seem to favor a SELL/HOLD";
      } else {
        return analystDescription = "The analysts seem to favor a SELL/STRONG SELL";
      }
    }
  }

  private double calculatePercentage(double targetPrice, double currentPrice) {
    return percentage = currentPrice / targetPrice * 100;
  }

  /**
   * Method for evaluating the price of the stock and the analyst's predictions.
   */
  @Override
  public void evaluate() {
    getDescription(symbol, currentPrice, targetPrice, ratingStrongBuy,
    ratingBuy, ratingHold, ratingSell, ratingStrongSell);
    calculatePercentage(targetPrice, currentPrice);
    getPercentageDescription();
    getDescriptionPrice();
    getAnalystDescription();
  }

  /**
   * Method for getting the symbol of the stock.
   *
   * @return the symbol of the stock.
   */
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

  /**
   * Method for getting the description of the evaluation.
   *
   * @return the description of the evaluation.
   */
  @Override
  public String getDescription() {
    return description;
  }
}
