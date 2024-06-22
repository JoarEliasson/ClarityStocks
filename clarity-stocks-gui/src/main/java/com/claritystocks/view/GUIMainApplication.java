package com.claritystocks.view;

import com.claritystocks.controller.GUIMainController;
import com.claritystocks.model.ClarityResourceManager;
import io.github.palexdev.materialfx.theming.JavaFXThemes;
import io.github.palexdev.materialfx.theming.MaterialFXStylesheets;
import io.github.palexdev.materialfx.theming.UserAgentBuilder;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * This is the main application for the GUI.
 * <p>
 * This class starts the program and loads in the necessary files for the program to run.
 * </p>
 *
 * @author Douglas Almö Thorsell
 * @author Ibrahim Tafankaji
 */
public class GUIMainApplication extends Application {

  private Scene mainView;
  private Stage stage;
  private GUIMainController mainController;

  @Override
  public void start(Stage newStage) throws IOException {
    this.stage = newStage;
    stage.getIcons().add(new Image(
        getClass().getResource("/com/claritystocks/view/images/cs_icon.png").toExternalForm()));
    setUpMainView();
    setUpMaterialsFX();
    setScene();
  }

  private void setUpMainView() throws IOException{
    String css = ClarityResourceManager.getCssResource("/com/claritystocks/styles.css");

    FXMLLoader mainLoader = new FXMLLoader(GUIMainApplication.class.getResource("Main-view.fxml"));
    mainView = new Scene(mainLoader.load(), 1280, 720);
    mainView.getStylesheets().add(css);
    mainController = mainLoader.getController();
    mainController.setApplication(this);
  }

  private void setUpMaterialsFX() {
    UserAgentBuilder.builder()
        .themes(JavaFXThemes.MODENA)
        .themes(MaterialFXStylesheets.forAssemble(true))
        .setDeploy(true)
        .setResolveAssets(true)
        .build()
        .setGlobal();
  }

  private void setScene() {
    stage.setTitle("Clarity Stocks");
    stage.setScene(mainView);
    stage.show();
  }

  private static void forceAntiAliasing() {
    System.setProperty("prism.lcdtext", "false");
  }

  public static void main(String[] args) {
    forceAntiAliasing();
    launch(args);
  }
}
