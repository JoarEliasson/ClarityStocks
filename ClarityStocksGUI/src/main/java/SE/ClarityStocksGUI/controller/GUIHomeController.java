package SE.ClarityStocksGUI.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * The GUIHomeController handles the home view. The class is a controller for the Home-view.fxml
 * file.
 *
 * @author Douglas Almö Thorsell
 */
public class GUIHomeController {

  private GUIMainController controller;
  @FXML
  private BorderPane layout;
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