package analysis.regression;

import alphaVantage.model.data.series.DailyDataPoint;
import alphaVantage.model.data.series.TimeSeriesDaily;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import alphaVantage.controller.AlphaVantageClient;
import java.util.Date;
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

    /**
     * Constructor for the LinearRegression class. Takes in an AlphaVantage Client and a stock symbol.
     * */
    public LinearRegression(String symbol, AlphaVantageClient alphaVantageClient,
        Date start, Date end) {
        this.symbol = symbol;
        this.alphaVantageClient = alphaVantageClient;
        timeSeriesDaily = alphaVantageClient.getTimeSeriesDaily(symbol);
        data = timeSeriesDaily.getDailyDataInRange(start.toString(), end.toString());
        linearRegression(data);
    }

    /**
     * Method which creates a linear regression analysis. Adds data from a 2D array with value pairs into the
     * regression.
     * */
    private void linearRegression(List<DailyDataPoint> data) {
        simpleRegression = new SimpleRegression(true);
        dailyDataPoints = createXYValues(data);
        simpleRegression.addData(dailyDataPoints);
    }

    /**
     * Method which creates the value pairs used for the linear regression analysis. In linear regression, there needs
     * to be two values (x and y) for an analysis to be possible, as the equation is Y = a + bX.
     * The value pairs are then put into a 2D double array.
     * @return a 2D double array with X and Y value pairs.
     * */
    private double[][] createXYValues(List<DailyDataPoint> data) {
        dailyDataPoints = new double[data.size()][2];
        for(int i = 0; i < data.size(); i++) {
                dailyDataPoints[i][0] = i;
                dailyDataPoints[i][1] = data.get(i).getClose();
        }
        return dailyDataPoints;
    }

}
