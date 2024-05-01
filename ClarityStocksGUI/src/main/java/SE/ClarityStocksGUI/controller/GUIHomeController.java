package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.model.Effects;
import alphaVantage.controller.AlphaVantageClient;
import alphaVantage.model.AlphaVantageStock;
import alphaVantage.model.GlobalMarketInfo;
import alphaVantage.model.data.global.DailyTopLists;
import alphaVantage.model.data.global.TopListDataPoint;
import alphaVantage.model.data.series.DailyDataPoint;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import userModel.UserProfile;
import userModel.UserProfileManager;
import javafx.scene.text.Text;

import java.util.*;



public class GUIHomeController {

  @FXML public Text usernameText;
  @FXML public Text welcomeToText;
  @FXML public Text clarityStocksText;

  @FXML public Label messageLabel;
  @FXML public BorderPane layout;
  @FXML public VBox mainVBox;
  @FXML public Button addStockButton;
  @FXML public ImageView userImage;

  @FXML public ListView<String> favoritesListView;
  @FXML public ListView<String> recentlyViewedListView;
  @FXML public ListView<String> topGainList;
  @FXML public ListView<String> topLooserList;
  @FXML public ListView<String> volumeList;

  @FXML public Rectangle favorite;
  @FXML public Rectangle recentView;
  @FXML public Rectangle topGainRectangle;
  @FXML public Rectangle topLooserRectangle;
  @FXML public Rectangle volumeRectangle;
  private AlphaVantageStock stock;

    private GUIMainController controller;
  private UserProfile userProfile;
  private final String userFilePath = "ClarityStocksUser/userInfo.json";

  @FXML private Label noFavoritesLabel;

  public void initialize() {

    userProfile = UserProfileManager.loadUserInformation(userFilePath);
    VBox.setVgrow(layout, Priority.ALWAYS);
    layout.setEffect(new GaussianBlur(20));


    Platform.runLater(() -> {
      addDefaultStocks();
      animateWelcomeTextVisibility();
      setupViewBasedOnUser();
      setTopGainList1();

      //setupInteractions();
    });
  }

// Take the topFlopVolume and put in the list TO DO fix the problem with multiple API calls
  public void setTopGainList1() {
    AlphaVantageClient alphaVantageClient = LoadData.getAlphaVantageClient();
    GlobalMarketInfo globalMarketInfo = alphaVantageClient.getGlobalMarketInfo();
    DailyTopLists dailyTopLists = globalMarketInfo.getDailyTopLists();

    List<TopListDataPoint> topGainers = dailyTopLists.getTopGainers();
    List<TopListDataPoint> topLosers = dailyTopLists.getTopLosers();
    List<TopListDataPoint> mostTraded = dailyTopLists.getMostTraded();

    topGainList.getItems().clear();
    topLooserList.getItems().clear();
    volumeList.getItems().clear();

    for (int i = 0; i < 4; i++) {
      try {
        if (topGainers.size() > i) {
          TopListDataPoint topList = topGainers.get(i);
          String topListSymbol = topList.getSymbol();
          if (!alphaVantageClient.getStock(topListSymbol).getTimeSeriesDaily().getDailyData().isEmpty()) {
            String topListName = alphaVantageClient.getStock(topListSymbol).getCompanyOverview().getName();
            double topListPrice = alphaVantageClient.getStock(topListSymbol).getTimeSeriesDaily().getDailyData().get(0).getClose();
            topGainList.getItems().add(topListName + "(" + topListSymbol + ")" + " | " + topListPrice);
          }
        }

        if (topLosers.size() > i) {
          TopListDataPoint flopList = topLosers.get(i);
          String flopListSymbol = flopList.getSymbol();
          if (!alphaVantageClient.getStock(flopListSymbol).getTimeSeriesDaily().getDailyData().isEmpty()) {
            String flopListName = alphaVantageClient.getStock(flopListSymbol).getCompanyOverview().getName();
            double flopListPrice = alphaVantageClient.getStock(flopListSymbol).getTimeSeriesDaily().getDailyData().get(0).getClose();
            topLooserList.getItems().add(flopListName + "(" + flopListSymbol + ")" + " | " + flopListPrice);
          }
        }

        if (mostTraded.size() > i) {
          TopListDataPoint volumeLists = mostTraded.get(i);
          String volumeListSymbol = volumeLists.getSymbol();
          if (!alphaVantageClient.getStock(volumeListSymbol).getTimeSeriesDaily().getDailyData().isEmpty()) {
            String volumeListName = alphaVantageClient.getStock(volumeListSymbol).getCompanyOverview().getName();
            double volumeListPrice = alphaVantageClient.getStock(volumeListSymbol).getTimeSeriesDaily().getDailyData().get(0).getClose();
            volumeList.getItems().add(volumeListName + "(" + volumeListSymbol + ")" + " | " + volumeListPrice);
          }
        }
      } catch (Exception e) {
        System.out.println("Error processing stock data for index " + i + ": " + e.getMessage());
      }
    }
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

    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Add Favorite Stock");
    dialog.setHeaderText("Enter the stock symbol you wish to add to favorites:");

    Optional<String> result = dialog.showAndWait();
    result.ifPresent(stockSymbol -> {
      userProfile.addFavoriteStock(stockSymbol);
      UserProfileManager.saveUserInformation(userProfile, userFilePath);

      updateView();
    });
  }

  private void setupViewBasedOnUser() {
    favorite.setEffect(Effects.getDropShadow());
    recentView.setEffect(Effects.getDropShadow());
    topGainRectangle.setEffect(Effects.getDropShadow());
    topLooserRectangle.setEffect(Effects.getDropShadow());
    volumeRectangle.setEffect(Effects.getDropShadow());
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


}
