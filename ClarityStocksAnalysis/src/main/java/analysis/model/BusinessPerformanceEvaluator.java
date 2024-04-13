package analysis.model;
import analysis.model.BusinessPerformanceEvaluation;

/** Possibly not necessary anymore, but class for business performance evaluation
 * @author Olivia Svensson
 * */
public class BusinessPerformanceEvaluator {

    public BusinessPerformanceEvaluator (String symbol, double ebidta, double totalRevenue)

    {
        businessPerformanceEvaluation(symbol, ebidta, totalRevenue);
    }

    public BusinessPerformanceEvaluation businessPerformanceEvaluation(String symbol, double ebidta, double totalRevenue) {
        BusinessPerformanceEvaluation businessPerformanceEvaluation =  new BusinessPerformanceEvaluation(symbol, ebidta, totalRevenue);
        return businessPerformanceEvaluation;
    }
    public String getDescription(String symbol, double ebidta, double totalRevenue) {
       return  businessPerformanceEvaluation(symbol, ebidta, totalRevenue).getDescription();
    }
}
