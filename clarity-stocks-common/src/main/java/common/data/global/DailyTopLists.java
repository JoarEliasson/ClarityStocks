package common.data.global;

import java.util.List;

public class DailyTopLists {

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
