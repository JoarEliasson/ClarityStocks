package model.stock;

/**
 * This record contains basic stock information, such as symbol, name and exchange. It is used to
 * represent a different stock listings in search related operations.
 *
 * @author Joar Eliasson
 */
public record StockInfo(String exchange, String symbol, String name, String fullName,
    String sector, String industry, String city, String state, String country) {

  @Override
  public String exchange() {
    return exchange;
  }

  @Override
  public String symbol() {
    return symbol;
  }

  @Override
  public String name() {
    return name;
  }

  @Override
  public String fullName() {
    return fullName;
  }

  @Override
  public String sector() {
    return sector;
  }

  @Override
  public String industry() {
    return industry;
  }

  @Override
  public String city() {
    return city;
  }

  @Override
  public String state() {
    return state;
  }

  @Override
  public String country() {
    return country;
  }

  @Override
  public String toString() {
    return String.format("StockInfo{exchange='%s', symbol='%s', name='%s', fullName='%s',"
        + "sector='%s', industry='%s', city='%s', state='%s', country='%s'}%n",
        exchange, symbol, name, fullName, sector, industry, city, state, country
    );
  }
}
