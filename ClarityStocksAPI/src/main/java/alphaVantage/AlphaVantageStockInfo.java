package alphaVantage;

public class AlphaVantageStockInfo {

  private final String symbol;
  private final String name;
  private final String type;
  private final String region;
  private final String marketOpen;
  private final String marketClose;
  private final String timezone;
  private final String currency;
  private final double matchScore;

  public AlphaVantageStockInfo(String symbol, String name, String type, String region,
      String marketOpen, String marketClose, String timezone, String currency, double matchScore) {
    this.symbol = symbol;
    this.name = name;
    this.type = type;
    this.region = region;
    this.marketOpen = marketOpen;
    this.marketClose = marketClose;
    this.timezone = timezone;
    this.currency = currency;
    this.matchScore = matchScore;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getRegion() {
    return region;
  }

  public String getMarketOpen() {
    return marketOpen;
  }

  public String getMarketClose() {
    return marketClose;
  }

  public String getTimezone() {
    return timezone;
  }

  public String getCurrency() {
    return currency;
  }

  public double getMatchScore() {
    return matchScore;
  }

  @Override
  public String toString() {
    return "StockInfo{" + "symbol=" + symbol + ", name=" + name + ", type=" + type + ", region="
        + region + ", marketOpen=" + marketOpen + ", marketClose=" + marketClose + ", timezone="
        + timezone + ", currency=" + currency + ", matchScore=" + matchScore + '}';
  }
}
