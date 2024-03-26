package yahooFinance;

public class StockQuotesRequest {
    StockQuotesData stockQuotesData;
    String symbol;

    public StockQuotesRequest(String symbol) {
        this.symbol = symbol;
    }

    public StockQuotesData getSingleResult() {
        return this.stockQuotesData;
    }
}
