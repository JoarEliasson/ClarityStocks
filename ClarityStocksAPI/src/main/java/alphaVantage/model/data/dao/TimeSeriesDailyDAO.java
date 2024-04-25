package alphaVantage.model.data.dao;

import alphaVantage.model.data.series.DailyDataPoint;
import alphaVantage.model.data.series.TimeSeriesDaily;
import org.jooq.DSLContext;
import org.jooq.Result;
import org.jooq.impl.DSL;
import org.jooq.Record;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TimeSeriesDailyDAO {
    private final DSLContext connectionContext;

    public TimeSeriesDailyDAO(DSLContext connection) {
        this.connectionContext = connection;
    }

    public void batchInsertDailyDataPoints(String symbol, DailyDataPoint[] dailyDataPoints) {
        try {
            connectionContext.transaction(configuration ->{
                DSLContext transactionContext = DSL.using(configuration);
                for (DailyDataPoint dailyDataPoint : dailyDataPoints) {
                    transactionContext.execute(
                            "CALL insertdailydata(?, ?, ?, ?, ?, ?, ?)",
                            symbol,
                            java.sql.Date.valueOf(dailyDataPoint.getDate()),
                            dailyDataPoint.getOpen(),
                            dailyDataPoint.getHigh(),
                            dailyDataPoint.getLow(),
                            dailyDataPoint.getClose(),
                            dailyDataPoint.getVolume()
                    );
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public TimeSeriesDaily getDailyData(String symbol) {
        try {
            TimeSeriesDaily timeSeriesDaily = new TimeSeriesDaily(symbol);

            timeSeriesDaily.setLastRefreshed(fetchLatestUpdate(symbol));

            Result<Record> result = connectionContext.fetch(
                    "SELECT stockSymbol, date, dailyOpen, dailyHigh, dailyLow, dailyClose, dailyVolume " +
                        "FROM getTimeSeriesDailyData(?)",
                    symbol
            );

            List<DailyDataPoint> dailyData = new ArrayList<>();
            for (Record record : result) {
                DailyDataPoint dataPoint = new DailyDataPoint(
                        record.getValue("date", String.class),
                        record.getValue("dailyOpen", Double.class),
                        record.getValue("dailyHigh", Double.class),
                        record.getValue("dailyLow", Double.class),
                        record.getValue("dailyClose", Double.class),
                        record.getValue("dailyVolume", Long.class)
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

    public String fetchLatestUpdate(String symbol) throws SQLException {
        Result<Record> result1 = connectionContext.fetch(
                "SELECT getLatestUpdate(?) " +
                    "as lastUpdated",
                symbol
        );

        Date lastUpdated = result1.getValue(0, DSL.field("lastUpdated", Date.class));
        return lastUpdated.toString();
    }



}
