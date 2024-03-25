package alphaVantage;

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

    public String getPERatioEvaluation() {
        return PERatioEvaluation;
    }
}
