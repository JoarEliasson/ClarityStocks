package common.evaluations;

import common.enums.Sectors;
import common.interfaces.Evaluation;

/**
 * Class for evaluating the stock price in relation to how the company is performing. How is the
 * annual earnings per share related to share price. P/E-ratio. Varies across sectors. The P/E-ratio
 * is not always an accurate indicator for the company's performance. Evaluation depends on
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
    return "Price to Performance";
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
    return "The price to performance evaluation evaluates the price of the stock in relation to how"
        + " well the company is performing. It evaluates how the annual earnings per share is"
        + " related to the share price, in other words the price to earnings ratio (P/E-ratio)."
        + " The P/E-ratio is one of the most widely used metrics for both investors and analysts"
        + " to determine the value of the stock. It indicates whether a company’s stock price is"
        + " overvalued or undervalued and can reveal how a stock’s valuation is compared to its"
        + " sector, as or a benchmark like the S&P 500.  The P/E-ratio is calculated by dividing"
        + " the stock’s current price by its latest earnings per share. The P/E-ratio varies across"
        + " different sectors, and it is not always an accurate indicator for the company’s"
        + " performance. A good P/E-ratio for one sector could be a poor P/E-ratio for another"
        + " sector. Companies that grow faster than average, such as technology companies,"
        + " typically have higher P/E-ratios. What the P/E-value shows is that investors"
        + " are willing got pay a higher share price now due to growth expectations in the"
        + " future. The median P/E-value for the S&P 500 was 14.93 as of May 2023."
        + " The P/E-value can also be used to determine the company’s future earnings growth."
        + " As an example, the investors might expect the company to increase its dividends as"
        + " a result if earnings are expected to rise. Higher earnings and higher dividends"
        + " typically lead to a higher stock price.";
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
    return "\n"
        + "The evaluations are made by comparing the P/E-value of the stock to the sector it is"
        + " a part of. This is important as the P/E-ratios for different sectors vary a lot,"
        + " and one P/E-ratio for one sector might be considered high, but for another sector,"
        + " it is considered low. The P/E-ratio percentage is calculated by dividing the P/E-ratio"
        + " of the stock with the average P/E-ratio of the sector in which the stock belongs."
        + " The value then gets graded based on six categories: if the P/E-percentage is higher"
        + " than 100% (same as the sector’s average), but lower than 133%, the price to performance"
        + " is considered to be on the higher side. Higher than 133% but lower than 166% is"
        + " considered to be high. Higher than 166% is considered to be very high. Lower than"
        + " 100% but more than 66% is considered to be on lower side. Higher than 33% but lower"
        + " than 66% is considered low. Lower than 33% is considered to be very low."
        + " If the P/E-ratio is considered high, it is possible that the stock is overvalued."
        + " It might also indicate that the price may fall in the future. If the P/E-ratio is"
        + " considered low, it is possible that the stock is undervalued."
        + " This might indicate that the stock’s price will rise in the future.\n";
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
