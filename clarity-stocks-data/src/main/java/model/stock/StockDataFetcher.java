package model.stock;

import alphaVantage.AlphaVantageClient;
import common.data.global.GlobalMarketInfo;
import java.io.FileInputStream;
import java.util.Properties;

public class StockDataFetcher {

  private AlphaVantageClient alphaVantageClient;

  private String apiKey;

  public StockDataFetcher() {
    try {
      Properties properties = new Properties();
      properties.load(new FileInputStream("clarity-stocks-data/src/main/resources/apikey.properties"));
      apiKey = properties.getProperty("apiKey");
    } catch (Exception e) {
      e.printStackTrace();
    }
    alphaVantageClient = new AlphaVantageClient(apiKey);
  }

  public StockData fetchStockData(String symbol) {
    StockData stockData = new StockData();
    stockData.setCompanyOverview(alphaVantageClient.getFullStockOverview(symbol));
    stockData.setTimeSeriesDaily(alphaVantageClient.getTimeSeriesDaily(symbol));
    stockData.setTimeSeriesMonthly(alphaVantageClient.getTimeSeriesMonthly(symbol));
    stockData.setIncomeStatements(alphaVantageClient.getIncomeStatements(symbol));
    stockData.runEvaluations();
    stockData.runAnalyses();
    return stockData;
  }

  public GlobalMarketInfo fetchGlobalMarketInfo() {
    return alphaVantageClient.getGlobalMarketInfo();
  }

}
