package SE.ClarityStocksGUI.view;

import SE.ClarityStocksGUI.controller.FavoriteListController;
import SE.ClarityStocksGUI.controller.GUIMainController;
import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * This is the main application for the GUI. This class starts the program and loads in the
 * necessary files for the program to run.
 * @author Douglas Almö Thorsell
 */
public class GUIMainApplication extends Application {

  private Scene mainView;
  private Stage stage;
  private GUIMainController mainController;

  @Override
  public void start(Stage newStage) throws IOException {
    this.stage = newStage;
    stage.getIcons().add(new Image(
        getClass().getResource("/SE/ClarityStocksGUI/view/claritystocksIcon.png").toExternalForm()));

    setUpMainView();
    setUpMaterialsFX();
    setScene();
  }

  private void setUpMainView() throws IOException{
    String css = this.getClass().getResource("/se/ClarityStocksGUI/styles.css").toExternalForm();

    FXMLLoader mainLoader = new FXMLLoader(GUIMainApplication.class.getResource("Main-view.fxml"));
    mainView = new Scene(mainLoader.load(), 1280, 720);
    mainView.getStylesheets().add(css);
    mainController = mainLoader.getController();
    mainController.setApplication(this);

    FXMLLoader favoriteLoader = new FXMLLoader(
        GUIMainApplication.class.getResource("FavoriteListView.fxml"));
    Parent favoriteRoot = favoriteLoader.load();
    FavoriteListController favoriteListController = favoriteLoader.getController();

    mainController.setFavoriteListController(favoriteListController);
  }

  private void setUpMaterialsFX(){
    UserAgentBuilder.builder()
        .themes(JavaFXThemes.MODENA)
        .themes(MaterialFXStylesheets.forAssemble(true))
        .setDeploy(true)
        .setResolveAssets(true)
        .build()
        .setGlobal();
  }

  private void setScene(){
    stage.setTitle("Clarity Stocks");
    stage.setScene(mainView);
    stage.show();
  }


  public static void main(String[] args) {
        /*
            All logging is disabled because of a bug in an external library that gives tons of error messages when
            nothing is wrong.

            Remove the two lines under to enable logging again.

         */

    Logger logger = Logger.getLogger(""); // COMMENT OUT THIS LINE
    logger.setLevel(Level.OFF);     //COMMENT OUT THIS LINE
    System.setProperty("prism.lcdtext", "false");
    launch(args);
  }

}