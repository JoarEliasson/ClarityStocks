package alphaVantage;

import analysis.controller.PERatioEvaluator;
import analysis.model.PERatioEvaluation;
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

  public AlphaVantageStock getStock(String symbol) {
    CompanyOverview companyOverview = null;
    List<DailyDataPoint> timeSeries = null;
    try {
      companyOverview = getCompanyOverview(symbol);
      timeSeries = getTimeSeries(symbol, Interval.DAILY);
    } catch (Exception e) {
      e.printStackTrace();
    }
    assert companyOverview != null;
    assert timeSeries != null;
    for (DailyDataPoint dailyDataPoint : timeSeries) {
      System.out.println(dailyDataPoint);
    }
    PERatioEvaluation peRatioEvaluation = PERatioEvaluator.evaluatePriceEarningsRatio(symbol,
        companyOverview.name(), companyOverview.peRatio());

    PERatioEvaluator.evaluatePriceEarningsRatio(symbol, companyOverview.name(),
        companyOverview.peRatio());
    List<DailyDataPoint> filteredDailyDataPoints = filterByYear(timeSeries,
        new int[]{2022, 2023, 2024});
    return new AlphaVantageStock(companyOverview, filteredDailyDataPoints,
        peRatioEvaluation.toString());
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
}
