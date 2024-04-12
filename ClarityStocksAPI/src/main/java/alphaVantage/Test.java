package alphaVantage;

import java.util.List;

public class Test {

  public static void main(String[] args) {
    String symbol = "AAPL";

    AlphaVantageClient client = new AlphaVantageClient("YKB1S8EYZ61LDH9B");
    CompanyOverview companyOverview = null;
    List<String> incomeStatement = null;
    List<Double> balanceSheet = null;
    List<Double> cashFlow = null;
    try {
      //companyOverview = client.getCompanyOverview(symbol);
      incomeStatement = client.getIncomeStatement(symbol);
      //balanceSheet = client.getBalanceSheet(symbol);
      //cashFlow = client.getCashFlow(symbol);
    } catch (Exception e) {
      e.printStackTrace();
    }
    assert incomeStatement != null;
    for (String value : incomeStatement) {
      System.out.println(value);
    }

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
