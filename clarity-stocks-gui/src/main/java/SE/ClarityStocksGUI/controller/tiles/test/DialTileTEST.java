package SE.ClarityStocksGUI.controller.tiles.test;

import eu.hansolo.medusa.Gauge;
import javafx.fxml.FXML;

public class DialTileTEST {
  @FXML
  private Gauge gauge;

  public void initialize(){
    setupGauge();
  }

  public void setupGauge(){
    gauge.setSkinType(Gauge.SkinType.FLAT);
    gauge.setMaxSize(300, 300);
    gauge.setMaxValue(100);
    gauge.setValue(40.7);
    gauge.setTitle("TEST");
  }
}