package analysis.model;

/** Class for evaluating the analyst prediction of a stock.
 * The analyst grades the evaluations on a scale of 0 to 10.
 *
 * @author Olivia Svensson
 * */
public class AnalystPrediction {
    String symbol;
    String description;
double analystTargetPrice;
double currentPrice;
int analystRatingStrongBuy;
int analystRatingBuy;
int analystRatingHold;
int analystRatingSell;
int analystRatingStrongSell;
String dPrice;
String aRB;
String aRSB;
String aRH;
String aRS;
String aRSS;


    public AnalystPrediction(String symbol, double currentPrice, double analystTargetPrice, int analystRatingStrongBuy, int analystRatingBuy, int analystRatingHold, int analystRatingSell, int analystRatingStrongSell) {
    this.symbol = symbol;
    this.currentPrice = currentPrice;
    this.analystRatingBuy = analystRatingBuy;
    this.analystRatingStrongBuy = analystRatingStrongBuy;
    this.analystRatingHold = analystRatingHold;
    this.analystRatingSell = analystRatingSell;
    this.analystRatingStrongSell = analystRatingStrongSell;
    this.analystTargetPrice = analystTargetPrice;
    }

    private String getDescription(String symbol, double currentPrice, double analystTargetPrice, int analystRatingStrongBuy, int analystRatingBuy, int analystRatingHold, int analystRatingSell, int analystRatingStrongSell) {
        return description = "Analyst target price for " + symbol + ": " + analystTargetPrice + ". Current price is " + currentPrice + "." +
                "\nAnalyst rating buy: " + analystRatingBuy +  "." +
                "\nAnalyst rating strong buy: " + analystRatingStrongBuy +  "." +
                "\nAnalyst rating hold: " + analystRatingHold +  "." +
                "\nAnalyst rating sell: " + analystRatingSell +  "." +
                "\nAnalyst rating strong sell: " + analystRatingStrongSell + ".";
    }

    private String getAnalystVsCurrentPrice(double analystTargetPrice, double currentPrice) {
        if(analystTargetPrice > currentPrice) {
            return "There is an indication that the stock is undervalued.";
        } else if (analystTargetPrice < currentPrice) {
            return "There is an indication that the stock is overvalued.";
        } else if(analystTargetPrice == currentPrice) {
            return "There is an indication that the stock is at a fair price.";
        } else {
            return "Something went wrong with the evaluation of the analyst target price compared to the current price.";
        }
    }

    private String get

}
