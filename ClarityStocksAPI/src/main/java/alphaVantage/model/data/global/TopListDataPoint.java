package alphaVantage.model.data.global;

/**
 * This class represents a data point in the top list of gainers, losers or most traded stocks.
 *
 * @author Joar Eliasson
 */
public class TopListDataPoint {

  private String symbol;
  private double priceDifference;
  private double changeAmount;
  private String changePercentage;
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

  public double getPriceDifference() {
    return priceDifference;
  }

  public void setPriceDifference(double priceDifference) {
    this.priceDifference = priceDifference;
  }

  public double getChangeAmount() {
    return changeAmount;
  }

  public void setChangeAmount(double changeAmount) {
    this.changeAmount = changeAmount;
  }

  public String getChangePercentage() {
    return changePercentage;
  }

  public void setChangePercentage(String changePercentage) {
    this.changePercentage = changePercentage;
  }

  public long getTradingVolume() {
    return tradingVolume;
  }

  public void setTradingVolume(long tradingVolume) {
    this.tradingVolume = tradingVolume;
  }
}
