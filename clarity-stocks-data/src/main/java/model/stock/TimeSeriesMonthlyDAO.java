package model.stock;

import common.data.series.DailyDataPoint;
import common.data.series.TimeSeriesMonthly;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;

/**
 * <h1>TimeSeriesMonthlyDAO</h1>
 * <p>
 * This class is designed to handle operations for storing and retrieving monthly data points of
 * stock prices through stored PostgreSQL procedures and functions using the jOOQ framework.
 * </p>
 * <ul>
 * <li>Inserting monthly data points of a stock into the database.</li>
 * <li>Retrieving monthly data points of a stock from the database.</li>
 * <li>Fetching when the data was last updated</li>
 * </ul>
 * @author Kasper Schröder
 */
public class TimeSeriesMonthlyDAO {
  private final DSLContext connectionContext;

  public TimeSeriesMonthlyDAO(DSLContext connection) {
    this.connectionContext = connection;
  }

  public void batchInsertTimeSeriesMonthlyQuery(TimeSeriesMonthly timeSeriesMonthly) {
    try {
      String symbol = timeSeriesMonthly.getSymbol();
      List<DailyDataPoint> dailyDataPoints = timeSeriesMonthly.getMonthlyData();

      var batchQueries = new ArrayList<Query>();
      for (DailyDataPoint dailyDataPoint : dailyDataPoints) {
        var query = connectionContext.insertInto(DSL.table("time_series_monthly"),
                DSL.field("stock_symbol"), DSL.field("date"),
                DSL.field("monthly_open"), DSL.field("monthly_high"),
                DSL.field("monthly_low"), DSL.field("monthly_close"),
                DSL.field("monthly_volume"))
            .values(symbol, java.sql.Date.valueOf(dailyDataPoint.getDate()),
                dailyDataPoint.getOpenFormatted(), dailyDataPoint.getHighFormatted(),
                dailyDataPoint.getLowFormatted(), dailyDataPoint.getCloseFormatted(),
                dailyDataPoint.getVolume())
            .onConflict(DSL.field("stock_symbol"), DSL.field("date")).doNothing();
        batchQueries.add(query);
      }

      connectionContext.batch(batchQueries).execute();
    } catch (DataAccessException e) {
      System.err.println("Error executing batch insert: " + e.getMessage());
    } catch (Exception e) {
      System.err.println("Error in batchInsertTimeSeriesDailyQuery " + e.getMessage());
    }
  }

  public TimeSeriesMonthly getMonthlyDataQuery(String symbol) {
    try {
      TimeSeriesMonthly timeSeriesMonthly = new TimeSeriesMonthly(symbol);
      timeSeriesMonthly.setLastRefreshed(fetchLatestUpdateQuery(symbol));

      Result<Record> result = connectionContext.select()
          .from("time_series_monthly")
          .where("stock_Symbol = ?", symbol)
          .orderBy(DSL.field("date").desc())
          .fetch();

      if (result.isEmpty()) {
        return null;
      }

      List<DailyDataPoint> dailyData = new ArrayList<>();
      for (Record record : result) {
        record.getValue("date", String.class);
        DailyDataPoint dataPoint = new DailyDataPoint(
            record.getValue("date", String.class),
            record.getValue("monthly_open", Double.class),
            record.getValue("monthly_high", Double.class),
            record.getValue("monthly_low", Double.class),
            record.getValue("monthly_close", Double.class),
            record.getValue("monthly_volume", Long.class)
        );
        dailyData.add(dataPoint);
      }
      timeSeriesMonthly.setMonthlyData(dailyData);

      return timeSeriesMonthly;
    } catch (DataAccessException e) {
      System.err.println("Error retrieving daily data: " + e.getMessage());
      return null;
    } catch (Exception e) {
      System.err.println("Error in getDailyDataQuery: " + e.getMessage());
      return null;
    }
  }

  public String fetchLatestUpdateQuery(String symbol)  {
    try {
      Result<Record> result = connectionContext.fetch(
          "select max(date) "
              + "from time_series_monthly "
              + "where stock_symbol = ?",
          symbol
      );

      if (result.isEmpty()) {
        return null;
      }

      Date lastUpdated = result.getValue(0, DSL.field("max", Date.class));
      if (lastUpdated != null) {
        return lastUpdated.toString();
      } else {
        return null;
      }
    } catch (DataAccessException e) {
      System.err.println("Error fetching latest update: " + e.getMessage());
      return null;
    } catch (Exception e) {
      System.err.println("Error in fetchLatestUpdateQuery: " + e.getMessage());
      return null;
    }
  }

}
