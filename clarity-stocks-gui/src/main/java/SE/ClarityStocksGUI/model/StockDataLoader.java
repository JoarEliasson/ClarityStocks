package SE.ClarityStocksGUI.model;

import alphaVantage.AlphaVantageClient;

public class StockDataLoader {

  private static AlphaVantageClient alphaVantageClient = new AlphaVantageClient("YKB1S8EYZ61LDH9B");

  public static AlphaVantageClient getAlphaVantageClient() {
    return alphaVantageClient;
  }
}
