package SE.ClarityStocksGUI.view;

import SE.ClarityStocksGUI.controller.GUIHomeController;
import SE.ClarityStocksGUI.controller.GUIStockViewController;
import alphaVantage.AlphaVantageClient;
import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;

import java.awt.*;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GUIMainApplication extends Application {

  private Scene homeView;
  private Scene stockView;
  private Stage stage;
  private GUIHomeController homeController;
  private GUIStockViewController stockViewController;
  private AlphaVantageClient alphaVantageClient;

  public static void main(String[] args) {
    launch();
  }

  @Override
  public void start(Stage stage) throws IOException {

    this.stage = stage;
    //TODO
    String css = this.getClass().getResource("/se/ClarityStocksGUI/styles.css").toExternalForm();
    //Setting up the Home view
    FXMLLoader homeLoader = new FXMLLoader(GUIMainApplication.class.getResource("Home-view.fxml"));
    homeView = new Scene(homeLoader.load(), 1280, 720);
    homeView.getStylesheets().add(css);
    homeController = homeLoader.getController();
    homeController.setApplication(this);
    //homeController.setCompanyData(companyData);

    //Setting up the Stock view
    FXMLLoader stockViewLoader = new FXMLLoader(
        GUIMainApplication.class.getResource("Stock-view.fxml"));
    stockView = new Scene(stockViewLoader.load(), 1280, 720);
    stockView.getStylesheets().add(css);

    stockViewController = stockViewLoader.getController();
    stockViewController.setApplication(this);
    //stockViewController.setCompanyData(companyData);

    //MaterialFX default code to get stylesheets working
    UserAgentBuilder.builder()
        .themes(JavaFXThemes.MODENA)
        .themes(MaterialFXStylesheets.forAssemble(true))
        .setDeploy(true)
        .setResolveAssets(true)
        .build()
        .setGlobal();


    stage.setTitle("Clarity Stocks");
    stage.setScene(homeView);
    homeView.getStylesheets().add("https://fonts.googleapis.com/css2?family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap");

    Image img = new Image("claritystocksicon.png");
    stage.getIcons().add(img);

    stage.show();

  }

  public void goToStockView(String stockSymbol) {
    stockViewController.changeButtonColor();
    stockViewController.loadStockView(stockSymbol);
    double height = stage.getHeight();
    double width = stage.getWidth();

    stage.setHeight(height);
    stage.setWidth(width);
    stage.setScene(stockView);
  }

  public void goToStockView() {
    stockViewController.changeButtonColor();
    stockViewController.loadStockView("TSLA");
    double height = stage.getHeight();
    double width = stage.getWidth();

    stage.setHeight(height);
    stage.setWidth(width);
    stage.setScene(stockView);
  }

  public void goToHomeView() {
    homeController.changeButtonColor();
    double height = stage.getHeight();
    double width = stage.getWidth();

    stage.setHeight(height);
    stage.setWidth(width);
    try {
      stage.setScene(homeView);
      homeController.resetSearchBar();

    } catch (ClassCastException e) {
      throw new RuntimeException(e);
    }

  }
}