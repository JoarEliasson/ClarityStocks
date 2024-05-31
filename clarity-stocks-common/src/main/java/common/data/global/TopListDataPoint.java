package common.data.global;

import java.io.Serial;
import java.io.Serializable;

/**
 * Class for storing top list data points.
 * <p>
 * This class holds data for a single stock in a top list.
 * </p>
 *
 * <ul>
 *   <li>{@code symbol} - The stock symbol.</li>
 *   <li>{@code currentPrice} - The current price of the stock.</li>
 *   <li>{@code changeAmount} - The change in price of the stock.</li>
 *   <li>{@code changePercentage} - The percentage change in price of the stock.</li>
 *   <li>{@code tradingVolume} - The trading volume of the stock.</li>
 * </ul>
 *
 * @see DailyTopLists
 * @see GlobalMarketInfo
 * @see MarketStatus
 *
 * @author Joar Eliason
 */
public class TopListDataPoint implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private String symbol;
  private double currentPrice;
  private double changeAmount;
  private double changePercentage;
  private long tradingVolume;

  public TopListDataPoint(String symbol) {
    this.symbol = symbol;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public double getCurrentPrice() {
    return currentPrice;
  }

  public void setCurrentPrice(double currentPrice) {
    this.currentPrice = currentPrice;
  }

  public double getChangeAmount() {
    return changeAmount;
  }

  public void setChangeAmount(double changeAmount) {
    this.changeAmount = changeAmount;
  }

  public double getChangePercentage() {
    return changePercentage;
  }

  public void setChangePercentage(String changePercentageString) {
    changePercentageString = changePercentageString.replace("%", "");
    try {
      double percentageDouble = Double.parseDouble(changePercentageString);
      this.changePercentage = Math.round(percentageDouble * 10.0) / 10.0;

    } catch (NumberFormatException e) {
      System.err.println("Invalid number format: " + changePercentageString);
    }
  }

  public long getTradingVolume() {
    return tradingVolume;
  }

  public void setTradingVolume(long tradingVolume) {
    this.tradingVolume = tradingVolume;
  }
}
