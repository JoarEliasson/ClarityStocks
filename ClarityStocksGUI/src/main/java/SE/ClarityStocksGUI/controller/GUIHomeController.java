package SE.ClarityStocksGUI.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import userModel.UserProfile;
import userModel.UserProfileManager;
import javafx.scene.text.Text;

import javax.swing.*;
import java.util.List;
import java.util.Objects;

public class GUIHomeController {

  @FXML public VBox welcomeBox;

  @FXML public VBox recentlyViewedVBox;
  @FXML public Text welcomeToText;
  @FXML public Text clarityStocksText;
  @FXML public Label messageLabel;
  @FXML public BorderPane layout;
  @FXML public VBox mainVBox;
  @FXML public Label welcomeText;
  @FXML public ListView<String> favoritesListView;
  @FXML public Button addStockButton;
  @FXML public ImageView userImage;
  @FXML public Text usernameText;
  @FXML public ListView<String> recentlyViewedListView;

  private GUIMainController controller;
  private UserProfile userProfile;
  private final String userFilePath = "ClarityStocksUser/userInfo.json";
  private final String userImagePath = "/SE/ClarityStocksGUI/view/user.png";




  public void initialize() {
    userProfile = UserProfileManager.loadUserInformation(userFilePath);

    Platform.runLater(() -> {

      welcomeToText.setStyle("-fx-font-size: 40px; -fx-fill: black;");
      clarityStocksText.setStyle("-fx-font-size: 55px; -fx-fill: #333333;");

    // Attempt to load the user profile


    // Check if the user profile is not loaded or the user is not logged in
    if (userProfile == null || !userProfile.isLoggedIn()) {
      messageLabel.setText("Log in to show favorite stocks");
      messageLabel.setVisible(true);  // Make the login message visible
      favoritesListView.setVisible(false);  // Hide the favorites list
      addStockButton.setVisible(false);  // Hide the add stock button
    } else {
      // If the user is logged in, update the view to show user data
        updateView(); // Ensures all FXML fields are injected before access

    }
      messageLabel.setOnMouseClicked(event -> handleLoginOrCreate());

    });
  }




  private void updateView() {

    if (userProfile != null && userProfile.isLoggedIn()) {

      Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(userImagePath)));
      userImage.setImage(image);

      usernameText.setText(userProfile.getUserName());

      List<String> stocks = userProfile.getFavoriteStocks();

      // Handle the favorite stocks list
      if (stocks == null || stocks.isEmpty()) {
        // User has no favorite stocks; show message and "add" button
        messageLabel.setText("No stocks here, add");
        messageLabel.setVisible(true);
        addStockButton.setVisible(true);
        favoritesListView.setVisible(false); // Hide the favorites list view as it's empty
      } else {
        // User has favorite stocks; populate and show the favorites list view
        favoritesListView.getItems().setAll(stocks);
        favoritesListView.setVisible(true);
        messageLabel.setVisible(false);
        addStockButton.setVisible(false);
      }

      List<String> recentStocks = userProfile.getRecentlyViewedStocks();
      if (recentStocks != null && !recentStocks.isEmpty()) {
        recentlyViewedListView.getItems().setAll(recentStocks);
        recentlyViewedListView.setVisible(true);
      } else {
        recentlyViewedListView.setVisible(false);
      }
    } else {
      // User is not logged in or userProfile is null
      messageLabel.setText("Log in to show favorite stocks");
      messageLabel.setVisible(true);
      addStockButton.setVisible(false);
      favoritesListView.setVisible(false);
      recentlyViewedListView.setVisible(false);
    }
  }


  private void handleLoginOrCreate() {
    Dialog<String> dialog = new Dialog<>();
    dialog.setTitle("Login or Create Account");
    dialog.setHeaderText("Enter your username:");
    ButtonType loginButtonType = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
    dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
    TextField usernameField = new TextField();
    usernameField.setPromptText("Username");
    dialog.getDialogPane().setContent(new VBox(8, usernameField));
    Button loginButton = (Button) dialog.getDialogPane().lookupButton(loginButtonType);
    loginButton.setDisable(true);
    usernameField.textProperty().addListener((obs, oldVal, newVal) -> loginButton.setDisable(newVal.trim().isEmpty()));
    dialog.setResultConverter(dialogButton -> dialogButton == loginButtonType ? usernameField.getText() : null);
    dialog.showAndWait().ifPresent(this::handleLogin);
  }
  public void handleLogin(String username) {
    if (username != null && !username.isEmpty()) {
      if (UserProfileManager.isUserExist(username, userFilePath)) {
        userProfile = UserProfileManager.loadUserInformation(userFilePath);
        userProfile.setLoggedIn(true);
      } else {
        userProfile = new UserProfile(username);
        userProfile.setLoggedIn(true);
        UserProfileManager.saveUserInformation(userProfile, userFilePath);
      }
      updateView();
    }
  }

  public void handleLogout() {
    if (userProfile != null) {
      userProfile.setLoggedIn(false);
      UserProfileManager.saveUserInformation(userProfile, userFilePath);
      updateView();
    }
  }
  public void setController(GUIMainController controller) {
    this.controller = controller;
  }

  public void handleAddStock(ActionEvent actionEvent) {
  }
}