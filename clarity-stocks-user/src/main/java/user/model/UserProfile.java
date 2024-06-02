package user.model;

import java.util.HashSet;

/**
 * Represents the profile of the user, including their username and list of favorite stocks.
 * <p>
 * Provides methods to manage the favorite stocks and access the user's details.
 * </p>
 *
 * @author Ibrahim Tafankaji
 * @see UserProfileManager
 */
public class UserProfile {

  private String userName;
  private HashSet<String> favoriteStocks;

  /**
   * Creates a new UserProfile with a default username.
   */
  public UserProfile() {
    this.userName = "Username";
  }

  /**
   * Creates a new UserProfile with the specified username.
   *
   * @param userName the username to be set
   */
  public UserProfile(String userName) {
    this.userName = userName;
    this.favoriteStocks = new HashSet<>();

  }

  /**
   * Adds a stock symbol to the user's list of favorite stocks.
   *
   * @param stockSymbol the stock symbol to add to the favorites list
   */
  public void addFavoriteStock(String stockSymbol) {
    if (!favoriteStocks.contains(stockSymbol)) {
      favoriteStocks.add(stockSymbol);
    }
  }

  /**
   * Checks if a stock symbol is in the user's list of favorite stocks.
   *
   * @param stockSymbol the stock symbol to check
   * @return true if the stock symbol is a favorite, false otherwise
   */
  public boolean isFavorite(String stockSymbol) {
    return favoriteStocks.contains(stockSymbol);
  }

  /**
   * Gets the username of the user.
   *
   * @return the username
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Sets the username of the user.
   *
   * @param userName the new username to set
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * Gets the user's list of favorite stocks.
   *
   * @return a set of favorite stock symbols
   */
  public HashSet<String> getFavoriteStocks() {
    if (favoriteStocks == null) {
      favoriteStocks = new HashSet<>();
    }
    return favoriteStocks;
  }
}
