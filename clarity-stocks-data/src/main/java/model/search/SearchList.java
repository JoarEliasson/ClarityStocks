package model.search;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import model.stock.StockInfo;

public class SearchList {

  private ArrayList<StockInfo> searchList = new ArrayList<>();

  public SearchList() {
    Path path = Paths.get("clarity-stocks-data/src/main/resources/listing_status.csv");
    try (BufferedReader reader = Files.newBufferedReader(path)) {
      String header = reader.readLine();
      String line = reader.readLine();
      while (line != null) {
        String[] attributes = line.split(",");
        String symbol = attributes[0];
        String name = attributes[1];
        String exchange = attributes[2];
        String type = attributes[3];
        if (type.equals("Stock")) {
          addToList(new StockInfo(symbol, name, exchange));
        }
        line = reader.readLine();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void addToList(StockInfo listing) {
    searchList.add(listing);
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

  public static void main(String[] args) {
    SearchList searchList = new SearchList();
    ArrayList<StockInfo> searchList1 = searchList.getSearchList();
    for (StockInfo listing : searchList1) {
      System.out.println("1. " + listing.symbol());
      System.out.println("2. " + listing.name());
      System.out.println("3. " + listing.exchange());
      System.out.println();
    }
  }
}
