package alphaVantage;

import analysis.model.BusinessPerformanceEvaluation;
import analysis.model.DividendEvaluationTiming;
import analysis.model.GoldenCross;
import analysis.model.HighAndLow;
import analysis.model.PERatioEvaluation;
import analysis.model.StockPriceInRelationToBusinessPerformance;
import java.util.ArrayList;
import java.util.List;

public class AlphaVantageStock {

  private final FullStockOverview companyOverview;
  private final List<DailyDataPoint> timeSeries;
  private final PERatioEvaluation PERatioEvaluation;
  private final BusinessPerformanceEvaluation businessPerformanceEvaluation;
  private final DividendEvaluationTiming dividendEvaluationTiming;
  private final GoldenCross goldenCross;
  private final HighAndLow highAndLow;
  private final StockPriceInRelationToBusinessPerformance priceInRelationToBusinessPerformance;

  public AlphaVantageStock(FullStockOverview companyOverview, List<DailyDataPoint> timeSeries,
      PERatioEvaluation PERatioEvaluation, BusinessPerformanceEvaluation businessPerformanceEvaluation, DividendEvaluationTiming dividendEvaluationTiming, GoldenCross goldenCross, HighAndLow highAndLow, StockPriceInRelationToBusinessPerformance stockPriceInRelationToBusinessPerformance) {
    this.companyOverview = companyOverview;
    this.timeSeries = timeSeries;
    this.PERatioEvaluation = PERatioEvaluation;
    this.businessPerformanceEvaluation = businessPerformanceEvaluation;
    this.dividendEvaluationTiming = dividendEvaluationTiming;
    this.goldenCross = goldenCross;
    this.highAndLow = highAndLow;
    this.priceInRelationToBusinessPerformance = stockPriceInRelationToBusinessPerformance;
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

  public PERatioEvaluation getPERatioEvaluation() {
    return PERatioEvaluation;
  }

  public BusinessPerformanceEvaluation getBusinessPerformanceEvaluation() {
    return businessPerformanceEvaluation;
  }

  public DividendEvaluationTiming getDividendEvaluationTiming() {
    return dividendEvaluationTiming;
  }

  public GoldenCross getGoldenCross() {
    return goldenCross;
  }

  public HighAndLow getHighAndLow() {
    return highAndLow;
  }

  public StockPriceInRelationToBusinessPerformance getPriceInRelationToBusinessPerformance() {
    return priceInRelationToBusinessPerformance;
  }
}
