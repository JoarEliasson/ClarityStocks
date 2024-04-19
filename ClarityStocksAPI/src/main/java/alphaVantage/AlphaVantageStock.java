package alphaVantage;

import analysis.model.evaluations.BusinessPerformanceEvaluation;
import analysis.model.evaluations.DividendEvaluation;
import analysis.model.evaluations.GoldenCrossEvaluation;
import analysis.model.evaluations.HighAndLow;
import analysis.model.unfinished.PriceToPerformance;
import java.util.ArrayList;
import java.util.List;

public class AlphaVantageStock {

  private final FullStockOverview companyOverview;
  private final List<DailyDataPoint> timeSeries;
  private final analysis.model.evaluations.PERatioEvaluation PERatioEvaluation;
  private final BusinessPerformanceEvaluation businessPerformanceEvaluation;
  private final DividendEvaluation dividendEvaluationTiming;
  private final GoldenCrossEvaluation goldenCrossEvaluation;
  private final HighAndLow highAndLow;
  private final PriceToPerformance priceInRelationToBusinessPerformance;

  public AlphaVantageStock(FullStockOverview companyOverview, List<DailyDataPoint> timeSeries,
      analysis.model.evaluations.PERatioEvaluation PERatioEvaluation, BusinessPerformanceEvaluation businessPerformanceEvaluation, DividendEvaluation dividendEvaluationTiming, GoldenCrossEvaluation goldenCrossEvaluation, HighAndLow highAndLow, PriceToPerformance priceToPerformance) {
    this.companyOverview = companyOverview;
    this.timeSeries = timeSeries;
    this.PERatioEvaluation = PERatioEvaluation;
    this.businessPerformanceEvaluation = businessPerformanceEvaluation;
    this.dividendEvaluationTiming = dividendEvaluationTiming;
    this.goldenCrossEvaluation = goldenCrossEvaluation;
    this.highAndLow = highAndLow;
    this.priceInRelationToBusinessPerformance = priceToPerformance;
  }

  public FullStockOverview getCompanyOverview() {
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

  public analysis.model.evaluations.PERatioEvaluation getPERatioEvaluation() {
    return PERatioEvaluation;
  }

  public BusinessPerformanceEvaluation getBusinessPerformanceEvaluation() {
    return businessPerformanceEvaluation;
  }

  public DividendEvaluation getDividendEvaluationTiming() {
    return dividendEvaluationTiming;
  }

  public GoldenCrossEvaluation getGoldenCross() {
    return goldenCrossEvaluation;
  }

  public HighAndLow getHighAndLow() {
    return highAndLow;
  }

  public PriceToPerformance getPriceInRelationToBusinessPerformance() {
    return priceInRelationToBusinessPerformance;
  }
}
