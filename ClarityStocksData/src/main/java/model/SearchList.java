package model;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SearchList {

  private ArrayList<AlphaVantageListing> searchList = new ArrayList<>();

  public SearchList() {
    Path path = Paths.get("ClarityStocksData/src/main/resources/listing_status.csv");
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
          addToList(new AlphaVantageListing(symbol, name, exchange));
        }
        line = reader.readLine();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private void addToList(AlphaVantageListing listing) {
    searchList.add(listing);
  }

  public ArrayList<AlphaVantageListing> getSearchList() {
    return searchList;
  }

  public static void main(String[] args) {
    SearchList searchList = new SearchList();
    ArrayList<AlphaVantageListing> searchList1 = searchList.getSearchList();
    for (AlphaVantageListing listing : searchList1) {
      System.out.println("1. " + listing.getSymbol());
      System.out.println("2. " + listing.getName());
      System.out.println("3. " + listing.getExchange());
      System.out.println();
    }
  }

}
