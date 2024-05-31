package alphaVantage.enums;

import alphaVantage.AlphaVantageClient;
import alphaVantage.AlphaVantageParser;

/**
 * Enum for different intervals available in the Alpha Vantage API.
 * <p>
 * The enum contains the different intervals available in the Alpha Vantage API, and methods for
 * getting the URL parameter for the different intervals.
 * </p>
 *
 * @see AlphaVantageClient
 * @see AlphaVantageParser
 *
 * @author Joar Eliason
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
