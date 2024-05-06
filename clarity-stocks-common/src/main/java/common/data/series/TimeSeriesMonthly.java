package common.data.series;

import java.util.ArrayList;
import java.util.List;

public class TimeSeriesMonthly {

  private String information;
  private String symbol;
  private String lastRefreshed;
  private List<DailyDataPoint> monthlyData;

  public TimeSeriesMonthly(String symbol) {
    this.symbol = symbol;
  }

  public List<DailyDataPoint> getMonthlyClosePrices(String month, int numberOfYears) {
    List<DailyDataPoint> monthlyData = getMonthlyData();
    List<DailyDataPoint> closePrices = new ArrayList<>();
    for (DailyDataPoint dataPoint : monthlyData) {
      if (dataPoint.getDate().split("-")[1].equals(month)) {
        closePrices.add(dataPoint);
      }
    }
    return closePrices.subList(0, numberOfYears).reversed();
  }

  public String getInformation() {
    return information;
  }

  public void setInformation(String information) {
    this.information = information;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
  }

  public String getLastRefreshed() {
    return lastRefreshed;
  }

  public void setLastRefreshed(String lastRefreshed) {
    this.lastRefreshed = lastRefreshed;
  }

  public List<DailyDataPoint> getMonthlyData() {
    return monthlyData;
  }

  public void setMonthlyData(List<DailyDataPoint> monthlyData) {
    this.monthlyData = monthlyData;
  }
}
