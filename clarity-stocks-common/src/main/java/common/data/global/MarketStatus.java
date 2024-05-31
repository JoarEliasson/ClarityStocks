package common.data.global;

import java.io.Serial;
import java.io.Serializable;

/**
 * Class for storing market status data.
 * <p>
 * This class holds data for the market status, including the market type, region, primary exchanges,
 * local opening time, local closing time, and current status.
 * </p>
 *
 * <ul>
 *   <li>{@code marketType} - The market type.</li>
 *   <li>{@code region} - The region of the market.</li>
 *   <li>{@code primaryExchanges} - The primary exchanges for the market.</li>
 *   <li>{@code localOpeningTime} - The local opening time for the market.</li>
 *   <li>{@code localClosingTime} - The local closing time for the market.</li>
 *   <li>{@code currentStatus} - The current status of the market.</li>
 * </ul>
 *
 * @see DailyTopLists
 * @see GlobalMarketInfo
 * @see TopListDataPoint
 *
 * @author Joar Eliason
 */
public class MarketStatus implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

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
