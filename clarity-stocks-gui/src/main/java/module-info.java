module clarity.stocks.gui {

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
  requires eu.hansolo.tilesfx;
  requires clarity.stocks.user;

  opens com.claritystocks.view to javafx.fxml;

  exports com.claritystocks.controller;
  exports com.claritystocks.controller.tiles;
  opens com.claritystocks.controller.tiles;
  opens com.claritystocks.controller to javafx.fxml;
  opens com.claritystocks to javafx.fxml;
  exports com.claritystocks.controller.graph;
  opens com.claritystocks.controller.graph to javafx.fxml;
  exports com.claritystocks.model;
  opens com.claritystocks.model to javafx.fxml;
  exports com.claritystocks.view;

}