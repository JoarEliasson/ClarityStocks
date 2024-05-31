package data.search;

import data.enums.Companies;
import data.stock.StockInfo;
import java.util.ArrayList;

/**
 * Class for storing a list of the supported companies and their basic stock information, used for
 * search functionality.
 * <p>
 * This class holds a list of stock information for all companies in the enum {@code Companies}.
 * </p>
 *
 * <ul>
 *   <li>{@code searchList} - List of stock information for all companies.</li>
 * </ul>
 *
 * @see Companies
 * @see StockInfo
 *
 * @author Joar Eliasson
 */
public class SearchList {

  private final ArrayList<StockInfo> searchList;

  public SearchList() {
    this.searchList = new ArrayList<>();
    collectInfoFromEnum();
  }

  private void collectInfoFromEnum() {
    for (Companies company : Companies.values()) {
      StockInfo stockInfo = company.getStockInfo();
      searchList.add(stockInfo);
    }
  }

  public ArrayList<StockInfo> getSearchList() {
    return searchList;
  }
}
