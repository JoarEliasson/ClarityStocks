package test;

import alphaVantage.AlphaVantageClient;
import common.data.fundamental.CompanyOverview;
import common.data.series.TimeSeriesDaily;
import dao.CompanyOverviewDAO;
import dao.DBConnectionPool;
import dao.TimeSeriesDailyDAO;

public class Test {
  public static void main(String[] args) {
    AlphaVantageClient client = new AlphaVantageClient("YKB1S8EYZ61LDH9B");
    DBConnectionPool dbConnectionPool = new DBConnectionPool();

    //TimeSeriesDaily timeSeriesDaily = client.getTimeSeriesDaily("AAPL");
    TimeSeriesDailyDAO timeSeriesDailyDAO = new TimeSeriesDailyDAO(dbConnectionPool.getConnection());
    //timeSeriesDailyDAO.batchInsertTimeSeriesDailyQuery(timeSeriesDaily);
    //timeSeriesDailyDAO.getDailyDataQuery("AAPL");

    //CompanyOverview companyOverview = client.getFullStockOverview("AAPL");
    CompanyOverviewDAO companyOverviewDAO = new CompanyOverviewDAO(dbConnectionPool.getConnection());
    //companyOverviewDAO.insertFundamentalData(companyOverview);
    System.out.println(companyOverviewDAO.getCompanyOverviewQuery("AAPL").toString());
  }
}