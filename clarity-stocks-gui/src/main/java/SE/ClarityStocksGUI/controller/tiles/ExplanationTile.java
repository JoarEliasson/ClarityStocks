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
  private Label companyTitle;
  @FXML
  private Label companyText;
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

    //FOR TESTING PURPOSES ONLY
    generalText.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Faucibus interdum posuere lorem ipsum dolor. Vel fringilla est ullamcorper eget nulla facilisi. Eget dolor morbi non arcu risus. Diam vel quam elementum pulvinar. Suscipit adipiscing bibendum est ultricies. Tortor aliquam nulla facilisi cras fermentum odio eu feugiat. Potenti nullam ac tortor vitae purus faucibus ornare suspendisse. Felis bibendum ut tristique et egestas quis ipsum suspendisse ultrices. Tristique sollicitudin nibh sit amet commodo nulla. Est ultricies integer quis auctor elit sed. Gravida neque convallis a cras semper auctor neque vitae. Quis viverra nibh cras pulvinar mattis nunc sed blandit. Tortor aliquam nulla facilisi cras fermentum odio. Ultricies leo integer malesuada nunc vel risus commodo viverra maecenas. Sem fringilla ut morbi tincidunt augue.\n"
        + "\n"
        + "Aenean et tortor at risus viverra adipiscing at in. Nibh venenatis cras sed felis eget. Lorem ipsum dolor sit amet consectetur adipiscing elit. Ultricies mi quis hendrerit dolor magna eget. Eget magna fermentum iaculis eu non diam phasellus vestibulum. Dignissim suspendisse in est ante in. Amet est placerat in egestas. Id aliquet risus feugiat in ante metus dictum. Amet consectetur adipiscing elit pellentesque. Faucibus interdum posuere lorem ipsum dolor. Eu nisl nunc mi ipsum faucibus vitae. Nulla facilisi morbi tempus iaculis urna id. Non nisi est sit amet facilisis. Libero nunc consequat interdum varius sit amet mattis vulputate.\n"
        + "\n"
        + "Sagittis vitae et leo duis. Sodales neque sodales ut etiam sit amet nisl purus in. Lorem ipsum dolor sit amet consectetur adipiscing elit pellentesque. Eu mi bibendum neque egestas congue quisque egestas diam in. Pellentesque massa placerat duis ultricies. Molestie a iaculis at erat pellentesque adipiscing commodo. Vitae suscipit tellus mauris a diam maecenas. Aliquet risus feugiat in ante metus. Et malesuada fames ac turpis egestas maecenas pharetra convallis posuere. Orci sagittis eu volutpat odio facilisis mauris sit. Morbi quis commodo odio aenean sed adipiscing. Amet nisl purus in mollis nunc sed id semper risus. At imperdiet dui accumsan sit amet nulla facilisi morbi tempus. Lorem sed risus ultricies tristique nulla aliquet. Netus et malesuada fames ac turpis. Eget nullam non nisi est sit amet facilisis magna. Enim sit amet venenatis urna cursus eget nunc scelerisque viverra.");
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
  public void setCompanyTitle(String text){
    companyTitle.setText(text);
  }
  public void setCompanyText(String text){
    companyText.setText(text);
  }

}
