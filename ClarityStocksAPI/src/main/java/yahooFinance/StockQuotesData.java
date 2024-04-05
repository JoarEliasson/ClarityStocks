package yahooFinance;

public class StockQuotesData {

  StockQuote stockQuote;
  StockStats stockStats;
  StockDividend stockDividend;
  Stock stock;

  public StockQuote getQuote() {
    return this.stockQuote;
  }

  public StockStats getStats() {
    return this.stockStats;
  }

  public StockDividend getDividend() {
    return this.stockDividend;
  }

  public Stock getStock() {
    return this.stock;
  }
}
