package alphaVantage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AlphaVantageParser {

    public CompanyOverview parseCompanyOverview(String responseBody) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(responseBody);

        CompanyOverview overview = new CompanyOverview();
        overview.setSymbol(root.path("Symbol").asText());
        overview.setName(root.path("Name").asText());
        overview.setDescription(root.path("Description").asText());
        overview.setSector(root.path("Sector").asText());
        overview.setIndustry(root.path("Industry").asText());
        overview.setMarketCapitalization(root.path("MarketCapitalization").asDouble());
        overview.setPeRatio(root.path("PERatio").asDouble());
        overview.setEarningsPerShare(root.path("EPS").asDouble());
        overview.setBookValue(root.path("BookValue").asDouble());
        overview.setDividendPerShare(root.path("DividendPerShare").asDouble());
        overview.setDividendYield(root.path("DividendYield").asDouble());
        overview.setRevenuePerShareTTM(root.path("RevenuePerShareTTM").asDouble());
        overview.setProfitMargin(root.path("ProfitMargin").asDouble());
        overview.setOperatingMarginTTM(root.path("OperatingMarginTTM").asDouble());

        return overview;
    }

    public List<DataPoint> parseTimeSeries(String responseBody) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        List<DataPoint> dataPoints = new ArrayList<>();

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

                    DataPoint dataPoint = new DataPoint(date, open, high, low, close, volume);
                    dataPoints.add(dataPoint);
                }
            } else {
                System.out.println("No daily time series data found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataPoints;
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
                    AlphaVantageStockInfo alphaVantageStockInfo = new AlphaVantageStockInfo(symbol, name, type, region, marketOpen, marketClose, timezone, currency, matchScore);
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
