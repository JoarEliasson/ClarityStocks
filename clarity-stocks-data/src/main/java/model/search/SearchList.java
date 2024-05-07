package model.search;

import java.util.ArrayList;
import model.stock.StockInfo;
import model.stock.StockInfoReader;

public class SearchList {

  private static final String FILE_PATH = "clarity-stocks-data/src/main/resources/companies.csv";
  private final ArrayList<StockInfo> searchList;

  public SearchList() {
    this.searchList = StockInfoReader.getSearchList(FILE_PATH);
  }

  public ArrayList<StockInfo> getSearchList() {
    return searchList;
  }

  public StockInfo getStockInfo(String symbol) {
    for (StockInfo stockInfo : searchList) {
      if (stockInfo.symbol().equals(symbol)) {
        return stockInfo;
      }
    }
    return null;
  }
}
