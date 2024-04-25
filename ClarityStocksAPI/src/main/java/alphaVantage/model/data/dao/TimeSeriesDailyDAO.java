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
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}
