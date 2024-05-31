package data.stock;

import data.enums.Markets;
import java.util.Objects;

/**
 * Market class represents a stock market.
 * <p>
 * Each market has an identifier, name, market type, region, city, timezone, UTC offset, local
 * open time, and local close time.
 * </p>
 *
 * <p>For example, the New York Stock Exchange (NYSE) has the following attributes:</p>
 * <ul>
 *   <li>Identifier: NYSE
 *   <li>Name: New York Stock Exchange
 *   <li>Market Type: Stock Exchange
 *   <li>Region: North America
 *   <li>City: New York
 *   <li>Timezone: Eastern Time
 *   <li>UTC Offset: -5
 *   <li>Local Open Time: 9:30 AM
 *   <li>Local Close Time: 4:00 PM
 * </ul>
 *
 * <p>
 * Another example is the National Association of Securities Dealers Automated Quotations (NASDAQ)
 * with the following attributes:</p>
 * <ul>
 *   <li>Identifier: NASDAQ
 *   <li>Name: National Association of Securities Dealers Automated Quotations
 *   <li>Market Type: Stock Exchange
 *   <li>Region: North America
 *   <li>City: New York
 *   <li>Timezone: Eastern Time
 *   <li>UTC Offset: -5
 *   <li>Local Open Time: 9:30 AM
 *   <li>Local Close Time: 4:00 PM
 * </ul>
 *
 * <p>
 * The CET offset can also be retrieved for each market.
 * </p>
 *
 * @author Joar Eliason
 */
public class Market {
  
  private final String identifier;
  private final String name;
  private final String marketType;
  private final String region;
  private final String city;
  private final String timezone;
  private final int utcOffset;
  private final String localOpenTime;
  private final String localCloseTime;
  
  public Market(String identifier, String name, String marketType, String region, String city,
      String timezone, int utcOffset, String localOpenTime, String localCloseTime) {

    if (Objects.equals(identifier, "NSE")) {
      identifier = "NASDAQ";
    } else if (Objects.equals(identifier, "NYQ")) {
      identifier = "NYSE";
    }
    this.identifier = identifier;
    this.name = name;
    this.marketType = marketType;
    this.region = region;
    this.city = city;
    this.timezone = timezone;
    this.utcOffset = utcOffset;
    this.localOpenTime = localOpenTime;
    this.localCloseTime = localCloseTime;
  }

  public int getCETOffset() {
    return Markets.valueOf(identifier).getUTCOffset() - 1;
  }

  public String getIdentifier() {
    return identifier;
  }

  public String getName() {
    return name;
  }

  public String getMarketType() {
    return marketType;
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

  public int getUtcOffset() {
    return utcOffset;
  }

  public String getLocalOpenTime() {
    return localOpenTime;
  }

  public String getLocalCloseTime() {
    return localCloseTime;
  }
}