package dao;

import common.data.fundamental.IncomeStatement;
import common.data.series.DailyDataPoint;
import common.data.series.TimeSeriesDaily;
import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.jooq.DSLContext;
import org.jooq.Query;
import org.jooq.Record;
import org.jooq.Result;
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
                DSL.field("operating_expenses"),
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
            .onConflict(DSL.field("symbol"), DSL.field("fiscal_date_ending"))
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
      System.err.println("Error retrieving income statement data: " + e.getMessage());
      e.printStackTrace();
    }
  }

  public List<IncomeStatement> getIncomeStatement(String symbol) {
    try {
      List<IncomeStatement> incomeStatements = new ArrayList<>();

      Result<Record> result = connectionContext.select()
        .from("income_statement")
        .where("symbol = ?", symbol)
        .orderBy(DSL.field("fiscal_date_ending").asc())
      .fetch();

      if (result.isEmpty()) {
        return null;
      }

      for (Record record : result) {
        IncomeStatement incomeStatement = new IncomeStatement(symbol);

        incomeStatement.setFiscalDateEnding(record.getValue(DSL.field(
            "fiscal_date_ending", Date.class)).toString()
        );
        incomeStatement.setReportedCurrency(record.getValue(DSL.field(
            "reported_currency", String.class))
        );
        incomeStatement.setGrossProfit(record.getValue(DSL.field(
            "gross_profit", BigInteger.class)).longValue()
        );
        incomeStatement.setTotalRevenue(record.getValue(DSL.field(
            "total_revenue", BigInteger.class)).longValue()
        );
        incomeStatement.setCostOfRevenue(record.getValue(DSL.field(
            "cost_of_revenue", BigInteger.class)).longValue()
        );
        incomeStatement.setCostOfGoodsAndServicesSold(record.getValue(DSL.field(
            "cost_of_goods_and_services_sold", BigInteger.class)).longValue()
        );
        incomeStatement.setOperatingIncome(record.getValue(DSL.field(
            "operating_income", BigInteger.class)).longValue()
        );
        incomeStatement.setSellingGeneralAndAdministrative(record.getValue(DSL.field(
            "selling_general_and_administrative", BigInteger.class)).longValue()
        );
        incomeStatement.setResearchAndDevelopment(record.getValue(DSL.field(
            "research_and_development", BigInteger.class)).longValue()
        );
        incomeStatement.setOperatingExpenses(record.getValue(DSL.field(
            "operating_expense", BigInteger.class)).longValue()
        );
        incomeStatement.setInvestmentIncomeNet(record.getValue(DSL.field(
            "investment_income_net", BigInteger.class)).longValue()
        );
        incomeStatement.setNetInterestIncome(record.getValue(DSL.field(
            "net_interest_income", BigInteger.class)).longValue()
        );
        incomeStatement.setInterestIncome(record.getValue(DSL.field(
            "interest_income", BigInteger.class)).longValue()
        );
        incomeStatement.setInterestExpense(record.getValue(DSL.field(
            "interest_expense", BigInteger.class)).longValue()
        );
        incomeStatement.setNonInterestIncome(record.getValue(DSL.field(
            "non_interest_income", BigInteger.class)).longValue()
        );
        incomeStatement.setOtherNonOperatingIncome(record.getValue(DSL.field(
            "other_non_operating_income", BigInteger.class)).longValue()
        );
        incomeStatement.setDepreciation(record.getValue(DSL.field(
            "depreciation", BigInteger.class)).longValue()
        );
        incomeStatement.setDepreciationAndAmortization(record.getValue(DSL.field(
            "depreciation_and_amortization", BigInteger.class)).longValue()
        );
        incomeStatement.setIncomeBeforeTax(record.getValue(DSL.field(
            "income_before_tax", BigInteger.class)).longValue()
        );
        incomeStatement.setIncomeTaxExpense(record.getValue(DSL.field(
            "income_tax_expense", BigInteger.class)).longValue()
        );
        incomeStatement.setInterestAndDebtExpense(record.getValue(DSL.field(
            "interest_and_debt_expense", BigInteger.class)).longValue()
        );
        incomeStatement.setNetIncomeFromContinuingOperations(record.getValue(DSL.field(
            "net_income_from_continuing_operations", BigInteger.class)).longValue()
        );
        incomeStatement.setComprehensiveIncomeNetOfTax(record.getValue(DSL.field(
            "comprehensive_income_net_of_tax", BigInteger.class)).longValue()
        );
        incomeStatement.setEBIT(record.getValue(DSL.field(
            "ebit", BigInteger.class)).longValue()
        );
        incomeStatement.setEBITDA(record.getValue(DSL.field(
            "ebitda", BigInteger.class)).longValue()
        );
        incomeStatement.setNetIncome(record.getValue(DSL.field(
            "net_income", BigInteger.class)).longValue()
        );

        incomeStatements.add(incomeStatement);
      }

      return incomeStatements;
    } catch (Exception e) {
      System.err.println("Error retrieving income statement data: " + e.getMessage());
      e.printStackTrace();
      return null;
    }
  }
}
