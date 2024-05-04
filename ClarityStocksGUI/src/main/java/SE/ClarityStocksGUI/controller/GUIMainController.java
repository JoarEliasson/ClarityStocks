package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.view.GUIMainApplication;
import alphaVantage.controller.AlphaVantageClient;
import alphaVantage.model.AlphaVantageStock;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import userModel.UserProfile;
import userModel.UserProfileManager;

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



  public void initialize() {
    homeViewController.setController(this);
    stockViewController.setController(this);
    menuBarController.setController(this);

    menuBarController.setWidthAndHeightProperty();
    stockViewController.setupScrollbar();

    homeView.setVisible(true);
    stockView.setVisible(false);
    System.out.println("Main Controller initialized");
  }

  public void setApplication(GUIMainApplication application) {
    this.application = application;
  }

  public void goToStockView() {
    stockView.setVisible(true);
    homeView.setVisible(false);
  }

  public void goToStockView(String stockSymbol) {
    stockView.setVisible(true);
    homeView.setVisible(false);
    stockViewController.loadStockView(stockSymbol);
  }

  public void goToHomeView() {
    stockView.setVisible(false);
    homeView.setVisible(true);
    favoriteListController.updateFavoriteList();

  }

  public void setFavoriteListController(FavoriteListController favoriteListController) {
    this.favoriteListController = favoriteListController;
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


  public ReadOnlyDoubleProperty getWidthProperty() {
    return mainBorderPane.widthProperty();
  }

  public ReadOnlyDoubleProperty getHeightProperty() {
    return mainBorderPane.heightProperty();
  }
}
