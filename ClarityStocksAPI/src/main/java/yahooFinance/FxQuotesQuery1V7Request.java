package yahooFinance;

import java.util.List;

public class FxQuotesQuery1V7Request {
    List<FxQuote> results;
    String symbol;
    FxQuote result;
    public FxQuotesQuery1V7Request(String symbol) {
        this.symbol = symbol;
    }

    public List<FxQuote> getResult() {
        return this.results;
    }

    public FxQuote getSingleResult() {
        return this.result;
    }
}
