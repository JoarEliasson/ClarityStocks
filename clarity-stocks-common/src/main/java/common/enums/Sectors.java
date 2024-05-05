package common.enums;

/**
 * Enum with values of different P/E-ratios for different company sectors.
 * These values are used for determining if the stocks P/E-value is considered high, low or average.
 * As P/E-ratios for different sectors vary to a great extent, it is necessary to compare the stocks
 * P/E-ratio to statistics on P/E-ratios for the sector of the stock.
 *
 * @author Olivia Svensson
 * */
public enum Sectors {

  ENERGY("energy", 11.15),
  TECHNOLOGY("technology", 44.0),
  SOFTWARE("software", 61.7),
  REAL_ESTATE("real estate", 49.3),
  MATERIALS("materials",30.7),
  INDUSTRY("industry", 30.4),
  CONSUMER_STAPLES("consumer staples", 28.9),
  HEALTHCARE("healthcare", 71.4 ),
  FINANCE("finance", 16.3),
  TELECOM("telecom", 29.3),
  UTILITIES("utilities", 71.4);

  private String sector;
  private double priceToEarnings;

  Sectors(String sector, double priceToEarnings) {
    this.sector = sector;
    this.priceToEarnings = priceToEarnings;
  }

  public double getPriceToEarnings() {
    return this.priceToEarnings;
  }

  public String getSector() {
    return this.sector;
  }
}
