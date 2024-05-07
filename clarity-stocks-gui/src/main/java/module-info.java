module SE.ClarityStocksGUI {

  requires org.controlsfx.controls;
  requires org.kordamp.bootstrapfx.core;
  requires javafx.graphics;
  requires javafx.controls;
  requires javafx.swing;
  requires javafx.fxml;
  requires javafx.base;
  requires VirtualizedFX;
  requires MaterialFX;
  requires clarity.stocks.api.integration;
  requires clarity.stocks.data;
  requires java.logging;
  requires clarity.stocks.analysis;
  requires clarity.stocks.common;
  requires eu.hansolo.medusa;

  requires clarity.stocks.user;

  opens SE.ClarityStocksGUI.view to javafx.fxml;

  exports SE.ClarityStocksGUI.controller;
  exports SE.ClarityStocksGUI.controller.tiles;
  opens SE.ClarityStocksGUI.controller.tiles;
  opens SE.ClarityStocksGUI.controller to javafx.fxml;
  opens SE.ClarityStocksGUI to javafx.fxml;
  exports SE.ClarityStocksGUI.controller.graph;
  opens SE.ClarityStocksGUI.controller.graph to javafx.fxml;
  exports SE.ClarityStocksGUI.model;
  opens SE.ClarityStocksGUI.model to javafx.fxml;
  exports SE.ClarityStocksGUI.controller.tiles.test;
  opens SE.ClarityStocksGUI.controller.tiles.test;
  exports SE.ClarityStocksGUI.view;

}