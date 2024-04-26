package alphaVantage.regression;

import alphaVantage.model.data.series.DailyDataPoint;
import alphaVantage.model.data.series.TimeSeriesDaily;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import alphaVantage.controller.AlphaVantageClient;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

/**
 * Class for implementing a linear regression. Linear regression is used for predicting the future price of the stock
 * by analyzing historical data. Uses an X and Y value which are data pairs. Model implements Pearson's linear
 * regression.
 * @author Olivia Svensson
 * */
public class LinearRegression {

    static AlphaVantageClient alphaVantageClient = new AlphaVantageClient("YKB1S8EYZ61LD");
    String symbol;
    double[][] dailyDataPoints;
    SimpleRegression simpleRegression;
    //AlphaVantageClient alphaVantageClient;
    TimeSeriesDaily timeSeriesDaily;
    List<DailyDataPoint> data;
    String description = "";
    String descriptionPrediction = "";

    /**
     * Constructor for the LinearRegression class. Takes in an AlphaVantage Client and a stock symbol.
     * */
    public LinearRegression(String symbol, AlphaVantageClient alphaVantageClient,
        Date start, Date end) {
        this.symbol = symbol;
        //this.alphaVantageClient = alphaVantageClient;
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

    /**
     * Method for predicting the price of the stock.
     * @returns the predicted y-value associated with the supplied x-value, based on data that has been added to the
     * model when the method is activated.
     * */
    private String predict(double prediction) {
        return descriptionPrediction = "The prediction for " + prediction + " is " +
        simpleRegression.predict(prediction);
    }

    /**
     * Method for getting a simple description of the linear regression analysis of a specific stock.
     * @returns a String.
     * */
    private String getDescription() {
        return description = "The R is: " + simpleRegression.getR() + "." +
        "\n" + "R is the Pearson's product moment correlation coefficient. It measures the linear relationship " +
        "between two variables. " +
        "\nThe coefficient ranges from -1 to 1, where: " +
        "\n1 indicates a perfect positive linear relationship." +
        "\n-1 indicates a perfect negative linear relationship." +
        "\n0 indicates no linear relationship." +
        "\n" + "The R-square is: " + simpleRegression.getRSquare() + "." +
        "\n" + "R-square is the coefficient of determination. It represents the proportion of the variance in the " +
        "dependent variable that is predictable from the independent variables. It indicates how well the " +
        "independent variables explain the variability of the dependent variable." +
        "\nR-square values range from 0 to 1, where: " +
        "\n0 indicates that the independent variables do not explain any of the variability of the dependent" +
        "variable." +
        "\n1 indicates that the independent variables perfectly explain all the variability of the dependent " +
        "variable." +
        "The slope is: " + simpleRegression.getSlope() + "." +
        "\nThe slope represents the rate of change in the dependent variable for a one-unit change in the " +
        "independent variable. It quantifies the effect of the independent variable on the dependent variable." +
        "\nThe significance is: " + simpleRegression.getSignificance() + "." +
        "\nSignificance is the statistical significance of the estimated coefficients. It indicated whether these " +
        "coefficients are reliably different from 0." +
        "\nA significance level less than 0.05 / 5% indicates that the coefficient is statistically significant, " +
        "suggesting that there is sufficient evidence to reject the null hypothesis that the coefficient is " +
        "equal to 0.";
    }

    /**
     * Main method for testing the class. The class has circular dependencies which makes it not runnable as of right
     * now.
     * */
    public static void main(String[] args) {
        System.out.println("Test");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        Date start = cal.getTime();
        Date end = cal.getTime();
        LinearRegression linearRegression = new LinearRegression("Apple", alphaVantageClient,start, end);
        System.out.println(linearRegression.getDescription());
    }
}
