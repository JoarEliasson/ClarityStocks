package model.stock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StockInfoReader {

  private static List<StockInfo> readStockInfoFromFile(String filePath) {
    List<StockInfo> searchList = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line = br.readLine();

      while ((line = br.readLine()) != null) {
        String[] attributes = line.split(",");
        if (attributes.length >= 9) {
          StockInfo stockInfo = createStockInfo(attributes);
          searchList.add(stockInfo);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return searchList;
  }

  private static StockInfo createStockInfo(String[] attributes) {
    String exchange = attributes[0];
    String symbol = attributes[1];
    String name = attributes[2];
    String fullName = attributes[3];
    String sector = attributes[4];
    String industry = attributes[5];
    String city = attributes[6];
    String state = attributes[7];
    String country = attributes[8];

    return new StockInfo(exchange, symbol, name, fullName, sector, industry, city, state, country);
  }

  public static ArrayList<StockInfo> getSearchList(String filePath) {
    return (ArrayList<StockInfo>) readStockInfoFromFile(filePath);
  }
}