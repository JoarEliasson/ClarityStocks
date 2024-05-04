package SE.ClarityStocksGUI.controller;

import io.github.palexdev.mfxcore.controls.Label;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import userModel.UserProfile;
import userModel.UserProfileManager;

import java.util.Optional;

public class FavoriteListController {
    @FXML public Rectangle favorite;
    @FXML public VBox favoriteListVBox;

    @FXML private ListView<String> favoritesListView;
    private UserProfile userProfile;
    private final String userFilePath = "ClarityStocksUser/userInfo.json";
    private ObservableList<String> favoriteItems = FXCollections.observableArrayList();


    @FXML
    public void initialize() {
        loadUserProfile();
        favoritesListView.setItems(favoriteItems);
        updateFavoriteList();
    }

    private void loadUserProfile() {
        userProfile = UserProfileManager.loadUserInformation(userFilePath);
    }

    public void updateFavoriteList() {
        favoriteItems.clear();
        if (userProfile != null && userProfile.getFavoriteStocks() != null) {
            favoriteItems.setAll(userProfile.getFavoriteStocks());
            System.out.println("Favorite list updated: " + userProfile.getFavoriteStocks());
        } else {
            favoriteItems.clear(); // Clear the list if no favorites are available or loaded
            System.out.println("No favorites available or user profile is not loaded.");
        }
    }


    public void addFavoriteStock(String stockSymbol) {
        if (userProfile != null && stockSymbol != null && !stockSymbol.isEmpty()) {
            System.out.println("Adding stock: " + stockSymbol);
            userProfile.addFavoriteStock(stockSymbol);
            UserProfileManager.saveUserInformation(userProfile, userFilePath);
            updateFavoriteList();
            System.out.println("Current favorites: " + userProfile.getFavoriteStocks());
        }
    }

    public UserProfile getUserProfile() {
        return userProfile;
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
            updateFavoriteList();
            System.out.println("Removed stock, updated favorites: " + userProfile.getFavoriteStocks());
        }
    }

}
