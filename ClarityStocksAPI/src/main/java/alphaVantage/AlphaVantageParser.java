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
}
