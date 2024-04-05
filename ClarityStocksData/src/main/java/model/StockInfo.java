package model;

public class StockInfo {

  private String symbol;
  private String name;
  private String Exchange;

  public StockInfo(String symbol, String name, String Exchange) {
    this.symbol = symbol;
    this.name = name;
    this.Exchange = Exchange;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getName() {
    return name;
  }

  public String getExchange() {
    return Exchange;
  }
}
