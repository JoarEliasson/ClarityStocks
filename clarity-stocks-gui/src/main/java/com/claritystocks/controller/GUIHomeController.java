package com.claritystocks.controller;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * The GUIHomeController handles the home view. The class is a controller for the Home-view.fxml
 * file.
 * <p>
 * The class is responsible for handling the home view and the user information.
 * </p>
 *
 * @see GUIMainController
 *
 * @author Douglas Almö Thorsell
 * @author Ibrahinm Tafankaji
 */
public class GUIHomeController {

  @FXML
  public Text usernameText;
  @FXML
  public Text welcomeToText;
  @FXML
  public Text clarityStocksText;
  @FXML
  public Label messageLabel;
  @FXML
  public BorderPane layout;
  @FXML
  private StackPane overlayPane;


  private GUIMainController controller;

  public void initialize() {
    VBox.setVgrow(layout, Priority.ALWAYS);
    layout.setEffect(new GaussianBlur(20));

    Platform.runLater(this::animateWelcomeTextVisibility);
  }

  public void setController(GUIMainController controller) {
    this.controller = controller;
  }

  private void animateWelcomeTextVisibility() {
    welcomeToText.setVisible(true);
    clarityStocksText.setVisible(true);

    welcomeToText.setStyle("-fx-font-size: 40px; -fx-fill: black;");
    clarityStocksText.setStyle("-fx-font-size: 55px; -fx-fill: #333333;");


    overlayPane.setVisible(true);
    overlayPane.toFront();

    SequentialTransition seqTransitionIn = getSequentialTransition();

    SequentialTransition seqTransition = new SequentialTransition(
        seqTransitionIn);
    seqTransition.setOnFinished(event -> {
      layout.setEffect(null);
      overlayPane.setVisible(false);
      welcomeToText.setVisible(false);
      clarityStocksText.setVisible(false);
    });

    seqTransition.play();
  }

  private SequentialTransition getSequentialTransition() {
    FadeTransition ftWelcomeIn = new FadeTransition(Duration.seconds(2), welcomeToText);
    ftWelcomeIn.setFromValue(0);
    ftWelcomeIn.setToValue(1);

    FadeTransition ftClarityIn = new FadeTransition(Duration.seconds(1), clarityStocksText);
    ftClarityIn.setFromValue(0);
    ftClarityIn.setToValue(1);

    SequentialTransition seqTransitionIn = new SequentialTransition(ftWelcomeIn, ftClarityIn);
    return seqTransitionIn;
  }
}
