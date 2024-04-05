package SE.ClarityStocksGUI.controller;

public class SearchController {

  private static SearchController searchController;

  private SearchController() {
  }

  private static SearchController getInstance() {
    if (searchController == null) {
      searchController = new SearchController();
    }

    return searchController;
  }
}
