package SE.ClarityStocksGUI.view;

import SE.ClarityStocksGUI.Test;
import SE.ClarityStocksGUI.controller.GUIHomeController;
import SE.ClarityStocksGUI.controller.GUIStockViewController;
import alphaVantage.AlphaVantageClient;
import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import java.io.IOException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
    alphaVantageClient = new AlphaVantageClient("YKB1S8EYZ61LDH9B");
    //System.out.println(alphaVantageClient.getFilteredSeries());
    this.stage = stage;
    String css = this.getClass().getResource("/se/ClarityStocksGUI/styles.css").toExternalForm();
    //Setting up the Home view
    FXMLLoader homeLoader = new FXMLLoader(GUIMainApplication.class.getResource("Home-view.fxml"));
    homeView = new Scene(homeLoader.load(), 1280, 720);
    homeView.getStylesheets().add(css);
    homeController = homeLoader.getController();
    homeController.setApplication(this);

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

    stage.setTitle("ClarityStocks");
    stage.setScene(homeView);
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
      e.printStackTrace();
    }
  }

  public AlphaVantageClient getAlphaVantageClient() {
    return alphaVantageClient;
  }

  private class TestThread extends Thread {

    @Override
    public void run() {
      while (!Thread.interrupted()) {
        Scanner scanner = new Scanner(System.in);
        Test.setTestData(scanner.nextLine());
      }
    }
  }
}