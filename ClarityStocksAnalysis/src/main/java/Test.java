import alphaVantage.AlphaVantageClient;
import alphaVantage.CompanyOverview;
import controller.PERatioEvaluator;

public class Test {

    public static void main(String[] args) {
        String symbol = "AAPL";
        String name = "Apple Inc.";

        AlphaVantageClient client = new AlphaVantageClient("YKB1S8EYZ61LDH9B");
        CompanyOverview companyOverview = null;

        try {
            companyOverview = client.getCompanyOverview(symbol);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assert companyOverview != null;
        double priceEarningsRatio = companyOverview.getPeRatio();
        String companyName = companyOverview.getIndustry() + " - " + companyOverview.getSector();

        System.out.println("The Price Earnings Ratio for " + name + " (" + symbol + ") is: " + priceEarningsRatio);
        System.out.println(PERatioEvaluator.evaluatePriceEarningsRatio(symbol, name, priceEarningsRatio));

    }
}
