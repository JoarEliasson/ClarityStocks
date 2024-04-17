package alphaVantage;

import analysis.model.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlphaVantageClient {

  private final String apiKey;
  private final HttpClient httpClient;
  private final AlphaVantageParser parser = new AlphaVantageParser();

  //Constructor. Creates instance of httpClient
  public AlphaVantageClient(String apiKey) {
    this.apiKey = apiKey;
    this.httpClient = HttpClient.newHttpClient();
  }

  public static void main(String[] args) {
    AlphaVantageClient client = new AlphaVantageClient("YKB1S8EYZ61LDH9B");
    List<AlphaVantageStockInfo> searchResults = client.searchEndpoint("");
    for (AlphaVantageStockInfo alphaVantageStockInfo : searchResults) {
      System.out.println(alphaVantageStockInfo);
    }

  }


  /** Method for displaying the stocks 52-week high and low. Returns an object of HighAndLow class
   * @author Olivia Svensson
   * */

  private HighAndLow highAndLow() {
    FullStockOverview fullStockOverview = new FullStockOverview();
    String symbol = fullStockOverview.getSymbol();
    double high = fullStockOverview.getWeek52High();
    double low = fullStockOverview.getWeek52Low();
    HighAndLow highAndLow = new HighAndLow(symbol, high, low);
    String description = highAndLow.getDescription();
    return highAndLow;
  }

  /** Method for evaluating the business performance of a company.Returns business performance object
   * @author Olivia Svensson
   * */

  private BusinessPerformanceEvaluator getBusinessPerformanceEvaluation() {
    FullStockOverview  fullStockOverview = new FullStockOverview();
    String name = fullStockOverview.getName();
    double ebidta = fullStockOverview.getEBITDA();
    double totalRevenue = fullStockOverview.getRevenueTTM();

    BusinessPerformanceEvaluator businessPerformanceEvaluator = new BusinessPerformanceEvaluator(name, ebidta, totalRevenue);
      return businessPerformanceEvaluator;
  }

  /** Method for getting dividend evaluation depending on fiscal year, also gets dividend yield depending on fiscal year. Returns dividend evaluation timing object
   * @author Olivia Svensson
   * */
  /*
  private DividendEvaluationTiming dividendEvaluationTiming(int year) {
      FullStockOverview fullStockOverview = new FullStockOverview();

    String symbol = fullStockOverview.getSymbol();
    int fiscalYear = year;
    double dividendPerShare = fullStockOverview.getDividendPerShare();
    double dividendYield = fullStockOverview.getDividendYield();
    long fiscalYearEnd = Long.parseLong(fullStockOverview.getFiscalYearEnd());

    DividendEvaluationTiming dividendEvaluationTiming = new DividendEvaluationTiming(symbol, fiscalYear, dividendYield, dividendYield, fiscalYearEnd);
    return dividendEvaluationTiming;
  }

   */

  /** Method for evaluating stock price in relation to business performance. Returns stock price in relation to business performance object
   * @author Olivia Svensson
   * */
  private StockPriceInRelationToBusinessPerformance stockPriceInRelationToBusinessPerformance() {
    FullStockOverview fullStockOverview = new FullStockOverview();
    String symbol = fullStockOverview.getName();
    double peRatio = fullStockOverview.getPERatio();
    String sector = fullStockOverview.getSector();
    StockPriceInRelationToBusinessPerformance stockPriceInRelationToBusinessPerformance = new StockPriceInRelationToBusinessPerformance(symbol, peRatio, sector);
    return stockPriceInRelationToBusinessPerformance;
  }

/** Method for analysing the stock price according to the golden cross method.
 * @author Olivia Svensson
 * */
  private GoldenCross goldenCross() {
    FullStockOverview fullStockOverview = new FullStockOverview();
    String symbol = fullStockOverview.getSymbol();
    double ma50 = fullStockOverview.getDay50MovingAverage();
    double ma200 = fullStockOverview.getDay200MovingAverage();
    GoldenCross goldenCross = new GoldenCross(symbol, ma50, ma200);
    String description = goldenCross.getDesciption();
    return goldenCross;
  }
/** Method for analyst prediction of stock.
 * Can't currently find the analyst ratings which are presented here  https://www.alphavantage.co/query?function=OVERVIEW&symbol=IBM&apikey=demo.
 * Method will be updated in the future.
 * There are two descriptions, one more elaborate and one which is more straight forward.
 * @author Olivia Svensson
 * */
  private AnalystPrediction analystPrediction() {
    FullStockOverview fullStockOverview = new FullStockOverview();
    String symbol = fullStockOverview.getSymbol();
    String description = "";
    String elaborateDescription = "";
    double analystTargetPrice = fullStockOverview.getAnalystTargetPrice();
    double currentPrice = 100; //can't find the current price.
    int analystRatingStrongBuy =  5;//can't find
    int analystRatingBuy = 5;  //can't find
    int analystRatingHold = 5;  //can't find
    int analystRatingSell = 5;  //can't find
    int analystRatingStrongSell = 5;  //can't find
    AnalystPrediction analystPrediction = new AnalystPrediction(symbol, currentPrice, analystTargetPrice, analystRatingStrongBuy, analystRatingBuy, analystRatingHold, analystRatingSell, analystRatingStrongSell);
    description = analystPrediction.getDescription(symbol, currentPrice, analystTargetPrice, analystRatingStrongBuy,analystRatingBuy,analystRatingHold, analystRatingSell, analystRatingStrongSell);
    elaborateDescription = analystPrediction.getElaborateDescription(analystTargetPrice, currentPrice, analystRatingBuy, analystRatingStrongBuy, analystRatingHold, analystRatingSell, analystRatingStrongSell);

    return analystPrediction;
  }

  public AlphaVantageStock getStock(String symbol) {
    FullStockOverview companyOverview = null;
    List<DailyDataPoint> timeSeries = null;
    try {
      companyOverview = getFullStockOverview(symbol);
      timeSeries = getTimeSeries(symbol, Interval.DAILY);
    } catch (Exception e) {
      e.printStackTrace();
    }
    assert companyOverview != null;
    assert timeSeries != null;

    List<DailyDataPoint> filteredDailyDataPoints = filterByYear(timeSeries,
        new int[]{2022, 2023, 2024});

    PERatioEvaluation peRatioEvaluation = new PERatioEvaluation(symbol, companyOverview.getPERatio());
    BusinessPerformanceEvaluation performanceEvaluation = new BusinessPerformanceEvaluation(symbol, companyOverview.getEBITDA(), companyOverview.getRevenueTTM());
    int currentYear = LocalDate.now().getYear();
    DividendEvaluationTiming dividendEvaluationTiming = new DividendEvaluationTiming(symbol, currentYear, companyOverview.getDividendPerShare(), companyOverview.getDividendYield(), companyOverview.getFiscalYearEnd());
    GoldenCross goldenCross = new GoldenCross(symbol, companyOverview.getDay50MovingAverage(), companyOverview.getDay200MovingAverage());
    HighAndLow highLow = new HighAndLow(symbol, companyOverview.getWeek52High(), companyOverview.getWeek52Low());
    StockPriceInRelationToBusinessPerformance priceInRelationToBusinessPerformance = new StockPriceInRelationToBusinessPerformance(symbol, companyOverview.getPERatio(),
        companyOverview.getSector());
    return new AlphaVantageStock(companyOverview, filteredDailyDataPoints, peRatioEvaluation, performanceEvaluation, dividendEvaluationTiming, goldenCross, highLow, priceInRelationToBusinessPerformance);
  }

  private List<DailyDataPoint> filterByYear(List<DailyDataPoint> dailyDataPoints, int[] years) {
    List<DailyDataPoint> filteredDailyDataPoints = new ArrayList<>();
    for (DailyDataPoint dailyDataPoint : dailyDataPoints) {
      String dateString = dailyDataPoint.getDate();
      String[] dateParts = dateString.split("-");
      int dataYear = Integer.parseInt(dateParts[0]);
      for (int year : years) {
        if (dataYear == year) {
          filteredDailyDataPoints.add(dailyDataPoint);
        }
      }
    }
    return filteredDailyDataPoints;
  }

  public CompanyOverview getCompanyOverview(String symbol) {
    String url = String.format(
        "https://www.alphavantage.co/query?function=OVERVIEW&symbol=%s&apikey=%s", symbol, apiKey);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .timeout(Duration.ofMinutes(1))
        .GET()
        .build();
    try {
      HttpResponse<String> response = httpClient.send(request,
          HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        return parser.parseCompanyOverview(response.body());
      } else {
        throw new RuntimeException(
            "Failed to fetch data: HTTP status code " + response.statusCode());
      }
    } catch (Exception e) {
      throw new RuntimeException("Error fetching company overview", e);
    }
  }

  public FullStockOverview getFullStockOverview(String symbol) {
    String url = String.format(
        "https://www.alphavantage.co/query?function=OVERVIEW&symbol=%s&apikey=%s", symbol, apiKey);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .timeout(Duration.ofMinutes(1))
        .GET()
        .build();
    try {
      HttpResponse<String> response = httpClient.send(request,
          HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        return parser.parseFullStockOverview(response.body());
      } else {
        throw new RuntimeException(
            "Failed to fetch data: HTTP status code " + response.statusCode());
      }
    } catch (Exception e) {
      throw new RuntimeException("Error fetching company overview", e);
    }
  }

  public List<DailyDataPoint> getTimeSeries(String symbol, Interval interval) throws Exception {

    String urlString =
        "https://www.alphavantage.co/query?function=" + interval.getUrlParameter() + "&symbol="
            + symbol + "&outputsize=full&apikey=" + apiKey;
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(urlString))
        .build();
    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    return parser.parseTimeSeries(response.body());
  }

  public List<AlphaVantageStockInfo> searchEndpoint(String query) {
    String url = String.format(
        "https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=%s&apikey=%s", query,
        apiKey);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .timeout(Duration.ofMinutes(1))
        .GET()
        .build();
    try {
      HttpResponse<String> response = httpClient.send(request,
          HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        return parser.parseSearchResults(response.body());
      } else {
        throw new RuntimeException(
            "Failed to fetch data: HTTP status code " + response.statusCode());
      }
    } catch (Exception e) {
      throw new RuntimeException("Error fetching search results", e);
    }
  }

  public List<String> getIncomeStatement(String symbol) {
    String url = String.format(
        "https://www.alphavantage.co/query?function=INCOME_STATEMENT&symbol=%s&apikey=%s", symbol, apiKey);
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .timeout(Duration.ofMinutes(1))
        .GET()
        .build();
    try {
      HttpResponse<String> response = httpClient.send(request,
          HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        return parser.parseIncomeStatement(response.body());
      } else {
        throw new RuntimeException(
            "Failed to fetch data: HTTP status code " + response.statusCode());
      }
    } catch (Exception e) {
      throw new RuntimeException("Error fetching company overview", e);
    }
  }

}
