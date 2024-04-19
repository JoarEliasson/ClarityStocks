package analysis.model.unfinished;

import analysis.model.interfaces.Evaluation;

/**
 * Class for evaluating the analyst prediction of a stock. The analyst grades the evaluations on a
 * scale of 0 to 10.
 *
 * @author Olivia Svensson
 */
public class AnalystPrediction implements Evaluation {

  String symbol;
  String description;
  String elaborateDescription;
  double analystTargetPrice;
  double currentPrice;
  int analystRatingStrongBuy;
  int analystRatingBuy;
  int analystRatingHold;
  int analystRatingSell;
  int analystRatingStrongSell;
  String aPrice;
  String aRB;
  String aRSB;
  String aRH;
  String aRS;
  String aRSS;

  public AnalystPrediction(String symbol, double currentPrice, double analystTargetPrice,
      int analystRatingStrongBuy, int analystRatingBuy, int analystRatingHold,
      int analystRatingSell, int analystRatingStrongSell) {
    this.symbol = symbol;
    this.currentPrice = currentPrice;
    this.analystRatingBuy = analystRatingBuy;
    this.analystRatingStrongBuy = analystRatingStrongBuy;
    this.analystRatingHold = analystRatingHold;
    this.analystRatingSell = analystRatingSell;
    this.analystRatingStrongSell = analystRatingStrongSell;
    this.analystTargetPrice = analystTargetPrice;
  }



  public String getDescription(String symbol, double currentPrice, double analystTargetPrice,
      int analystRatingStrongBuy, int analystRatingBuy, int analystRatingHold,
      int analystRatingSell, int analystRatingStrongSell) {
    return description =
        "Analyst target price for " + symbol + ": " + analystTargetPrice + ". Current price is "
            + currentPrice + "." +
            "\nAnalyst rating buy: " + analystRatingBuy + "." +
            "\nAnalyst rating strong buy: " + analystRatingStrongBuy + "." +
            "\nAnalyst rating hold: " + analystRatingHold + "." +
            "\nAnalyst rating sell: " + analystRatingSell + "." +
            "\nAnalyst rating strong sell: " + analystRatingStrongSell + ".";
  }

  private String getaPriceDescription(double analystTargetPrice, double currentPrice) {
    if (analystTargetPrice > currentPrice) {
      return aPrice = "There is an indication that the stock is undervalued.";
    } else if (analystTargetPrice < currentPrice) {
      return aPrice = "There is an indication that the stock is overvalued.";
    } else if (analystTargetPrice == currentPrice) {
      return aPrice = "There is an indication that the stock is at a fair price.";
    } else {
      return aPrice = "Something went wrong with the evaluation of the analyst target price compared to the current price.";
    }
  }

  private String getARBDescription(int analystRatingBuy) {
    if (analystRatingBuy > 5) {
      return aRB = "There is an indication that it is a good idea to buy the stock according to the analyst.";
    } else if (analystRatingBuy < 5) {
      return aRB = "There is an indication that it is a bad idea to buy the stock according to the analyst.";
    } else if (analystRatingBuy == 5) {
      return aRB = "There is an indication that it is an okay idea to buy the stock according to the analyst.";
    } else {
      return aRB = "Something went wrong with the analyst buy rating.";
    }
  }

  private String getaRSBDescription(int analystRatingStrongBuy) {
    if (analystRatingStrongBuy > 5) {
      return aRSB = "There is a strong indication that it is a good idea to buy the stock according to the analyst.";
    } else if (analystRatingStrongBuy < 5) {
      return aRSB = "There is a strong indication that it is a bad idea to buy the stock according to the analyst.";
    } else if (analystRatingStrongBuy == 5) {
      return aRSB = "There is a strong indication that it is an okay idea to buy the stock according to the analyst.";
    } else {
      return aRSB = "Something went wrong with the analyst strong buy rating.";
    }
  }

  private String getaRHDescription(int analystRatingHold) {
    if (analystRatingHold > 5) {
      return aRH = "There is an indication that it is a good idea to hold the stock according to the analyst.";
    } else if (analystRatingHold < 5) {
      return aRH = "There is an indication that it is a bad idea to hold the stock according to the analyst.";
    } else if (analystRatingHold == 5) {
      return aRH = "There is an indication that it is an okay idea to hold the stock according to the analyst.";
    } else {
      return aRH = "Something went wrong with the analyst hold rating.";
    }
  }

  private String getaRSDescription(int analystRatingSell) {
    if (analystRatingSell > 5) {
      return aRS = "There is an indication that it is a good idea to sell the stock according to the analyst.";
    } else if (analystRatingSell < 5) {
      return aRS = "There is an indication that it is a bad idea to sell the stock according to the analyst.";
    } else if (analystRatingSell == 5) {
      return aRS = "There is an indication that it is an okay idea to sell the stock according to the analyst.";
    } else {
      return aRS = "Something went wrong with the analyst sell rating.";
    }
  }

  private String getaRSSDescription(int analystRatingStrongSell) {
    if (analystRatingStrongSell > 5) {
      return aRSS = "There is a strong indication that it is a good idea to sell the stock according to the analyst.";
    } else if (analystRatingStrongSell < 5) {
      return aRSS = "There is a strong indication that it is a bad idea to sell the stock according to the analyst.";
    } else if (analystRatingStrongSell == 5) {
      return aRSS = "There is a strong indication that it is an okay idea to sell the stock according to the analyst.";
    } else {
      return aRSS = "Something went wrong with the analyst strong sell rating.";
    }
  }


  public String getElaborateDescription(double analystTargetPrice, double currentPrice,
      int analystRatingBuy, int analystRatingStrongBuy, int analystRatingHold,
      int analystRatingSell, int analystRatingStrongSell) {
    aPrice = getaPriceDescription(analystTargetPrice, currentPrice);
    aRB = getARBDescription(analystRatingBuy);
    aRSB = getaRSBDescription(analystRatingStrongBuy);
    aRH = getaRHDescription(analystRatingHold);
    aRS = getaRSDescription(analystRatingSell);
    aRSS = getaRSSDescription(analystRatingStrongSell);

    return elaborateDescription =
        aPrice + "\n" + aRB + "\n" + aRSB + "\n" + aRH + "\n" + aRS + "\n" + aRSS;
  }

  /**
   * Method for evaluating the stock parameters.
   */
  @Override
  public void evaluate() {
    getDescription(symbol, currentPrice, analystTargetPrice, analystRatingStrongBuy,
        analystRatingBuy, analystRatingHold, analystRatingSell, analystRatingStrongSell);
    getElaborateDescription(analystTargetPrice, currentPrice, analystRatingBuy,
        analystRatingStrongBuy, analystRatingHold, analystRatingSell, analystRatingStrongSell);
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
