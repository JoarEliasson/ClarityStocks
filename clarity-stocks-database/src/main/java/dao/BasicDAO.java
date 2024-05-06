package dao;

import model.stock.StockInfo;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;


/**
 * The class manages the database interactions related to the data necessary for both stocks
 * and exchange markets.
 * <p>
 * This class is designed to handle operations for storing basic financial data
 * such as stock and market information through PostgreSQL queries using the jOOQ framework.
 * </p>
 * <p>The main functionalities include:</p>
 * <ul>
 *  <li>Inserting stock data into the database.</li>
 *  <li>Inserting market data into the database.</li>
 * </ul>
 * @author Kasper Schröder
 */
public class BasicDAO {
  private final DSLContext connectionContext;

  public BasicDAO(DSLContext connectionContext) {
    this.connectionContext = connectionContext;
  }

  public void insertStockDataQuery(StockInfo stockInfo) {
    try {
      connectionContext.insertInto(DSL.table("stock"),
              DSL.field("symbol"), DSL.field("companyname"), DSL.field("marketsymbol"))
          .values(stockInfo.symbol(), stockInfo.name(),
              stockInfo.exchange())
          .onConflict(DSL.field("symbol"))
          .doNothing()
          .execute();

    } catch (DataAccessException e) {
      System.err.println("Error in insertStockDataQuery: " + e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void insertExchangeDataQuery(String symbol, String name, String country) {
    try {
      connectionContext.insertInto(DSL.table("market"),
              DSL.field("symbol"), DSL.field("name"), DSL.field("country"))
          .values(symbol, name, country)
          .onConflict(DSL.field("symbol"))
          .doNothing()
          .execute();

    } catch (DataAccessException e) {
      System.err.println("Error in insertStockDataQuery: " + e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}