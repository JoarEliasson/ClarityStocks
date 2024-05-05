package alphaVantage;

public enum Function {
  TIME_SERIES_INTRADAY("TIME_SERIES_INTRADAY"),
  TIME_SERIES_DAILY("TIME_SERIES_DAILY"),
  TIME_SERIES_DAILY_ADJUSTED("TIME_SERIES_DAILY_ADJUSTED"),
  TIME_SERIES_WEEKLY("TIME_SERIES_WEEKLY"),
  TIME_SERIES_WEEKLY_ADJUSTED("TIME_SERIES_WEEKLY_ADJUSTED"),
  TIME_SERIES_MONTHLY("TIME_SERIES_MONTHLY"),
  TIME_SERIES_MONTHLY_ADJUSTED("TIME_SERIES_MONTHLY_ADJUSTED"),
  GLOBAL_QUOTE("GLOBAL_QUOTE"),
  SYMBOL_SEARCH("SYMBOL_SEARCH"),
  OVERVIEW("OVERVIEW"),
  INCOME_STATEMENT("INCOME_STATEMENT"),
  BALANCE_SHEET("BALANCE_SHEET"),
  CASH_FLOW("CASH_FLOW"),
  EARNINGS("EARNINGS"),
  LISTING_STATUS("LISTING_STATUS"),
  LISTING("LISTING"),
  SECTOR("SECTOR"),
  TECHNICAL_INDICATORS("TECHNICAL_INDICATORS"),
  SECTOR_PERFORMANCE("SECTOR_PERFORMANCE"),
  TOP_GAINERS_LOSERS("TOP_GAINERS_LOSERS"),
  MARKET_STATUS("MARKET_STATUS");

  private final String FUNCTION;
  private final String SUFFIX = "&apikey=";
  private final String BASE_URL = "https://www.alphavantage.co/query?function=";

  Function(String function) {
    this.FUNCTION = function;
  }

  public String getURL(String symbol, boolean fullOutputSize) {
    if (fullOutputSize) {
      return BASE_URL + FUNCTION + "&symbol=" + symbol + "&outputsize=full" + SUFFIX;
    } else if (symbol != null) {
      return BASE_URL + FUNCTION + "&symbol=" + symbol + SUFFIX;
    } else {
      return BASE_URL + FUNCTION + SUFFIX;
    }
  }
}
