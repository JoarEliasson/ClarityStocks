package model.stock;

import java.util.Objects;

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