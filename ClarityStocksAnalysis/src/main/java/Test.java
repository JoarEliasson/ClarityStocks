import alphaVantage.AlphaVantageClient;
import alphaVantage.CompanyOverview;
import alphaVantage.DataPoint;
import alphaVantage.Interval;
import controller.PERatioEvaluator;

import java.util.List;

public class Test {

    public static void main(String[] args) {
        String symbol = "AAPL";
        String name = "Apple Inc.";

        AlphaVantageClient client = new AlphaVantageClient("YKB1S8EYZ61LDH9B");
        CompanyOverview companyOverview = null;
        List<DataPoint> timeSeries = null;

        try {
            companyOverview = client.getCompanyOverview(symbol);
            timeSeries = client.getFilteredSeries();

        } catch (Exception e) {
            e.printStackTrace();
        }

        assert companyOverview != null;
        double priceEarningsRatio = companyOverview.getPeRatio();
        String companyName = companyOverview.getIndustry() + " - " + companyOverview.getSector();

        System.out.println("The Price Earnings Ratio for " + name + " (" + symbol + ") is: " + priceEarningsRatio);
        System.out.println(PERatioEvaluator.evaluatePriceEarningsRatio(symbol, name, priceEarningsRatio));

        if (timeSeries != null && !timeSeries.isEmpty()) {
            for (DataPoint dataPoint : timeSeries) {
                System.out.println(dataPoint.getDate() + " - " + dataPoint.getClose());
            }
        }


    }
}
