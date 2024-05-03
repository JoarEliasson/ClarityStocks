package common.data.fundamental;

import java.util.ArrayList;
import java.util.List;

public class EarningsData {

  private List<AnnualEarnings> annualEarnings = new ArrayList<>();
  private List<QuarterlyEarnings> quarterlyEarnings = new ArrayList<>();

  public List<AnnualEarnings> getAnnualEarnings() {
    return annualEarnings;
  }

  public void setAnnualEarnings(List<AnnualEarnings> annualEarnings) {
    this.annualEarnings = annualEarnings;
  }

  public List<QuarterlyEarnings> getQuarterlyEarnings() {
    return quarterlyEarnings;
  }

  public void setQuarterlyEarnings(List<QuarterlyEarnings> quarterlyEarnings) {
    this.quarterlyEarnings = quarterlyEarnings;
  }
}
