package com.claritystocks.controller;

import java.io.IOException;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import user.model.UserProfile;
import user.model.UserProfileManager;

/**
 * The GUIHomeController handles the home view. The class is a controller for the Home-view.fxml
 * file.
 *
 * @author Douglas Almö Thorsell
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
  private UserProfile userProfile;
  private final String userFilePath = "clarity-stocks-user/userInfo.json";

  public void initialize() {
    userProfile = UserProfileManager.loadUserInformation(userFilePath);
    VBox.setVgrow(layout, Priority.ALWAYS);
    layout.setEffect(new GaussianBlur(20));

    Platform.runLater(this::animateWelcomeTextVisibility);
  }

  public void updateUserProfile(UserProfile userProfile) {
    this.userProfile = userProfile;
    updateView();
  }
  public void updateView() {
    if (userProfile != null ) {
      usernameText.setText(userProfile.getUserName());
    } else {
      usernameText.setText("Not logged in");
    }
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

    /*
    FadeTransition ftWelcomeOut = new FadeTransition(Duration.seconds(1), welcomeToText);
    ftWelcomeOut.setFromValue(1);
    ftWelcomeOut.setToValue(0);
    ftWelcomeOut.setDelay(Duration.seconds(1)); // Delay after which the text disappears

    FadeTransition ftClarityOut = new FadeTransition(Duration.seconds(1), clarityStocksText);
    ftClarityOut.setFromValue(1);
    ftClarityOut.setToValue(0);
    ftClarityOut.setDelay(Duration.seconds(1)); // Delay after which the text disappears
    */
    SequentialTransition seqTransitionIn = new SequentialTransition(ftWelcomeIn, ftClarityIn);
    return seqTransitionIn;
  }

  @FXML
  private void handleUserInfo() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource(
          "/com/claritystocks/view/ChangeUserNameDialog.fxml"));
      Parent root = loader.load();

      ChangeUserNameDialogController dialogController = loader.getController();
      dialogController.setUserProfile(userProfile);
      dialogController.setMainController(controller);

      Stage stage = new Stage();
      dialogController.setStage(stage);

      stage.setScene(new Scene(root));
      stage.setTitle("Change UserName");
      stage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
