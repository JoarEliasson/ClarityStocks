package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.model.Effects;
import alphaVantage.controller.AlphaVantageClient;
import alphaVantage.model.AlphaVantageStock;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import userModel.UserProfile;
import userModel.UserProfileManager;
import javafx.scene.text.Text;

import java.util.*;


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
  public ImageView userImage;

  @FXML
  public ListView<String> recentlyViewedListView;
  @FXML
  public Rectangle recentView;

  private GUIMainController controller;
  private UserProfile userProfile;
  private final String userFilePath = "ClarityStocksUser/userInfo.json";

  public void initialize() {

    userProfile = UserProfileManager.loadUserInformation(userFilePath);
    VBox.setVgrow(layout, Priority.ALWAYS);
    layout.setEffect(new GaussianBlur(20));

    Platform.runLater(() -> {
     // addDefaultStocks();
      animateWelcomeTextVisibility();
      setupViewBasedOnUser();
    });
  }


  public void updateView() {
    if (userProfile != null && userProfile.isLoggedIn()) {
      String userImagePath = "/SE/ClarityStocksGUI/view/user.png";
      Image image = new Image(
          Objects.requireNonNull(getClass().getResourceAsStream(userImagePath)));
      userImage.setImage(image);
    } else {
      usernameText.setText("Not logged in");
    }
  }

  public void setController(GUIMainController controller) {
    this.controller = controller;
  }

  private void setupViewBasedOnUser() {
    recentView.setEffect(Effects.getDropShadow());

    if (userProfile == null || !userProfile.isLoggedIn()) {
      messageLabel.setText("Log in to show favorite stocks");
      showElements(false);
    } else {
      updateView();
    }
  }

  private void showElements(boolean b) {
    messageLabel.setVisible(true);
    updateView();
  }

  private void animateWelcomeTextVisibility() {
    welcomeToText.setVisible(true);
    clarityStocksText.setVisible(true);

    welcomeToText.setStyle("-fx-font-size: 40px; -fx-fill: black;");
    clarityStocksText.setStyle("-fx-font-size: 55px; -fx-fill: #333333;");

    SequentialTransition seqTransitionIn = getSequentialTransition();
    // SequentialTransition seqTransitionOut = new SequentialTransition(ftWelcomeOut, ftClarityOut);

    SequentialTransition seqTransition = new SequentialTransition(
        seqTransitionIn/*, seqTransitionOut*/);
    seqTransition.setOnFinished(event -> {
      layout.setEffect(null);
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


}
