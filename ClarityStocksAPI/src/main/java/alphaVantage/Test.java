package alphaVantage;

import alphaVantage.controller.AlphaVantageClient;
import alphaVantage.model.data.fundamental.IncomeStatement;
import alphaVantage.model.data.series.DailyDataPoint;
import alphaVantage.model.data.series.TimeSeriesMonthly;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.math3.stat.regression.SimpleRegression;

public class Test {


  public static void main(String[] args) {

    AlphaVantageClient alphaVantageClient = new AlphaVantageClient("YKB1S8EYZ61LD");


    TimeSeriesMonthly timeSeriesMonthly = alphaVantageClient.getTimeSeriesMonthly("AAPL");

    List<DailyDataPoint> data = timeSeriesMonthly.getMonthlyData();
    List<Double> filteredData = new ArrayList<>();
    for (int i = 0; i < data.size(); i++) {
      String[] split = data.get(i).getDate().split("-");
      if (split[1].equals("09")) {
        filteredData.add(data.get(i).getClose());
        System.out.println(data.get(i).getDate() + " " + data.get(i).getClose());
      }
    }

    List<IncomeStatement> incomeStatements = alphaVantageClient.getIncomeStatements("AAPL");
    String date = incomeStatements.getFirst().getFiscalDateEnding();


    List<Long> netIncome = new ArrayList<>();
    for (IncomeStatement statement : incomeStatements) {
      netIncome.add(statement.getNetIncome());
    }


    //netIncome regression
    SimpleRegression netIncomeReg = new SimpleRegression();

    for (int i = 0; i < incomeStatements.size(); i++) {
      netIncomeReg.addData(netIncome.get(i), filteredData.get(i));
    }
    System.out.println("NET INCOME");
    System.out.println("Price Regression Slope: " + netIncomeReg.getSlope());
    System.out.println("Price Regression Intercept: " + netIncomeReg.getIntercept());
    System.out.println("Price Regression R^2: " + netIncomeReg.getRSquare());
    System.out.println("The equation = " + netIncomeReg.getSlope() + "x + " + netIncomeReg.getIntercept());
    System.out.println();

    //revenue regression

    List<Long> revenue = new ArrayList<>();
    for (IncomeStatement statement : incomeStatements) {
      revenue.add(statement.getTotalRevenue());
    }

    SimpleRegression revenueReg = new SimpleRegression();

    for (int i = 0; i < incomeStatements.size(); i++) {
      revenueReg.addData(revenue.get(i), filteredData.get(i));
    }

    System.out.println("REVENUE");
    System.out.println("Price Regression Slope: " + revenueReg.getSlope());
    System.out.println("Price Regression Intercept: " + revenueReg.getIntercept());
    System.out.println("Price Regression R^2: " + revenueReg.getRSquare());
    System.out.println("The equation = " + revenueReg.getSlope() + "x + " + revenueReg.getIntercept());
    System.out.println();

    //gross profit
    List<Long> grossProfit = new ArrayList<>();
    for (IncomeStatement statement : incomeStatements) {
      grossProfit.add(statement.getGrossProfit());
    }

    SimpleRegression grossProfitReg = new SimpleRegression();

    for (int i = 0; i < incomeStatements.size(); i++) {
      grossProfitReg.addData(grossProfit.get(i), filteredData.get(i));
    }
    System.out.println("GROSS PROFIT");
    System.out.println("Price Regression Slope: " + grossProfitReg.getSlope());
    System.out.println("Price Regression Intercept: " + grossProfitReg.getIntercept());
    System.out.println("Price Regression R^2: " + grossProfitReg.getRSquare());
    System.out.println("The equation = " + grossProfitReg.getSlope() + "x + " + grossProfitReg.getIntercept());
  }
}
