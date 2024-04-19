module ClarityStocksAPI {
  requires java.net.http;
  requires com.fasterxml.jackson.databind;
  requires ClarityStocksAnalysis;
  requires java.logging;

  exports alphaVantage.controller;
  exports alphaVantage.model;
  exports alphaVantage.model.enums;
  exports alphaVantage.model.data.series;
  exports alphaVantage.model.data.fundamental;

}