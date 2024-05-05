package common.data.global;

public class MarketStatus {

  private String marketType;
  private String region;
  private String primaryExchanges;
  private String localOpeningTime;
  private String localClosingTime;
  private String currentStatus;

  public String getMarketType() {
    return marketType;
  }

  public void setMarketType(String marketType) {
    this.marketType = marketType;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getPrimaryExchanges() {
    return primaryExchanges;
  }

  public void setPrimaryExchanges(String primaryExchanges) {
    this.primaryExchanges = primaryExchanges;
  }

  public String getLocalOpeningTime() {
    return localOpeningTime;
  }

  public void setLocalOpeningTime(String localOpeningTime) {
    this.localOpeningTime = localOpeningTime;
  }

  public String getLocalClosingTime() {
    return localClosingTime;
  }

  public void setLocalClosingTime(String localClosingTime) {
    this.localClosingTime = localClosingTime;
  }

  public String getCurrentStatus() {
    return currentStatus;
  }

  public void setCurrentStatus(String currentStatus) {
    this.currentStatus = currentStatus;
  }
}
