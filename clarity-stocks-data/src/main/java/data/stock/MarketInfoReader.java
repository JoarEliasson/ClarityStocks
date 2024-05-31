package data.stock;

import data.enums.Markets;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * MarketInfoReader class reads market information from a CSV file.
 * <p>
 * This class reads market information from a CSV file and creates a list of markets.
 * </p>
 *
 * <p>
 * The CSV file contains the following columns:
 * </p>
 * <ul>
 *   <li>Identifier</li>
 *   <li>Name</li>
 *   <li>Region</li>
 *   <li>City</li>
 *   <li>Timezone</li>
 *   <li>UTC Offset</li>
 *   <li>Local Open Time</li>
 *   <li>Local Close Time</li>
 * </ul>
 *
 * <p>
 * The class also contains a method for loading the supported stock markets.
 * </p>
 *
 * @see Market
 * @see Markets
 *
 * @author Joar Eliasson
 */
public class MarketInfoReader {

  private static final String FILE_PATH = "clarity-stocks-data/src/main/resources/markets_v2.csv/";
  private List<Market> markets;

  public MarketInfoReader() {
    this.markets = new ArrayList<>();
    this.markets = loadMarkets(FILE_PATH);
  }

  public List<Market> loadMarkets() {
    List<Market> marketList = new ArrayList<>();
    String marketType = "Stock Exchange";
    for (Markets market : Markets.values()) {
      Market newMarket = new Market(
          market.name(),
          market.getMarketName(),
          marketType,
          market.getRegion(),
          market.getCity(),
          market.getTimezone(),
          market.getUTCOffset(),
          market.getOpenTime(),
          market.getClosingTime()
      );
      marketList.add(newMarket);
    }
    return marketList;
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
