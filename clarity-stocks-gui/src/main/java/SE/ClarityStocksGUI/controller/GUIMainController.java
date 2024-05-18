package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.view.GUIMainApplication;
import java.io.IOException;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import user.model.UserProfile;
import user.model.UserProfileManager;

/**
 * This is the main controller class for the GUI. It's the main container of all the different views
 * and handles the logic between them. The class is a controller for the Main-view.fxml file.
 * <p>
 * The GUI is designed to have a hierarchy between the classes with the Main-view at the top of the
 * hierarchy and then includes or views under it.
 * <p>
 * The children for this view is the following, Home-view, Stock-view and Menu-bar.
 *
 * @author Douglas Almö Thorsell
 * @see GUIHomeController
 * @see GUIStockViewController
 * @see MenuBarController
 */
public class GUIMainController {

  private GUIMainApplication application;
  @FXML
  private VBox stockView;
  @FXML
  private VBox homeView;
  @FXML
  private BorderPane mainBorderPane;
  @FXML
  private MenuBarController menuBarController;
  @FXML
  private GUIStockViewController stockViewController;
  @FXML
  private GUIHomeController homeViewController;
  @FXML
  private FavoriteListController favoriteListController;
  private static GUIMainController guiMainController;
  @FXML
  private Dialog<String> stockNotLoadedError;
  private UserProfile userProfile;
  public void initialize() {
    homeViewController.setController(this);
    stockViewController.setController(this);
    menuBarController.setController(this);
    guiMainController = this;

    menuBarController.setWidthAndHeightProperty();
    stockViewController.setupScrollbar();

    homeView.setVisible(true);
    stockView.setVisible(false);

    setUpStockNotLoadedError();
    userProfile = UserProfileManager.loadUserInformation("clarity-stocks-user/userInfo.json");
    if (userProfile != null && userProfile.getUserName() != null && !userProfile.getUserName().isEmpty()) {
      updateView();
    }
  }

  public void setApplication(GUIMainApplication application) {
    this.application = application;
  }

  public void goToStockView() {
    menuBarController.setStockButtonActive();
    stockView.setVisible(true);
    homeView.setVisible(false);
  }

  public void goToStockView(String stockSymbol) {
    menuBarController.setStockButtonActive();
    stockView.setVisible(true);
    homeView.setVisible(false);
    stockViewController.loadStockView(stockSymbol);
  }

  public void goToHomeView() {
    menuBarController.setHomeButtonActive();
    stockView.setVisible(false);
    homeView.setVisible(true);
  }



  public void errorLoadingStock(){
    goToHomeView();
    menuBarController.setCurrentStock(null);
    menuBarController.resetSearchBar();
  }

  public void setFavoriteListController(FavoriteListController favoriteListController) {
    this.favoriteListController = favoriteListController;
  }
  public void showStockNotLoaded(){
    stockNotLoadedError.showAndWait();
  }

  public void stockFavoritePressed(boolean stockIsFavorite, String stockSymbol) {
    if (favoriteListController == null) {
      System.out.println("FavoriteListController is not initialized.");
      return;
    }
    if (stockIsFavorite) {
      favoriteListController.addFavoriteStock(stockSymbol);
    } else {
      favoriteListController.removeFavoriteStock(stockSymbol);
    }
  }

  private void setUpStockNotLoadedError(){
    stockNotLoadedError = new Dialog<>();
    stockNotLoadedError.setTitle("You haven't selected a stock!");
    stockNotLoadedError.setContentText("You haven't selected a stock.\n"
        + "Please search for a stock in the search bar.");
    ButtonType button = new ButtonType("Ok", ButtonData.OK_DONE);
    stockNotLoadedError.getDialogPane().getButtonTypes().add(button);
  }
  public ReadOnlyDoubleProperty getWidthProperty() {
    return mainBorderPane.widthProperty();
  }

  public ReadOnlyDoubleProperty getHeightProperty() {
    return mainBorderPane.heightProperty();
  }

  public static GUIMainController getInstance(){
    return guiMainController;
  }
  public void updateView() {
    if (userProfile != null) {
      homeViewController.updateUserProfile(userProfile);
    }
  }
}
