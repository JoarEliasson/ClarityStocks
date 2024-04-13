package model;

public class AlphaVantageListing {

  private String symbol;
  private String name;
  private String exchange;

  public AlphaVantageListing() {
  }

  public AlphaVantageListing(String symbol, String name, String exchange) {
    this.symbol = symbol;
    this.name = name;
    this.exchange = exchange;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getName() {
    if (name.length() > 50) {
      return name.substring(0, 50) + "...";
    }
    return name;
  }

  public String getExchange() {
    return exchange;
  }
}
