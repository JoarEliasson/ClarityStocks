package analysis.regression;


import analysis.interfaces.TechnicalAnalysis;
import common.data.fundamental.IncomeStatement;
import common.data.series.DailyDataPoint;
import common.enums.IncomeStatementVariable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class for performing regression analysis on stock data.
 * <p>
 * This class analyzes the relationship between various financial variables from the income statement
 * and the stock price using linear regression. The analysis helps in understanding how different
 * financial metrics impact the stock price.
 * </p>
 *
 * <ul>
 *   <li>{@code symbol} - The unique identifier for the company's stock.</li>
 *   <li>{@code regressionCalculator} - The calculator used to perform the regression analysis.</li>
 *   <li>{@code regressionResults} - List of results from the regression analysis.</li>
 *   <li>{@code variables} - Array of financial variables to be analyzed.</li>
 *   <li>{@code result} - String summarizing the highest r-square values from the analysis.</li>
 * </ul>
 *
 * @see TechnicalAnalysis
 * @see IncomeStatement
 * @see DailyDataPoint
 * @see IncomeStatementVariable
 * @see RegressionCalculator
 * @see RegressionResult
 *
 * @author by Joar Eliasson
 * @author by Olivia Svensson
 */
public class RegressionAnalysis implements TechnicalAnalysis {

  private final String symbol;
  private final RegressionCalculator regressionCalculator;
  private final List<RegressionResult> regressionResults = new ArrayList<>();
  private final IncomeStatementVariable[] variables = IncomeStatementVariable.values();
  private String result;
  private List<DailyDataPoint> priceData;
  private List<IncomeStatement> incomeStatements;

  public RegressionAnalysis(String symbol, List<IncomeStatement> incomeStatements,
      List<DailyDataPoint> priceData) {
    this.symbol = symbol;
    this.priceData = priceData;
    this.incomeStatements = incomeStatements;
    this.regressionCalculator = new RegressionCalculator(
        incomeStatements,
        fetchAdjustedData(priceData)
    );
    analyze();
    fetchResults();
  }

  /**
   * Performs the regression analysis on the stock data.
   */
  @Override
  public void analyze() {
    for (IncomeStatementVariable variable : variables) {
      regressionResults.add(regressionCalculator.runAnalysis(variable));
    }
    if (!regressionResults.isEmpty()) {
      System.out.println();
      System.out.println("Regression Results for (" + symbol + "):");
      for (int i = 0; i < 3; i++) {
        System.out.println();
        System.out.println("[Correlation between " + regressionResults.get(i).getVariable() + " and the stock price]");

        String info = String.format("\tIntercept = %.2f, Slope = %.2f",
            regressionResults.get(i).getCoefficients()[0],
            regressionResults.get(i).getCoefficients()[1]
        );
        String prediction = String.format("\tPrice (%s) = %.2f%n\tPredicted price based on %s = %.2f%n",
            regressionResults.get(i).getLatestPrediction().getPredictionDate(),
            regressionResults.get(i).getLatestPrediction().getCurrentPrice(),
            regressionResults.get(i).getVariable(),
            regressionResults.get(i).getLatestPrediction().getPredictedPrice()
        );
        System.out.println(info);
        System.out.println(prediction);
      }
      System.out.println();
    }
  }

  public List<RegressionResult> getRegressionTop() {
    return regressionResults.subList(0, 3);
  }

  public List<RegressionResult> getRegressionResults() {
    return regressionResults;
  }

  public List<DailyDataPoint> getPriceData() {
    return priceData;
  }

  public List<IncomeStatement> getIncomeStatements() {
    return incomeStatements;
  }

  /**
   * Gets the symbol of the stock.
   *
   * @return the symbol of the stock
   */
  @Override
  public String getSymbol() {
    return symbol;
  }

  /**
   * Gets the title of the analysis.
   *
   * <p>The title corresponds to the type of analysis that is performed.
   *
   * @return the title of the analysis
   */
  @Override
  public String getAnalysisTitle() {
    return "Regression Analysis";
  }

  /**
   * Gets the subtitle of the analysis.
   *
   * <p>The subtitle is a short description of the data that the analysis is based on.
   *
   * @return the subtitle of the analysis
   */
  @Override
  public String getGeneralAnalysisInfo() {
    return String.format(
        "Understanding Regression Analysis:%n%n"
            + "Regression analysis is a powerful statistical method that allows us to examine the relationship between two or more variables of interest. "
            + "In the context of stock prices, regression analysis helps us understand how different financial metrics from a company's income statement impact its stock price.%n%n"
            + "By analyzing historical data, we can identify trends and patterns that can inform future predictions. This analysis is particularly useful for investors and financial analysts who seek to make informed decisions based on quantitative data."
    );
  }

  /**
   * Gets the analysis method.
   *
   * <p>The evaluation method is a short description of how the analysis is performed.
   *
   * @return the analysis method
   */
  @Override
  public String getAnalysisMethodInfo() {
    return String.format(
        "Evaluation Method Explained:%n%n"
            + "The regression analysis involves several key steps:%n"
            + "1. **Data Collection**: Gathering historical stock price data and financial variables from the company's income statements.%n"
            + "2. **Data Indexing**: Normalizing the financial variables to create a consistent data set for analysis.%n"
            + "3. **Model Creation**: Applying linear regression to determine the relationship between each financial variable and the stock price. "
            + "This involves calculating the coefficients that represent the slope and intercept of the regression line.%n"
            + "4. **Prediction**: Using the regression model to predict future stock prices based on the established relationships.%n%n"
            + "The outcome of this analysis provides insights into which financial metrics are most strongly correlated with stock price movements, enabling more accurate predictions and better investment decisions."
    );
  }

  /**
   * Gets the detailed description of the analysis.
   *
   * @return the detailed description of the analysis
   */
  @Override
  public String getResultDescription() {
    StringBuilder detailedDescription = new StringBuilder(String.format(
        "Regression Analysis Results for %s:%n%n", symbol
    ));
    for (RegressionResult result : regressionResults) {
      detailedDescription.append(String.format(
          "Variable: %s%n"
              + "Intercept: %.2f%n"
              + "Slope: %.2f%n"
              + "R-Square: %.2f%n"
              + "Current Price: %.2f%n"
              + "Predicted Price: %.2f%n%n",
          result.getVariable(),
          result.getCoefficients()[0],
          result.getCoefficients()[1],
          result.getRSquare(),
          result.getLatestPrediction().getCurrentPrice(),
          result.getLatestPrediction().getPredictedPrice()
      ));
    }
    return detailedDescription.toString();
  }

  /**
   * Fetches the adjusted closing prices from the daily price data.
   *
   * @param priceData the list of daily stock prices
   * @return an array of adjusted closing prices
   */
  private double[] fetchAdjustedData(List<DailyDataPoint> priceData) {
    double[] priceDataArray = new double[priceData.size()];
    for (int i = 0; i < priceData.size(); i++) {
      priceDataArray[i] = priceData.get(i).getAdjustedClose();
    }
    return priceDataArray;
  }

  /**
   * Fetches and formats the results of the regression analysis.
   */
  public void fetchResults() {
    RegressionResult[] sortedArray = bubbleSort();
    result = String.format(
        "The highest r-square values for (%s) are: \n" + sortedArray[0].getRSquare() + " from " +
            sortedArray[0].getVariable() +  ". \n" +
            sortedArray[1].getRSquare() + " from " + sortedArray[1].getVariable() +  ". \n" +
            sortedArray[2].getRSquare() + " from " + sortedArray[2].getVariable() +  ".",
        symbol
    );
  }

  /**
   * Sorts the regression results based on the r-square values in descending order.
   *
   * @return a sorted array of RegressionResult
   */
  private RegressionResult[] bubbleSort() {
    RegressionResult[] regressionArray = regressionResults.toArray(new RegressionResult[0]);
    int n = regressionArray.length;
    RegressionResult temp;
    for(int i = 0; i < n; i++) {
      for(int j = 1; j < (n - i); j++) {
        if (regressionArray[j-1].getRSquare() < regressionArray[j].getRSquare()) {
          temp = regressionArray[j-1];
          regressionArray[j-1] = regressionArray[j];
          regressionArray[j] = temp;
        }
      }
    }
    return regressionArray;
  }

  private List<RegressionResult> listSort() {
    regressionResults.sort(Comparator.comparingDouble(RegressionResult::getRSquare).reversed());
    System.out.println("List sort");
    for (RegressionResult result : regressionResults) {
      System.out.println(result.getVariable() + " R^2 = " + result.getRSquare());
    }
    return regressionResults;
  }

  /**
   * Method for debugging the regression data.
   * <p>
   * This method is used for debugging the regression data, checking the dataset for the both X and
   * Y values. The method prints out the following information:
   * <ul>
   *   <li>Size of the datasets</li>
   *   <li>Start dates of the datasets</li>
   *   <li>End dates of the datasets</li>
   * </ul>
   * @param incomeStatements the retrieved income statement data
   * @param priceData the retrieved price data
   */
  private void debugRegressionData(List<IncomeStatement> incomeStatements, List<DailyDataPoint> priceData) {
    System.out.println("Regression Data:\n");
    System.out.println("\tDataSize:");
    System.out.println("\t Income Statements Size: " + incomeStatements.size());
    System.out.println("\t        Price Data Size: " + priceData.size() + "\n");
    System.out.println("\tStart Dates:");
    System.out.println("\t Income Statements Date: " + incomeStatements.getFirst().getFiscalDateEnding());
    System.out.println("\t  Price Data Start Date: " + priceData.getFirst().getDate() + "\n");
    System.out.println("\tEnd Dates:");
    System.out.println("\t Income Statements Date: " + incomeStatements.getLast().getFiscalDateEnding());
    System.out.println("\t  Price Data Start Date: " + priceData.getLast().getDate() + "\n");
  }

}
