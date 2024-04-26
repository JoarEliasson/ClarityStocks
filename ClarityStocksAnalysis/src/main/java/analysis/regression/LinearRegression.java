package analysis.regression;

import alphaVantage.model.data.series.DailyDataPoint;
import org.apache.commons.math3.stat.regression.SimpleRegression;
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

    public LinearRegression(String symbol, double[][] dailyDataPoints) {
        this.symbol = symbol;
        this.dailyDataPoints = dailyDataPoints;
    }


    private void linearRegression() {
        simpleRegression = new SimpleRegression(true);
        simpleRegression.addData(dailyDataPoints);
    }

}
