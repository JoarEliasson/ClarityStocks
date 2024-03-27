package yahooFinance;

import java.util.List;

public class FxQuotesRequest {
    List<FxQuote> results;
    String symbol;
    FxQuote result;
    public FxQuotesRequest(String symbol) {
    this.symbol = symbol;
    }

    public List<FxQuote> getResult() {
        return this.results;
    }

    public FxQuote getSingleResult() {
        return this.result;
    }
}
