package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.model.Effects;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import userModel.UserProfile;
import userModel.UserProfileManager;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
  @FXML public Rectangle favorite;
  @FXML public Rectangle recentView;
  @FXML public StackPane recentlyViewedStack;

  private GUIMainController controller;
  private UserProfile userProfile;
  private final String userFilePath = "ClarityStocksUser/userInfo.json";
    @FXML private StackPane favoritesStack;
  @FXML private Label noFavoritesLabel;



  public void initialize() {
    userProfile = UserProfileManager.loadUserInformation(userFilePath);
    VBox.setVgrow(layout, javafx.scene.layout.Priority.ALWAYS);



    Platform.runLater(() -> {
      favorite.setEffect(Effects.getDropShadow());
      recentView.setEffect(Effects.getDropShadow());
      welcomeToText.setStyle("-fx-font-size: 40px; -fx-fill: black;");
      clarityStocksText.setStyle("-fx-font-size: 55px; -fx-fill: #333333;");

    // Check if the user profile is not loaded or the user is not logged in
    if (userProfile == null || !userProfile.isLoggedIn()) {
      messageLabel.setText("Log in to show favorite stocks");
      messageLabel.setVisible(true);  // Make the login message visible
      favoritesListView.setVisible(false);  // Hide the favorites list
      addStockButton.setVisible(false);  // Hide the add stock button
    } else {

        updateView(); // Ensures all FXML fields are injected before access

    }
      noFavoritesLabel.setOnMouseEntered(e -> noFavoritesLabel.setUnderline(true));
      noFavoritesLabel.setOnMouseExited(e -> noFavoritesLabel.setUnderline(false));
      noFavoritesLabel.setOnMouseClicked(this::handleAddStock);
      noFavoritesLabel.setCursor(Cursor.HAND);
      messageLabel.setOnMouseClicked(event -> handleLoginOrCreate());

    });
  }




  private void updateView() {

    if (userProfile != null && userProfile.isLoggedIn()) {

        String userImagePath = "/SE/ClarityStocksGUI/view/user.png";
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(userImagePath)));
      userImage.setImage(image);
      usernameText.setText(userProfile.getUserName());

      List<String> stocks = userProfile.getFavoriteStocks();
      // Handle the favorite stocks list
      if (stocks == null || stocks.isEmpty()) {
        // User has no favorite stocks; show message and "add" button
        noFavoritesLabel.setVisible(true);
        favoritesListView.setVisible(false);

      } else {
        // User has favorite stocks; populate and show the favorites list view
        favoritesListView.getItems().setAll(stocks);
        noFavoritesLabel.setVisible(false);
        favoritesListView.setVisible(true);
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
      noFavoritesLabel.setVisible(true);
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

  public void handleAddStock(MouseEvent actionEvent) {
    // You'll need to determine how you want to get the stock symbol.
    // This could be from a text input dialog or by selecting a stock from a list.
    // For example, let's assume we open a dialog and get a stock symbol back.
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Add Favorite Stock");
    dialog.setHeaderText("Enter the stock symbol you wish to add to favorites:");

    // Traditional way to get the response value.
    Optional<String> result = dialog.showAndWait();
    result.ifPresent(stockSymbol -> {
      // Check if the stockSymbol is valid or not before adding.
      // For simplicity, let's assume it's always valid.
      userProfile.addFavoriteStock(stockSymbol);
      // Save the updated list of favorite stocks.
      UserProfileManager.saveUserInformation(userProfile, userFilePath);

      // Update the view to reflect the new list of favorite stocks.
      updateView();
    });
  }
}
