package common.data.fundamental;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for storing earnings data.
 * <p>
 * This class holds lists of annual and quarterly earnings data.
 * </p>
 *
 * <ul>
 *   <li>{@code annualEarnings} - A list of annual earnings data.</li>
 *   <li>{@code quarterlyEarnings} - A list of quarterly earnings data.</li>
 * </ul>
 *
 * @see AnnualEarnings
 * @see QuarterlyEarnings
 *
 * @author Joar Eliason
 */
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
