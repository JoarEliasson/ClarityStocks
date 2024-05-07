package model.stock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MarketInfoReader {

  private static final String FILE_PATH = "clarity-stocks-data/src/main/resources/markets.csv/";
  private List<Market> markets;

  public MarketInfoReader() {
    this.markets = loadMarkets(FILE_PATH);
  }

  public List<Market> loadMarkets(String filePath) {
    List<StockInfo> searchList = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line = br.readLine();

      while ((line = br.readLine()) != null) {
        String[] attributes = line.split(",");
        if (attributes.length >= 9) {
          Market market = createMarket(attributes);
          markets.add(market);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return markets;
  }

  private Market createMarket(String[] attributes) {
    String identifier = attributes[0];
    String name = attributes[1];
    String marketType = attributes[2];
    String region = attributes[3];
    String city = attributes[4];
    String timezone = attributes[5];
    int utcOffset = Integer.parseInt(attributes[6]);
    String localOpenTime = attributes[7];
    String localCloseTime = attributes[8];

    return new Market(identifier, name, marketType, region, city, timezone, utcOffset, localOpenTime, localCloseTime);
  }
}
