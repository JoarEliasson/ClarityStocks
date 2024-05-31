package common.data.global;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * Class for storing global market information.
 * <p>
 * This class holds data for the daily top lists and market status.
 * </p>
 *
 * <ul>
 *   <li>{@code dailyTopLists} - The daily top lists.</li>
 *   <li>{@code marketStatus} - The market status.</li>
 * </ul>
 *
 * @see DailyTopLists
 * @see MarketStatus
 * @see TopListDataPoint
 *
 * @author Joar Eliason
 */
public class GlobalMarketInfo implements Serializable {

  @Serial
  private static final long serialVersionUID = 1L;

  private DailyTopLists dailyTopLists;
  private List<MarketStatus> marketStatus;

  public DailyTopLists getDailyTopLists() {
    return dailyTopLists;
  }

  public void setDailyTopLists(DailyTopLists dailyTopLists) {
    this.dailyTopLists = dailyTopLists;
  }

  public List<MarketStatus> getMarketStatus() {
    return marketStatus;
  }

  public void setMarketStatus(List<MarketStatus> marketStatus) {
    this.marketStatus = marketStatus;
  }
}
