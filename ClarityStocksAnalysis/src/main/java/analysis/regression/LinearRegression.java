package analysis.regression;

import alphaVantage.model.data.series.DailyDataPoint;

/**
 * Class for implementing a linear regression. Linear regression is used for predicting the future price of the stock
 * by analyzing historical data. Uses an X and Y value which are data pairs. Model implements Pearsons linear regression
 *
 * @author Olivia Svensson
 * */
public class LinearRegression {
    String symbol;
    DailyDataPoint[] dailyDataPoints;

    public LinearRegression(String symbol, DailyDataPoint[] dailyDataPoints) {
        this.symbol = symbol;
        this.dailyDataPoints = dailyDataPoints;
    }


    private void linearRegression() {
        

    }

}
