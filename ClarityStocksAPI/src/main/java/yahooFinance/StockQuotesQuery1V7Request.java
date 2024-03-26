package yahooFinance;

public class StockQuotesQuery1V7Request {
    String symbol;
    Stock stock;

    public StockQuotesQuery1V7Request(String symbol) {
        this.symbol = symbol;
    }

    public Stock getSingleResult() {
        return stock;
    }
}
