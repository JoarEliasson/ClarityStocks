package alphaVantage;

/**
 * Class for representing details of stock company retrieved from Alpa Vantage API.
 * */

public class CompanyOverview {

    private String symbol;
    private String name;
    private String description;
    private String sector;
    private String industry;
    private double marketCapitalization;
    private double peRatio;
    private double earningsPerShare;
    private double bookValue;
    private double dividendPerShare;
    private double dividendYield;
    private double revenuePerShareTTM;
    private double profitMargin;
    private double operatingMarginTTM;

    public CompanyOverview(String symbol, String name, String description, String sector, String industry, double marketCapitalization, double peRatio, double earningsPerShare, double bookValue, double dividendPerShare, double dividendYield, double revenuePerShareTTM, double profitMargin, double operatingMarginTTM) {
        this.symbol = symbol;
        this.name = name;
        this.description = description;
        this.sector = sector;
        this.industry = industry;
        this.marketCapitalization = marketCapitalization;
        this.peRatio = peRatio;
        this.earningsPerShare = earningsPerShare;
        this.bookValue = bookValue;
        this.dividendPerShare = dividendPerShare;
        this.dividendYield = dividendYield;
        this.revenuePerShareTTM = revenuePerShareTTM;
        this.profitMargin = profitMargin;
        this.operatingMarginTTM = operatingMarginTTM;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSector() {
        return sector;
    }

    public String getIndustry() {
        return industry;
    }

    public double getMarketCapitalization() {
        return marketCapitalization;
    }

    public double getPeRatio() {
        return peRatio;
    }

    public double getEarningsPerShare() {
        return earningsPerShare;
    }

    public double getBookValue() {
        return bookValue;
    }

    public double getDividendPerShare() {
        return dividendPerShare;
    }

    public double getDividendYield() {
        return dividendYield;
    }

    public double getRevenuePerShareTTM() {
        return revenuePerShareTTM;
    }

    public double getProfitMargin() {
        return profitMargin;
    }

    public double getOperatingMarginTTM() {
        return operatingMarginTTM;
    }
}
