package SE.ClarityStocksGUI.controller.stockViewTiles;

import SE.ClarityStocksGUI.controller.GUIStockViewController;
import java.util.ArrayList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javax.swing.ImageIcon;

/**
 * This class handles the info tile which displays general information about the stock in the
 * stock-view.
 * <p>
 * It's parent class is the Main-view.
 * @author Douglas Almö Thorsell
 * @see SE.ClarityStocksGUI.controller.GUIStockViewController
 */
public class InfoTile {
  @FXML
  private Label nameLabel;
  @FXML
  private Label sectorLabel;
  @FXML
  private Label descriptionLabel;
  @FXML
  private ImageView favoriteIcon;
  private ArrayList<Image> favoriteImages;
  private boolean stockIsFavorite = false;
  private GUIStockViewController controller;
  public void initialize(){
    loadFavoriteImages();
    setUpFavoriteIcon();
  }

  public void setUpFavoriteIcon(){
    favoriteIcon.setFitWidth(25);
    favoriteIcon.setFitHeight(25);
    favoriteIcon.setImage(favoriteImages.get(0));
  }


  @FXML
  public void favoritePressed(){
    controller.stockFavoritePressed(stockIsFavorite);
  }

  private void loadFavoriteImages(){
    favoriteImages = new ArrayList<>();
    favoriteImages.add(new Image(
        getClass().getResource("/SE/ClarityStocksGUI/view/favorite-star-not-selected.png")
            .toExternalForm()));
    favoriteImages.add(new Image(
        getClass().getResource("/SE/ClarityStocksGUI/view/favorite-star-selected.png")
            .toExternalForm()));
  }
  public void setCompanyName(String text) {
    nameLabel.setText(text);
  }

  public void setSector(String text) {
    sectorLabel.setText(text);
  }

  public void setDescription(String text) {
    descriptionLabel.setText(text);
  }

  public void setController(GUIStockViewController controller){
    this.controller = controller;
  }
}
