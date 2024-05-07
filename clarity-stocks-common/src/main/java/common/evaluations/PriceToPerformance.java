package common.evaluations;

import common.enums.Sectors;
import common.interfaces.Evaluation;

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
  private double peRatio;
  private final String sector;
  private final Sectors energy = Sectors.ENERGY;
  private final Sectors technology = Sectors.TECHNOLOGY;
  private final Sectors telecom = Sectors.TELECOM;
  private final Sectors industry = Sectors.INDUSTRY;
  private final Sectors finance = Sectors.FINANCE;
  private final Sectors software = Sectors.SOFTWARE;
  private final Sectors consumerStaples = Sectors.CONSUMER_STAPLES;
  private final Sectors healthcare = Sectors.HEALTHCARE;
  private final Sectors materials = Sectors.MATERIALS;
  private final Sectors realEstate = Sectors.REAL_ESTATE;
  private final Sectors utilities = Sectors.UTILITIES;
  private String description;
  private double pePercentage;

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

    switch (sector.toLowerCase()) {
      case "energy":
        evaluatePE(peRatio, energy.getPriceToEarnings(), energy.getSector());
        break;
      case "technology":
        evaluatePE(peRatio, technology.getPriceToEarnings(), technology.getSector());
        break;
      case "software":
        evaluatePE(peRatio, software.getPriceToEarnings(), software.getSector());
        break;
      case "real estate":
        evaluatePE(peRatio, realEstate.getPriceToEarnings(), realEstate.getSector());
        break;
      case "materials":
        evaluatePE(peRatio, materials.getPriceToEarnings(), materials.getSector());
        break;
      case "industry":
        evaluatePE(peRatio, industry.getPriceToEarnings(), industry.getSector());
        break;
      case "consumer staples":
        evaluatePE(peRatio, consumerStaples.getPriceToEarnings(), consumerStaples.getSector());
        break;
      case "healthcare":
        evaluatePE(peRatio, healthcare.getPriceToEarnings(), healthcare.getSector());
        break;
      case "finance":
        evaluatePE(peRatio, finance.getPriceToEarnings(), finance.getSector());
        break;
      case "telecom":
        evaluatePE(peRatio, telecom.getPriceToEarnings(), telecom.getSector());
        break;
      case "utilities":
        evaluatePE(peRatio, utilities.getPriceToEarnings(), utilities.getSector());
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

  /**
   * Method for getting the title of the evaluation.
   * <p>
   * The title corresponds to the type of evaluation that is performed.
   *
   * @return the title of the evaluation.
   */
  @Override
  public String getTitle() {
    return "";
  }

  /**
   * Method for getting the subtitle of the evaluation.
   * <p>
   * The subtitle is a short description of the data that the evaluation is based on.
   *
   * @return the subtitle of the evaluation.
   */
  @Override
  public String getSubtitle() {
    return "";
  }

  /**
   * Method for getting the evaluation method.
   * <p>
   * The evaluation method is a short description of how the evaluation is performed.
   *
   * @return the evaluation method.
   */
  @Override
  public String getEvaluationMethod() {
    return "";
  }

  @Override
  public String getDescription() {
    return description;
  }


  public static void main(String[] args) {
    PriceToPerformance priceToPerformance = new PriceToPerformance("AAPL", 50.0, "technology");
    System.out.println(priceToPerformance.getDescription());
  }
}
