package alphaVantage.regression;

import alphaVantage.controller.AlphaVantageClient;
import alphaVantage.model.data.fundamental.IncomeStatement;

import java.util.ArrayList;
import java.util.List;

/**
 *  Class for making the analysis of the linear regression. The linear regression requires some more
 *  analysis which makes having a separate class for these more comprehensible.
 * @author Olivia Svensson
 * */

public class LinearRegressionAnalysis {
  private LinearRegression[] linearRegressions;
  private IncomeStatement incomeStatement;
  private AlphaVantageClient alphaVantageClient;
  private String symbol;
  private long[] incomeStatementsList;
  private int numberOfIncomeStatements;
  private List<String> results = new ArrayList<>();

  public LinearRegressionAnalysis(IncomeStatement incomeStatement, String symbol, AlphaVantageClient alphaVantageClient){
    this.incomeStatement = incomeStatement;
    this.symbol = symbol;
    this.alphaVantageClient = alphaVantageClient;
    numberOfIncomeStatements = 24;
    incomeStatementsList = new long[numberOfIncomeStatements];

    //amount of statements in IncomeStatement class which are able to run the analysis on
    linearRegressions = new LinearRegression[numberOfIncomeStatements];
  }

  private void populateIncomeStatementList() {
    incom

   }

  public LinearRegression[] getLinearRegressions() {
    return this.linearRegressions;
  }

  private void makeLinearRegressions() {
    for(int i = 0; i < linearRegressions.length; i++) {
      linearRegressions[i] = new LinearRegression(symbol, );
    }
  }

}
