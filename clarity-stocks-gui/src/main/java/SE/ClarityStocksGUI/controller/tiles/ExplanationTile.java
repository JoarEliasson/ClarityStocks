package SE.ClarityStocksGUI.controller.tiles;

import SE.ClarityStocksGUI.controller.GUIMainController;
import SE.ClarityStocksGUI.controller.GUIStockViewController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ExplanationTile {

  @FXML
  private Label mainTitle;
  @FXML
  private Label generalText;
  @FXML
  private ImageView closeButton;
  @FXML
  private BorderPane layout;
  @FXML
  private VBox explanationTile;
  private GUIMainController controller;

  public void initialize(){
    VBox.setVgrow(layout, javafx.scene.layout.Priority.ALWAYS);
    explanationTile.prefWidthProperty().bind(layout.widthProperty().multiply(0.5));
    mainTitle.setText("This is the title");

    //This is for testing purposes only
    generalText.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Adipiscing vitae proin sagittis nisl rhoncus mattis rhoncus. Tincidunt vitae semper quis lectus nulla at volutpat diam ut. Pellentesque sit amet porttitor eget. Donec ultrices tincidunt arcu non sodales. Eget lorem dolor sed viverra ipsum nunc. Faucibus vitae aliquet nec ullamcorper sit. Nunc eget lorem dolor sed viverra ipsum. Vivamus at augue eget arcu dictum varius duis at. Nibh praesent tristique magna sit amet purus. Sapien pellentesque habitant morbi tristique senectus et netus. Adipiscing elit ut aliquam purus sit amet luctus. Tempor id eu nisl nunc mi ipsum faucibus. Sit amet facilisis magna etiam tempor orci eu. Sed tempus urna et pharetra. Nulla aliquet porttitor lacus luctus accumsan tortor. Fames ac turpis egestas maecenas pharetra convallis posuere morbi leo. Eu mi bibendum neque egestas congue quisque egestas diam in.\n"
        + "\n"
        + "Convallis convallis tellus id interdum. At in tellus integer feugiat scelerisque varius. Scelerisque in dictum non consectetur a erat nam at lectus. Mauris nunc congue nisi vitae suscipit. Id consectetur purus ut faucibus pulvinar. Libero justo laoreet sit amet. Sed vulputate odio ut enim. Porttitor eget dolor morbi non arcu. Diam volutpat commodo sed egestas. Lacinia quis vel eros donec ac odio tempor. Augue eget arcu dictum varius. Augue mauris augue neque gravida in fermentum. Turpis massa sed elementum tempus egestas sed. Vestibulum mattis ullamcorper velit sed ullamcorper morbi tincidunt. Donec et odio pellentesque diam volutpat commodo. Vitae suscipit tellus mauris a diam maecenas sed enim ut.\n"
        + "\n"
        + "Tortor condimentum lacinia quis vel eros donec ac odio tempor. Augue lacus viverra vitae congue eu consequat ac felis. In nisl nisi scelerisque eu ultrices. Tellus rutrum tellus pellentesque eu tincidunt tortor aliquam nulla. Morbi quis commodo odio aenean sed adipiscing diam donec adipiscing. Mauris ultrices eros in cursus turpis massa tincidunt dui ut. Consequat interdum varius sit amet mattis vulputate enim. Arcu non sodales neque sodales ut etiam sit amet nisl. At urna condimentum mattis pellentesque id nibh tortor id aliquet. Rhoncus mattis rhoncus urna neque viverra justo. Arcu ac tortor dignissim convallis aenean et tortor at.");
    setUpCloseButton();
  }

  private void setUpCloseButton(){
    Image image = new Image(
        getClass().getResource("/SE/ClarityStocksGUI/view/close.png").toExternalForm());
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

}
