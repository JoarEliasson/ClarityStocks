package alphaVantage;

import analysis.model.BusinessPerformanceEvaluator;
import analysis.model.PERatioEvaluation;
import analysis.model.StockPriceInRelationToBusinessPerformance;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
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
  /** Method for evaluating the business performance of a company.Returns business performance
   * @author Olivia Svensson
   * */

  private BusinessPerformanceEvaluator getBusinessPerformanceEvaluation(String symbol) {
    FullStockOverview  fullStockOverview = new FullStockOverview();
    String name = fullStockOverview.getName();
    double ebidta = fullStockOverview.getEBITDA();
    double totalRevenue = fullStockOverview.getRevenueTTM();

    BusinessPerformanceEvaluator businessPerformanceEvaluator = new BusinessPerformanceEvaluator(name, ebidta, totalRevenue);
      return businessPerformanceEvaluator;
  }

  private StockPriceInRelationToBusinessPerformance stockPriceInRelationToBusinessPerformance() {
    FullStockOverview fullStockOverview = new FullStockOverview();
    String symbol = fullStockOverview.getName();
    double peRatio = fullStockOverview.getPERatio();
    String sector = fullStockOverview.getSector();
    StockPriceInRelationToBusinessPerformance stockPriceInRelationToBusinessPerformance = new StockPriceInRelationToBusinessPerformance(symbol, peRatio, sector);
    return stockPriceInRelationToBusinessPerformance;
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
    for (DailyDataPoint dailyDataPoint : timeSeries) {
      System.out.println(dailyDataPoint);
    }

    List<DailyDataPoint> filteredDailyDataPoints = filterByYear(timeSeries,
        new int[]{2022, 2023, 2024});
    return new AlphaVantageStock(companyOverview, filteredDailyDataPoints,
        (new PERatioEvaluation(symbol, companyOverview.getPERatio())));
  }

  private List<DailyDataPoint> filterByYear(List<DailyDataPoint> dailyDataPoints, int[] years) {
    List<DailyDataPoint> filteredDailyDataPoints = new ArrayList<>();
    for (DailyDataPoint dailyDataPoint : dailyDataPoints) {
      String dateString = dailyDataPoint.getDate();
      String[] dateParts = dateString.split("-");
      int dataYear = Integer.parseInt(dateParts[0]);
      for (int year : years) {
        if (dataYear == year) {
          System.out.println(dailyDataPoint);
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
