package SE.ClarityStocksGUI.controller;

import alphaVantage.AlphaVantageClient;

public class LoadData {

  private static final AlphaVantageClient alphaVantageClient = new AlphaVantageClient(
      "YKB1S8EYZ61LDH9B");

  public static AlphaVantageClient getAlphaVantageClient() {
    return alphaVantageClient;
  }
}
