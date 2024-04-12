package SE.ClarityStocksGUI.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class GUIHomeController {

  private GUIMainController controller;
  @FXML
  private BorderPane layout;
  @FXML
  private VBox mainVBox;
  //For testing purposes, will be removed later
  @FXML
  private Label welcomeText;


  public void initialize() {
    VBox.setVgrow(layout, javafx.scene.layout.Priority.ALWAYS);

    welcomeText.setText("Clarity Stocks");

  }

  public void setController(GUIMainController controller) {
    this.controller = controller;
  }

}