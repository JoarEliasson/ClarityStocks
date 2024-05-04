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
  requires ClarityStocksAPI;
  requires ClarityStocksData;
  requires java.logging;
  requires ClarityStocksAnalysis;
  requires eu.hansolo.medusa;



  opens SE.ClarityStocksGUI.view to javafx.fxml;

  exports SE.ClarityStocksGUI.view;
  exports SE.ClarityStocksGUI.controller;
  exports SE.ClarityStocksGUI.controller.tiles;
  opens SE.ClarityStocksGUI.controller.tiles;
  opens SE.ClarityStocksGUI.controller to javafx.fxml;
  opens SE.ClarityStocksGUI to javafx.fxml;
  exports SE.ClarityStocksGUI.controller.graph;
  opens SE.ClarityStocksGUI.controller.graph to javafx.fxml;

}
