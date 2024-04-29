package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.model.Effects;
import alphaVantage.controller.AlphaVantageClient;
import alphaVantage.model.AlphaVantageStock;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import userModel.UserProfile;
import userModel.UserProfileManager;
import javafx.scene.text.Text;

import java.util.*;

public class GUIHomeController {

  @FXML private ListView<String> stockListView;

  @FXML public Text welcomeToText;
  @FXML public Text clarityStocksText;
  @FXML public Label messageLabel;
  @FXML public BorderPane layout;
  @FXML public VBox mainVBox;
  @FXML public ListView<String> favoritesListView;
  @FXML public Button addStockButton;
  @FXML public ImageView userImage;
  @FXML public Text usernameText;
  @FXML public ListView<String> recentlyViewedListView;
  @FXML public Rectangle favorite;
  @FXML public Rectangle recentView;
  @FXML public Rectangle topStocks;

  @FXML public StackPane recentlyViewedStack;

  private GUIMainController controller;
  private UserProfile userProfile;
  private final String userFilePath = "ClarityStocksUser/userInfo.json";
  @FXML private StackPane favoritesStack;
  @FXML private Label noFavoritesLabel;

  public void initialize() {

    userProfile = UserProfileManager.loadUserInformation(userFilePath);
    VBox.setVgrow(layout, javafx.scene.layout.Priority.ALWAYS);
    layout.setEffect(new GaussianBlur(20));

    Platform.runLater(() -> {

      addDefaultStocks();
      animateWelcomeTextVisibility();
      setupViewBasedOnUser();
      //setupInteractions();
    });
  }

  private void addDefaultStocks() {
    List<String> defaultStocks = Arrays.asList("AAPL", "AMZN", "GOOGL", "MSFT", "TSLA");

    if (userProfile != null && (userProfile.getFavoriteStocks() == null || userProfile.getFavoriteStocks().isEmpty())) {
      for (String stockSymbol : defaultStocks) {
        AlphaVantageClient alphaVantageClient = LoadData.getAlphaVantageClient();
        AlphaVantageStock stock = alphaVantageClient.getStock(stockSymbol);
        userProfile.addFavoriteStock(String.valueOf(stock));
      }
      UserProfileManager.saveUserInformation(userProfile, userFilePath);
    }
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
        recentlyViewedListView.setVisible(true);
      }
    } else {
      // User is not logged in or userProfile is null
      noFavoritesLabel.setVisible(true);
      addStockButton.setVisible(true);
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

  private void setupViewBasedOnUser() {
    favorite.setEffect(Effects.getDropShadow());
    recentView.setEffect(Effects.getDropShadow());
    topStocks.setEffect(Effects.getDropShadow());
    if (userProfile == null || !userProfile.isLoggedIn()) {
      messageLabel.setText("Log in to show favorite stocks");
      showElements(false);
    } else {
      updateView();
    }
  }

  private void showElements(boolean b) {
    messageLabel.setVisible(true);
    favoritesListView.setVisible(b);
    addStockButton.setVisible(b);
    updateView();
  }
  private void setupInteractions() {
    noFavoritesLabel.setOnMouseEntered(e -> noFavoritesLabel.setUnderline(true));
    noFavoritesLabel.setOnMouseExited(e -> noFavoritesLabel.setUnderline(false));
    noFavoritesLabel.setOnMouseClicked(this::handleAddStock);
    noFavoritesLabel.setCursor(Cursor.HAND);
    messageLabel.setOnMouseClicked(event -> handleLoginOrCreate());
  }

  private void animateWelcomeTextVisibility() {
    welcomeToText.setVisible(true);
    clarityStocksText.setVisible(true);

    welcomeToText.setStyle("-fx-font-size: 40px; -fx-fill: black;");
    clarityStocksText.setStyle("-fx-font-size: 55px; -fx-fill: #333333;");

    SequentialTransition seqTransitionIn = getSequentialTransition();
    // SequentialTransition seqTransitionOut = new SequentialTransition(ftWelcomeOut, ftClarityOut);

    SequentialTransition seqTransition = new SequentialTransition(seqTransitionIn/*, seqTransitionOut*/);
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
  public void showTopGainers() {
    List<String> gainers = fetchStockData("gainers");
    stockListView.setItems(FXCollections.observableArrayList(gainers));
  }

  public void showTopLosers() {
    List<String> losers = fetchStockData("losers");
    stockListView.setItems(FXCollections.observableArrayList(losers));
  }

  public void showMostActive() {
    List<String> active = fetchStockData("active");
    stockListView.setItems(FXCollections.observableArrayList(active));
  }
  private List<String> fetchStockData(String type) {

    switch (type) {
      case "gainers":
        return Arrays.asList("AAPL $150 +5%", "MSFT $305 +3%");
      case "losers":
        return Arrays.asList("TSLA $600 -4%", "FB $270 -2%");
      case "active":
        return Arrays.asList("AMZN $3200 +1%", "GOOGL $1500 +1.5%");
      default:
        return new ArrayList<>();
    }
  }
}
