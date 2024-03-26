package SE.ClarityStocksGUI.view;

import SE.ClarityStocksGUI.controller.GUIHomeController;
import SE.ClarityStocksGUI.controller.GUIStockViewController;
import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.NasdaqStockholmCompanyData;

import java.io.IOException;

public class GUIMainApplication extends Application {
    private Scene homeView;
    private Scene stockView;
    private Stage stage;
    private GUIHomeController homeController;
    private GUIStockViewController stockViewController;
    private AlphaVantageClient alphaVantageClient;

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

        //new TestThread().start();  //<---- ONLY FOR TESTING - Testing for updating testData, just write in the console what you want the text to be
    }

    public static void main(String[] args) {
        launch();
    }

    //TEST
    public String getTestData(){
        return Test.getTestData();
    }

    public void goToStockView(){
        double height = stage.getHeight();
        double width = stage.getWidth();

        stage.setHeight(height);
        stage.setWidth(width);
        stage.setScene(stockView);
    }

    public void goToHomeView(){
        double height = stage.getHeight();
        double width = stage.getWidth();

        stage.setHeight(height);
        stage.setWidth(width);
        stage.setScene(homeView);

    }

    private class TestThread extends Thread{
        @Override
        public void run() {
            while (true){
                Scanner scanner = new Scanner(System.in);
                Test.setTestData(scanner.nextLine());
            }
        }
    }

    public AlphaVantageClient getAlphaVantageClient(){
        return alphaVantageClient;
    }

}