package model.stock;

import alphaVantage.AlphaVantageClient;
import common.data.fundamental.CompanyOverview;
import common.data.fundamental.IncomeStatement;
import common.data.global.GlobalMarketInfo;
import dao.CompanyOverviewDAO;
import dao.DBConnectionPool;
import dao.IncomeStatementDAO;
import dao.TimeSeriesDailyDAO;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

public class StockDataFetcher {

  private AlphaVantageClient alphaVantageClient;

  private String apiKey;

  private DBConnectionPool dbConnectionPool;
  private CompanyOverviewDAO companyOverviewDAO;
  private IncomeStatementDAO incomeStatementDAO;
  private TimeSeriesDailyDAO timeSeriesDailyDAO;

  public StockDataFetcher() {
    try {
      Properties properties = new Properties();
      properties.load(new FileInputStream("clarity-stocks-data/src/main/resources/apikey.properties"));
      apiKey = properties.getProperty("apiKey");
      dbConnectionPool = new DBConnectionPool();
    } catch (Exception e) {
      e.printStackTrace();
    }
    alphaVantageClient = new AlphaVantageClient(apiKey);
    companyOverviewDAO = new CompanyOverviewDAO(dbConnectionPool.getConnection());
    incomeStatementDAO = new IncomeStatementDAO(dbConnectionPool.getConnection());
    timeSeriesDailyDAO = new TimeSeriesDailyDAO(dbConnectionPool.getConnection());
  }

  public StockData fetchStockData(String symbol) {
    StockData stockData = new StockData();

    CompanyOverview companyOverview = companyOverviewDAO.getCompanyOverview(symbol);
    if (companyOverview == null) {
      companyOverview = alphaVantageClient.getFullStockOverview(symbol);
      companyOverviewDAO.insertCompanyOverview(companyOverview);
    }
    stockData.setCompanyOverview(companyOverview);

    String latestUpdateDateString = timeSeriesDailyDAO.fetchLatestUpdateQuery(symbol);
    if (latestUpdateDateString == null) {
      stockData.setTimeSeriesDaily(alphaVantageClient.getTimeSeriesDaily(symbol));
      timeSeriesDailyDAO.batchInsertTimeSeriesDailyQuery(stockData.getTimeSeriesDaily());
    } else {
      LocalDate latestUpdateDate = LocalDate.parse(latestUpdateDateString);
      if (latestUpdateDate.isBefore(LocalDate.now().minusDays(3))) {
        stockData.setTimeSeriesDaily(alphaVantageClient.getTimeSeriesDaily(symbol));
        timeSeriesDailyDAO.batchInsertTimeSeriesDailyQuery(stockData.getTimeSeriesDaily());
      } else {
        stockData.setTimeSeriesDaily(timeSeriesDailyDAO.getDailyDataQuery(symbol));
      }
    }

    List<IncomeStatement> incomeStatements = incomeStatementDAO.getIncomeStatement(symbol);
    if (incomeStatements == null) {
      stockData.setIncomeStatements(alphaVantageClient.getIncomeStatements(symbol));
      incomeStatementDAO.insertIncomeStatement(stockData.getIncomeStatements());
    } else {
      stockData.setIncomeStatements(incomeStatements);
    }

    //stockData.setCompanyOverview(alphaVantageClient.getFullStockOverview(symbol));
    //stockData.setTimeSeriesDaily(alphaVantageClient.getTimeSeriesDaily(symbol));
    stockData.setTimeSeriesMonthly(alphaVantageClient.getTimeSeriesMonthly(symbol));
    //stockData.setIncomeStatements(alphaVantageClient.getIncomeStatements(symbol));
    stockData.runEvaluations();
    stockData.runAnalyses();
    return stockData;
  }

  public GlobalMarketInfo fetchGlobalMarketInfo() {
    return alphaVantageClient.getGlobalMarketInfo();
  }

}
