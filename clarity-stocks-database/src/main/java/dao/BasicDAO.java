package dao;


import java.sql.Time;
import java.util.List;
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

  public void insertStockDataQuery(List<String> symbols, List<String> markets, List<String> names,
      List<String> fullNames, List<String> sectors, List<String> industries, List<String> cities,
      List<String> states, List<String> countries) {
    try {
      var batchQueries = new java.util.ArrayList<org.jooq.Query>();

      for (int i = 0; i < symbols.size(); i++) {
        var query = connectionContext.insertInto(DSL.table("stock"),
          DSL.field("symbol"), DSL.field("exchange"), DSL.field("name"),
          DSL.field("full_name"), DSL.field("sector"), DSL.field("industry"),
          DSL.field("city"), DSL.field("state"), DSL.field("country"))
          .values(symbols.get(i), markets.get(i), names.get(i), fullNames.get(i),
              sectors.get(i), industries.get(i), cities.get(i), states.get(i), countries.get(i))
          .onConflict(DSL.field("symbol"))
          .doNothing();
        batchQueries.add(query);
      }

      connectionContext.batch(batchQueries).execute();
    } catch (DataAccessException e) {
      System.err.println("Error in insertStockDataQuery: " + e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void insertExchangeDataQuery(List<String> identifiers, List<String> names,
      List<String> marketTypes, List<String> regions, List<String> cities, List<String> timezones,
      List<Integer> utcOffsets, List<String> localOpenTimes, List<String> localCloseTimes) {
    try {
      var batchQueries = new java.util.ArrayList<org.jooq.Query>();

      for (int i = 0; i < identifiers.size(); i++) {
        var query = connectionContext.insertInto(DSL.table("market"),
          DSL.field("symbol"), DSL.field("name"), DSL.field("market_type"),
          DSL.field("region"), DSL.field("city"), DSL.field("timezone"),
          DSL.field("utc_offset"), DSL.field("local_open_time"),
          DSL.field("local_close_time"))
          .values(identifiers.get(i), names.get(i), marketTypes.get(i), regions.get(i),
              cities.get(i), timezones.get(i), utcOffsets.get(i),
              Time.valueOf(localOpenTimes.get(i)),
              Time.valueOf(localCloseTimes.get(i)))
          .onConflict(DSL.field("symbol"))
          .doNothing();
        batchQueries.add(query);
      }

      connectionContext.batch(batchQueries).execute();
    } catch (DataAccessException e) {
      System.err.println("Error in insertStockDataQuery: " + e.getMessage());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}