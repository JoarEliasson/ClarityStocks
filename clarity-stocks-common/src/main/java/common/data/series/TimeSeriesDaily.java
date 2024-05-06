package common.data.series;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TimeSeriesDaily {

  private final String symbol;
  private String lastRefreshed;
  private List<DailyDataPoint> dailyData = new ArrayList<>();

  public TimeSeriesDaily(String symbol) {
    this.symbol = symbol;
  }

  public List<DailyDataPoint> getDailyDataInRange(String startDate, String endDate) {
    if (startDate == null || endDate == null) {
      return dailyData;
    }
    List<DailyDataPoint> dataInRange = new ArrayList<>();
    for (DailyDataPoint dataPoint : dailyData) {
      if (dataPoint.getDate().compareTo(startDate) >= 0 &&
          dataPoint.getDate().compareTo(endDate) <= 0) {
        dataInRange.add(dataPoint);
      }
    }
    return dataInRange;
  }

  public List<DailyDataPoint> getDailyDataLastYear() {
    List<DailyDataPoint> reversedData = dailyData.reversed();
    List<DailyDataPoint> dataLastYear = new ArrayList<>();
    for (int i = 0; i < 252; i++) {
      dataLastYear.add(reversedData.get(i));
    }
    return dataLastYear.reversed();
  }

  public List<DailyDataPoint> getDailyDataLastYearToDate() {
    List<DailyDataPoint> dataLastYearToDate = new ArrayList<>();
    LocalDate today = LocalDate.now();
    for (DailyDataPoint dataPoint : dailyData) {
      if (dataPoint.getDate().substring(0, 4).equals(String.valueOf(today.getYear())) &&
          dataPoint.getDate().compareTo(today.toString()) <= 0) {
        dataLastYearToDate.add(dataPoint);
      }

    }
    return dataLastYearToDate;
  }

  public List<DailyDataPoint> getDailyDataLastMonth() {
    List<DailyDataPoint> reversedData = dailyData.reversed();
    List<DailyDataPoint> dataLastMonth = new ArrayList<>();
    for (int i = 0; i < 21; i++) {
      dataLastMonth.add(reversedData.get(i));
    }
    return dataLastMonth.reversed();
  }

  public List<DailyDataPoint> getDailyDataLastWeek() {
    List<DailyDataPoint> reversedData = dailyData.reversed();
    List<DailyDataPoint> dataLastWeek = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      dataLastWeek.add(reversedData.get(i));
    }
    return dataLastWeek.reversed();
  }

  public List<String> getGoldenCrossEvents(String startDate, String endDate) {
    List<DailyDataPoint> dailyDataPointsInRange = getDailyDataInRange(startDate, endDate);
    List<DailyDataPoint> movingAverages50 = calculateMovingAverages(dailyDataPointsInRange, 50);
    List<DailyDataPoint> movingAverages200 = calculateMovingAverages(dailyDataPointsInRange, 200);
    List<String> goldenCrossEvents = new ArrayList<>();
    for (int i = 0; i < dailyDataPointsInRange.size(); i++) {
      if (movingAverages50.get(i).getClose() > movingAverages200.get(i).getClose() &&
          movingAverages50.get(i - 1).getClose() < movingAverages200.get(i - 1).getClose()) {
        goldenCrossEvents.add(dailyDataPointsInRange.get(i).getDate());
      }
    }
    return goldenCrossEvents;
  }

  public List<String> getDeathCrossEvents(String startDate, String endDate) {
    List<DailyDataPoint> dailyDataPointsInRange = getDailyDataInRange(startDate, endDate);
    List<DailyDataPoint> movingAverages50 = calculateMovingAverages(dailyDataPointsInRange, 50);
    List<DailyDataPoint> movingAverages200 = calculateMovingAverages(dailyDataPointsInRange, 200);
    List<String> deathCrossEvents = new ArrayList<>();
    for (int i = 0; i < dailyDataPointsInRange.size(); i++) {
      if (movingAverages50.get(i).getClose() < movingAverages200.get(i).getClose() &&
          movingAverages50.get(i - 1).getClose() > movingAverages200.get(i - 1).getClose()) {
        deathCrossEvents.add(dailyDataPointsInRange.get(i).getDate());
      }
    }
    return deathCrossEvents;
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
