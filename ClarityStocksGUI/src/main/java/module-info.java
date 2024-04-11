module SE.ClarityStocksGUI {

  requires org.controlsfx.controls;
  requires org.kordamp.bootstrapfx.core;
  requires javafx.graphics;
  requires javafx.controls;
  requires javafx.fxml;
  requires javafx.base;
  requires VirtualizedFX;
  requires MaterialFX;
  requires ClarityStocksAPI;
  requires ClarityStocksData;
  requires java.logging;
  requires atlantafx.base;

  opens SE.ClarityStocksGUI.view to javafx.fxml;

  exports SE.ClarityStocksGUI.view;
  exports SE.ClarityStocksGUI.controller;
  exports SE.ClarityStocksGUI.controller.stockViewTiles;
  opens SE.ClarityStocksGUI.controller.stockViewTiles;
  opens SE.ClarityStocksGUI.controller to javafx.fxml;
  exports SE.ClarityStocksGUI;
  opens SE.ClarityStocksGUI to javafx.fxml;
  exports SE.ClarityStocksGUI.controller.graphControllers;
  opens SE.ClarityStocksGUI.controller.graphControllers to javafx.fxml;

}
