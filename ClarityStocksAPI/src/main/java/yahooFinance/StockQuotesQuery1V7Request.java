package yahooFinance;

import java.util.List;

public class StockQuotesQuery1V7Request {
    String symbol;
    Stock stock;

    List<Stock> stockList;
    public StockQuotesQuery1V7Request(String symbol) {
        this.symbol = symbol;
    }

    public Stock getSingleResult() {
        return stock;
    }

    public List<Stock> getResult() {
        return this.stockList;
    }
}
