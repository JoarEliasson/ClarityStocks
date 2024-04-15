package analysis.model;

/** Class for getting data on stocks 52-week high and low
 * @author Olivia Svensson
 * */

public class HighAndLow {
    String symbol;
    double high;
    double low;
    int week = 52;
    String description = "";

    public HighAndLow(String symbol, double high, double low) {
        this.symbol = symbol;
        this.high = high;
        this.low = low;
    }

  public String getDescription() {
        return description = "The " + week + "-week high for " + symbol + " is " + high +  ".\n "
                + "The " + week + "-week low for " + symbol + " is " + low;
  }


}
