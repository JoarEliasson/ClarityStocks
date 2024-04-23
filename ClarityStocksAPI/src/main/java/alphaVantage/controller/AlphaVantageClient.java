package alphaVantage.controller;

import alphaVantage.model.AlphaVantageStock;
import alphaVantage.model.data.fundamental.BalanceSheet;
import alphaVantage.model.data.fundamental.CashFlowReport;
import alphaVantage.model.data.fundamental.CompanyOverview;
import alphaVantage.model.data.fundamental.EarningsData;
import alphaVantage.model.data.fundamental.IncomeStatement;
import alphaVantage.model.data.series.TimeSeriesDaily;
import alphaVantage.model.enums.Function;
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

  public AlphaVantageStock getStock(String symbol) {
    AlphaVantageStock stock = new AlphaVantageStock();
    stock.setCompanyOverview(getFullStockOverview(symbol));
    stock.setTimeSeriesDaily(getTimeSeriesDaily(symbol));
    stock.setIncomeStatements(getIncomeStatements(symbol));
    stock.setBalanceSheets(getBalanceSheet(symbol));
    stock.setCashFlowReports(getCashFlowReports(symbol));
    stock.setEarningsData(getEarningsData(symbol));
    stock.runEvaluations();
    return stock;
  }

  public CompanyOverview getFullStockOverview(String symbol) {
    String requestURL = Function.OVERVIEW.getURL() + symbol + "&apikey=" + apiKey;
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
    String requestURL = Function.TIME_SERIES_DAILY.getURL() + symbol + "&apikey=" + apiKey;
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

  public List<IncomeStatement> getIncomeStatements(String symbol) {
    String requestURL = Function.INCOME_STATEMENT.getURL() + symbol + "&apikey=" + apiKey;
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
    String requestURL = Function.BALANCE_SHEET.getURL() + symbol + "&apikey=" + apiKey;
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
    String requestURL = Function.CASH_FLOW.getURL() + symbol + "&apikey=" + apiKey;
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
    String requestURL = Function.EARNINGS.getURL() + symbol + "&apikey=" + apiKey;
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
}