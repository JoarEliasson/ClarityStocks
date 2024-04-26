module ClarityStocksAPI {
  requires java.net.http;
  requires com.fasterxml.jackson.databind;
  requires ClarityStocksAnalysis;
  requires java.logging;
  requires commons.math3;
  requires org.jfree.jfreechart;
  requires java.desktop;
  requires org.jooq;
  requires com.zaxxer.hikari;

  exports alphaVantage.controller;
  exports alphaVantage.model;
  exports alphaVantage.model.enums;
  exports alphaVantage.model.data.series;
  exports alphaVantage.model.data.fundamental;
  exports alphaVantage.model.data.global;

}