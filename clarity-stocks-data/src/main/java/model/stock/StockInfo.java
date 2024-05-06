package model.stock;

/**
 * This record contains basic stock information, such as symbol, name and exchange. It is used to
 * represent a different stock listings in search related operations.
 *
 * @author Joar Eliasson
 */
public record StockInfo(String symbol, String name, String exchange) {

  @Override
  public String name() {
    if (name.length() > 50) {
      return name.substring(0, 50) + "...";
    }
    return name;
  }
}
