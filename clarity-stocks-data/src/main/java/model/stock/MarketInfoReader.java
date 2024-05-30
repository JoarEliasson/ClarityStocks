package model.stock;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <h1>MarketInfoReader</h1>
 * <p>
 * The class provides methods for reading market data from a CSV file and creating Market objects.
 * </p>
 * @author Joar Eliasson
 * @author Kasper Schröder
 */
public class MarketInfoReader {

  private static final String FILE_PATH = "clarity-stocks-data/src/main/resources/markets_v2.csv/";
  private List<Market> markets;

  public MarketInfoReader() {
    this.markets = new ArrayList<>();
    this.markets = loadMarkets(FILE_PATH);
  }

  public List<Market> loadMarkets(String filePath) {
    List<StockInfo> searchList = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line = br.readLine();

      while ((line = br.readLine()) != null) {
        String[] attributes = line.split(",");
        if (attributes.length >= 8) {
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
    String region = attributes[2];
    String city = attributes[3];
    String timezone = attributes[4];
    int utcOffset = Integer.parseInt(attributes[5]);
    String localOpenTime = attributes[6];
    String localCloseTime = attributes[7];

    return new Market(identifier, name, null, region, city, timezone, utcOffset,
        localOpenTime, localCloseTime);
  }

  public List<Market> getMarkets() {
    return markets;
  }
}
