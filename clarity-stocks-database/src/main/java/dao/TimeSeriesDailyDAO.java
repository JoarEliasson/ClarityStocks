package dao;

import common.data.series.TimeSeriesDaily;
import common.data.series.DailyDataPoint;
import java.sql.Date;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.jooq.Record;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The class manages the database interactions related to daily financial time series data.
 * <p>
 * This class is designed to handle operations for storing and retrieving daily data points of
 * stock prices through stored PostgreSQL procedures and functions using the jOOQ framework.
 * </p>
 * <p>The main functionalities include:</p>
 * <ul>
 *  <li>Inserting daily data points of a stock into the database.</li>
 *  <li>Retrieving daily data points of a stock from the database.</li>
 * </ul>
 * @author Kasper Schröder
 */
public class TimeSeriesDailyDAO {
  private final DSLContext connectionContext;

  public TimeSeriesDailyDAO(DSLContext connection) {
    this.connectionContext = connection;
  }

  public void batchInsertTimeSeriesDailyQuery(TimeSeriesDaily timeSeriesDaily) {
    try {
      String symbol = timeSeriesDaily.getSymbol();
      List<DailyDataPoint> dailyDataPoints = timeSeriesDaily.getDailyData();

      var batchQueries = new ArrayList<org.jooq.Query>();
      for (DailyDataPoint dailyDataPoint : dailyDataPoints) {
        var query = connectionContext.insertInto(DSL.table("timeSeriesDaily"),
                DSL.field("stocksymbol"), DSL.field("date"), DSL.field("dailyopen"),
                DSL.field("dailyhigh"), DSL.field("dailylow"), DSL.field("dailyclose"),
                DSL.field("dailyvolume"))
            .values(symbol, java.sql.Date.valueOf(dailyDataPoint.getDate()),
                dailyDataPoint.getOpenFormatted(), dailyDataPoint.getHighFormatted(),
                dailyDataPoint.getLowFormatted(), dailyDataPoint.getCloseFormatted(),
                dailyDataPoint.getVolume())
            .onConflict(DSL.field("stocksymbol"), DSL.field("date")).doNothing();
        batchQueries.add(query);
      }

      connectionContext.batch(batchQueries).execute();
    } catch (DataAccessException e) {
      System.err.println("Error executing batch insert: " + e.getMessage());
      e.printStackTrace();
    } catch (Exception e) {
      System.err.println("Error in batchInsertTimeSeriesDailyQuery " + e.getMessage());
    }
  }

  public TimeSeriesDaily getDailyDataQuery(String symbol) {
    try {
      TimeSeriesDaily timeSeriesDaily = new TimeSeriesDaily(symbol);
      timeSeriesDaily.setLastRefreshed(fetchLatestUpdateQuery(symbol));

      Result<Record> result = connectionContext.select()
          .from("timeSeriesDaily")
          .where("stockSymbol = ?", symbol)
          .orderBy(DSL.field("date").asc())
          .fetch();


      List<DailyDataPoint> dailyData = new ArrayList<>();
      for (Record record : result) {
        record.getValue("date", String.class);
        DailyDataPoint dataPoint = new DailyDataPoint(
            record.getValue("date", String.class),
            record.getValue("dailyopen", Double.class),
            record.getValue("dailyhigh", Double.class),
            record.getValue("dailylow", Double.class),
            record.getValue("dailyclose", Double.class),
            record.getValue("dailyvolume", Long.class)
        );
        dailyData.add(dataPoint);
      }
      timeSeriesDaily.setDailyData(dailyData);

      return timeSeriesDaily;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public String fetchLatestUpdateQuery(String symbol) throws SQLException {
    try {
      Result<Record> result = connectionContext.fetch(
          "select max(date) "
              + "from timeSeriesDaily "
              + "where stockSymbol = ?",
          symbol
      );

      Date lastUpdated = result.getValue(0, DSL.field("max", Date.class));
      return lastUpdated.toString();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }



}