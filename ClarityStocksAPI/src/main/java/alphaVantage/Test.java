package alphaVantage;

import alphaVantage.controller.AlphaVantageClient;
import alphaVantage.model.data.fundamental.IncomeStatement;
import alphaVantage.model.data.series.DailyDataPoint;
import alphaVantage.model.data.series.TimeSeriesMonthly;
import java.util.List;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Test {


  public static void main(String[] args) {

    AlphaVantageClient alphaVantageClient = new AlphaVantageClient("YKB1S8EYZ61LD");

    List<IncomeStatement> incomeStatements = alphaVantageClient.getIncomeStatements("AAPL");
    TimeSeriesMonthly timeSeriesMonthly = alphaVantageClient.getTimeSeriesMonthly("AAPL");
    System.out.println("CLOSE PRICES");
    String fiscalDateEndingMonth = incomeStatements.getLast().getFiscalDateEnding().split("-")[1];
    List<DailyDataPoint> closePrices = timeSeriesMonthly.getMonthlyClosePrices(
        fiscalDateEndingMonth, incomeStatements.size());

    System.out.println("Ending " + incomeStatements.getLast().getFiscalDateEnding() + " Size" + incomeStatements.size());
    double[] closePricesArray = new double[closePrices.size()];
    for (int i = 0; i < closePrices.size(); i++) {
      System.out.println(closePrices.get(i).getDate() + " " + closePrices.get(i).getAdjustedClose());
      closePricesArray[i] = closePrices.get(i).getAdjustedClose();
    }

    System.out.println("NET INCOME");
    double[] netIncomeData = new double[closePricesArray.length];

    List<IncomeStatement> reversedIncomeStatements = incomeStatements.reversed();

    int index = 0;
    for (int i = 0; i < reversedIncomeStatements.size(); i++) {
      netIncomeData[index] = reversedIncomeStatements.get(i).getNetIncome();
      System.out.println(reversedIncomeStatements.get(i).getFiscalDateEnding() + " " + netIncomeData[index]);
      index++;
    }

    System.out.println("INDEXED NET INCOME");
    double[] indexedNetIncome = new double[closePricesArray.length];
    for (int i = 0; i < indexedNetIncome.length; i++) {
      double indexValue = netIncomeData[i] / netIncomeData[0];
      double roundedValue = Math.round(indexValue * 100.0) / 100.0;
      indexedNetIncome[i] = roundedValue;
      System.out.println(indexedNetIncome[i]);
    }


    //netIncome regression
    SimpleRegression netIncomeReg = new SimpleRegression();

    for (int i = 0; i < closePricesArray.length; i++) {
      System.out.println("Net Income: " + indexedNetIncome[i] + " Close Price: " + closePricesArray[i]);
      netIncomeReg.addData(indexedNetIncome[i], closePricesArray[i]);
    }

    System.out.println("NET INCOME");
    System.out.println("Price Regression Slope: " + netIncomeReg.getSlope());
    System.out.println("Price Regression Intercept: " + netIncomeReg.getIntercept());
    System.out.println("Price Regression R^2: " + netIncomeReg.getRSquare());
    System.out.println("The equation = " + netIncomeReg.getSlope() + "x + " + netIncomeReg.getIntercept());

  }
}
