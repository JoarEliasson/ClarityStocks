package model.stock;

import java.util.ArrayList;
import java.util.List;
import model.search.SearchList;

/**
 * <h1>DatabaseInitializer</h1
 * <p>
 * This class is designed to handle operations for initializing the database by inserting market
 * and stock data using the BasicDAO class.
 * </p>
 * <ul>
 *   <li>Inserting market data into the database.</li>
 *   <li>Inserting stock data into the database.</li>
 * </ul>
 * @author Kasper Schröder
 * @author Joar Eliasson
 */
public class DatabaseInitializer {
  DBConnectionPool dbConnectionPool = new DBConnectionPool();
  BasicDAO basicDAO = new BasicDAO(dbConnectionPool.getConnection());

  public static void main(String[] args) {
    DatabaseInitializer databaseInitializer = new DatabaseInitializer();
    //method to insert market data into the market table
    databaseInitializer.initializeMarketData();
    //method to insert stock data into the stock table
    databaseInitializer.initializeStockData();
  }

  public void initializeMarketData() {
    MarketInfoReader marketInfoReader = new MarketInfoReader();

    List<String> identifiers = new ArrayList<>();
    List<String> names = new ArrayList<>();
    List<String> marketTypes = new ArrayList<>();
    List<String> regions = new ArrayList<>();
    List<String> cities = new ArrayList<>();
    List<String> timezones = new ArrayList<>();
    List<Integer> utcOffsets = new ArrayList<>();
    List<String> localOpenTimes = new ArrayList<>();
    List<String> localCloseTimes = new ArrayList<>();

    for (Market market : marketInfoReader.getMarkets()) {
      identifiers.add(market.getIdentifier());
      names.add(market.getName());
      marketTypes.add(market.getMarketType());
      regions.add(market.getRegion());
      cities.add(market.getCity());
      timezones.add(market.getTimezone());
      utcOffsets.add(market.getUtcOffset());
      localOpenTimes.add(market.getLocalOpenTime());
      localCloseTimes.add(market.getLocalCloseTime());
    }

    basicDAO.insertExchangeDataQuery(identifiers, names, marketTypes, regions, cities, timezones,
        utcOffsets, localOpenTimes, localCloseTimes);
  }

  public void initializeStockData() {
    SearchList searchList = new SearchList();

    List<String> symbols = new ArrayList<>();
    List<String> markets = new ArrayList<>();
    List<String> names = new ArrayList<>();
    List<String> fullNames = new ArrayList<>();
    List<String> sectors = new ArrayList<>();
    List<String> industries = new ArrayList<>();
    List<String> cities = new ArrayList<>();
    List<String> states = new ArrayList<>();
    List<String> countries = new ArrayList<>();

    for (StockInfo stockInfo : searchList.getSearchList()) {
      symbols.add(stockInfo.symbol());
      markets.add(stockInfo.exchange());
      names.add(stockInfo.name());
      fullNames.add(stockInfo.fullName());
      sectors.add(stockInfo.sector());
      industries.add(stockInfo.industry());
      cities.add(stockInfo.city());
      states.add(stockInfo.state());
      countries.add(stockInfo.country());
    }

    basicDAO.insertStockDataQuery(symbols, markets, names, fullNames, sectors, industries, cities,
        states, countries);
  }
}
