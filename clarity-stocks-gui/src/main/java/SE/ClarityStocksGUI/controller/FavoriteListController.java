package SE.ClarityStocksGUI.controller;

import SE.ClarityStocksGUI.model.Effects;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import user.model.UserProfile;
import user.model.UserProfileManager;

public class FavoriteListController {
    @FXML public Rectangle favorite;
    @FXML public VBox favoriteListVBox;

    @FXML private ListView<String> favoritesListView;
    private UserProfile userProfile;
    private final String userFilePath = "clarity-stocks-user/userInfo.json";
    private ObservableList<String> favoriteItems = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        userProfile = UserProfileManager.loadUserInformation(userFilePath);
        favorite.setEffect(Effects.getDropShadow());
        favoritesListView.setItems(favoriteItems);
        loadFavorites();
    }

    public void loadFavorites() {
        UserProfile userProfile = UserProfileManager.loadUserInformation(userFilePath);
        if (userProfile != null && userProfile.getFavoriteStocks() != null) {
            favoritesListView.getItems().setAll(userProfile.getFavoriteStocks());
        } else {
            favoritesListView.getItems().clear();
        }
    }
    @FXML
    private void handleLoadFavorites() {
        loadFavorites();
    }

    public void addFavoriteStock(String stockSymbol) {
        if (userProfile != null && stockSymbol != null && !stockSymbol.isEmpty()) {
            System.out.println("Adding stock: " + stockSymbol);
            userProfile.addFavoriteStock(stockSymbol);
            UserProfileManager.saveUserInformation(userProfile, userFilePath);
            System.out.println("Current favorites: " + userProfile.getFavoriteStocks());
            loadFavorites();
        }
    }

    public void removeFavoriteStock(String stockSymbol) {
        if (userProfile == null) {
            System.out.println("User profile is null.");
        } else if (stockSymbol == null || stockSymbol.isEmpty()) {
            System.out.println("Stock symbol is null or empty.");
        } else if (!userProfile.getFavoriteStocks().contains(stockSymbol)) {
            System.out.println("Stock symbol not found in favorites: " + stockSymbol);
            System.out.println("Favorites list: " + userProfile.getFavoriteStocks());
        } else {
            userProfile.getFavoriteStocks().remove(stockSymbol);
            UserProfileManager.saveUserInformation(userProfile, userFilePath);
            loadFavorites();
        }
    }
}
