package yahooFinance;

import java.util.Calendar;
import java.util.List;

public class HistSplitsRequest {

  public static final Calendar DEFAULT_FROM = null;
  public static final Calendar DEFAULT_TO = null;
  String symbol;
  Calendar from;
  Calendar to;
  List<HistoricalSplit> historicalSplitsList;

  public HistSplitsRequest(String symbol, Calendar from, Calendar to) {
    this.symbol = symbol;
    this.from = from;
    this.to = to;
  }

  public List<HistoricalSplit> getResult() {
    return this.historicalSplitsList;
  }
}
