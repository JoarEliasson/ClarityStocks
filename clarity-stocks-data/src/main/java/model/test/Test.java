package model.test;

import common.data.series.TimeSeriesDaily;
import dao.CompanyOverviewDAO;
import dao.DBConnectionPool;
import dao.IncomeStatementDAO;
import dao.TimeSeriesDailyDAO;
import model.stock.StockDataFetcher;

public class Test {

  public static void main(String[] args) {
    DBConnectionPool dbConnectionPool = new DBConnectionPool();

    StockDataFetcher stockDataFetcher = new StockDataFetcher();

    //Works
    //TimeSeriesDailyDAO timeSeriesDailyDAO = new TimeSeriesDailyDAO(dbConnectionPool.getConnection());
    //TimeSeriesDaily timeSeriesDaily = stockDataFetcher.fetchStockData("AAPL").getTimeSeriesDaily();
    //timeSeriesDailyDAO.batchInsertTimeSeriesDailyQuery(timeSeriesDaily);
    //timeSeriesDailyDAO.getDailyDataQuery("GOOGL");

    //Works
    //CompanyOverviewDAO companyOverviewDAO = new CompanyOverviewDAO(dbConnectionPool.getConnection());
    //companyOverviewDAO.insertCompanyOverview(stockDataFetcher.fetchStockData("MSFT").getCompanyOverview());
    //if (companyOverviewDAO.getCompanyOverview("TSLA") == null) {
   //   System.out.println("Company not found");
    //} else {
    //  System.out.println("Company found");
   // }

    IncomeStatementDAO incomeStatementDAO = new IncomeStatementDAO(dbConnectionPool.getConnection());
    //incomeStatementDAO.insertIncomeStatement(stockDataFetcher.fetchStockData("MSFT").getIncomeStatements());
    incomeStatementDAO.getIncomeStatement("MSFT"); //works
  }
}
