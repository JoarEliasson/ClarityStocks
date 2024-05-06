package model;

import analysis.regression.RegressionAnalysis;
import analysis.regression.RegressionResult;
import common.data.fundamental.IncomeStatement;
import common.data.series.DailyDataPoint;
import java.util.List;
import model.stock.StockData;
import model.stock.StockDataFetcher;

public class AnalysisTesting {
  public static void main(String[] args) {

    StockDataFetcher dataFetcher = new StockDataFetcher();
    StockData stockData = dataFetcher.fetchStockData("AAPL");
    List<IncomeStatement> incomeStatements = stockData.getOrderedIncomeStatementData();
    String fiscalYearEnd = incomeStatements.getLast().getFiscalDateEnding();
    String month = fiscalYearEnd.split("-")[1];
    int numberOfYears = incomeStatements.size();

    // Prints used to verify the order of the income statements
    System.out.println("Income Statements");
    System.out.println("Size: " + incomeStatements.size());
    System.out.println("First: " + incomeStatements.getFirst().getFiscalDateEnding());
    System.out.println("Last: " + incomeStatements.getLast().getFiscalDateEnding());
    for (IncomeStatement incomeStatement : incomeStatements) {
      System.out.println(incomeStatement.getFiscalDateEnding());
    }
    System.out.println();
    List<DailyDataPoint> priceData = stockData.getTimeSeriesMonthly().getPriceDataMatching(fiscalYearEnd, numberOfYears);
    //List<DailyDataPoint> priceData = timeSeriesMonthly.getPriceDataMatching(incomeStatements);
    // Prints used to verify the order of the price data
    System.out.println("Price Data");
    System.out.println("Size: " + priceData.size());
    System.out.println("First: " + priceData.getFirst().getDate());
    System.out.println("Last: " + priceData.getLast().getDate());
    //for (DailyDataPoint dataPoint : priceData) {
    //  System.out.println(dataPoint.getDate());
    //}
    RegressionAnalysis regressionAnalysis = new RegressionAnalysis("AAPL", incomeStatements, priceData);
    List<RegressionResult> regressionResults = regressionAnalysis.getRegressionResults();
    System.out.println("Regression Results");
    for (RegressionResult regressionResult : regressionResults) {
      System.out.println(regressionResult.getVariable());
      System.out.println("R: " + regressionResult.getDescription());
      System.out.println("Predicted price: " + regressionResult.getPricePrediction().getPredictedPrice());
      System.out.println("Prediction date: " + regressionResult.getPricePrediction().getPredictionDate());
      System.out.println();
    }
  }
}
