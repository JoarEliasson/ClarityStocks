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

  /**
   * This method returns monthly price data filtered to retrieve the {@code DailyDataPoint} that
   * match the fiscal year-end date and the number of years specified.
   * <p>
   * The price data is reversed to display the data in chronological order.
   * @param fiscalYearEnd The fiscal year-end date used to filter the {@code DailyDataPoint}
   * @param numberOfYears The number of years to retrieve data points for.
   * @return A list of {@code DailyDataPoint} objects matching the specified parameters.
   * @see DailyDataPoint
   *
   * @author Joar Eliasson
   */
  public List<DailyDataPoint> getPriceDataMatching(String fiscalYearEnd, int numberOfYears) {
    String month = fiscalYearEnd.split("-")[1];
    List<DailyDataPoint> monthlyPriceData = getMonthlyData();
    List<DailyDataPoint> matchingPriceData = new ArrayList<>();
    for (DailyDataPoint dataPoint : monthlyPriceData) {
      if (dataPoint.getDate().split("-")[1].equals(month)) {
        matchingPriceData.add(dataPoint);
      }
    }
    return matchingPriceData.subList(0, numberOfYears).reversed();
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
