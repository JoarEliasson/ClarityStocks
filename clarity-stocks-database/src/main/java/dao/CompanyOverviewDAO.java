package dao;

import common.data.fundamental.CompanyOverview;
import java.math.BigDecimal;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.jooq.exception.DataAccessException;
import org.jooq.impl.DSL;
import java.sql.Date;
import org.postgresql.util.PSQLException;

/**
 * The class facilitates interaction with the database to manage company fundamental data.
 * <p>
 * It provides methods to insert and retrieve details through a CompanyOverview object.
 * This class uses the jOOQ library to create a connection context that ensures SQL operations are
 * safe and efficient.
 * </p>
 * <p>The main functionalities include:</p>
 * <ul>
 *  <li>Inserting fundamental data of a company into the database.</li>
 *  <li>Retrieving fundamental data of a company from the database.</li>
 * </ul>
 * @author Kasper Schröder
 */
public class CompanyOverviewDAO {
  private final DSLContext connectionContext;

  public CompanyOverviewDAO(DSLContext connection) {
    this.connectionContext = connection;
  }

  public void insertCompanyOverview(CompanyOverview companyOverview) {
    try {
      connectionContext.insertInto(DSL.table("company_overview"),
              DSL.field("symbol"),
              DSL.field("asset_type"),
              DSL.field("name"),
              DSL.field("description"),
              DSL.field("cik"),
              DSL.field("exchange"),
              DSL.field("currency"),
              DSL.field("country"),
              DSL.field("sector"),
              DSL.field("industry"),
              DSL.field("address"),
              DSL.field("fiscal_year_end"),
              DSL.field("latest_quarter"),
              DSL.field("market_capitalization"),
              DSL.field("ebitda"),
              DSL.field("pe_ratio"),
              DSL.field("peg_ratio"),
              DSL.field("book_value"),
              DSL.field("dividend_per_share"),
              DSL.field("dividend_yield"),
              DSL.field("eps"),
              DSL.field("revenue_per_share_ttm"),
              DSL.field("profit_margin"),
              DSL.field("operating_margin_ttm"),
              DSL.field("return_on_assets_ttm"),
              DSL.field("return_on_equity_ttm"),
              DSL.field("revenue_ttm"),
              DSL.field("gross_profit_ttm"),
              DSL.field("diluted_eps_ttm"),
              DSL.field("quarterly_earnings_growth_yoy"),
              DSL.field("quarterly_revenue_growth_yoy"),
              DSL.field("analyst_target_price"),
              DSL.field("analyst_rating_strong_buy"),
              DSL.field("analyst_rating_buy"),
              DSL.field("analyst_rating_hold"),
              DSL.field("analyst_rating_sell"),
              DSL.field("analyst_rating_strong_sell"), //
              DSL.field("trailing_pe"),
              DSL.field("forward_pe"),
              DSL.field("price_to_sales_ratio_ttm"),
              DSL.field("price_to_book_ratio"),
              DSL.field("ev_to_revenue"),
              DSL.field("ev_to_ebitda"),
              DSL.field("beta"),
              DSL.field("week_52_high"),
              DSL.field("week_52_low"),
              DSL.field("moving_average_50"),
              DSL.field("moving_average_200"),
              DSL.field("shares_outstanding"),
              DSL.field("dividend_date"),
              DSL.field("ex_dividend_date"))
          .values(
              companyOverview.getSymbol(),
              companyOverview.getAssetType(),
              companyOverview.getName(),
              companyOverview.getDescription(),
              companyOverview.getCIK(),
              companyOverview.getExchange(),
              companyOverview.getCurrency(),
              companyOverview.getCountry(),
              companyOverview.getSector(),
              companyOverview.getIndustry(),
              companyOverview.getAddress(),
              companyOverview.getFiscalYearEnd(),
              Date.valueOf(companyOverview.getLatestQuarter()),
              companyOverview.getMarketCapitalization(),
              companyOverview.getEBITDA(),
              companyOverview.getPERatio(),
              companyOverview.getPEGRatio(),
              companyOverview.getBookValue(),
              companyOverview.getDividendPerShare(),
              companyOverview.getDividendYield(),
              companyOverview.getEPS(),
              companyOverview.getRevenuePerShareTTM(),
              companyOverview.getProfitMargin(),
              companyOverview.getOperatingMarginTTM(),
              companyOverview.getReturnOnAssetsTTM(),
              companyOverview.getReturnOnEquityTTM(),
              companyOverview.getRevenueTTM(),
              companyOverview.getGrossProfitTTM(),
              companyOverview.getDilutedEPSTTM(),
              companyOverview.getQuarterlyEarningsGrowthYOY(),
              companyOverview.getQuarterlyRevenueGrowthYOY(),
              companyOverview.getAnalystTargetPrice(),
              companyOverview.getAnalystRatingStrongBuy(), //
              companyOverview.getAnalystRatingBuy(),
              companyOverview.getAnalystRatingHold(),
              companyOverview.getAnalystRatingSell(),
              companyOverview.getAnalystRatingStrongSell(),
              companyOverview.getTrailingPE(),
              companyOverview.getForwardPE(),
              companyOverview.getPriceToSalesRatioTTM(),
              companyOverview.getPriceToBookRatio(),
              companyOverview.getEVToRevenue(),
              companyOverview.getEVToEBITDA(),
              companyOverview.getBeta(),
              companyOverview.getWeek52High(),
              companyOverview.getWeek52Low(),
              companyOverview.getMovingAverage50(),
              companyOverview.getMovingAverage200(),
              companyOverview.getSharesOutstanding(),
              Date.valueOf(companyOverview.getDividendDate()),
              Date.valueOf(companyOverview.getExDividendDate())
          )
          .onConflict(DSL.field("symbol"))
          .doNothing()
          .execute();
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

  public CompanyOverview getCompanyOverview(String symbol) {
    try {
      CompanyOverview companyOverview = new CompanyOverview();

      Result<Record> result = connectionContext.fetch(
        "SELECT * " +
            "FROM company_overview where symbol = ?",
        symbol
      );

      if (result.isEmpty()) {
        return null;
      }

      companyOverview.setSymbol(result.getValue(0, DSL.field(
          "symbol", String.class))
      );
      companyOverview.setAssetType(result.getValue(0, DSL.field(
          "asset_type", String.class))
      );
      companyOverview.setName(result.getValue(0, DSL.field(
          "name", String.class))
      );
      companyOverview.setDescription(result.getValue(0, DSL.field(
          "description", String.class))
      );
      companyOverview.setCIK(result.getValue(0, DSL.field(
          "cik", String.class))
      );
      companyOverview.setExchange(result.getValue(0, DSL.field(
          "exchange", String.class))
      );
      companyOverview.setCurrency(result.getValue(0, DSL.field(
          "currency", String.class))
      );
      companyOverview.setCountry(result.getValue(0, DSL.field(
          "country", String.class))
      );
      companyOverview.setSector(result.getValue(0, DSL.field(
          "sector", String.class))
      );
      companyOverview.setIndustry(result.getValue(0, DSL.field(
          "industry", String.class))
      );
      companyOverview.setAddress(result.getValue(0, DSL.field(
          "address", String.class))
      );
      companyOverview.setFiscalYearEnd(result.getValue(0, DSL.field(
          "fiscal_year_end", String.class))
      );
      companyOverview.setLatestQuarter(result.getValue(0, DSL.field(
          "latest_quarter", Date.class)).toString()
      );
      companyOverview.setMarketCapitalization(result.getValue(0, DSL.field(
          "market_capitalization", Long.class))
      );
      companyOverview.setEBITDA(result.getValue(0, DSL.field(
          "ebitda", BigDecimal.class)).doubleValue()
      );
      companyOverview.setPERatio(result.getValue(0, DSL.field(
          "pe_ratio", BigDecimal.class)).doubleValue()
      );
      companyOverview.setPEGRatio(result.getValue(0, DSL.field(
          "peg_ratio", BigDecimal.class)).doubleValue()
      );
      companyOverview.setBookValue(result.getValue(0, DSL.field(
          "book_value", BigDecimal.class)).doubleValue()
      );
      companyOverview.setDividendPerShare(result.getValue(0, DSL.field(
          "dividend_per_share", BigDecimal.class)).doubleValue()
      );
      companyOverview.setDividendYield(result.getValue(0, DSL.field(
          "dividend_yield", BigDecimal.class)).doubleValue()
      );
      companyOverview.setEPS(result.getValue(0, DSL.field(
          "eps", BigDecimal.class)).doubleValue()
      );
      companyOverview.setRevenuePerShareTTM(result.getValue(0, DSL.field(
          "revenue_per_share_ttm", BigDecimal.class)).doubleValue()
      );
      companyOverview.setProfitMargin(result.getValue(0, DSL.field(
          "profit_margin", BigDecimal.class)).doubleValue()
      );
      companyOverview.setOperatingMarginTTM(result.getValue(0, DSL.field(
          "operating_margin_ttm", BigDecimal.class)).doubleValue()
      );
      companyOverview.setReturnOnAssetsTTM(result.getValue(0, DSL.field(
          "return_on_assets_ttm", BigDecimal.class)).doubleValue()
      );
      companyOverview.setReturnOnEquityTTM(result.getValue(0, DSL.field(
          "return_on_equity_ttm", BigDecimal.class)).doubleValue()
      );
      companyOverview.setRevenueTTM(result.getValue(0, DSL.field(
          "revenue_ttm", Long.class))
      );
      companyOverview.setGrossProfitTTM(result.getValue(0, DSL.field(
          "gross_profit_ttm", Long.class))
      );
      companyOverview.setDilutedEPSTTM(result.getValue(0, DSL.field(
          "diluted_eps_ttm", BigDecimal.class)).doubleValue()
      );
      companyOverview.setQuarterlyEarningsGrowthYOY(result.getValue(0, DSL.field(
          "quarterly_earnings_growth_yoy", BigDecimal.class)).doubleValue()
      );
      companyOverview.setQuarterlyRevenueGrowthYOY(result.getValue(0, DSL.field(
          "quarterly_revenue_growth_yoy", BigDecimal.class)).doubleValue()
      );
      companyOverview.setAnalystTargetPrice(result.getValue(0, DSL.field(
          "analyst_target_price", BigDecimal.class)).doubleValue()
      );
      companyOverview.setAnalystRatingStrongBuy(result.getValue(0, DSL.field(
          "analyst_rating_strong_buy", Integer.class))
      );
      companyOverview.setAnalystRatingBuy(result.getValue(0, DSL.field(
          "analyst_rating_buy", Integer.class))
      );
      companyOverview.setAnalystRatingHold(result.getValue(0, DSL.field(
          "analyst_rating_hold", Integer.class))
      );
      companyOverview.setAnalystRatingSell(result.getValue(0, DSL.field(
          "analyst_rating_sell", Integer.class))
      );
      companyOverview.setAnalystRatingStrongSell(result.getValue(0, DSL.field(
          "analyst_rating_strong_sell", Integer.class))
      );
      companyOverview.setTrailingPE(result.getValue(0, DSL.field(
          "trailing_pe", BigDecimal.class)).doubleValue()
      );
      companyOverview.setForwardPE(result.getValue(0, DSL.field(
          "forward_pe", BigDecimal.class)).doubleValue()
      );
      companyOverview.setPriceToSalesRatioTTM(result.getValue(0, DSL.field(
          "price_to_sales_ratio_ttm", BigDecimal.class)).doubleValue()
      );
      companyOverview.setPriceToBookRatio(result.getValue(0, DSL.field(
          "price_to_book_ratio", BigDecimal.class)).doubleValue()
      );
      companyOverview.setEVToRevenue(result.getValue(0, DSL.field(
          "ev_to_revenue", BigDecimal.class)).doubleValue()
      );
      companyOverview.setEVToEBITDA(result.getValue(0, DSL.field(
          "ev_to_ebitda", BigDecimal.class)).doubleValue()
      );
      companyOverview.setBeta(result.getValue(0, DSL.field(
          "beta", BigDecimal.class)).doubleValue()
      );
      companyOverview.setWeek52High(result.getValue(0, DSL.field(
          "week_52_high", BigDecimal.class)).doubleValue()
      );
      companyOverview.setWeek52Low(result.getValue(0, DSL.field(
          "week_52_low", BigDecimal.class)).doubleValue()
      );
      companyOverview.setMovingAverage50(result.getValue(0, DSL.field(
          "moving_average_50", BigDecimal.class)).doubleValue()
      );
      companyOverview.setMovingAverage200(result.getValue(0, DSL.field(
          "moving_average_200", BigDecimal.class)).doubleValue()
      );
      companyOverview.setSharesOutstanding(result.getValue(0, DSL.field(
          "shares_outstanding", Long.class))
      );
      companyOverview.setDividendDate(result.getValue(0, DSL.field(
          "dividend_date", Date.class)).toString()
      );
      companyOverview.setExDividendDate(result.getValue(0, DSL.field(
          "ex_dividend_date", Date.class)).toString()
      );


      return companyOverview;
    } catch (Exception e) {
      System.out.println("Error when fetching company overview data");
      e.printStackTrace();
      return null;
    }
  }

  public String fetchLatestUpdate(String symbol) {
    try {
      Result<Record> result = connectionContext.fetch(
          "select dateretrieved "
              + "from fundamentaldata "
              + "where stockSymbol = ?",
          symbol
      );

      Date dateRetrieved = result.getValue(0, DSL.field("dateretrieved", Date.class));
      return dateRetrieved.toString();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }


}