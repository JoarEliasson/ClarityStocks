package alphaVantage;

public record AlphaVantageStockInfo(String symbol, String name, String type, String region,
                                    String marketOpen, String marketClose, String timezone,
                                    String currency, double matchScore) {

  @Override
  public String toString() {
    return "StockInfo{" + "symbol=" + symbol + ", name=" + name + ", type=" + type + ", region="
        + region + ", marketOpen=" + marketOpen + ", marketClose=" + marketClose + ", timezone="
        + timezone + ", currency=" + currency + ", matchScore=" + matchScore + '}';
  }
}
