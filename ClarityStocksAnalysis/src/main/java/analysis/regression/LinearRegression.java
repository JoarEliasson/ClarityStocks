package analysis.regression;

import alphaVantage.model.data.series.DailyDataPoint;
import alphaVantage.model.data.series.TimeSeriesDaily;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import alphaVantage.controller.AlphaVantageClient;
import java.util.Date;

import java.time.LocalDate;
import java.util.List;

/**
 * Class for implementing a linear regression. Linear regression is used for predicting the future price of the stock
 * by analyzing historical data. Uses an X and Y value which are data pairs. Model implements Pearson's linear
 * regression.
 * @author Olivia Svensson
 * */
public class LinearRegression {
    String symbol;
    double[][] dailyDataPoints;
    SimpleRegression simpleRegression;
    AlphaVantageClient alphaVantageClient;
   TimeSeriesDaily timeSeriesDaily;
    List<DailyDataPoint> data;

    public LinearRegression(String symbol, double[][] dailyDataPoints, AlphaVantageClient alphaVantageClient, Date start, Date end) {
        this.symbol = symbol;
        this.alphaVantageClient = alphaVantageClient;
        this.dailyDataPoints = dailyDataPoints;
        timeSeriesDaily = alphaVantageClient.getTimeSeriesDaily(symbol);
        data = timeSeriesDaily.getDailyDataInRange(start.toString(), end.toString());
    }


    private void linearRegression() {
        simpleRegression = new SimpleRegression(true);
        simpleRegression.addData(dailyDataPoints);
    }

}
