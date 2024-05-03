package SE.ClarityStocksGUI.controller.tile;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class InfoTile {
  @FXML
  private Label nameLabel;
  @FXML
  private Label sectorLabel;
  @FXML
  private Label descriptionLabel;

  public void initialize() {
    System.out.println("Info tile initialized");

  }

  public void setCompanyName(String text) {
    nameLabel.setText(text);
  }

  public void setSector(String text) {
    sectorLabel.setText(text);
  }

  public void setDescription(String text) {
    descriptionLabel.setText(text);
  }
}
