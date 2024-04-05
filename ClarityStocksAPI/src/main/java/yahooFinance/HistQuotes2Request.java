package yahooFinance;

import java.util.Calendar;
import java.util.List;

public class HistQuotes2Request {

  String symbol;
  Calendar to;
  Calendar from;
  Interval interval;
  List<HistoricalQuote> historicalQuoteList;

  public HistQuotes2Request(String symbol, Calendar from, Calendar to, Interval interval) {
    this.to = to;
    this.from = from;
    this.symbol = symbol;
    this.interval = interval;

  }

  public List<HistoricalQuote> getResult() {
    return historicalQuoteList;
  }
}
