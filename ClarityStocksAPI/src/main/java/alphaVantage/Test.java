package alphaVantage;

import alphaVantage.controller.AlphaVantageClient;
import alphaVantage.model.data.series.DailyDataPoint;
import alphaVantage.model.data.series.TimeSeriesDaily;
import java.time.LocalDate;
import java.util.List;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Test {

  public static void main(String[] args) {

    AlphaVantageClient alphaVantageClient = new AlphaVantageClient("YKB1S8EYZ61LD");


    TimeSeriesDaily timeSeriesDaily = alphaVantageClient.getTimeSeriesDaily("AAPL");
    List<DailyDataPoint> data = timeSeriesDaily.getDailyDataInRange("2020-01-01", LocalDate.now().toString());


    SimpleRegression priceRegression = new SimpleRegression();
    XYSeries seriesPriceTime = new XYSeries("Price");

    //SimpleRegression timeSeriesRegression = new SimpleRegression();
    //Add data to the priceRegression
    for (int i = 0; i < data.size(); i++) {
      priceRegression.addData(i, data.get(i).getClose());
      seriesPriceTime.add(i, data.get(i).getClose());

    }

    XYSeriesCollection dataset = new XYSeriesCollection();
    dataset.addSeries(seriesPriceTime);

    System.out.println("Price Regression Slope: " + priceRegression.getSlope());
    System.out.println("Price Regression Intercept: " + priceRegression.getIntercept());
    System.out.println("Price Regression R^2: " + priceRegression.getRSquare());

    ChartTest chartTest = new ChartTest("Price vs Time", dataset, priceRegression);
    chartTest.pack();
    chartTest.setVisible(true);
  }
}
