package common.evaluations;

import common.interfaces.Evaluation;
import java.time.LocalDate;

/**
 * Class for evaluating the dividend yield and timing of the dividend payout.
 * <p>
 *   The class calculates the dividend yield for the fiscal year and compares the fiscal year
 *   with the current month.
 * </p>
 *
 * @author Olivia Svensson, Joar Eliasson
 */
public class DividendEvaluation implements Evaluation {

  private final String symbol;
  private final int currentYear;
  private final double dividendPerShare;
  private final double dividendYield;
  private final String fiscalYearEnd;
  private String description;

  public DividendEvaluation(String symbol, double dividendPerShare,
      double dividendYield, String fiscalYearEnd) {
    this.symbol = symbol;
    this.dividendPerShare = dividendPerShare;
    this.dividendYield = dividendYield;
    this.fiscalYearEnd = fiscalYearEnd;
    this.currentYear = LocalDate.now().getYear();
  }

  /**
   * Method for evaluating the dividend yield and timing of the dividend payout.
   * <p>
   *   The method calculates the dividend yield for the fiscal year and compares the fiscal year
   *   with the current month.
   * </p>
   *
   * @author Olivia Svensson, Joar Eliasson
   */
  @Override
  public void evaluate() {
    int month = getMonthNumber(fiscalYearEnd);
    int currentMonth = LocalDate.now().getMonthValue();
    if (month == currentMonth) {
      description = String.format(
          "The dividend for fiscal year %d has been paid out in %s.%n"
          + "The dividend yield for fiscal year %d was %.2f%%.%n"
          + "The dividend per share was %.2f.",
          currentYear, fiscalYearEnd, currentYear, dividendYield, dividendPerShare
      );
    } else {
      int monthDifference = month - currentMonth;
      String monthString = monthDifference == 1 ? "month" : "months";
      description = String.format(
          "The dividend for fiscal year %d has not been paid out yet.%n"
          + "The dividend yield for last year was %.2f%%.%n"
          + "The dividend per share was %.2f.%n"
          + "The dividend will be paid out in %d %s.",
          currentYear, dividendYield, dividendPerShare, monthDifference, monthString
      );
    }
  }

  @Override
  public String getSymbol() {
    return symbol;
  }

  /**
   * Method for getting the title of the evaluation.
   * <p>
   * The title corresponds to the type of evaluation that is performed.
   *
   * @return the title of the evaluation.
   */
  @Override
  public String getTitle() {
    return "";
  }

  /**
   * Method for getting the subtitle of the evaluation.
   * <p>
   * The subtitle is a short description of the data that the evaluation is based on.
   *
   * @return the subtitle of the evaluation.
   */
  @Override
  public String getSubtitle() {
    return "";
  }

  /**
   * Method for getting the evaluation method.
   * <p>
   * The evaluation method is a short description of how the evaluation is performed.
   *
   * @return the evaluation method.
   */
  @Override
  public String getEvaluationMethod() {
    return "";
  }

  @Override
  public String getDescription() {
    return description;
  }

  private int getMonthNumber(String month) {
    return switch (month) {
      case "January" -> 1;
      case "February" -> 2;
      case "March" -> 3;
      case "April" -> 4;
      case "May" -> 5;
      case "June" -> 6;
      case "July" -> 7;
      case "August" -> 8;
      case "September" -> 9;
      case "October" -> 10;
      case "November" -> 11;
      case "December" -> 12;
      default -> 0;
    };
  }
}
