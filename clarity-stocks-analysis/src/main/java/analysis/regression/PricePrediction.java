package analysis.regression;

import analysis.interfaces.LinearRegressions;

/**
 * Class for storing the result of price predictions made during linear regression analysis.
 * <p>
 * The class contains information about the variable name, the date of the prediction, the current
 * price, the predicted price, the rating of the prediction and a description of the prediction.
 * </p>
 *
 * @see LinearRegressions
 * @see RegressionResult
 *
 * @author Joar Eliason
 */
public class PricePrediction {

  private final String variableName;
  private final String predictionDate;
  private final double currentPrice;
  private final double predictedPrice;
  private final double rating;
  private final String description;

  public PricePrediction(String variableName, String predictionDate, double currentPrice,
      double predictedPrice, String description) {

    this.variableName = variableName;
    this.predictionDate = predictionDate;
    this.currentPrice = currentPrice;
    this.predictedPrice = predictedPrice;
    this.rating = currentPrice / predictedPrice;
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

  public double getRating() {
    return rating;
  }

  public String getDescription() {
    return description;
  }
}
