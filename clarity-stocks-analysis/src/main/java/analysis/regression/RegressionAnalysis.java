package analysis.regression;


import common.data.fundamental.IncomeStatement;
import common.data.series.DailyDataPoint;
import common.enums.IncomeStatementVariable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 *  Class for making the analysis of the linear regression. The linear regression requires some more
 *  analysis which makes having a separate class for these more comprehensible.
 * @author Olivia Svensson, Joar Eliasson
 *
 */
public class RegressionAnalysis {

  private final String symbol;
  private final RegressionCalculator regressionCalculator;
  private final List<RegressionResult> regressionResults = new ArrayList<>();
  private final IncomeStatementVariable[] variables = IncomeStatementVariable.values();
  private String result;

  public RegressionAnalysis(String symbol, List<IncomeStatement> incomeStatements,
      List<DailyDataPoint> priceData) {
    this.symbol = symbol;
    this.regressionCalculator = new RegressionCalculator(
        incomeStatements,
        fetchAdjustedData(priceData)
    );
    //Preserved for debugging purposes
    //System.out.println("Income Statements Size: " + incomeStatements.size());
    //System.out.println("Date First" + incomeStatements.getFirst().getFiscalDateEnding());
    //System.out.println("Date Last" + incomeStatements.getLast().getFiscalDateEnding());
    //System.out.println("Price Data Size: " + priceData.size());
    //System.out.println("Date First" + priceData.getFirst().getDate());
    //System.out.println("Date Last" + priceData.getLast().getDate());
    runRegressionAnalysis();
    fetchResults();
  }

  private double[] fetchAdjustedData(List<DailyDataPoint> priceData) {
    double[] priceDataArray = new double[priceData.size()];
    for (int i = 0; i < priceData.size(); i++) {
      priceDataArray[i] = priceData.get(i).getAdjustedClose();
    }
    return priceDataArray;
  }

  /**
   * Method for making all the linear regressions based on the income statements.
   * The method has a for-loop which loops through all the elements of the linear regression array. For each element
   * the method assigns the element in the array to a new linear regression made.
   * */
  private void runRegressionAnalysis() {
    for (IncomeStatementVariable variable : variables) {
      regressionResults.add(regressionCalculator.runAnalysis(variable));
    }
    if (!regressionResults.isEmpty()) {
      System.out.println();
      System.out.println("Regression Results for (" + symbol + "):");
      for (int i = 0; i < 3; i++) {
        System.out.println();
        System.out.println("[Correlation between " + regressionResults.get(i).getVariable() + " and the stock price]");
        String info = String.format("\tR = %.2f, R^2 = %.2f",
            regressionResults.get(i).getSimpleRegression().getR(),
            regressionResults.get(i).getSimpleRegression().getRSquare()
        );
        String prediction = String.format("\tPrice (%s) = %.2f%n\tPredicted price based on %s = %.2f%n",
            regressionResults.get(i).getPricePrediction().getPredictionDate(),
            regressionResults.get(i).getPricePrediction().getCurrentPrice(),
            regressionResults.get(i).getVariable(),
            regressionResults.get(i).getPricePrediction().getPredictedPrice()
        );
        System.out.println(info);
        System.out.println(prediction);
      }
      System.out.println();
    }
  }

  public List<RegressionResult> getRegressionResults() {
    return regressionResults;
  }

  /**
   * Method which sorts the linear regressions array to an ascending order based on the regressions r-square value.
   * The r-square value is an indicator to show which income statements have the highest correlation with the price of
   * the stock.
   * */
  private RegressionResult[] bubbleSort() {
    RegressionResult[] regressionArray = regressionResults.toArray(new RegressionResult[0]);
    int n = regressionArray.length;
    RegressionResult temp;
    for(int i = 0; i < n; i++) {
      for(int j = 1; j < (n - i); j++) {
        if(regressionArray[j-1].getSimpleRegression().getRSquare() < regressionArray[j].getSimpleRegression().getRSquare()) {
          temp = regressionArray[j-1];
          regressionArray[j-1] = regressionArray[j];
          regressionArray[j] = temp;
        }
      }
    }
    return regressionArray;
  }

  private List<RegressionResult> listSort() {
    regressionResults.sort(Comparator.comparingDouble(r -> r.getSimpleRegression().getRSquare()));
    System.out.println("List sort");
    for (RegressionResult result : regressionResults) {
      System.out.println(result.getVariable() + " R^2 = " + result.getSimpleRegression().getRSquare());
    }
    return regressionResults;
  }

  public void fetchResults() {
    RegressionResult[] sortedArray = bubbleSort();
    result =  String.format(
        "The highest r-square values for (%s) are: \n" + sortedArray[0].getSimpleRegression().getRSquare() + " from " +
            sortedArray[0].getVariable() +  ". \n" +
            sortedArray[1].getSimpleRegression().getRSquare() + " from " + sortedArray[1].getVariable() +  ". \n" +
            sortedArray[2].getSimpleRegression().getRSquare() + " from " + sortedArray[2].getVariable() +  ".",
        symbol
    );
    //Preliminary result printing for later GUI implementation
    /*
    for (RegressionResult result : sortedArray) {
      System.out.println();
      System.out.println(symbol);
      System.out.println(result.getVariable() + " R = " + result.getSimpleRegression().getR());
      System.out.println(result.getVariable() + " R^2 = " + result.getSimpleRegression().getRSquare());
      System.out.println(result.getPricePrediction().getDescription());
    }
     */
  }

  public String getResult() {
    return result;
  }
}
