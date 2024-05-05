package analysis.regression;

public class PricePrediction {

  private final String symbol;
  private final String variable;
  private final double currentPrice;
  private final double predictedPrice;
  private final String description;

  public PricePrediction(String symbol, String variable, double currentPrice, double predictedPrice, String description) {
    this.symbol = symbol;
    this.variable = variable;
    this.currentPrice = currentPrice;
    this.predictedPrice = predictedPrice;
    this.description = description;
  }

  public String getSymbol() {
    return symbol;
  }

  public String getVariable() {
    return variable;
  }

  public double getCurrentPrice() {
    return currentPrice;
  }

  public double getPredictedPrice() {
    return predictedPrice;
  }

  public String getDescription() {
    return description;
  }
}
