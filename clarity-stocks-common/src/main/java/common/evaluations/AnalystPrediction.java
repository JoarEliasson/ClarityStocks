//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package common.evaluations;

import common.interfaces.Evaluation;

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

  //compare target price with current price

  public AnalystPrediction(String symbol, double currentPrice, double analystTargetPrice, int analystRatingStrongBuy, int analystRatingBuy, int analystRatingHold, int analystRatingSell, int analystRatingStrongSell) {
    this.symbol = symbol;
    this.currentPrice = currentPrice;
    this.ratingBuy = analystRatingBuy;
    this.ratingStrongBuy = analystRatingStrongBuy;
    this.ratingHold = analystRatingHold;
    this.ratingSell = analystRatingSell;
    this.ratingStrongSell = analystRatingStrongSell;
    this.targetPrice = analystTargetPrice;
    this.evaluate();
  }

  public String getDescription(String symbol, double currentPrice, double targetPrice, int ratingStrongBuy, int ratingBuy, int ratingHold, int ratingSell, int ratingStrongSell) {
    return this.description = "Analyst target price for " + symbol + ": " + targetPrice + ". Current price is " + currentPrice + ".\nAnalyst rating buy: " + ratingBuy + ".\nAnalyst rating strong buy: " + ratingStrongBuy + ".\nAnalyst rating hold: " + ratingHold + ".\nAnalyst rating sell: " + ratingSell + ".\nAnalyst rating strong sell: " + ratingStrongSell + ".";
  }

  private String getDescriptionPrice(double targetPrice, double currentPrice) {
    if (targetPrice > currentPrice) {
      return this.descriptionPrice = "There is an indication that the stock is undervalued.";
    } else if (targetPrice < currentPrice) {
      return this.descriptionPrice = "There is an indication that the stock is overvalued.";
    } else {
      return targetPrice == currentPrice ? (this.descriptionPrice = "There is an indication that the stock is at a fair price.") : (this.descriptionPrice = "Something went wrong with the evaluation of the analyst target price compared to the current price.");
    }
  }

  private String getDescriptionBuy(int ratingBuy) {
    if (ratingBuy > 5) {
      return this.descriptionBuy = "There is an indication that it is a good idea to buy the stock according to the analyst.";
    } else if (ratingBuy < 5) {
      return this.descriptionBuy = "There is an indication that it is a bad idea to buy the stock according to the analyst.";
    } else {
      return ratingBuy == 5 ? (this.descriptionBuy = "There is an indication that it is an okay idea to buy the stock according to the analyst.") : (this.descriptionBuy = "Something went wrong with the analyst buy rating.");
    }
  }

  private String getDescriptionStrongBuy(int ratingStrongBuy) {
    if (ratingStrongBuy > 5) {
      return this.descriptionStrongBuy = "There is a strong indication that it is a good idea to buy the stock according to the analyst.";
    } else if (ratingStrongBuy < 5) {
      return this.descriptionStrongBuy = "There is a strong indication that it is a bad idea to buy the stock according to the analyst.";
    } else {
      return ratingStrongBuy == 5 ? (this.descriptionStrongBuy = "There is a strong indication that it is an okay idea to buy the stock according to the analyst.") : (this.descriptionStrongBuy = "Something went wrong with the analyst strong buy rating.");
    }
  }

  private String getDescriptionHold(int ratingHold) {
    if (ratingHold > 5) {
      return this.descriptionHold = "There is an indication that it is a good idea to hold the stock according to the analyst.";
    } else if (ratingHold < 5) {
      return this.descriptionHold = "There is an indication that it is a bad idea to hold the stock according to the analyst.";
    } else {
      return ratingHold == 5 ? (this.descriptionHold = "There is an indication that it is an okay idea to hold the stock according to the analyst.") : (this.descriptionHold = "Something went wrong with the analyst hold rating.");
    }
  }

  private String getDescriptionSell(int ratingSell) {
    if (ratingSell > 5) {
      return this.descriptionSell = "There is an indication that it is a good idea to sell the stock according to the analyst.";
    } else if (ratingSell < 5) {
      return this.descriptionSell = "There is an indication that it is a bad idea to sell the stock according to the analyst.";
    } else {
      return ratingSell == 5 ? (this.descriptionSell = "There is an indication that it is an okay idea to sell the stock according to the analyst.") : (this.descriptionSell = "Something went wrong with the analyst sell rating.");
    }
  }

  private String getDescriptionStrongSell(int ratingStrongSell) {
    if (ratingStrongSell > 5) {
      return this.descriptionStrongSell = "There is a strong indication that it is a good idea to sell the stock according to the analyst.";
    } else if (ratingStrongSell < 5) {
      return this.descriptionStrongSell = "There is a strong indication that it is a bad idea to sell the stock according to the analyst.";
    } else {
      return ratingStrongSell == 5 ? (this.descriptionStrongSell = "There is a strong indication that it is an okay idea to sell the stock according to the analyst.") : (this.descriptionStrongSell = "Something went wrong with the analyst strong sell rating.");
    }
  }

  public String getElaborateDescription(double analystTargetPrice, double currentPrice, int analystRatingBuy, int analystRatingStrongBuy, int analystRatingHold, int analystRatingSell, int analystRatingStrongSell) {
    this.descriptionPrice = this.getDescriptionPrice(analystTargetPrice, currentPrice);
    this.descriptionBuy = this.getDescriptionBuy(analystRatingBuy);
    this.descriptionStrongBuy = this.getDescriptionStrongBuy(analystRatingStrongBuy);
    this.descriptionHold = this.getDescriptionHold(analystRatingHold);
    this.descriptionSell = this.getDescriptionSell(analystRatingSell);
    this.descriptionStrongSell = this.getDescriptionStrongSell(analystRatingStrongSell);
    return this.elaborateDescription = this.descriptionPrice + "\n" + this.descriptionBuy + "\n" + this.descriptionStrongBuy + "\n" + this.descriptionHold + "\n" + this.descriptionSell + "\n" + this.descriptionStrongSell;
  }

  public void evaluate() {
    this.getDescription(this.symbol, this.currentPrice, this.targetPrice, this.ratingStrongBuy, this.ratingBuy, this.ratingHold, this.ratingSell, this.ratingStrongSell);
    this.getElaborateDescription(this.targetPrice, this.currentPrice, this.ratingBuy, this.ratingStrongBuy, this.ratingHold, this.ratingSell, this.ratingStrongSell);
  }

  public String getSymbol() {
    return this.symbol;
  }

  public String getDescription() {
    return this.description;
  }
}

