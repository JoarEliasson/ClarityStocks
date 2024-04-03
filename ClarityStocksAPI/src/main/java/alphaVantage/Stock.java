package alphaVantage;

import java.util.ArrayList;
import java.util.List;

public class Stock {

    private CompanyOverview companyOverview;
    private List<DataPoint> timeSeries;
    private String PERatioEvaluation;

    public Stock(CompanyOverview companyOverview, List<DataPoint> timeSeries, String PERatioEvaluation){
        this.companyOverview = companyOverview;
        this.timeSeries = timeSeries;
        this.PERatioEvaluation = PERatioEvaluation;
    }

    public CompanyOverview getCompanyOverview() {
        return companyOverview;
    }

    public List<DataPoint> getTimeSeries() {
        return timeSeries;
    }

    public List<DataPoint> getDailyAverage() {
        List<DataPoint> dailyAverage = new ArrayList<>();
        for (DataPoint dataPoint : timeSeries) {
            dailyAverage.add(new DataPoint(dataPoint.getDate(), dataPoint.getAverage()));
        }
        return dailyAverage;
    }

    public List<DataPoint> getMovingAverage(int days) {
        List<DataPoint> movingAverage = new ArrayList<>();
        for (int i = 0; i < timeSeries.size(); i++) {
            int end = Math.min(i + days, timeSeries.size());
            double sum = 0;
            for (int j = i; j < end; j++) {
                sum += timeSeries.get(j).getClose();
            }
            double average = sum / (end - i);
            movingAverage.add(new DataPoint(timeSeries.get(i).getDate(), average));
        }

            /*
        for (int i = 0; i < timeSeries.size(); i++) {
            if (i < days) {
                movingAverage.add(new DataPoint(timeSeries.get(i).getDate(), timeSeries.get(i).getClose()));
            } else {
                double sum = 0;
                for (int j = i - days; j < i; j++) {
                    sum += timeSeries.get(j).getClose();
                }
                double average = sum / days;
                movingAverage.add(new DataPoint(timeSeries.get(i).getDate(), average));
            }
        }

             */
        return movingAverage;


    }

    public String getPERatioEvaluation() {
        return PERatioEvaluation;
    }
}
