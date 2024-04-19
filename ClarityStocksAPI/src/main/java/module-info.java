module ClarityStocksAPI {
  requires java.net.http;
  requires com.fasterxml.jackson.databind;
  requires ClarityStocksAnalysis;
  requires java.logging;

  exports alphaVantage;
}