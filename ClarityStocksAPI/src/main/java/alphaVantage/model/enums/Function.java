package alphaVantage.model.enums;

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
  SECTOR_PERFORMANCE("SECTOR_PERFORMANCE");

  private final String URL;

  Function(String function) {
    String baseURL = "https://www.alphavantage.co/query?function=";
    String suffix = "&symbol=";
    this.URL = baseURL + function + suffix;
  }

  public String getURL() {
    return URL;
  }
}