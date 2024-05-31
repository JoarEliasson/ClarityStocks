package common.data.global;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * Class for storing daily top list data retrieved from the Alpha Vantage API.
 * <p>
 * This class holds the top gainers, top losers, and most traded stocks for a given day.
 * </p>
 *
 * <ul>
 *   <li>{@code information} - Information about the top lists.</li>
 *   <li>{@code lastUpdated} - The date and time the top lists were last updated.</li>
 *   <li>{@code topGainers} - A list of the top gaining stocks.</li>
 *   <li>{@code topLosers} - A list of the top losing stocks.</li>
 *   <li>{@code mostTraded} - A list of the most traded stocks.</li>
 * </ul>
 *
 * @see GlobalMarketInfo
 * @see MarketStatus
 * @see TopListDataPoint
 *
 * @author Joar Eliason
 */
public class DailyTopLists implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private String information;
  private String lastUpdated;
  private List<TopListDataPoint> topGainers;
  private List<TopListDataPoint> topLosers;
  private List<TopListDataPoint> mostTraded;

  public String getInformation() {
    return information;
  }

  public void setInformation(String information) {
    this.information = information;
  }

  public String getLastUpdated() {
    return lastUpdated;
  }

  public void setLastUpdated(String lastUpdated) {
    this.lastUpdated = lastUpdated;
  }

  public List<TopListDataPoint> getTopGainers() {
    return topGainers;
  }

  public void setTopGainers(List<TopListDataPoint> topGainers) {
    this.topGainers = topGainers;
  }

  public List<TopListDataPoint> getTopLosers() {
    return topLosers;
  }

  public void setTopLosers(List<TopListDataPoint> topLosers) {
    this.topLosers = topLosers;
  }

  public List<TopListDataPoint> getMostTraded() {
    return mostTraded;
  }

  public void setMostTraded(List<TopListDataPoint> mostTraded) {
    this.mostTraded = mostTraded;
  }
}
