package alphaVantage;

import alphaVantage.controller.AlphaVantageClient;
import alphaVantage.model.data.fundamental.IncomeStatement;
import alphaVantage.model.data.series.TimeSeriesMonthly;
import alphaVantage.regression.RegressionAnalysis;
import alphaVantage.regression.RegressionResult;
import java.util.List;

public class Test {


  public static void main(String[] args) {

    AlphaVantageClient alphaVantageClient = new AlphaVantageClient("YKB1S8EYZ61LD");

    List<IncomeStatement> incomeStatements = alphaVantageClient.getIncomeStatements("AAPL");
    TimeSeriesMonthly timeSeriesMonthly = alphaVantageClient.getTimeSeriesMonthly("AAPL");
    RegressionAnalysis regressionAnalysis = new RegressionAnalysis("AAPL", incomeStatements, timeSeriesMonthly);
    List<RegressionResult> regressionResults = regressionAnalysis.getRegressionResults();
    System.out.println(regressionAnalysis.getResult());
  }
}
