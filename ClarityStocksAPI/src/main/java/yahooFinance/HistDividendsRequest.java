package yahooFinance;

import java.util.Calendar;
import java.util.List;

public class HistDividendsRequest {

  public static final Calendar DEFAULT_FROM = null;
  public static final Calendar DEFAULT_TO = null;

  String symbol;
  Calendar from;
  Calendar to;
  List<HistoricalDividend> historicalDividendList;

  public HistDividendsRequest(String symbol, Calendar from, Calendar to) {
    this.symbol = symbol;
    this.to = to;
    this.from = from;
  }

  public List<HistoricalDividend> getResult() {
    return this.historicalDividendList;
  }
}
