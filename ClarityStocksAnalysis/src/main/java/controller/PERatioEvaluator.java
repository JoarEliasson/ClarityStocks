package controller;

import model.PERatioEvaluation;

public class PERatioEvaluator {

    public static PERatioEvaluation evaluatePriceEarningsRatio(String symbol, String name, double priceEarningsRatio) {

        String description = "";
        int score = getScore(priceEarningsRatio);

        if (score == -1) {
            description = "Price Earnings ratio is negative.\nThis is not possible, something must have went wrong.";
        }
        if (score == 5) {
            description = "Price Earnings ratio is less than 10, which is LOW.\nThis is a POSITIVE sign, indicating that the company is likely to be undervalued.";
        }
        if (score == 4) {
            description = "Price Earnings ratio is between 10 and 15, which is LOW to FAIR.\nThis is a POSITIVE sign, indicating that the company potentially may be undervalued.";
        }
        if (score == 3) {
            description = "Price Earnings ratio is between 15 and 20, which is FAIR.\nThis is a NEUTRAL sign, indicating that the company is likely valued correctly.";
        }
        if (score == 2) {
            description = "Price Earnings ratio is between 20 and 25, which is FAIR to HIGH.\nThis is potentially a NEGATIVE sign, indicating that there is a risk that the company is overvalued.";
        }
        if (score == 1) {
            description = "Price Earnings ratio is between 25 and 30, which is HIGH.\nThis is potentially a NEGATIVE sign, indicating that there is a substantial risk that the company is overvalued.";
        }
        if (score == 0) {
            description = "Price Earnings ratio is greater than 30, which is HIGH.\nThis is a NEGATIVE sign, indicating that there is a substantial risk that the company is overvalued.";
        }
        return new PERatioEvaluation(symbol, name, score, description);
    }

    private static int getScore(double peRatio) {
        int score = 0;
        if (peRatio < 30) score = 1;
        if (peRatio < 25) score = 2;
        if (peRatio < 20) score = 3;
        if (peRatio < 15) score = 4;
        if (peRatio < 10) score = 5;
        if (peRatio < 0) score = -1;
        return score;
    }
}
