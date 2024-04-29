package alphaVantage.model.data.series;

import java.util.List;

public class TimeSeriesMonthly {

  private String information;
  private String symbol;
  private String lastRefreshed;
  private List<DailyDataPoint> monthlyData;

  public TimeSeriesMonthly(String symbol) {
    this.symbol = symbol;
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
