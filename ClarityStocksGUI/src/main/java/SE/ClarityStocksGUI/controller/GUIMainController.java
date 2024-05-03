package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.view.GUIMainApplication;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

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
  }


  public void stockFavoritePressed(boolean stockIsFavorite, String stockSymbol){
    System.out.println(stockSymbol + " favorite status: " + stockIsFavorite);
  }

  public ReadOnlyDoubleProperty getWidthProperty() {
    return mainBorderPane.widthProperty();
  }

  public ReadOnlyDoubleProperty getHeightProperty() {
    return mainBorderPane.heightProperty();
  }
}
