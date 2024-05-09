package dao;

import common.data.fundamental.IncomeStatement;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import org.postgresql.util.PSQLException;

public class IncomeStatementDAO {
  private final DSLContext connectionContext;

  public IncomeStatementDAO(DSLContext connectionContext) {
    this.connectionContext = connectionContext;
  }

  public void insertIncomeStatement(List<IncomeStatement> incomeStatements) {
    try {
      var batchQueries = new ArrayList<Query>();

      for (IncomeStatement incomeStatement : incomeStatements) {
        var query = connectionContext.insertInto(DSL.table("income_statement"),
                DSL.field("symbol"),
                DSL.field("fiscal_date_ending"),
                DSL.field("reported_currency"),
                DSL.field("gross_profit"),
                DSL.field("total_revenue"),
                DSL.field("cost_of_revenue"),
                DSL.field("cost_of_goods_and_services_sold"),
                DSL.field("operating_income"),
                DSL.field("selling_general_and_administrative"),
                DSL.field("research_and_development"),
                DSL.field("operating_expense"),
                DSL.field("investment_income_net"),
                DSL.field("net_interest_income"),
                DSL.field("interest_income"),
                DSL.field("interest_expense"),
                DSL.field("non_interest_income"),
                DSL.field("other_non_operating_income"),
                DSL.field("depreciation"),
                DSL.field("depreciation_and_amortization"),
                DSL.field("income_before_tax"),
                DSL.field("income_tax_expense"),
                DSL.field("interest_and_debt_expense"),
                DSL.field("net_income_from_continuing_operations"),
                DSL.field("comprehensive_income_net_of_tax"),
                DSL.field("ebit"),
                DSL.field("ebitda"),
                DSL.field("net_income"))
            .values(
                incomeStatement.getSymbol(),
                Date.valueOf(incomeStatement.getFiscalDateEnding()),
                incomeStatement.getReportedCurrency(),
                incomeStatement.getGrossProfit(),
                incomeStatement.getTotalRevenue(),
                incomeStatement.getCostOfRevenue(),
                incomeStatement.getCostOfGoodsAndServicesSold(),
                incomeStatement.getOperatingIncome(),
                incomeStatement.getSellingGeneralAndAdministrative(),
                incomeStatement.getResearchAndDevelopment(),
                incomeStatement.getOperatingExpenses(),
                incomeStatement.getInvestmentIncomeNet(),
                incomeStatement.getNetInterestIncome(),
                incomeStatement.getInterestIncome(),
                incomeStatement.getInterestExpense(),
                incomeStatement.getNonInterestIncome(),
                incomeStatement.getOtherNonOperatingIncome(),
                incomeStatement.getDepreciation(),
                incomeStatement.getDepreciationAndAmortization(),
                incomeStatement.getIncomeBeforeTax(),
                incomeStatement.getIncomeTaxExpense(),
                incomeStatement.getInterestAndDebtExpense(),
                incomeStatement.getNetIncomeFromContinuingOperations(),
                incomeStatement.getComprehensiveIncomeNetOfTax(),
                incomeStatement.getEBIT(),
                incomeStatement.getEBITDA(),
                incomeStatement.getNetIncome()
            )
            .onConflict(DSL.field("symbol"))
            .doNothing();

        batchQueries.add(query);
      }

      connectionContext.batch(batchQueries).execute();
    } catch (DataAccessException e) {
      if (e.getCause() instanceof PSQLException psqlException) {
        if (psqlException.getSQLState().equals("23505")) {
          System.out.println("A record with the same stock symbol already exists in the database.");
        } else {
          System.out.println("Database error: " + e.getMessage());
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void getIncomeStatement() {
    // Get income statement
  }
}
