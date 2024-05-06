package alphaVantage;

import common.data.fundamental.BalanceSheet;
import common.data.fundamental.CashFlowReport;
import common.data.fundamental.CompanyOverview;
import common.data.fundamental.EarningsData;
import common.data.fundamental.IncomeStatement;
import common.data.global.DailyTopLists;
import common.data.global.GlobalMarketInfo;
import common.data.global.MarketStatus;
import common.data.series.TimeSeriesDaily;
import common.data.series.TimeSeriesMonthly;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class AlphaVantageClient {

  private final String apiKey;
  private final HttpClient httpClient;
  private final AlphaVantageParser parser = new AlphaVantageParser();

  public AlphaVantageClient(String apiKey) {
    this.apiKey = apiKey;
    this.httpClient = HttpClient.newHttpClient();
  }

  public GlobalMarketInfo getGlobalMarketInfo() {
    GlobalMarketInfo globalMarketInfo = new GlobalMarketInfo();
    globalMarketInfo.setDailyTopLists(getDailyTopLists());
    globalMarketInfo.setMarketStatus(getGlobalMarketStatus());
    return globalMarketInfo;
  }

  public CompanyOverview getFullStockOverview(String symbol) {
    String requestURL = Function.OVERVIEW.getURL(symbol,false) + apiKey;
    try (HttpClient httpClient = HttpClient.newHttpClient()) {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(requestURL))
          .timeout(Duration.ofMinutes(1))
          .GET()
          .build();
      HttpResponse<String> response = httpClient.send(
          request, HttpResponse.BodyHandlers.ofString()
      );
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

  public TimeSeriesDaily getTimeSeriesDaily(String symbol) {
    String requestURL = Function.TIME_SERIES_DAILY.getURL(symbol, true) + apiKey;
    try (HttpClient httpClient = HttpClient.newHttpClient()) {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(requestURL))
          .timeout(Duration.ofMinutes(1))
          .GET()
          .build();
      HttpResponse<String> response = httpClient.send(
          request, HttpResponse.BodyHandlers.ofString()
      );
      if (response.statusCode() == 200) {
        return parser.parseTimeSeriesDaily(response.body());
      } else {
        throw new RuntimeException(
            "Failed to fetch data: HTTP status code " + response.statusCode());
      }
    } catch (Exception e) {
      throw new RuntimeException("Error fetching time series data", e);
    }
  }

  public TimeSeriesMonthly getTimeSeriesMonthly(String symbol) {
    String requestURL = Function.TIME_SERIES_MONTHLY_ADJUSTED.getURL(symbol, true) + apiKey;
    try (HttpClient httpClient = HttpClient.newHttpClient()) {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(requestURL))
          .timeout(Duration.ofMinutes(1))
          .GET()
          .build();
      HttpResponse<String> response = httpClient.send(
          request, HttpResponse.BodyHandlers.ofString()
      );
      if (response.statusCode() == 200) {
        return parser.parseTimeSeriesMonthly(response.body());
      } else {
        throw new RuntimeException(
            "Failed to fetch data: HTTP status code " + response.statusCode());
      }
    } catch (Exception e) {
      throw new RuntimeException("Error fetching time series data", e);
    }
  }

  public List<IncomeStatement> getIncomeStatements(String symbol) {
    String requestURL = Function.INCOME_STATEMENT.getURL(symbol,false) + apiKey;
    try (HttpClient httpClient = HttpClient.newHttpClient()) {
      HttpRequest request = HttpRequest.newBuilder()
          .uri(URI.create(requestURL))
          .timeout(Duration.ofMinutes(1))
          .GET()
          .build();
      HttpResponse<String> response = httpClient.send(
          request, HttpResponse.BodyHandlers.ofString()
      );
      if (response.statusCode() == 200) {
        return parser.parseIncomeStatements(response.body());
      } else {
        throw new RuntimeException(
            "Failed to fetch data: HTTP status code " + response.statusCode());
      }
    } catch (Exception e) {
      throw new RuntimeException("Error fetching company overview", e);
    }
  }

  public List<BalanceSheet> getBalanceSheet(String symbol) {
    String requestURL = Function.BALANCE_SHEET.getURL(symbol,false) + apiKey;
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(requestURL))
        .timeout(Duration.ofMinutes(1))
        .GET()
        .build();
    try {
      HttpResponse<String> response = httpClient.send(request,
          HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        return parser.parseBalanceSheets(response.body());
      } else {
        throw new RuntimeException(
            "Failed to fetch data: HTTP status code " + response.statusCode());
      }
    } catch (Exception e) {
      throw new RuntimeException("Error fetching company overview", e);
    }
  }

  public List<CashFlowReport> getCashFlowReports(String symbol) {
    String requestURL = Function.CASH_FLOW.getURL(symbol,false) + apiKey;
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(requestURL))
        .timeout(Duration.ofMinutes(1))
        .GET()
        .build();
    try {
      HttpResponse<String> response = httpClient.send(request,
          HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        return parser.parseCashFlowReports(response.body());
      } else {
        throw new RuntimeException(
            "Failed to fetch data: HTTP status code " + response.statusCode());
      }
    } catch (Exception e) {
      throw new RuntimeException("Error fetching company overview", e);
    }
  }

  public EarningsData getEarningsData(String symbol) {
    String requestURL = Function.EARNINGS.getURL(symbol,false) + apiKey;
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(requestURL))
        .timeout(Duration.ofMinutes(1))
        .GET()
        .build();
    try {
      HttpResponse<String> response = httpClient.send(request,
          HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        return parser.parseEarningsData(response.body());
      } else {
        throw new RuntimeException(
            "Failed to fetch data: HTTP status code " + response.statusCode());
      }
    } catch (Exception e) {
      throw new RuntimeException("Error fetching company overview", e);
    }
  }

  public DailyTopLists getDailyTopLists() {
    String requestURL = Function.TOP_GAINERS_LOSERS.getURL(null,false) + apiKey;
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(requestURL))
        .timeout(Duration.ofMinutes(1))
        .GET()
        .build();
    try {
      HttpResponse<String> response = httpClient.send(request,
          HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        return parser.parseDailyTopLists(response.body());
      } else {
        throw new RuntimeException(
            "Failed to fetch data: HTTP status code " + response.statusCode());
      }
    } catch (Exception e) {
      throw new RuntimeException("Error fetching company overview", e);
    }
  }

  public List<MarketStatus> getGlobalMarketStatus() {
    String requestURL = Function.MARKET_STATUS.getURL(null,false) + apiKey;
    HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create(requestURL))
        .timeout(Duration.ofMinutes(1))
        .GET()
        .build();
    try {
      HttpResponse<String> response = httpClient.send(request,
          HttpResponse.BodyHandlers.ofString());
      if (response.statusCode() == 200) {
        return parser.parseMarketStatus(response.body());
      } else {
        throw new RuntimeException(
            "Failed to fetch data: HTTP status code " + response.statusCode());
      }
    } catch (Exception e) {
      throw new RuntimeException("Error fetching company overview", e);
    }
  }

}
