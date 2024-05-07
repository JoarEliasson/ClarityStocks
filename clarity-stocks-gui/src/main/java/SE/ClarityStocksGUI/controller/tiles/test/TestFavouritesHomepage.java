package SE.ClarityStocksGUI.controller.tiles.test;

/*


This class is purely for testing GUI functionality.
The methods and variables are static, so you don't have to create an object of the class.


 */
public class TestFavouritesHomepage {

  private static String testData;

  public static String getTestData() {
    if (testData == null) {
      return "Test data has been updated but it's null";
    }
    return testData;
  }

  public static void setTestData(String testData) {
    TestFavouritesHomepage.testData = testData;
  }

}
