package common.interfaces;

public interface RatingEvaluation extends Evaluation {

  /**
   * Method for getting the rating of the evaluation.
   *
   * @return the rating of the evaluation.
   */
  int getRating();

  /**
   * Method for getting the value of the variable that is being evaluated.
   *
   * @return the value of the variable that is being evaluated.
   */
  String getValue();

}
