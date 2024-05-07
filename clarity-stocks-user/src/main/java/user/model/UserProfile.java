package user.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserProfile {
    private String userName;
    private List<String> favoriteStocks;
    private boolean isLoggedIn = false;


    public UserProfile() {
        this.userName = "Username";
    }

    public UserProfile(String userName) {
        this.userName = userName;
        this.favoriteStocks = new ArrayList<>();

    }
    public void addFavoriteStock(String stockSymbol) {
        if (!favoriteStocks.contains(stockSymbol)) {
            favoriteStocks.add(stockSymbol);
        }
    }

    public boolean isFavorite(String stockSymbol) {
        return favoriteStocks.contains(stockSymbol);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<String> getFavoriteStocks() {
        if (favoriteStocks == null) {
            favoriteStocks = new ArrayList<>();
        }
        return favoriteStocks;
    }
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

}
