package alphaVantage.model.data.dao;

import alphaVantage.model.data.series.DailyDataPoint;
import alphaVantage.model.data.series.TimeSeriesDaily;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

public class TimeSeriesDailyDAO {
    private final DSLContext connection;

    public TimeSeriesDailyDAO(DSLContext connection) {
        this.connection = connection;
    }

    public void batchInsertDailyDataPoints(String symbol, DailyDataPoint[] dailyDataPoints) {
        try {
            connection.transaction(configuration ->{
                DSLContext transactionContext = DSL.using(configuration);
                for (DailyDataPoint dailyDataPoint : dailyDataPoints) {
                    transactionContext.execute("CALL insertdailydata(?, ?, ?, ?, ?, ?, ?)",
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
            connection.select().from("daily_data").where("symbol = ?", symbol).fetch().forEach(record -> {
                timeSeriesDaily.addDailyDataPoint(new DailyDataPoint(
                        record.get("date", String.class),
                        record.get("open", Double.class),
                        record.get("high", Double.class),
                        record.get("low", Double.class),
                        record.get("close", Double.class),
                        record.get("volume", Integer.class)
                ));
            });
            return timeSeriesDaily;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
