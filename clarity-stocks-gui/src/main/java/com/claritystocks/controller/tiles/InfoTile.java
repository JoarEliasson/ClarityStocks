package com.claritystocks.controller.tiles;

import com.claritystocks.controller.GUIMainController;
import com.claritystocks.controller.GUIStockViewController;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


/**
 * This class handles the info tile which displays general information about the stock in the
 * stock-view.
 * <p>
 * It's parent class is the {@code GUIMainController} which is the controller for the main view.
 * </p>
 *
 * @see GUIMainController
 *
 * @author Douglas Almö Thorsell
 * @author Ibrahinm Tafankaji
 */
public class InfoTile {
  @FXML
  private Label nameLabel;
  @FXML
  private Label sectorLabel;
  @FXML
  private Label exchangeLabel;
  @FXML
  private Label descriptionLabel;
  @FXML
  private ImageView favoriteIcon;
  private ArrayList<Image> favoriteImages;
  private boolean stockIsFavorite;
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
    stockIsFavorite = !stockIsFavorite;
    favoriteIcon.setImage(favoriteImages.get(stockIsFavorite ? 1 : 0));
  }

  private void loadFavoriteImages(){
    favoriteImages = new ArrayList<>();
    try {
      Image notSelected = new Image(getClass().getResource(
          "/com/claritystocks/view/images/favorite-star-not-selected.png").toExternalForm());
      Image selected = new Image(getClass().getResource(
          "/com/claritystocks/view/images/favorite-star-selected.png").toExternalForm());
      favoriteImages.add(notSelected);
      favoriteImages.add(selected);
    } catch (Exception e) {
      System.err.println("Error loading images: " + e.getMessage());
    }
  }

  public void setCompanyName(String text) {
    nameLabel.setText(text);
  }

  public void setSector(String text) {
    sectorLabel.setText(text);
  }

  public void setExchange(String text){
    exchangeLabel.setText(text);
  }

  public void setDescription(String text) {
    descriptionLabel.setText(text);
  }

  public void setController(GUIStockViewController controller){
    this.controller = controller;
  }
}
