package SE.ClarityStocksGUI.controller;

import alphaVantage.controller.AlphaVantageClient;

public class LoadData {

  private static AlphaVantageClient alphaVantageClient = new AlphaVantageClient("YKB1S8EYZ61LDH9B");

  public static AlphaVantageClient getAlphaVantageClient() {
    return alphaVantageClient;
  }
}
