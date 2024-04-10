package alphaVantage;

/**
 * Enum for representing different time intervals for retrieving time series data from the Alpha
 * Vantage API. Intervals with their corresponding url parameters used in API request.
 */
public enum Interval {

  DAILY("TIME_SERIES_DAILY"),
  WEEKLY("TIME_SERIES_WEEKLY"),
  MONTHLY("TIME_SERIES_MONTHLY");

  private final String interval;

  //Constructor
  Interval(String timeSeries) {
    this.interval = timeSeries;
  }

  public String getUrlParameter() {
    return interval;
  }
}
