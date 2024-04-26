package analysis.model.evaluations;

import analysis.model.interfaces.Evaluation;

/**
 * Class for evaluating the growth of a company.
 * @author Olivia Svensson
 * */
public class CompanyGrowth implements Evaluation {
    String symbol;
    private double quarterlyRevenueGrowthYOY;
    String description = "";

    public CompanyGrowth(String symbol, double quarterlyRevenueGrowthYOY) {
        this.symbol = symbol;
        this.quarterlyRevenueGrowthYOY = quarterlyRevenueGrowthYOY;
    }

/**
 * The company’s revenue growth compared with previous quarters.
 *
 * Decline – Varning signal for any stock and company < 0%
 *
 * Stable < 5%
 *
 * Growth > 10%
 *
 * Strong growth > 15 %
 *
 *    * Year-over-year (YOY) quarterly revenue growth compares the revenue of a company between two
 *    * quarters (one year apart), showing the percentage increase or decrease in revenue.
 *    */

    @Override
    public void evaluate() {
        if(quarterlyRevenueGrowthYOY < 0) {
            description = "There is a high indication that " + symbol + " has a very poor performance.";
        } else if(quarterlyRevenueGrowthYOY >= 0  && quarterlyRevenueGrowthYOY <= 5) {
            description = "There is an indication that " + symbol + " has a stable performance.";
        } else if(quarterlyRevenueGrowthYOY > 5 && quarterlyRevenueGrowthYOY < 10) {
            description = "There is an indication that " + symbol + " is experiencing slight growth.";
        } else if(quarterlyRevenueGrowthYOY >= 10 && quarterlyRevenueGrowthYOY < 15) {
            description = "There is an indication that " + symbol + " is experiencing growth.";
        } else if(quarterlyRevenueGrowthYOY >= 15) {
            description = "There is an indication that " + symbol + " is experiencing strong growth.";
        } else {
            description = "Something went wrong with the company growth evaluation;";
        }
    }

    @Override
    public String getSymbol() {
        return symbol;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
