package analysis.regression;

public class PricePrediction {

  private final String variableName;
  private final String predictionDate;
  private final double currentPrice;
  private final double predictedPrice;
  private final String description;

  public PricePrediction(String variableName, String predictionDate, double currentPrice,
      double predictedPrice, String description) {

    this.variableName = variableName;
    this.predictionDate = predictionDate;
    this.currentPrice = currentPrice;
    this.predictedPrice = predictedPrice;
    this.description = description;
  }

  public String getVariableName() {
    return variableName;
  }

  public String getPredictionDate() {
    return predictionDate;
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
