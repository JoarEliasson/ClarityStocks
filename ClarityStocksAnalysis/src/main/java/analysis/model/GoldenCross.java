package analysis.model;

/** Class for comparing the stock price currently as to how it has been traded historically.
 * ma50 is the moving average for the past 50 days.
 * ma200 is the moving average for the past 200 days.
 * @author Olivia Svensson
 * */
public class GoldenCross {

    String symbol;
    double ma50;
    double ma200;
    double percentageDifference;
    String description;

    public GoldenCross(String symbol, double ma50, double ma200) {
        this.symbol = symbol;
        this.ma50 = ma50;
        this.ma200 = ma200;
        percentageDifference = calculatePercentageDifference(ma50, ma200);
        description = evaluatePercentageDifference(percentageDifference);
    }
    /** Method for calculating the percentage difference between ma50 and ma200. Returns a double.
     * @author Olivia Svensson
     * */
    private double calculatePercentageDifference(double ma50, double ma200) {
        percentageDifference = (1 - (ma50 / ma200)) * 100;
        return percentageDifference;
    }

    /** Method for evaluating the percentage difference. Returns a string reflecting on the considerations to be made when trading the stock.
     * @author Olivia Svensson
     * */
    private String evaluatePercentageDifference(double percentageDifference) {
        if(percentageDifference > 0) {
           return description = String.format("Stock traded %.2f higher than usual. \nConsider being a bit cautious if the high trading price has no reflection in business indicators.", percentageDifference);
        } else if (percentageDifference < 0) {
            return description = String.format("Stock traded %.2f  lower than usual. %nConsider buying if business indicators are favorable.", Math.abs(percentageDifference));
        } else {
            return description = "Something went wrong with the evaluation of the percentage difference";
        }
    }

    public String getDescription() {
        return this.description;
    }

}
