package alphaVantage.model.data.series;

import java.util.ArrayList;
import java.util.List;

public class TimeSeriesDaily {

  private final String symbol;
  private String lastRefreshed;
  private List<DailyDataPoint> dailyData = new ArrayList<>();

  public TimeSeriesDaily(String symbol) {
    this.symbol = symbol;
  }

  public void addDailyDataPoint(DailyDataPoint dataPoint) {
    dailyData.add(dataPoint);
  }

  public List<DailyDataPoint> getDailyDataInRange(String startDate, String endDate) {
    List<DailyDataPoint> dataInRange = new ArrayList<>();
    for (DailyDataPoint dataPoint : dailyData) {
      if (dataPoint.getDate().compareTo(startDate) >= 0 &&
          dataPoint.getDate().compareTo(endDate) <= 0) {
        dataInRange.add(dataPoint);
      }
    }
    return dataInRange;
  }

  public List<DailyDataPoint> calculateMovingAverages(List<DailyDataPoint> data, int days) {
    List<DailyDataPoint> movingAverages = new ArrayList<>();
    int dataSize = data.size();
    for (int i = 0; i < dataSize; i++) {
      double sum = 0;
      int count = 0;
      for (int j = Math.max(0, i - days + 1); j <= Math.min(i, dataSize - 1); j++) {
        sum += data.get(j).getClose();
        count++;
      }
      double average = count > 0 ? sum / count : 0;
      movingAverages.add(new DailyDataPoint(data.get(i).getDate(), average));
    }
    return movingAverages;
  }

  public List<DailyDataPoint> calculateTrailingMovingAverage(List<DailyDataPoint> dataPoints,
      int days) {
    List<DailyDataPoint> movingAverages = new ArrayList<>();
    for (int i = 0; i < dataPoints.size(); i++) {
      double sum = 0;
      int count = 0;
      for (int j = Math.max(0, i - days + 1); j <= i; j++) {
        sum += dataPoints.get(j).getClose();
        count++;
      }
      movingAverages.add(new DailyDataPoint(dataPoints.get(i).getDate(),(sum / count)));
    }
    return movingAverages;
  }

  public List<DailyDataPoint> calculateCenteredMovingAverage(List<DailyDataPoint> dataPoints,
      int days) {
    List<DailyDataPoint> movingAverages = new ArrayList<>();
    int halfWindow = days / 2;
    for (int i = 0; i < dataPoints.size(); i++) {
      double sum = 0;
      int count = 0;
      for (int j = Math.max(0, i - halfWindow); j <=
          Math.min(dataPoints.size() - 1, i + halfWindow); j++) {
        sum += dataPoints.get(j).getClose();
        count++;
      }
      movingAverages.add(new DailyDataPoint(dataPoints.get(i).getDate(),(sum / count)));
    }
    return movingAverages;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getLastRefreshed() {
    return lastRefreshed;
  }

  public void setLastRefreshed(String lastRefreshed) {
    this.lastRefreshed = lastRefreshed;
  }

  public List<DailyDataPoint> getDailyData() {
    return dailyData;
  }

  public void setDailyData(List<DailyDataPoint> dailyData) {
    this.dailyData = dailyData.reversed();
  }

  @Override
  public String toString() {
    return String.format(
        "Time Series Daily for [%s] last refreshed on %s",
        symbol, lastRefreshed
    );
  }
}
