package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.model.Effects;
import io.github.palexdev.mfxcore.controls.Label;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import userModel.UserProfile;
import userModel.UserProfileManager;

import java.util.Optional;

public class FavoriteListController {

  @FXML
  public Rectangle favorite;
  @FXML
  public VBox favoriteListVBox;

  @FXML
  private ListView<String> favoritesListView;
  //@FXML private Label noFavoritesLabel;

  private UserProfile userProfile;
  private final String userFilePath = "ClarityStocksUser/userInfo.json";

  @FXML
  public void initialize() {
    favorite.setEffect(Effects.getDropShadow());
    loadUserProfile();
    updateFavoriteList();
  }

  private void loadUserProfile() {
    userProfile = UserProfileManager.loadUserInformation(userFilePath);
  }

  public void updateFavoriteList() {
    if (userProfile != null && userProfile.getFavoriteStocks() != null
        && !userProfile.getFavoriteStocks().isEmpty()) {
      favoritesListView.setItems(
          FXCollections.observableArrayList(userProfile.getFavoriteStocks()));
      //noFavoritesLabel.setVisible(false);
    } else {
      //noFavoritesLabel.setVisible(true);
      System.out.println("sium");
    }
  }

  @FXML
  public void handleAddStock() {
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Add Favorite Stock");
    dialog.setHeaderText("Enter the stock symbol you wish to add to favorites:");
    Optional<String> result = dialog.showAndWait();
    result.ifPresent(this::addFavoriteStock);
  }

  private void addFavoriteStock(String stockSymbol) {
    if (userProfile != null && stockSymbol != null && !stockSymbol.isEmpty()) {
      userProfile.addFavoriteStock(stockSymbol);
      UserProfileManager.saveUserInformation(userProfile, userFilePath);
      updateFavoriteList();
    }
  }

}
