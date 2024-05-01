package alphaVantage.regression;

import alphaVantage.model.data.fundamental.IncomeStatement;
import alphaVantage.model.data.series.DailyDataPoint;
import alphaVantage.model.data.series.TimeSeriesDaily;
import alphaVantage.model.data.series.TimeSeriesMonthly;
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
    private String symbol;
    private String fiscalDateEndingMonth;
    private String description = "";
    private String descriptionPrediction = "";
    private SimpleRegression simpleRegression;
    //AlphaVantageClient alphaVantageClient;
    private TimeSeriesDaily timeSeriesDaily;
    private TimeSeriesMonthly timeSeriesMonthly;

   //might come to use later, however will not be implemented as of right now
    //TimeSeriesDaily getTimeSeriesDaily;
   private double[] closePricesArray;
    private double[][] dailyDataPoints;
    private double[] netIncomeData;
    private double[] indexedNetIncome;
    private List<DailyDataPoint> data;
    private List<DailyDataPoint> closePrices;
    private List<IncomeStatement> incomeStatements;
    private List<IncomeStatement> reversedIncomeStatements;


    /**
     * Constructor for the LinearRegression class. Takes in an AlphaVantage Client and a stock symbol.
     *
     */
    public LinearRegression(String symbol, AlphaVantageClient alphaVantageClient,
        Date start, Date end) {
        this.symbol = symbol;
        //this.alphaVantageClient = alphaVantageClient;
        timeSeriesDaily = alphaVantageClient.getTimeSeriesDaily(symbol);
        data = timeSeriesDaily.getDailyDataInRange(start.toString(), end.toString());
        linearRegression(data);
        setIncomeStatements(symbol);
        setTimeSeriesMonthly(symbol);
        setClosePrices();
        reverseIncomeStatements();
        setNetIncome();
        indexNetIncome();
    }

    /**
     * Method for getting the income statements of a company.
     *
     * */

    private void setIncomeStatements(String symbol) {
        incomeStatements = alphaVantageClient.getIncomeStatements(symbol);
    }

    /**
     * Method for setting the closing prices of a stock. Has a for-loop which loops through all the close prices
     * and sets the double array with the closing prices  at that particular index to the adjusted closing price of
     * the stock. The adjusted stock price is necessary as there could be stock-splits which means that a company
     * divides their stocks so that more shares are created, making the price of the stock lower. If this is not
     * considered, the analysis will be incorrect. The adjusted close means that the historical prices are compared to
     * the current prices and takes into consideration stock-splits.
     * */
    private void setClosePrices() {
        closePrices = timeSeriesMonthly.getMonthlyClosePrices(fiscalDateEndingMonth, incomeStatements.size());
        closePricesArray = new double[closePrices.size()];
        for (int i = 0; i < closePrices.size(); i++) {
            System.out.println(closePrices.get(i).getDate() + " " + closePrices.get(i).getAdjustedClose());
            closePricesArray[i] = closePrices.get(i).getAdjustedClose();
        }
    }

    /**
     * Method for sorting the income statements by reversing them, as we want the latest income statement to be first
     * in the list.
     * */
    private void reverseIncomeStatements() {
        reversedIncomeStatements = incomeStatements.reversed();
    }

    /**
     * Method for setting the net income.
     * */
    private void setNetIncome() {
       // System.out.println("NET INCOME");
        netIncomeData = new double[closePricesArray.length];
        int index = 0;
        for (int i = 0; i < reversedIncomeStatements.size(); i++) {
            netIncomeData[index] = reversedIncomeStatements.get(i).getNetIncome();
          //  System.out.println(reversedIncomeStatements.get(i).getFiscalDateEnding() + " " + netIncomeData[index]);
            index++;
        }
    }

    /**
     * Method for indexing the net income of a stock. Loops through a double array and calculates the index value
     * by dividing each net income value by the first net income value. This indexes each net income value to the first
     * value in the array. For each iteration the index value gets rounded to two decimal places, which is done by
     * multiplying the index value by 100, which rounds it to the nearest integer, then it divides it back by 100
     * to obtain the rounded value with two decimal places.
     * The index income is then set to the rounded value in the array.
     * */

    private void indexNetIncome() {
        indexedNetIncome = new double[closePricesArray.length];
        for (int i = 0; i < indexedNetIncome.length; i++) {
            double indexValue = netIncomeData[i] / netIncomeData[0];
            double roundedValue = Math.round(indexValue * 100.0) / 100.0;
            indexedNetIncome[i] = roundedValue;
            System.out.println(indexedNetIncome[i]);
        }
    }

    /**
     * Method for getting the time series of the stock on a monthly basis
     * */
    private void setTimeSeriesMonthly(String symbol) {
        timeSeriesMonthly = alphaVantageClient.getTimeSeriesMonthly(symbol);
        fiscalDateEndingMonth = incomeStatements.getLast().getFiscalDateEnding().split("-")[1];
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

    private void

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
