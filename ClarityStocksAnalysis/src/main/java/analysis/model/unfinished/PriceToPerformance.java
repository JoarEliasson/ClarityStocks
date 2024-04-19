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
  private String description;

  public PriceToPerformance(String symbol, double peRatio, String sector) {
    this.symbol = symbol;
    this.peRatio = peRatio;
    this.sector = sector;

    PERatioEvaluation peRatioEvaluation = new PERatioEvaluation(symbol, peRatio);

    peRatio = peRatioEvaluation.getRating();

    switch (sector.toLowerCase()) {
      case "energy":
        energySector(peRatio);
        break;
      case "technology":
        technologySector(peRatio);
        break;
      case "software":
        softwareSector(peRatio);
        break;
      case "real estate":
        realEstateSector(peRatio);
        break;
      case "materials":
        materialsSector(peRatio);
        break;
      case "industry":
        industrySector(peRatio);
        break;
      case "consumer staples":
        consumerStaplesSector(peRatio);
        break;
      case "healthcare":
        healthcareSector(peRatio);
        break;
      case "finance":
        financialSector(peRatio);
        break;
      case "telecom":
        telecomSector(peRatio);
        break;
      case "utilities":
        utilitiesSector(peRatio);
        break;
      default:
        unknownSector();
    }
  }

  public String unknownSector() {
    return description = "Could not find sector for stock " + symbol;
  }

  public String energySector(double peRatio) {
    double averagePE = 11.15;
    if (peRatio < averagePE) {
      return description =
          "The stocks price in relation to its business performance is considered to be high. "
              + "\nThe average P/E-ratio for the energy sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    } else {
      return description =
          "The stocks price in relation to its business performance is considered to be low. "
              + "\nThe average P/E-ratio for the energy sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    }
  }

  public String technologySector(double peRatio) {
    double averagePE = 44;
    if (peRatio < averagePE) {
      return description =
          "The stocks price in relation to its business performance is considered to be high. "
              + "\nThe average P/E-ratio for the energy sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    } else {
      return description =
          "The stocks price in relation to its business performance is considered to be low. "
              + "\nThe average P/E-ratio for the energy sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    }
  }

  public String softwareSector(double peRatio) {
    double averagePE = 61.7;
    if (peRatio < averagePE) {
      return description =
          "The stocks price in relation to its business performance is considered to be high. "
              + "\nThe average P/E-ratio for the software sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    } else {
      return description =
          "The stocks price in relation to its business performance is considered to be low. "
              + "\nThe average P/E-ratio for the software sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    }
  }

  public String realEstateSector(double peRatio) {
    double averagePE = 49.3;
    if (peRatio < averagePE) {
      return description =
          "The stocks price in relation to its business performance is considered to be high. "
              + "\nThe average P/E-ratio for the real estate sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    } else {
      return description =
          "The stocks price in relation to its business performance is considered to be low. "
              + "\nThe average P/E-ratio for the real estate sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    }
  }

  public String materialsSector(double peRatio) {
    double averagePE = 30.7;
    if (peRatio < averagePE) {
      return description =
          "The stocks price in relation to its business performance is considered to be high. "
              + "\nThe average P/E-ratio for the materials sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    } else {
      return description =
          "The stocks price in relation to its business performance is considered to be low. "
              + "\nThe average P/E-ratio for the materials sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    }
  }

  public String industrySector(double peRatio) {
    double averagePE = 30.4;
    if (peRatio < averagePE) {
      return description =
          "The stocks price in relation to its business performance is considered to be high. "
              + "\nThe average P/E-ratio for the industry sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    } else {
      return description =
          "The stocks price in relation to its business performance is considered to be low. "
              + "\nThe average P/E-ratio for the industry sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    }
  }

  public String consumerStaplesSector(double peRatio) {
    double averagePE = 28.9;
    if (peRatio < averagePE) {
      return description =
          "The stocks price in relation to its business performance is considered to be high. "
              + "\nThe average P/E-ratio for the consumer staples sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    } else {
      return description =
          "The stocks price in relation to its business performance is considered to be low. "
              + "\nThe average P/E-ratio for the consumer staples sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    }
  }

  public String healthcareSector(double peRatio) {
    double averagePE = 71.4;
    if (peRatio < averagePE) {
      return description =
          "The stocks price in relation to its business performance is considered to be high. "
              + "\nThe average P/E-ratio for the healthcare sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    } else {
      return description =
          "The stocks price in relation to its business performance is considered to be low. "
              + "\nThe average P/E-ratio for the healthcare sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    }
  }

  public String financialSector(double peRatio) {
    double averagePE = 16.3;
    if (peRatio < averagePE) {
      return description =
          "The stocks price in relation to its business performance is considered to be high. "
              + "\nThe average P/E-ratio for the financial sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    } else {
      return description =
          "The stocks price in relation to its business performance is considered to be low. "
              + "\nThe average P/E-ratio for the financial sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    }
  }

  public String telecomSector(double peRatio) {
    double averagePE = 29.3;
    if (peRatio < averagePE) {
      return description =
          "The stocks price in relation to its business performance is considered to be high. "
              + "\nThe average P/E-ratio for the telecom sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    } else {
      return description =
          "The stocks price in relation to its business performance is considered to be low. "
              + "\nThe average P/E-ratio for the telecom sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    }
  }

  public String utilitiesSector(double peRatio) {
    double averagePE = 71.4;
    if (peRatio < averagePE) {
      return description =
          "The stocks price in relation to its business performance is considered to be high. "
              + "\nThe average P/E-ratio for the utilities sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    } else {
      return description =
          "The stocks price in relation to its business performance is considered to be low. "
              + "\nThe average P/E-ratio for the utilities sector is "
              + averagePE + " and " + symbol + " P/E-ratio is " + peRatio;
    }
  }

  @Override
  public void evaluate() {
    // TODO: Implement method
  }

  @Override
  public String getSymbol() {
    return symbol;
  }

  @Override
  public String getDescription() {
    return description;
  }

}
