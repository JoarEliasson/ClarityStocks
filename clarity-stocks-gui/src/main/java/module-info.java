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
  requires eu.hansolo.medusa;
  requires clarity.stocks.common;

  opens SE.ClarityStocksGUI.view to javafx.fxml;
  opens SE.ClarityStocksGUI.controller.tile;
  opens SE.ClarityStocksGUI.controller to javafx.fxml;
  opens SE.ClarityStocksGUI to javafx.fxml;
  opens SE.ClarityStocksGUI.controller.graph to javafx.fxml;

  exports SE.ClarityStocksGUI.view;
  exports SE.ClarityStocksGUI.controller;
  exports SE.ClarityStocksGUI.controller.tile;
  exports SE.ClarityStocksGUI.controller.graph;
}
