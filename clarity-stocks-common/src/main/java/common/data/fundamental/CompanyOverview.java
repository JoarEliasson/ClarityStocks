package common.data.fundamental;

/**
 * Encapsulates detailed financial and operational information about a publicly traded company. This
 * class includes attributes for basic company identification, financial metrics, stock performance
 * indicators, and investment ratios.
 * <p>
 * It serves as a data model for representing a company's stock overview, providing all the
 * available information that can be retrieved from the API response, including market
 * capitalization, earnings metrics, dividend data, and stock performance statistics over different
 * periods.
 */
public class CompanyOverview {

  /**
   * The unique symbol used to identify the company's stock on the stock exchange.
   */
  private String symbol;

  /**
   * The type of financial asset, such as Common Stock, indicating the specific class of security.
   */
  private String assetType;

  /**
   * The official name of the company.
   */
  private String name;

  /**
   * A brief description of the company, including its main line of business and operations.
   */
  private String description;

  /**
   * The Central Index Key (CIK) is a unique identifier assigned by the Securities and Exchange
   * Commission (SEC), to all entities that file financial statements with them.
   */
  private String CIK;

  /**
   * The name of the stock exchange where the company's stock is traded, such as the New York Stock
   * Exchange (NYSE) or NASDAQ.
   */
  private String exchange;

  /**
   * The currency in which the company's financial transactions are reported, e.g., USD for U.S.
   * dollars and SEK for Swedish krona.
   */
  private String currency;

  /**
   * The country where the company's headquarters are located.
   */
  private String country;

  /**
   * The sector represents the larger industry category to which the company belongs, such as
   * technology, healthcare, or finance.
   */
  private String sector;

  /**
   * The specific industry within the broader sector category in which the company operates, for
   * example, software development within the technology sector.
   */
  private String industry;

  /**
   * The physical address of the company's main headquarters.
   */
  private String address;

  /**
   * The end of the fiscal year for the company, typically presented in the format MM-DD, indicating
   * when the company closes its annual financial books.
   */
  private String fiscalYearEnd;

  /**
   * The end date of the most recent fiscal quarter, providing insight into the latest period for
   * which financial data is available.
   */
  private String latestQuarter;

  /**
   * The total market value of the company's outstanding shares, calculated as share price times the
   * number of outstanding shares. It provides a snapshot of the company's overall market value.
   */
  private long marketCapitalization;

  /**
   * Earnings Before Interest, Taxes, Depreciation, and Amortization (EBITDA) is a measure of a
   * company's overall financial performance.
   */
  private double EBITDA;

  /**
   * The Price to Earnings (P/E) ratio is a measure of the company's current share price relative to
   * its per-share earnings, indicating how much investors are willing to pay for a dollar of
   * earnings.
   */
  private double PERatio;

  /**
   * The Price/Earnings to Growth (PEG) Ratio is a stock's price-to-earnings (P/E) ratio divided by
   * the growth rate of its earnings for a specified time period. It provides a more nuanced view
   * than the P/E ratio by taking growth into account.
   */
  private double PEGRatio;

  /**
   * The book value per share is a measure of the net asset value of a company divided by the number
   * of shares outstanding. It represents the value of the company's assets on its books, giving
   * investors an idea of what the company would be worth if it were liquidated.
   */
  private double bookValue;

  /**
   * The annual dividends paid out per share, indicating the amount of money a company returns to
   * its shareholders out of its profits in the form of dividends.
   */
  private double dividendPerShare;

  /**
   * The dividend yield is a financial ratio that shows how much a company pays out in dividends
   * each year relative to its stock price. It is often expressed as a percentage.
   */
  private double dividendYield;

  /**
   * Earnings per share (EPS) is a measure of a company's profitability calculated as the company's
   * net income divided by the number of outstanding shares. It indicates how much money a company
   * makes for each share of its stock.
   */
  private double EPS;

  /**
   * Revenue per share for the trailing twelve months (TTM) is a measure of a company's revenue
   * divided by the number of outstanding shares, standardized to a 12-month period. Illustrating
   * the revenue-generating capability of a company on a per-share basis.
   */
  private double revenuePerShareTTM;

  /**
   * The profit margin is a measure of profitability calculated as net income divided by revenue. It
   * shows the percentage of revenue that constitutes net income.
   */
  private double profitMargin;

  /**
   * Operating margin for the trailing twelve months (TTM) is a profitability ratio that measures
   * what percentage of a company's revenue is left over after paying for variable costs of
   * production, such as wages and raw materials, before deducting interest and taxes.
   */
  private double operatingMarginTTM;

  /**
   * Return on assets (ROA) for the trailing twelve months (TTM) is a financial ratio that shows the
   * percentage of profit a company earns in relation to its overall resources (assets). It is a
   * measure of how efficiently a company uses its assets to generate profit.
   */
  private double returnOnAssetsTTM;

  /**
   * Return on equity (ROE) for the trailing twelve months (TTM) is a measure of the profitability
   * of a business in relation to the equity, showing how effectively a company uses the money
   * invested by its shareholders to generate profits.
   */
  private double returnOnEquityTTM;

  /**
   * Total revenue for the trailing twelve months (TTM) indicates the total income from all sources
   * over the past 12 months, excluding expenses.
   */
  private long revenueTTM;

  /**
   * Gross profit for the trailing twelve months (TTM) is the profit a company makes after deducting
   * the costs associated with making and selling its products, or the costs associated with
   * providing its services. It does not include indirect fixed costs like office expenses.
   */
  private long grossProfitTTM;

  /**
   * Diluted earnings per share (EPS) for the trailing twelve months (TTM) takes into account all
   * possible shares that could be created through options, warrants, convertible debt, or other
   * securities that could be converted into ordinary shares.
   */
  private double dilutedEPSTTM;

  /**
   * Year-over-year (YOY) quarterly earnings growth compares the earnings of a company between two
   * quarters (one year apart), indicating the percentage increase or decrease.
   */
  private double quarterlyEarningsGrowthYOY;

  /**
   * Year-over-year (YOY) quarterly revenue growth compares the revenue of a company between two
   * quarters (one year apart), showing the percentage increase or decrease in revenue.
   */
  private double quarterlyRevenueGrowthYOY;

  /**
   * The target price of the stock according to analysts, representing the future price level that
   * analysts expect the stock to reach within a certain time frame.
   */
  private double analystTargetPrice;

  private int analystRatingStrongBuy;
  private int analystRatingBuy;
  private int analystRatingHold;
  private int analystRatingSell;
  private int analystRatingStrongSell;

  /**
   * The trailing price to earnings (P/E) ratio measures the current share price relative to its
   * per-share earnings over the past 12 months, providing a snapshot of how a stock is valued in
   * the current market.
   */
  private double trailingPE;

  /**
   * The forward price to earnings (P/E) ratio is an estimate of the P/E ratio for the next fiscal
   * period, indicating how much investors are willing to pay now for anticipated future earnings
   * per share.
   */
  private double forwardPE;

  /**
   * The price to sales ratio (P/S) for the trailing twelve months (TTM) is a valuation ratio that
   * compares a company's stock price to its revenues, helping to determine the value placed on each
   * dollar of a company's sales or revenues.
   */
  private double priceToSalesRatioTTM;

  /**
   * The price to book (P/B) ratio compares a company's market value to its book value, indicating
   * how much shareholders are paying for the net assets of a company.
   */
  private double priceToBookRatio;

  /**
   * The enterprise value to revenue (EV/Revenue) ratio compares the total value of the company
   * (market capitalization plus debt minus cash) to its revenue, providing a measure of how much it
   * costs to buy the company's sales.
   */
  private double EVToRevenue;

  /**
   * The enterprise value to EBITDA (EV/EBITDA) ratio is a valuation metric used to compare the
   * value of a company, including debt and liabilities, to its earnings before interest, taxes,
   * depreciation, and amortization. It gives investors a clearer picture of a company's valuation
   * and profitability.
   */
  private double EVToEBITDA;

  /**
   * The beta value of the stock measures its volatility in comparison to the overall market; a beta
   * greater than 1 indicates that the stock is more volatile than the market, while a beta less
   * than 1 suggests it is less volatile.
   */
  private double beta;

  /**
   * The highest price of the stock in the last 52 weeks, indicating the maximum level at which the
   * stock has traded during this period.
   */
  private double week52High;

  /**
   * The lowest price of the stock in the last 52 weeks, showing the minimum level at which the
   * stock has traded during this time frame.
   */
  private double week52Low;

  /**
   * The 50-day moving average of the stock price, which is the average closing price of the stock
   * over the last 50 days, helping to smooth out price data by creating a constantly updated
   * average price.
   */
  private double movingAverage50;

  /**
   * The 200-day moving average of the stock price, similar to the 50-day moving average but over a
   * longer period, often used to determine the long-term trend of a stock.
   */
  private double movingAverage200;

  /**
   * The number of shares that are currently outstanding, which are shares that have been issued by
   * the company and are held by investors, including share blocks held by institutional investors
   * and restricted shares owned by the company’s officers and insiders.
   */
  private long sharesOutstanding;

  /**
   * The date of the last dividend payment, indicating when shareholders last received a dividend
   * from the company.
   */
  private String dividendDate;

  /**
   * The last ex-dividend date, which is the final date on which new shareholders are not entitled
   * to receive the next dividend. This date is crucial for determining which shareholders are
   * eligible to receive the company’s dividend.
   */
  private String exDividendDate;

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getAssetType() {
    return assetType;
  }

  public void setAssetType(String assetType) {
    this.assetType = assetType;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCIK() {
    return CIK;
  }

  public void setCIK(String CIK) {
    this.CIK = CIK;
  }

  public String getExchange() {
    return exchange;
  }

  public void setExchange(String exchange) {
    this.exchange = exchange;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getSector() {
    return sector;
  }

  public void setSector(String sector) {
    this.sector = sector;
  }

  public String getIndustry() {
    return industry;
  }

  public void setIndustry(String industry) {
    this.industry = industry;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getFiscalYearEnd() {
    return fiscalYearEnd;
  }

  public void setFiscalYearEnd(String fiscalYearEnd) {
    this.fiscalYearEnd = fiscalYearEnd;
  }

  public String getLatestQuarter() {
    return latestQuarter;
  }

  public void setLatestQuarter(String latestQuarter) {
    this.latestQuarter = latestQuarter;
  }

  public long getMarketCapitalization() {
    return marketCapitalization;
  }

  public void setMarketCapitalization(long marketCapitalization) {
    this.marketCapitalization = marketCapitalization;
  }

  public double getEBITDA() {
    return EBITDA;
  }

  public void setEBITDA(double EBITDA) {
    this.EBITDA = EBITDA;
  }

  public double getPERatio() {
    return PERatio;
  }

  public void setPERatio(double PERatio) {
    this.PERatio = PERatio;
  }

  public double getPEGRatio() {
    return PEGRatio;
  }

  public void setPEGRatio(double PEGRatio) {
    this.PEGRatio = PEGRatio;
  }

  public double getBookValue() {
    return bookValue;
  }

  public void setBookValue(double bookValue) {
    this.bookValue = bookValue;
  }

  public double getDividendPerShare() {
    return dividendPerShare;
  }

  public void setDividendPerShare(double dividendPerShare) {
    this.dividendPerShare = dividendPerShare;
  }

  public double getDividendYield() {
    return dividendYield;
  }

  public void setDividendYield(double dividendYield) {
    this.dividendYield = dividendYield;
  }

  public double getEPS() {
    return EPS;
  }

  public void setEPS(double EPS) {
    this.EPS = EPS;
  }

  public double getRevenuePerShareTTM() {
    return revenuePerShareTTM;
  }

  public void setRevenuePerShareTTM(double revenuePerShareTTM) {
    this.revenuePerShareTTM = revenuePerShareTTM;
  }

  public double getProfitMargin() {
    return profitMargin;
  }

  public void setProfitMargin(double profitMargin) {
    this.profitMargin = profitMargin;
  }

  public double getOperatingMarginTTM() {
    return operatingMarginTTM;
  }

  public void setOperatingMarginTTM(double operatingMarginTTM) {
    this.operatingMarginTTM = operatingMarginTTM;
  }

  public double getReturnOnAssetsTTM() {
    return returnOnAssetsTTM;
  }

  public void setReturnOnAssetsTTM(double returnOnAssetsTTM) {
    this.returnOnAssetsTTM = returnOnAssetsTTM;
  }

  public double getReturnOnEquityTTM() {
    return returnOnEquityTTM;
  }

  public void setReturnOnEquityTTM(double returnOnEquityTTM) {
    this.returnOnEquityTTM = returnOnEquityTTM;
  }

  public long getRevenueTTM() {
    return revenueTTM;
  }

  public void setRevenueTTM(long revenueTTM) {
    this.revenueTTM = revenueTTM;
  }

  public long getGrossProfitTTM() {
    return grossProfitTTM;
  }

  public void setGrossProfitTTM(long grossProfitTTM) {
    this.grossProfitTTM = grossProfitTTM;
  }

  public double getDilutedEPSTTM() {
    return dilutedEPSTTM;
  }

  public void setDilutedEPSTTM(double dilutedEPSTTM) {
    this.dilutedEPSTTM = dilutedEPSTTM;
  }

  public double getQuarterlyEarningsGrowthYOY() {
    return quarterlyEarningsGrowthYOY;
  }

  public void setQuarterlyEarningsGrowthYOY(double quarterlyEarningsGrowthYOY) {
    this.quarterlyEarningsGrowthYOY = quarterlyEarningsGrowthYOY;
  }

  public double getQuarterlyRevenueGrowthYOY() {
    return quarterlyRevenueGrowthYOY;
  }

  public void setQuarterlyRevenueGrowthYOY(double quarterlyRevenueGrowthYOY) {
    this.quarterlyRevenueGrowthYOY = quarterlyRevenueGrowthYOY;
  }

  public double getAnalystTargetPrice() {
    return analystTargetPrice;
  }

  public void setAnalystTargetPrice(double analystTargetPrice) {
    this.analystTargetPrice = analystTargetPrice;
  }

  public int getAnalystRatingStrongBuy() {
    return analystRatingStrongBuy;
  }

  public void setAnalystRatingStrongBuy(int analystRatingStrongBuy) {
    this.analystRatingStrongBuy = analystRatingStrongBuy;
  }

  public int getAnalystRatingBuy() {
    return analystRatingBuy;
  }

  public void setAnalystRatingBuy(int analystRatingBuy) {
    this.analystRatingBuy = analystRatingBuy;
  }

  public int getAnalystRatingHold() {
    return analystRatingHold;
  }

  public void setAnalystRatingHold(int analystRatingHold) {
    this.analystRatingHold = analystRatingHold;
  }

  public int getAnalystRatingSell() {
    return analystRatingSell;
  }

  public void setAnalystRatingSell(int analystRatingSell) {
    this.analystRatingSell = analystRatingSell;
  }

  public int getAnalystRatingStrongSell() {
    return analystRatingStrongSell;
  }

  public void setAnalystRatingStrongSell(int analystRatingStrongSell) {
    this.analystRatingStrongSell = analystRatingStrongSell;
  }

  public double getTrailingPE() {
    return trailingPE;
  }

  public void setTrailingPE(double trailingPE) {
    this.trailingPE = trailingPE;
  }

  public double getForwardPE() {
    return forwardPE;
  }

  public void setForwardPE(double forwardPE) {
    this.forwardPE = forwardPE;
  }

  public double getPriceToSalesRatioTTM() {
    return priceToSalesRatioTTM;
  }

  public void setPriceToSalesRatioTTM(double priceToSalesRatioTTM) {
    this.priceToSalesRatioTTM = priceToSalesRatioTTM;
  }

  public double getPriceToBookRatio() {
    return priceToBookRatio;
  }

  public void setPriceToBookRatio(double priceToBookRatio) {
    this.priceToBookRatio = priceToBookRatio;
  }

  public double getEVToRevenue() {
    return EVToRevenue;
  }

  public void setEVToRevenue(double EVToRevenue) {
    this.EVToRevenue = EVToRevenue;
  }

  public double getEVToEBITDA() {
    return EVToEBITDA;
  }

  public void setEVToEBITDA(double EVToEBITDA) {
    this.EVToEBITDA = EVToEBITDA;
  }

  public double getBeta() {
    return beta;
  }

  public void setBeta(double beta) {
    this.beta = beta;
  }

  public double getWeek52High() {
    return week52High;
  }

  public void setWeek52High(double week52High) {
    this.week52High = week52High;
  }

  public double getWeek52Low() {
    return week52Low;
  }

  public void setWeek52Low(double week52Low) {
    this.week52Low = week52Low;
  }

  public double getMovingAverage50() {
    return movingAverage50;
  }

  public void setMovingAverage50(double movingAverage50) {
    this.movingAverage50 = movingAverage50;
  }

  public double getMovingAverage200() {
    return movingAverage200;
  }

  public void setMovingAverage200(double movingAverage200) {
    this.movingAverage200 = movingAverage200;
  }

  public long getSharesOutstanding() {
    return sharesOutstanding;
  }

  public void setSharesOutstanding(long sharesOutstanding) {
    this.sharesOutstanding = sharesOutstanding;
  }

  public String getDividendDate() {
    return dividendDate;
  }

  public void setDividendDate(String dividendDate) {
    this.dividendDate = dividendDate;
  }

  public String getExDividendDate() {
    return exDividendDate;
  }

  public void setExDividendDate(String exDividendDate) {
    this.exDividendDate = exDividendDate;
  }

  @Override
  public String toString() {
    return String.format(
        "CompanyOverview [%s]:%n Asset Type: %s%n "
        + "Name: %s%n Description: %s%n CIK: %s%n Exchange: %s%n Currency: %s%n Country: %s%n "
            + "Sector: %s%n Industry: %s%n Address: %s%n Fiscal Year End: %s%n "
            + "Latest Quarter: %s%n Market Capitalization: %d%n EBITDA: %.2f%n "
            + "PE Ratio: %.2f%n PEG Ratio: %.2f%n Book Value: %.2f%n "
            + "Dividend Per Share: %.2f%n Dividend Yield: %.2f%n EPS: %.2f%n "
            + "Revenue Per Share TTM: %.2f%n Profit Margin: %.2f%n Operating Margin TTM: %.2f%n "
            + "Return on Assets TTM: %.2f%n Return on Equity TTM: %.2f%n Revenue TTM: %d%n "
            + "Gross Profit TTM: %d%n Diluted EPS TTM: %.2f%n "
            + "Quarterly Earnings Growth YOY: %.2f%n Quarterly Revenue Growth YOY: %.2f%n "
            + "Analyst Target Price: %.2f%n Analyst Rating (Strong Buy): %d%n"
            + "Analyst Rating (Buy): %d%n Analyst Rating (Hold): %d%n Analyst Rating (Sell): %d%n "
            + "Analyst Rating (Strong Sell): %d%n Trailing PE: %.2f%n Forward PE: %.2f%n "
            + "Price to Sales Ratio TTM: %.2f%n Price to Book Ratio: %.2f%n EV to Revenue: %.2f%n "
            + "EV to EBITDA: %.2f%n Beta: %.2f%n 52-Week High: %.2f%n 52-Week Low: %.2f%n "
            + "50-Day Moving Average: %.2f%n 200-Day Moving Average: %.2f%n "
            + "Shares Outstanding: %d%n Dividend Date: %s%n Ex-Dividend Date: %s%n",
        symbol, assetType, name, description, CIK, exchange, currency, country, sector, industry,
        address, fiscalYearEnd, latestQuarter, marketCapitalization, EBITDA, PERatio, PEGRatio,
        bookValue, dividendPerShare, dividendYield, EPS, revenuePerShareTTM, profitMargin,
        operatingMarginTTM, returnOnAssetsTTM, returnOnEquityTTM, revenueTTM, grossProfitTTM,
        dilutedEPSTTM, quarterlyEarningsGrowthYOY, quarterlyRevenueGrowthYOY, analystTargetPrice,
        analystRatingStrongBuy, analystRatingBuy, analystRatingHold, analystRatingSell,
        analystRatingStrongSell, trailingPE, forwardPE, priceToSalesRatioTTM, priceToBookRatio,
        EVToRevenue, EVToEBITDA, beta, week52High, week52Low, movingAverage50, movingAverage200,
        sharesOutstanding, dividendDate, exDividendDate
    );
  }
}
