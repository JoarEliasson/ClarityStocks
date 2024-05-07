package test;

import dao.BasicDAO;
import dao.CompanyOverviewDAO;
import dao.DBConnectionPool;
import dao.TimeSeriesDailyDAO;
import model.search.SearchList;
import model.stock.StockData;
import model.stock.StockDataFetcher;
import model.stock.StockInfo;

public class Test {
  public static void main(String[] args) {
    SearchList searchList = new SearchList();
    StockDataFetcher stockDataFetcher = new StockDataFetcher();
    DBConnectionPool dbConnectionPool = new DBConnectionPool();


    String[] symbols = {"MSFT", "AMZN", "FB"};
    for (String symbol : symbols) {
      StockInfo stockInfo = searchList.getStockInfo(symbol);
      if (stockInfo == null) {
        System.out.println("Stock not found: " + symbol);
      } else {
        System.out.println("Stock found: " + stockInfo.symbol());
        System.out.println("Inserting basic stock data into database...");
        BasicDAO basicDAO = new BasicDAO(dbConnectionPool.getConnection());
        basicDAO.insertStockDataQuery(stockInfo);
        System.out.println("Fetching stock data...");
        StockData stockData = stockDataFetcher.fetchStockData(stockInfo.symbol());
        System.out.println("Inserting stock data into database...");
        TimeSeriesDailyDAO timeSeriesDailyDAO = new TimeSeriesDailyDAO(dbConnectionPool.getConnection());
        timeSeriesDailyDAO.batchInsertTimeSeriesDailyQuery(stockData.getTimeSeriesDaily());
        if (timeSeriesDailyDAO.getDailyDataQuery(stockInfo.symbol()) != null) {
          System.out.println("Stock data successfully inserted into database.");
        } else {
          System.out.println("Error inserting stock data into database.");
          break;
        }
        CompanyOverviewDAO companyOverviewDAO = new CompanyOverviewDAO(dbConnectionPool.getConnection());
        companyOverviewDAO.insertFundamentalData(stockData.getCompanyOverview());
        if (companyOverviewDAO.getCompanyOverviewQuery(stockInfo.symbol()) != null) {
          System.out.println("Fundamental data successfully inserted into database.");
        } else {
          System.out.println("Error inserting fundamental data into database.");
          break;
        }
        System.out.println("Finished inserting data for " + stockInfo.symbol());
      }
    }

    //Sample code for testing individual components
    //TimeSeriesDaily timeSeriesDaily = stockDataFetcher.fetchStockData("GOOGL").getTimeSeriesDaily();
    //TimeSeriesDailyDAO timeSeriesDailyDAO = new TimeSeriesDailyDAO(dbConnectionPool.getConnection());
    //timeSeriesDailyDAO.batchInsertTimeSeriesDailyQuery(timeSeriesDaily);
    //timeSeriesDailyDAO.getDailyDataQuery("GOOGL");

    //CompanyOverview companyOverview = client.getFullStockOverview("AAPL");
    //CompanyOverviewDAO companyOverviewDAO = new CompanyOverviewDAO(dbConnectionPool.getConnection());
    //companyOverviewDAO.insertFundamentalData(companyOverview);
    //System.out.println(companyOverviewDAO.getCompanyOverviewQuery("AAPL").toString());
  }
}