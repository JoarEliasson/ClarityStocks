package common.data.series;

import java.util.Locale;

/**
 * Class for storing daily stock data points.
 * <p>
 * This class holds data for a single stock on a given day.
 * </p>
 *
 * <ul>
 *   <li>{@code date} - The date of the data point.</li>
 *   <li>{@code open} - The opening price of the stock.</li>
 *   <li>{@code high} - The highest price of the stock during the day.</li>
 *   <li>{@code low} - The lowest price of the stock during the day.</li>
 *   <li>{@code close} - The closing price of the stock.</li>
 *   <li>{@code adjustedClose} - The adjusted closing price of the stock.</li>
 *   <li>{@code average} - The average price of the stock during the day.</li>
 *   <li>{@code volume} - The trading volume of the stock.</li>
 * </ul>
 *
 * @see TimeSeriesDaily
 * @see TimeSeriesMonthly
 *
 * @author Joar Eliason
 */
public class DailyDataPoint {

  private final String date;
  private double open;
  private double high;
  private double low;
  private double close;
  private double adjustedClose;
  private double average;
  private long volume;

  public DailyDataPoint(String date, double open, double high, double low, double close,
      double adjustedClose, long volume) {
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.adjustedClose = adjustedClose;
    this.average = getDailyAverage();
    this.volume = volume;
  }

  public DailyDataPoint(String date, double open, double high, double low, double close,
      long volume) {
    this.date = date;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.adjustedClose = close;
    this.average = getDailyAverage();
    this.volume = volume;
  }

  public DailyDataPoint(String date, double close) {
    this.date = date;
    this.close = close;
  }

  private double getDailyAverage() {
    return (open + high + low + close) / 4;
  }

  public int getYear() {
    String[] parts = date.split("-");
    return Integer.parseInt(parts[0]);
  }

  public int getMonth() {
    String[] parts = date.split("-");
    return Integer.parseInt(parts[1]);
  }

  public int getDay() {
    String[] parts = date.split("-");
    return Integer.parseInt(parts[2]);
  }

  public int[] getDateArray() {
    String[] parts = date.split("-");
    int[] dateArray = new int[3];
    dateArray[0] = Integer.parseInt(parts[0]);
    dateArray[1] = Integer.parseInt(parts[1]);
    dateArray[2] = Integer.parseInt(parts[2]);
    return dateArray;
  }

  public String getDate() {
    return date;
  }

  public double getOpen() {
    return open;
  }

  public void setOpen(double open) {
    this.open = open;
  }

  public double getHigh() {
    return high;
  }

  public void setHigh(double high) {
    this.high = high;
  }

  public double getLow() {
    return low;
  }

  public void setLow(double low) {
    this.low = low;
  }

  public double getClose() {
    return close;
  }

  public void setClose(double close) {
    this.close = close;
  }

  public double getAdjustedClose() {
    return adjustedClose;
  }

  public void setAdjustedClose(double adjustedClose) {
    this.adjustedClose = adjustedClose;
  }

  public double getAverage() {
    return average;
  }

  public void setAverage(double average) {
    this.average = average;
  }

  public long getVolume() {
    return volume;
  }

  public void setVolume(long volume) {
    this.volume = volume;
  }

  public String getOpenFormatted() {
      return formatDouble(this.open);
  }

  public String getHighFormatted() {
      return formatDouble(this.high);
  }

  public String getLowFormatted() {
      return formatDouble(this.low);
  }

  public String getCloseFormatted() {
      return formatDouble(this.close);
  }

  public String getAdjustedCloseFormatted() {
      return formatDouble(this.adjustedClose);
  }

  public String getAverageFormatted() {
      return formatDouble(this.average);
  }

  private String formatDouble(double value) {
      return String.format(Locale.US, "%.4E", value);
  }

}
