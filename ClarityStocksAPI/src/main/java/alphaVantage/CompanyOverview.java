package alphaVantage;

/**
 * Class for representing details of stock company retrieved from Alpa Vantage API.
 */

public record CompanyOverview(String symbol, String name, String description, String sector,
                              String industry, double marketCapitalization, double peRatio,
                              double earningsPerShare, double bookValue, double dividendPerShare,
                              double dividendYield, double revenuePerShareTTM, double profitMargin,
                              double operatingMarginTTM) {

}
