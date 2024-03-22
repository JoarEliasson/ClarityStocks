package alphaVantage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class AlphaVantageClient {

    private final String apiKey;
    private final HttpClient httpClient;
    private final AlphaVantageParser parser = new AlphaVantageParser();

    public AlphaVantageClient(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = HttpClient.newHttpClient();
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
}
