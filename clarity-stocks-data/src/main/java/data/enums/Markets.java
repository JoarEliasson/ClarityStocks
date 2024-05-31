package data.enums;

import data.stock.Market;

/**
 * Enum for supported stock markets.
 * <p>
 * This enum contains data for the supported stock markets.
 * </p>
 *
 * <ul>
 *   <li>{@code marketName} - The name of the market.</li>
 *   <li>{@code region} - The region of the market.</li>
 *   <li>{@code city} - The city of the market.</li>
 *   <li>{@code timezone} - The timezone of the market.</li>
 *   <li>{@code UTCOffset} - The UTC offset of the market.</li>
 *   <li>{@code openTime} - The opening time of the market.</li>
 *   <li>{@code closingTime} - The closing time of the market.</li>
 * </ul>
 *
 * @see Market
 *
 * @author Joar Eliasson
 */
public enum Markets {

  BATS("BATS Exchange", "United States", "Kansas City", "CST", -6, "08:00", "17:00"),
  NASDAQ_SE("NASDAQ Stock Exchange", "United States", "New York City", "EST", -5, "09:30", "16:00"),
  NASDAQ_CM("NASDAQ Capital Market", "United States", "New York City", "EST", -5, "09:30", "16:00"),
  NASDAQ_GM("NASDAQ Global Market", "United States", "New York City", "EST", -5, "09:30", "16:00"),
  NASDAQ_GS("NASDAQ Global Select", "United States", "New York City", "EST", -5, "09:30", "16:00"),
  NYSE("New York Stock Exchange", "United States", "New York City", "EST", -5, "09:30", "16:00");

  private final String marketName;
  private final String region;
  private final String city;
  private final String timezone;
  private final int UTCOffset;
  private final String openTime;
  private final String closingTime;

  Markets(String marketName, String region, String city, String timezone, int UTCOffset, String openTime, String closingTime) {
    this.marketName = marketName;
    this.region = region;
    this.city = city;
    this.timezone = timezone;
    this.UTCOffset = UTCOffset;
    this.openTime = openTime;
    this.closingTime = closingTime;
  }

  public Markets[] getMarkets() {
    return Markets.values();
  }

  public String getMarketName() {
    return marketName;
  }

  public String getRegion() {
    return region;
  }

  public String getCity() {
    return city;
  }

  public String getTimezone() {
    return timezone;
  }

  public int getUTCOffset() {
    return UTCOffset;
  }

  public String getOpenTime() {
    return openTime;
  }

  public String getClosingTime() {
    return closingTime;
  }
}
