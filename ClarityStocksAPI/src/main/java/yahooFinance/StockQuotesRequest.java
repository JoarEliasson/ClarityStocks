package yahooFinance;

import java.util.List;

public class StockQuotesRequest {

  StockQuotesData stockQuotesData;
  String symbol;
  List<StockQuotesData> stockQuotesDataList;

  public StockQuotesRequest(String symbol) {
    this.symbol = symbol;
  }

  public StockQuotesData getSingleResult() {
    return this.stockQuotesData;
  }

  public List<StockQuotesData> getResult() {
    return this.stockQuotesDataList;
  }
}
