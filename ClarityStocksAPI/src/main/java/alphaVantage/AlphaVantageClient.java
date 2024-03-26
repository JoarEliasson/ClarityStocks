package alphaVantage;

import analysis.controller.PERatioEvaluator;
import analysis.model.PERatioEvaluation;

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

    public Stock getStock(String symbol) {
        CompanyOverview companyOverview = null;
        List<DataPoint> timeSeries = null;
        try {
            companyOverview = getCompanyOverview(symbol);
            timeSeries = getTimeSeries(symbol, Interval.DAILY);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assert companyOverview != null;
        assert timeSeries != null;
        for (DataPoint dataPoint : timeSeries) {
            System.out.println(dataPoint);
        }
        PERatioEvaluator peRatioEvaluator = new PERatioEvaluator();
        PERatioEvaluation peRatioEvaluation = peRatioEvaluator.evaluatePriceEarningsRatio(symbol, companyOverview.getName(), companyOverview.getPeRatio());

        PERatioEvaluator.evaluatePriceEarningsRatio(symbol, companyOverview.getName(), companyOverview.getPeRatio());
        return new Stock(companyOverview, timeSeries, peRatioEvaluation.toString());
    }

    public CompanyOverview getCompanyOverview(String symbol) throws Exception {
        String url = String.format("https://www.alphavantage.co/query?function=OVERVIEW&symbol=%s&apikey=%s", symbol, apiKey);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMinutes(1))
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return parser.parseCompanyOverview(response.body());
            } else {
                throw new RuntimeException("Failed to fetch data: HTTP status code " + response.statusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching company overview", e);
        }
    }

    public List<DataPoint> getTimeSeries(String symbol, Interval interval) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + symbol + "&apikey=" + apiKey;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(urlString))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        return parser.parseTimeSeries(response.body());
    }

    public List<AlphaVantageStockInfo> searchEndpoint(String query) {
        String url = String.format("https://www.alphavantage.co/query?function=SYMBOL_SEARCH&keywords=%s&apikey=%s", query, apiKey);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMinutes(1))
                .GET()
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return parser.parseSearchResults(response.body());
            } else {
                throw new RuntimeException("Failed to fetch data: HTTP status code " + response.statusCode());
            }
        } catch (Exception e) {
            throw new RuntimeException("Error fetching search results", e);
        }
    }

    public static void main(String[] args) {
        AlphaVantageClient client = new AlphaVantageClient("YKB1S8EYZ61LDH9B");
        List<AlphaVantageStockInfo> searchResults = client.searchEndpoint("");
        for (AlphaVantageStockInfo alphaVantageStockInfo : searchResults) {
            System.out.println(alphaVantageStockInfo);
        }

    }

}
