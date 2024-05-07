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
    return "Analyst Prediction";
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
    return "The analyst prediction analysis is based on analysts’ ratings of the stock."
        + " The analysts have research public financial statements of the stock company, listen"
        + " in on conference calls, as well as talk to managers and customers of the company."
        + " Through this investigation into company’s performance, the analyst decides whether the"
        + " stock is a “strong buy”, “buy”, “hold”, “sell” or “strong sell” and grades it"
        + " accordingly. The ratings provided by the financial analysts are independent of the"
        + " companies, as there would be legal repercussions for analysts rating stocks which"
        + " they have an interest in. The analyst ratings are meant as a complement for the other"
        + " statistics and is not something to solely base your judgement of the stock on. There is"
        + " also no industry standard for the rating scale of the stock. What we call “buy” could"
        + " be called “outperform” according to other rating scales. The analysts also gives a"
        + " target price, which is an estimation of what the stock's price is valued at in the"
        + " future.\n";
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
    return "The evaluation of the analyst prediction in the application is made by comparing the"
        + " target price and the current price of the stock. This is calculated by dividing the"
        + " current price with the target price. From this we get a value which is either under or"
        + " over the target price. We multiply the value by 100 to get the percentage. If the"
        + " current price is less than 100% of the target price, that is an indication that the"
        + " stock is currently undervalued. If the current price is more than 100%, it is an"
        + " indication that the stock is currently overvalued. Another part of the analysis returns"
        + " how many percentage points the current price of the stock is over or under the target"
        + " price. The analysts’ ratings we receive are on a scale of 1-10, ranking each of the"
        + " grades accordingly (“strong buy”, “buy”, “hold”, “sell” or “strong sell”). We sort the"
        + " two most favored gradings from the analysts by evaluating which two have the highest"
        + " grade.";
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
