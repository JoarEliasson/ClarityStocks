package com.claritystocks.controller.tiles;

import com.claritystocks.controller.GUIMainController;
import com.claritystocks.controller.GUIStockViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 * This class handles the explanation tile which includes information about different analyses.
 * <p>
 * This page can be accessed by pressing any of the question mark (?) buttons in the stock-view.
 * </p>
 *
 * <p>
 * Apart from the other tile classes, this tile does not have the stock-view as their parent class,
 * even though the explanation page can only be accessed from the stock-view. The reason for this is
 * that it layers over the entire stock-view, for this reason it has {@code GUIMainController} as
 * its parent class.
 * </p>
 *
 * @see GUIStockViewController
 * @see GUIMainController
 *
 * @author Douglas Almö Thorsell
 */
public class ExplanationTile {

  @FXML
  private Label mainTitle;
  @FXML
  private Label generalText;
  @FXML
  private Label companyTitle;
  @FXML
  private Label companyText;
  @FXML
  private ImageView closeButton;
  @FXML
  private BorderPane layout;
  @FXML
  private VBox explanationTile;
  @FXML
  private ScrollPane scrollPane;
  private GUIMainController controller;

  public void initialize(){
    VBox.setVgrow(layout, javafx.scene.layout.Priority.ALWAYS);
    explanationTile.prefWidthProperty().bind(layout.widthProperty().multiply(0.5));
    setUpCloseButton();
  }

  private void setUpCloseButton(){
    Image image = new Image(
        getClass().getResource("/com/claritystocks/view/images/close.png").toExternalForm());
    closeButton.setImage(image);

    closeButton.setFitWidth(25);
    closeButton.setFitHeight(25);
  }

  @FXML
  public void closeButtonPressed(){
    controller.closeExplanationPage();
  }

  public void setController(GUIMainController controller){
    this.controller = controller;
  }

  public void setMainTitle(String text){
    mainTitle.setText(text);
  }

  public void setGeneralText(String text){
    generalText.setText(text);
  }
  public void setCompanyTitle(String text){
    companyTitle.setText(text);
  }
  public void setCompanyText(String text){
    companyText.setText(text);
  }

  public void setupScrollbar() {
    scrollPane.minWidthProperty().bind(explanationTile.widthProperty());
    scrollPane.minHeightProperty().bind(controller.getHeightProperty());
    scrollPane.setFitToWidth(true);
    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    scrollPane.setEffect(null);
  }

  public void setScrollPaneMaxHeight(){
    scrollPane.maxHeightProperty().bind(controller.getHeightProperty());
  }

}
