package data.stock;

import alphaVantage.AlphaVantageClient;
import common.data.fundamental.CompanyOverview;
import common.data.fundamental.IncomeStatement;
import common.data.global.GlobalMarketInfo;
import common.data.series.TimeSeriesDaily;
import common.data.series.TimeSeriesMonthly;
import data.database.DBConnectionPool;
import data.database.dao.CompanyOverviewDAO;
import data.database.dao.IncomeStatementDAO;
import data.database.dao.TimeSeriesDailyDAO;
import data.database.dao.TimeSeriesMonthlyDAO;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;

/**
 *<h1>StockDataFetcher</h1>
 * <p>
 * This class is responsible for fetching stock data from the Alpha Vantage API and the database.
 * First checks whether there is data within the database and if it's recent enough to be relevant,
 * if not it fetches the data from the API, stores it in the database and returns it as a StockData
 * object.
 * </p>
 * @author Joar Eliasson
 * @author Kasper Schröder
 */
public class StockDataFetcher {

  private final AlphaVantageClient alphaVantageClient;

  private String apiKey;

  private DBConnectionPool dbConnectionPool;
  private boolean useDatabase;
  private CompanyOverviewDAO companyOverviewDAO;
  private IncomeStatementDAO incomeStatementDAO;
  private TimeSeriesDailyDAO timeSeriesDailyDAO;
  private TimeSeriesMonthlyDAO timeSeriesMonthlyDAO;

  public StockDataFetcher() {
    try {
      Properties properties = new Properties();
      properties.load(new FileInputStream("clarity-stocks-data/src/main/resources/apikey.properties"));
      apiKey = properties.getProperty("apiKey");
    } catch (Exception e) {
      e.printStackTrace();
    }
    alphaVantageClient = new AlphaVantageClient(apiKey);

    try {
      dbConnectionPool = new DBConnectionPool();
      if (dbConnectionPool.isConnectionValid()) {
        useDatabase = true;
        companyOverviewDAO = new CompanyOverviewDAO(dbConnectionPool.getConnection());
        incomeStatementDAO = new IncomeStatementDAO(dbConnectionPool.getConnection());
        timeSeriesDailyDAO = new TimeSeriesDailyDAO(dbConnectionPool.getConnection());
        timeSeriesMonthlyDAO = new TimeSeriesMonthlyDAO(dbConnectionPool.getConnection());
      } else {
        useDatabase = false;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public StockData fetchStockData(String symbol) {
    StockData stockData = new StockData();

    if (useDatabase) {
      stockData.setCompanyOverview(fetchCompanyOverview(symbol));
      stockData.setTimeSeriesDaily(fetchTimeSeriesDaily(symbol));
      stockData.setTimeSeriesMonthly(fetchTimeSeriesMonthly(symbol));
      stockData.setIncomeStatements(fetchIncomeStatements(symbol));
    } else {
      stockData.setCompanyOverview(alphaVantageClient.getFullStockOverview(symbol));
      stockData.setTimeSeriesDaily(alphaVantageClient.getTimeSeriesDaily(symbol));
      stockData.setTimeSeriesMonthly(alphaVantageClient.getTimeSeriesMonthly(symbol));
      stockData.setIncomeStatements(alphaVantageClient.getIncomeStatements(symbol));
    }

    stockData.setCashFlowReports(alphaVantageClient.getCashFlowReports(symbol));

    stockData.runEvaluations();
    stockData.runAnalyses();
    return stockData;
  }

  public CompanyOverview fetchCompanyOverview(String symbol) {
    CompanyOverview companyOverview = companyOverviewDAO.getCompanyOverview(symbol);
    if (companyOverview == null) {
      companyOverview = alphaVantageClient.getFullStockOverview(symbol);
      companyOverviewDAO.insertCompanyOverview(companyOverview);
    }
    return companyOverview;
  }

  public TimeSeriesDaily fetchTimeSeriesDaily(String symbol) {
    TimeSeriesDaily timeSeriesDaily;
    String latestDailyUpdateDateString = timeSeriesDailyDAO.fetchLatestUpdateQuery(symbol);
    if (latestDailyUpdateDateString == null) {
      timeSeriesDaily = alphaVantageClient.getTimeSeriesDaily(symbol);
      timeSeriesDailyDAO.batchInsertTimeSeriesDailyQuery(timeSeriesDaily);
      return timeSeriesDaily;
    } else {
      LocalDate latestUpdateDate = LocalDate.parse(latestDailyUpdateDateString);
      if (latestUpdateDate.isBefore(LocalDate.now().minusDays(3))) {
        timeSeriesDaily = alphaVantageClient.getTimeSeriesDaily(symbol);
        timeSeriesDailyDAO.batchInsertTimeSeriesDailyQuery(timeSeriesDaily);
        return timeSeriesDaily;
      } else {
        return timeSeriesDailyDAO.getDailyDataQuery(symbol);
      }
    }
  }

  public TimeSeriesMonthly fetchTimeSeriesMonthly(String symbol) {
    TimeSeriesMonthly timeSeriesMonthly;
    String latestMonthlyUpdateDateString = timeSeriesMonthlyDAO.fetchLatestUpdateQuery(symbol);
    if (latestMonthlyUpdateDateString == null) {
      timeSeriesMonthly = alphaVantageClient.getTimeSeriesMonthly(symbol);
      timeSeriesMonthlyDAO.batchInsertTimeSeriesMonthlyQuery(timeSeriesMonthly);
      return timeSeriesMonthly;
    } else {
      LocalDate latestUpdateDate = LocalDate.parse(latestMonthlyUpdateDateString);
      if (latestUpdateDate.isBefore(LocalDate.now().minusMonths(1))) {
        timeSeriesMonthly = alphaVantageClient.getTimeSeriesMonthly(symbol);
        timeSeriesMonthlyDAO.batchInsertTimeSeriesMonthlyQuery(timeSeriesMonthly);
        return timeSeriesMonthly;
      } else {
        return timeSeriesMonthlyDAO.getMonthlyDataQuery(symbol);
      }
    }
  }


  public List<IncomeStatement> fetchIncomeStatements(String symbol) {
    List<IncomeStatement> incomeStatements = incomeStatementDAO.getIncomeStatement(symbol);
    if (incomeStatements == null) {
      incomeStatements = alphaVantageClient.getIncomeStatements(symbol);
      incomeStatementDAO.insertIncomeStatement(incomeStatements);
    }
    return incomeStatements;
  }

  public GlobalMarketInfo fetchGlobalMarketInfo() {
    return alphaVantageClient.getGlobalMarketInfo();
  }
}
