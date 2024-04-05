package alphaVantage;

import java.util.ArrayList;
import java.util.List;

public class AlphaVantageStock {

  private final CompanyOverview companyOverview;
  private final List<DailyDataPoint> timeSeries;
  private final String PERatioEvaluation;

  public AlphaVantageStock(CompanyOverview companyOverview, List<DailyDataPoint> timeSeries,
      String PERatioEvaluation) {
    this.companyOverview = companyOverview;
    this.timeSeries = timeSeries;
    this.PERatioEvaluation = PERatioEvaluation;
  }

  public CompanyOverview getCompanyOverview() {
    return companyOverview;
  }

  public List<DailyDataPoint> getTimeSeries() {
    return timeSeries;
  }

  public List<DailyDataPoint> getDailyAverage() {
    List<DailyDataPoint> dailyAverage = new ArrayList<>();
    for (DailyDataPoint dailyDataPoint : timeSeries) {
      dailyAverage.add(new DailyDataPoint(dailyDataPoint.getDate(), dailyDataPoint.getAverage()));
    }
    return dailyAverage;
  }

  public List<DailyDataPoint> getMovingAverage(int days) {
    List<DailyDataPoint> movingAverage = new ArrayList<>();
    for (int i = 0; i < timeSeries.size(); i++) {
      int end = Math.min(i + days, timeSeries.size());
      double sum = 0;
      for (int j = i; j < end; j++) {
        sum += timeSeries.get(j).getClose();
      }
      double average = sum / (end - i);
      movingAverage.add(new DailyDataPoint(timeSeries.get(i).getDate(), average));
    }

    return movingAverage;


  }

  public String getPERatioEvaluation() {
    return PERatioEvaluation;
  }
}
