package analysis.model.unfinished;

import analysis.model.interfaces.Evaluation;

/**
 * Class for evaluating the analyst prediction of a stock. The analyst grades the evaluations on a
 * scale of 0 to 10.
 *
 * @author Olivia Svensson
 */
public class AnalystPrediction implements Evaluation {
  private String symbol;
  private String description;
  private String elaborateDescription;
  private double targetPrice;
  private double currentPrice;
  private int ratingStrongBuy;
  private int ratingBuy;
  private int ratingHold;
  private int ratingSell;
  private int ratingStrongSell;
  private String descriptionPrice;
  private String descriptionBuy;
  private String descriptionStrongBuy;
  private String descriptionHold;
  private String descriptionSell;
  private String descriptionStrongSell;

  /*
  * Constructor for the AnalystPrediction. Passes in values from the Alpha Vantage API and sets the instance variables
  *  of the class to the ones passed in from the method parameters. Also calls the evaluate method.
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
*
*/

  public String getDescription(String symbol, double currentPrice, double targetPrice,
    int ratingStrongBuy, int ratingBuy, int ratingHold,
    int ratingSell, int ratingStrongSell) {
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

  private String getDescriptionPrice(double targetPrice, double currentPrice) {
    if (targetPrice > currentPrice) {
      return descriptionPrice = "There is an indication that the stock is undervalued.";
    } else if (targetPrice < currentPrice) {
      return descriptionPrice = "There is an indication that the stock is overvalued.";
    } else if (targetPrice == currentPrice) {
      return descriptionPrice = "There is an indication that the stock is at a fair price.";
    } else {
      return descriptionPrice = "Something went wrong with the evaluation of the analyst target price compared to" +
      " the current price.";
    }
  }

  /*
   * Method for getting a description of analysts buy rating.
   * @return description in a String
   * */

  private String getDescriptionBuy(int ratingBuy) {
    if (ratingBuy > 5) {
      return descriptionBuy = "There is an indication that it is a good idea to buy the stock according to the" +
      " analyst.";
    } else if (ratingBuy < 5) {
      return descriptionBuy = "There is an indication that it is a bad idea to buy the stock according to the" +
      " analyst.";
    } else if (ratingBuy == 5) {
      return descriptionBuy = "There is an indication that it is an okay idea to buy the stock according to the" +
      " analyst.";
    } else {
      return descriptionBuy = "Something went wrong with the analyst buy rating.";
    }
  }

  /*
   * Method for getting a description of analysts strong buy rating.
   * @return description in a String
   * */

  private String getDescriptionStrongBuy(int ratingStrongBuy) {
    if (ratingStrongBuy > 5) {
      return descriptionStrongBuy = "There is a strong indication that it is a good idea to buy the stock according" +
      " to the analyst.";
    } else if (ratingStrongBuy < 5) {
      return descriptionStrongBuy = "There is a strong indication that it is a bad idea to buy the stock according" +
      " to the analyst.";
    } else if (ratingStrongBuy == 5) {
      return descriptionStrongBuy = "There is a strong indication that it is an okay idea to buy the stock according" +
      " to the analyst.";
    } else {
      return descriptionStrongBuy = "Something went wrong with the analyst strong buy rating.";
    }
  }

  /*
   * Method for getting a description of analysts hold rating.
   * @return description in a String
   * */

  private String getDescriptionHold(int ratingHold) {
    if (ratingHold > 5) {
      return descriptionHold = "There is an indication that it is a good idea to hold the stock according to" +
      " the analyst.";
    } else if (ratingHold < 5) {
      return descriptionHold = "There is an indication that it is a bad idea to hold the stock according to" +
      " the analyst.";
    } else if (ratingHold == 5) {
      return descriptionHold = "There is an indication that it is an okay idea to hold the stock according to" +
      " the analyst.";
    } else {
      return descriptionHold = "Something went wrong with the analyst hold rating.";
    }
  }

  /*
   * Method for getting a description of analysts sell rating.
   * @return description in a String
   * */

  private String getDescriptionSell(int ratingSell) {
    if (ratingSell > 5) {
      return descriptionSell = "There is an indication that it is a good idea to sell the stock according to" +
      " the analyst.";
    } else if (ratingSell < 5) {
      return descriptionSell = "There is an indication that it is a bad idea to sell the stock according to" +
      " the analyst.";
    } else if (ratingSell == 5) {
      return descriptionSell = "There is an indication that it is an okay idea to sell the stock according to" +
      " the analyst.";
    } else {
      return descriptionSell = "Something went wrong with the analyst sell rating.";
    }
  }

  /*
   * Method for getting a description of analysts strong sell rating.
   * @return description in a String
   * */

  private String getDescriptionStrongSell(int ratingStrongSell) {
    if (ratingStrongSell > 5) {
      return descriptionStrongSell = "There is a strong indication that it is a good idea to sell the stock according" +
      " to the analyst.";
    } else if (ratingStrongSell < 5) {
      return descriptionStrongSell = "There is a strong indication that it is a bad idea to sell the stock according" +
      " to the analyst.";
    } else if (ratingStrongSell == 5) {
      return descriptionStrongSell = "There is a strong indication that it is an okay idea to sell the stock " +
      "according to the analyst.";
    } else {
      return descriptionStrongSell = "Something went wrong with the analyst strong sell rating.";
    }
  }
  /*
  * Method for getting the more elaborate description of the analysis.
  * @return description in a String
  * */

  public String getElaborateDescription(double analystTargetPrice, double currentPrice,
    int analystRatingBuy, int analystRatingStrongBuy, int analystRatingHold,
    int analystRatingSell, int analystRatingStrongSell) {
    descriptionPrice = getDescriptionPrice(analystTargetPrice, currentPrice);
    descriptionBuy = getDescriptionBuy(analystRatingBuy);
    descriptionStrongBuy = getDescriptionStrongBuy(analystRatingStrongBuy);
    descriptionHold = getDescriptionHold(analystRatingHold);
    descriptionSell = getDescriptionSell(analystRatingSell);
    descriptionStrongSell = getDescriptionStrongSell(analystRatingStrongSell);

    return elaborateDescription =
    descriptionPrice + "\n" + descriptionBuy + "\n" + descriptionStrongBuy + "\n" + descriptionHold + "\n"
    + descriptionSell + "\n" + descriptionStrongSell;
  }

  /**
   * Method for evaluating the stock parameters.
   */

  @Override
  public void evaluate() {
    getDescription(symbol, currentPrice,targetPrice, ratingStrongBuy,
    ratingBuy, ratingHold, ratingSell, ratingStrongSell);
    getElaborateDescription(targetPrice, currentPrice, ratingBuy,
    ratingStrongBuy, ratingHold, ratingSell, ratingStrongSell);
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
   * Method for getting the description of the evaluation.
   *
   * @return the description of the evaluation.
   */

  @Override
  public String getDescription() {
    return description;
  }



}
