package analysis.model;

/**Class for calculating the business performance of a company. Returns an EBITDA margin.
 * EBIDTA margin is calculated by EBIDTA divided by the companies total revenue.
 * @author Olivia Svensson
 * */
public class BusinessPerformanceEvaluation {
    private final String symbol;
    private final double ebidtamargin;
    private final String description;


    public BusinessPerformanceEvaluation(String symbol, double ebidta, double totalRevenue) {
        this.symbol = symbol;
        ebidtamargin =  calcEBIDTAMargin(ebidta, totalRevenue);
        description =  evaluateEBIDTAMargin(ebidtamargin);
    }

    private double calcEBIDTAMargin(double ebidta, double totalRevenue) {
        return ebidta/totalRevenue;
    }

    private String evaluateEBIDTAMargin(double ebidtamargin) {
        if(ebidtamargin < 0.05) {
            return  "There is an indication that " + symbol + " has a weak performance";
        } else if (ebidtamargin > 0.05 && ebidtamargin < 0.15) {
            return  "There is an indication that " + symbol + " has an average performance";
        } else if (ebidtamargin > 0.15 && ebidtamargin < 0.20) {
            return  "There is an indication that " + symbol + " has a good performance";
        } else {
            return  "There is an indication that " + symbol + " has a great performance";
        }
    }

    public String getDescription() {
        return this.description;
    }
}