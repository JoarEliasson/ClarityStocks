package analysis.model.unfinished;

import analysis.model.evaluations.PERatioEvaluation;
import analysis.model.interfaces.Evaluation;

/**
 * Class for evaluating the stock price in relation to how the company is performing. How is the
 * annual earnings per share related to share price. P/E-ratio. Varies across sectors. The P/E-ratio
 * is not always an accurate indicator for if the company's performance. Evaluation depends on
 * topic.
 *
 * @author Olivia Svensson
 */
public class PriceToPerformance implements Evaluation {
  private final String symbol;
  private final double peRatio;
  private final String sector;
  private final Sectors energy = Sectors.ENERGY;
  private final Sectors technology = Sectors.TECHNOLOGY;
  private final Sectors energy = Sectors.ENERGY;
  private final Sectors energy = Sectors.ENERGY;
  private final Sectors energy = Sectors.ENERGY;
  private final Sectors energy = Sectors.ENERGY;

  private String description;
  private double pePercentage = 0.0;
  private final double energyPE = 11.15;
  private final double technologyPE = 44;
  private final double softwarePE = 61.7;
  private final double realEstatePE = 49.3;
  private final double materialsPE = 30.7;
  private final double industryPE = 30.4;
  private final double consumerStaplesPE = 28.9;
  private final double healthCarePE = 71.4;
  private final double financePE = 16.3;
  private final double telecomPE = 29.3;
  private final double utilitiesPE = 71.4;

  /*
  * Constructor for PriceToPerformance class. Has a switch statement which determines which method
  *  should be called depending on the sector of the stock.
  * */
  public PriceToPerformance(String symbol, double peRatio, String sector) {
    this.symbol = symbol;
    this.peRatio = peRatio;
    this.sector = sector;


    PERatioEvaluation peRatioEvaluation = new PERatioEvaluation(symbol, peRatio);
    peRatio = peRatioEvaluation.getRating();

    switch (sectors.getSector()) {
      case "energy", "technology", "software", "real estate", "materials", "industry",
        "consumer staples", "healthcare", "finance", "telecom", "utilities":
        evaluatePE(peRatio, sectors.getPriceToEarnings(), sectors.getSector());
        break;
      default:
        unknownSector();
    }
  }

  private String unknownSector() {
    return description = "Could not find sector for stock " + symbol;
  }

  /**
   * General method for evaluating the price to earnings ratio for the company compared to the
   * average price to earnings ratio of the sector.
   * Calculates the percentage of the percentage to earnings ratio from the average and evaluates
   * the performance of the stock based on the percentage interval.
   * */
  private String evaluatePE(double peRatio, double averagePE, String sector) {
    pePercentage = calculatePercentage(peRatio, averagePE);
    if(pePercentage > 1.0 && pePercentage < 1.33) {
      return description = String.format(
      "The stocks price in relation to its business performance is considered to be on the higher "
      + "side.%n" +
      "The average P/E-ratio for the %s sector is %.2f and %s P/E-ratio is %.2f",
      sector, averagePE, symbol, peRatio);
    } else if(pePercentage > 1.33 && pePercentage < 1.66) {
      return description = String.format(
      "The stocks price in relation to its business performance is considered to be high.%n" +
      "The average P/E-ratio for the %s sector is %.2f and %s P/E-ratio is %.2f",
      sector, averagePE, symbol, peRatio);
    } else if(pePercentage > 1.6) {
      return description = String.format(
      "The stocks price in relation to its business performance is considered to be very high.%n" +
      "The average P/E-ratio for the %s sector is %.2f and %s P/E-ratio is %.2f",
      sector, averagePE, symbol, peRatio);
    } else if (pePercentage < 1.0 && pePercentage > 0.66) {
      return description = String.format(
      "The stocks price in relation to its business performance is considered to be on the lower"
      + " side.%n" +
      "The average P/E-ratio for the %s sector is %.2f and %s P/E-ratio is %.2f",
      sector, averagePE, symbol, peRatio);
    } else if(pePercentage < 0.66 && pePercentage > 0.33) {
      return description = String.format(
      "The stocks price in relation to its business performance is considered to be low.%n" +
      "The average P/E-ratio for the %s sector is %.2f and %s P/E-ratio is %.2f",
      sector, averagePE, symbol, peRatio);
    } else if(pePercentage < 0.33) {
      return description = String.format(
      "The stocks price in relation to its business performance is considered to be very low.%n" +
      "The average P/E-ratio for the %s sector is %.2f and %s P/E-ratio is %.2f",
      sector, averagePE, symbol, peRatio);
    }
    else {
      return description = "Something went wrong with the evaluations.";
    }
  }
  /**
   * Method which divides the performance to earnings ratio of a stock with the average performance
   * to earnings ratio of the sector which the stock is a part of.
   * */
  public double calculatePercentage(double peRatioStock, double peRatioSector) {
    return peRatioStock / peRatioSector;
  }

  @Override
  public void evaluate() {

  }
  @Override
  public String getSymbol() {
    return symbol;
  }

  @Override
  public String getDescription() {
    return description;
  }


  public static void main(String[] args) {
    PriceToPerformance priceToPerformance = new PriceToPerformance("AAPL", 30, "technology");

  }
}
