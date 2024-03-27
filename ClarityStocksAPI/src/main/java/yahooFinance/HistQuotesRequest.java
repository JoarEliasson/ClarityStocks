package yahooFinance;

import java.util.Calendar;
import java.util.List;

public class HistQuotesRequest {
    public static final Interval DEFAULT_INTERVAL = null;
    String symbol;
    Calendar to;
    Calendar from;
    Interval interval;
    List<HistoricalQuote> historicalQuoteList;
    public static final Calendar DEFAULT_FROM = null;
    public static final Calendar DEFAULT_TO = null;

    public HistQuotesRequest(String symbol, Calendar from, Calendar to, Interval interval) {
        this.from = from;
        this.to = to;
        this.symbol = symbol;
        this.interval = interval;
    }

    public List<HistoricalQuote> getResult() {
        return this.historicalQuoteList;
    }
}
