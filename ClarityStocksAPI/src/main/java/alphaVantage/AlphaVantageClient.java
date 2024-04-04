package alphaVantage;

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

  //Constructor. Creates instanse of httpClient
  public AlphaVantageClient(String apiKey) {
    this.apiKey = apiKey;
    this.httpClient = HttpClient.newHttpClient();
  }

  //Method for retrieving company data. Creates a url for fetching data. Sends http GET request, retrieves and parses the response.
  public CompanyOverview getCompanyOverview(String symbol) throws Exception {
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

  public FullStockOverview getFullStockOverview(String symbol) throws Exception {
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

  //Method for getting time data. Constructs an url for fetching data. Sends http GET request. Parses response.
  //need for new http client? Why not use httpclient instead?
  public List<DailyDataPoint> getTimeSeries(String symbol, Interval interval) throws Exception {
    HttpClient client = HttpClient.newHttpClient();

    String urlString =
        "https://www.alphavantage.co/query?function=" + interval.getUrlParameter() + "&symbol="
            + symbol + "&apikey=" + apiKey;
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(urlString))
        .build();
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
    return parser.parseTimeSeries(response.body());
  }

  //Method for filtering retrieved data depending on month and year.
  public List<DailyDataPoint> getFilteredSeries(int month, int year) {

    if (month == -1) {
      return getFilteredSeries(year);
    }

    List<DailyDataPoint> timeSeries = new ArrayList<>();
    List<DailyDataPoint> unfilteredTimeSeries = new ArrayList<>();
    try {
      unfilteredTimeSeries = getTimeSeries("AAPL", Interval.DAILY);
    } catch (Exception e) {
      e.printStackTrace();
    }
    for (DailyDataPoint dailyDataPoint : unfilteredTimeSeries) {
      int dataPointYear = Integer.parseInt(dailyDataPoint.getDate().substring(0, 4));
      int dataPointMonth = Integer.parseInt(dailyDataPoint.getDate().substring(5, 7));
      if (dataPointYear == year && dataPointMonth == month) {
        timeSeries.add(dailyDataPoint);
      }
    }
    return timeSeries;
  }

  //Method for filtering retrieved data depending on year.

  public List<DailyDataPoint> getFilteredSeries(int year) {
    List<DailyDataPoint> timeSeries = new ArrayList<>();
    List<DailyDataPoint> unfilteredTimeSeries = new ArrayList<>();
    try {
      unfilteredTimeSeries = getTimeSeries("AAPL", Interval.DAILY);
    } catch (Exception e) {
      e.printStackTrace();
    }
    for (DailyDataPoint dailyDataPoint : unfilteredTimeSeries) {
      int dataPointYear = Integer.parseInt(dailyDataPoint.getDate().substring(0, 4));

      timeSeries.add(dailyDataPoint);

    }
    return timeSeries;
  }
}
