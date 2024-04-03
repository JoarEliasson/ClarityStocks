package SE.ClarityStocksGUI.view;

import SE.ClarityStocksGUI.controller.GUIHomeController;
import SE.ClarityStocksGUI.controller.GUIStockViewController;
import atlantafx.base.theme.PrimerLight;
import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.kordamp.bootstrapfx.BootstrapFX;

import java.io.IOException;

import java.lang.reflect.Array;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUIMainApplication extends Application {
    private Scene homeView;
    private Scene stockView;
    private Stage stage;
    private GUIHomeController homeController;
    private GUIStockViewController stockViewController;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        String css = this.getClass().getResource("/se/ClarityStocksGUI/styles.css").toExternalForm();
        //Setting up the Home view
        FXMLLoader homeLoader = new FXMLLoader(GUIMainApplication.class.getResource("Home-view.fxml"));
        homeView = new Scene(homeLoader.load(), 1280, 720);
        homeView.getStylesheets().add(css);
        homeController = homeLoader.getController();
        homeController.setApplication(this);
        //homeController.setCompanyData(companyData);

        //Setting up the Stock view
        FXMLLoader stockViewLoader = new FXMLLoader(GUIMainApplication.class.getResource("Stock-view.fxml"));
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

    public void goToStockView(String stockSymbol){
        stockViewController.changeButtonColor();
        double height = stage.getHeight();
        double width = stage.getWidth();
        stockViewController.loadStockView(stockSymbol);



        stage.setHeight(height);
        stage.setWidth(width);
        stage.setScene(stockView);
    }

    public void goToStockView(){
        stockViewController.changeButtonColor();
        double height = stage.getHeight();
        double width = stage.getWidth();
        stockViewController.loadStockView("TSLA");

        stage.setHeight(height);
        stage.setWidth(width);
        stage.setScene(stockView);

    }

    public void goToHomeView(){
        homeController.changeButtonColor();
        double height = stage.getHeight();
        double width = stage.getWidth();

        stage.setHeight(height);
        stage.setWidth(width);
        try {
            stage.setScene(homeView);
            homeController.resetSearchBar();

        }catch (ClassCastException e){

        }

    }

    public static void main(String[] args) {
        /*
            All logging is disabled because of a bug in an external library that gives tons of error messages when
            nothing is wrong.

            Remove the two lines under to enable logging again.

         */

        Logger logger = Logger.getLogger(""); // COMMENT OUT THIS LINE
        logger.setLevel(Level.OFF);     //COMMENT OUT THIS LINE
        launch(args);
    }
}