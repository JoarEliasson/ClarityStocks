package alphaVantage;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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
}
