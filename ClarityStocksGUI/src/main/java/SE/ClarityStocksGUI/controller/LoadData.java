package SE.ClarityStocksGUI.controller;

import alphaVantage.AlphaVantageClient;

public class LoadData {

  private AlphaVantageClient alphaVantageClient;

  public LoadData() {
    this.alphaVantageClient = new AlphaVantageClient("YKB1S8EYZ61LDH9B");
  }

  public AlphaVantageClient getAlphaVantageClient() {
    return alphaVantageClient;
  }
}
