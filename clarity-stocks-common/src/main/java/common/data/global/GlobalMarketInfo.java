package common.data.global;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

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
