package user.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserProfile {
    private String userName;
    private HashSet<String> favoriteStocks;


    public UserProfile() {
        this.userName = "Username";
    }

    public UserProfile(String userName) {
        this.userName = userName;
        this.favoriteStocks = new HashSet<>();

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

    public HashSet<String> getFavoriteStocks() {
        if (favoriteStocks == null) {
            favoriteStocks = new HashSet<>();
        }
        return favoriteStocks;
    }
}
