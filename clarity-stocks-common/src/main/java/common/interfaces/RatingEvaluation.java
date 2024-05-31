package common.interfaces;

import common.evaluations.rating.*;

/**
 * Interface for evaluating stock parameters with a rating system.
 * <p>
 * This interface extends {@code Evaluation} and includes methods for obtaining a rating and the
 * value of the variable that is being evaluated.
 * </p>
 *
 * @see common.interfaces.Evaluation
 * @see AnalystPredictionEvaluation
 * @see BusinessPerformanceEvaluation
 * @see CompanyGrowthEvaluation
 * @see CompanySizeEvaluation
 * @see HighAndLowEvaluation
 * @see PERatioEvaluation
 * @see PriceToPerformanceEvaluation
 *
 * @author Joar Eliasson
 */
public interface RatingEvaluation extends Evaluation {

  /**
   * Method for getting the rating of the evaluation.
   * <p>
   * The rating represents the result of the evaluation as a numerical value.
   *
   * @return the rating of the evaluation.
   */
  double getRating();

  /**
   * Method for getting the description of the rating.
   * <p>
   * The description provides a textual representation of the rating.
   *
   * @return the description of the rating.
   */
  String getRatingDescription();

  /**
   * Method for getting the value of the variable that is being evaluated.
   * <p>
   * This method returns the specific value that was used in the evaluation process.
   *
   * @return the value of the variable that is being evaluated.
   */
  String getValue();

}
