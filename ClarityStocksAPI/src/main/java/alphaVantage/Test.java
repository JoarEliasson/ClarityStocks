package alphaVantage;

public class Test {

  public static void main(String[] args) {
    String symbol = "AAPL";

    AlphaVantageClient client = new AlphaVantageClient("YKB1S8EYZ61LDH9B");
    CompanyOverview companyOverview = null;

    try {
      FullStockOverview fullStockOverview = client.getFullStockOverview(symbol);
      System.out.println(fullStockOverview.toString());
      String desc = fullStockOverview.getDescription();
      String[] descParts = desc.split("\\.");
      for (String part : descParts) {
        System.out.println(part);
      }
      System.out.println("Sector" + fullStockOverview.getSector());
      System.out.println("Industry" + fullStockOverview.getIndustry());
      System.out.println(fullStockOverview.getAssetType());
      System.out.println(fullStockOverview.getAnalystTargetPrice());


    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
