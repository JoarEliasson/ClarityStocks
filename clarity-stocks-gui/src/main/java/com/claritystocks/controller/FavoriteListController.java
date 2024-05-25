package com.claritystocks.controller;

import com.claritystocks.model.Effects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import user.model.UserProfile;
import user.model.UserProfileManager;
/**
 * Controller for managing the favorite stocks list.
 * Handles the loading, adding, and removing of favorite stocks.
 * Also manages interactions with the favorite stocks list view.
 * @author: Ibrahim Tafankaji
 */
public class FavoriteListController {
    @FXML public Rectangle favorite;
    @FXML public VBox favoriteListVBox;

    @FXML private ListView<String> favoritesListView;
    private UserProfile userProfile;
    private final String userFilePath = "clarity-stocks-user/userInfo.json";
    private ObservableList<String> favoriteItems = FXCollections.observableArrayList();
    private GUIMainController mainController;

    /**
     * Initializes the controller.
     * Loads user profile, sets up the drop shadow effect, and initializes the favorite stocks list view.
     */
    @FXML
    public void initialize() {
        userProfile = UserProfileManager.loadUserInformation(userFilePath);
        favorite.setEffect(Effects.getDropShadow());
        favoritesListView.setItems(favoriteItems);
        mainController = GUIMainController.getInstance();
        System.out.println(mainController);
        loadFavorites();
    }
    /**
     * Loads the favorite stocks from the user profile into the list view.
     */
    public void loadFavorites() {
        UserProfile userProfile = UserProfileManager.loadUserInformation(userFilePath);
        if (userProfile != null && userProfile.getFavoriteStocks() != null) {
            favoritesListView.getItems().setAll(userProfile.getFavoriteStocks());
        } else {
            favoritesListView.getItems().clear();
        }
    }
    /**
     * Handles the action to load favorite stocks.
     */
    @FXML
    private void handleLoadFavorites() {
        loadFavorites();
    }

    /**
     * Adds a stock symbol to the user's list of favorite stocks.
     *
     * @param stockSymbol the stock symbol to add to the favorites list
     */
    public void addFavoriteStock(String stockSymbol) {
        if (userProfile != null && stockSymbol != null && !stockSymbol.isEmpty()) {
            System.out.println("Adding stock: " + stockSymbol);
            userProfile.addFavoriteStock(stockSymbol);
            UserProfileManager.saveUserInformation(userProfile, userFilePath);
            System.out.println("Current favorites: " + userProfile.getFavoriteStocks());
            loadFavorites();
        }
    }

    /**
     * Removes a stock symbol from the user's list of favorite stocks.
     *
     * @param stockSymbol the stock symbol to remove from the favorites list
     */

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

    /**
     * Handles the click event on the list view.
     * Navigates to the detailed view of the selected stock.
     *
     * @param mouseEvent the mouse event
     */

    @FXML
    public void handleListViewClick(MouseEvent mouseEvent) {
        System.out.println("ListView clicked");
        String selectedStock = favoritesListView.getSelectionModel().getSelectedItem();
        if (selectedStock == null) {
            System.out.println("No stock selected.");
            return;
        }
        System.out.println("Selected stock: " + selectedStock);
        if (mainController == null) {
            mainController = GUIMainController.getInstance();
        }
            mainController.goToStockView(selectedStock);
    }
    /**
     * Sets the main controller.
     *
     * @param controller the main controller to set
     */
    public void setController(GUIMainController controller){
        this.mainController = controller;
    }

}
