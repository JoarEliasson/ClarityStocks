package alphaVantage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Class for parsing JSON responses from Alpha Vantage API (company overview and series data).
 */

public class AlphaVantageParser {

  //Method for parsing JSON response containing company overview data. Sets values in CompanyOverview object. Returns overview object.
  public CompanyOverview parseCompanyOverview(String responseBody) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode root = null;
    CompanyOverview overview = null;
    try {
      root = mapper.readTree(responseBody);

      String symbol = root.path("Symbol").asText();
      String name = root.path("Name").asText();
      String description = root.path("Description").asText();
      String sector = root.path("Sector").asText();
      String industry = root.path("Industry").asText();
      double marketCapitalization = root.path("MarketCapitalization").asDouble();
      double peRatio = root.path("PERatio").asDouble();
      double earningsPerShare = root.path("EPS").asDouble();
      double bookValue = root.path("BookValue").asDouble();
      double dividendPerShare = root.path("DividendPerShare").asDouble();
      double dividendYield = root.path("DividendYield").asDouble();
      double revenuePerShareTTM = root.path("RevenuePerShareTTM").asDouble();
      double profitMargin = root.path("ProfitMargin").asDouble();
      double operatingMarginTTM = root.path("OperatingMarginTTM").asDouble();

      overview = new CompanyOverview(symbol, name, description, sector, industry,
          marketCapitalization, peRatio, earningsPerShare, bookValue, dividendPerShare,
          dividendYield, revenuePerShareTTM, profitMargin, operatingMarginTTM);

    } catch (IOException e) {
      e.printStackTrace();
    }
    return overview;
  }

  public FullStockOverview parseFullStockOverview(String body) {
    ObjectMapper mapper = new ObjectMapper();
    JsonNode root = null;
    FullStockOverview fullStockOverview = new FullStockOverview();
    try {
      root = mapper.readTree(body);

      fullStockOverview.setSymbol(root.path("Symbol").asText());
      fullStockOverview.setAssetType(root.path("AssetType").asText());
      fullStockOverview.setName(root.path("Name").asText());
      fullStockOverview.setDescription(root.path("Description").asText());
      fullStockOverview.setCIK(root.path("CIK").asText());
      fullStockOverview.setExchange(root.path("Exchange").asText());
      fullStockOverview.setCurrency(root.path("Currency").asText());
      fullStockOverview.setCountry(root.path("Country").asText());
      fullStockOverview.setSector(root.path("Sector").asText());
      fullStockOverview.setIndustry(root.path("Industry").asText());
      fullStockOverview.setAddress(root.path("Address").asText());
      fullStockOverview.setFiscalYearEnd(root.path("FiscalYearEnd").asText());
      fullStockOverview.setLatestQuarter(root.path("LatestQuarter").asText());
      fullStockOverview.setMarketCapitalization(root.path("MarketCapitalization").asLong());
      fullStockOverview.setEBITDA(root.path("EBITDA").asDouble());
      fullStockOverview.setPERatio(root.path("PERatio").asDouble());
      fullStockOverview.setPEGRatio(root.path("PEGRatio").asDouble());
      fullStockOverview.setBookValue(root.path("BookValue").asDouble());
      fullStockOverview.setDividendPerShare(root.path("DividendPerShare").asDouble());
      fullStockOverview.setDividendYield(root.path("DividendYield").asDouble());
      fullStockOverview.setEPS(root.path("EPS").asDouble());
      fullStockOverview.setRevenuePerShareTTM(root.path("RevenuePerShareTTM").asDouble());
      fullStockOverview.setProfitMargin(root.path("ProfitMargin").asDouble());
      fullStockOverview.setOperatingMarginTTM(root.path("OperatingMarginTTM").asDouble());
      fullStockOverview.setReturnOnAssetsTTM(root.path("ReturnOnAssetsTTM").asDouble());
      fullStockOverview.setReturnOnEquityTTM(root.path("ReturnOnEquityTTM").asDouble());
      fullStockOverview.setRevenueTTM(root.path("RevenueTTM").asLong());
      fullStockOverview.setGrossProfitTTM(root.path("GrossProfitTTM").asLong());
      fullStockOverview.setDilutedEPSTTM(root.path("DilutedEPSTTM").asDouble());
      fullStockOverview.setQuarterlyEarningsGrowthYOY(
          root.path("QuarterlyEarningsGrowthYOY").asDouble());
      fullStockOverview.setQuarterlyRevenueGrowthYOY(
          root.path("QuarterlyRevenueGrowthYOY").asDouble());
      fullStockOverview.setAnalystTargetPrice(root.path("AnalystTargetPrice").asDouble());
      fullStockOverview.setTrailingPE(root.path("TrailingPE").asDouble());
      fullStockOverview.setForwardPE(root.path("ForwardPE").asDouble());
      fullStockOverview.setPriceToSalesRatioTTM(root.path("PriceToSalesRatioTTM").asDouble());
      fullStockOverview.setPriceToBookRatio(root.path("PriceToBookRatio").asDouble());
      fullStockOverview.setEVToRevenue(root.path("EVToRevenue").asDouble());
      fullStockOverview.setEVToEBITDA(root.path("EVToEBITDA").asDouble());
      fullStockOverview.setBeta(root.path("Beta").asDouble());
      fullStockOverview.setWeek52High(root.path("52WeekHigh").asDouble());
      fullStockOverview.setWeek52Low(root.path("52WeekLow").asDouble());
      fullStockOverview.setDay50MovingAverage(root.path("50DayMovingAverage").asDouble());
      fullStockOverview.setDay200MovingAverage(root.path("200DayMovingAverage").asDouble());
      fullStockOverview.setSharesOutstanding(root.path("SharesOutstanding").asLong());
      fullStockOverview.setDividendDate(root.path("DividendDate").asText());
      fullStockOverview.setExDividendDate(root.path("ExDividendDate").asText());

    } catch (IOException e) {
      e.printStackTrace();
    }
    return fullStockOverview;
  }

  //Method for parsing JSON response containing time data. Iterates over each entry, extracts data and creates a DataPoint object for each entry. Returns list of DataPoint objects.
  public List<DailyDataPoint> parseTimeSeries(String responseBody) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    List<DailyDataPoint> dailyDataPoints = new ArrayList<>();

    try {
      JsonNode root = mapper.readTree(responseBody);
      JsonNode timeSeries = root.path("Time Series (Daily)");
      if (!timeSeries.isMissingNode()) {
        Iterator<Map.Entry<String, JsonNode>> fields = timeSeries.fields();
        while (fields.hasNext()) {
          Map.Entry<String, JsonNode> entry = fields.next();
          String date = entry.getKey();
          double open = entry.getValue().get("1. open").asDouble();
          double high = entry.getValue().get("2. high").asDouble();
          double low = entry.getValue().get("3. low").asDouble();
          double close = entry.getValue().get("4. close").asDouble();
          long volume = entry.getValue().get("5. volume").asLong();

          DailyDataPoint dailyDataPoint = new DailyDataPoint(date, open, high, low, close, volume);
          dailyDataPoints.add(dailyDataPoint);
        }
      } else {
        System.out.println("No daily time series data found.");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return dailyDataPoints;
  }


  public List<AlphaVantageStockInfo> parseSearchResults(String body) {
    List<AlphaVantageStockInfo> searchResults = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();
    try {
      JsonNode root = mapper.readTree(body);
      JsonNode bestMatches = root.path("bestMatches");
      if (!bestMatches.isMissingNode()) {
        Iterator<JsonNode> elements = bestMatches.elements();
        while (elements.hasNext()) {
          JsonNode match = elements.next();
          String symbol = match.path("1. symbol").asText();
          String name = match.path("2. name").asText();
          String type = match.path("3. type").asText();
          String region = match.path("4. region").asText();
          String marketOpen = match.path("5. marketOpen").asText();
          String marketClose = match.path("6. marketClose").asText();
          String timezone = match.path("7. timezone").asText();
          String currency = match.path("8. currency").asText();
          double matchScore = match.path("9. matchScore").asDouble();
          AlphaVantageStockInfo alphaVantageStockInfo = new AlphaVantageStockInfo(symbol, name,
              type, region, marketOpen, marketClose, timezone, currency, matchScore);
          searchResults.add(alphaVantageStockInfo);
        }
      } else {
        System.out.println("No search results found.");
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return searchResults;
  }
}
