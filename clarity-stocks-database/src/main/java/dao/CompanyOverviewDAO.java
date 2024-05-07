package dao;

import common.data.fundamental.CompanyOverview;
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

  public void insertFundamentalData(CompanyOverview companyOverview) {
    try {
      connectionContext.insertInto(DSL.table("fundamentaldata"),
              DSL.field("stockSymbol"),
              DSL.field("assetType"),
              DSL.field("name"),
              DSL.field("description"),
              DSL.field("cik"),
              DSL.field("marketSymbol"),
              DSL.field("currency"),
              DSL.field("country"),
              DSL.field("sector"),
              DSL.field("industry"),
              DSL.field("address"),
              DSL.field("fiscalYearEnd"),
              DSL.field("latestQuarter"),
              DSL.field("marketCapitalization"),
              DSL.field("ebitda"),
              DSL.field("peRatio"),
              DSL.field("pegRatio"),
              DSL.field("bookValue"),
              DSL.field("dividendPerShare"),
              DSL.field("dividendYield"),
              DSL.field("eps"),
              DSL.field("revenuePerShareTTM"),
              DSL.field("profitMargin"),
              DSL.field("operationMarginTTM"),
              DSL.field("returnOnAssetsTTM"),
              DSL.field("returnOnEquityTTM"),
              DSL.field("revenueTTM"),
              DSL.field("grossProfitTTM"),
              DSL.field("dilutedEPSTTM"),
              DSL.field("quarterlyEarningsGrowthYOY"),
              DSL.field("quarterlyRevenueGrowthYOY"),
              DSL.field("analystTargetPrice"),
              DSL.field("trailingPE"),
              DSL.field("forwardPE"),
              DSL.field("priceToSalesRatioTTM"),
              DSL.field("priceToBookRatio"),
              DSL.field("evToRevenue"),
              DSL.field("evToEBITDA"),
              DSL.field("beta"),
              DSL.field("week52High"),
              DSL.field("week52Low"),
              DSL.field("day50movingaverage"),
              DSL.field("day200movingaverage"),
              DSL.field("sharesOutstanding"),
              DSL.field("dividendDate"),
              DSL.field("exDividendDate"),
              DSL.field("dateRetrieved"))
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
              Date.valueOf(companyOverview.getExDividendDate()),
              DSL.currentDate()
          )
          .onConflict(DSL.field("stockSymbol"))
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

  public CompanyOverview getCompanyOverviewQuery(String symbol) {
    try {
      CompanyOverview companyOverview = new CompanyOverview();

      Result<Record> result = connectionContext.fetch(
          "SELECT * " +
              "FROM fundamentaldata where stockSymbol = ?",
          symbol
      );

      companyOverview.setSymbol(result.getValue(0, DSL.field(
          "stocksymbol", String.class))
      );
      companyOverview.setAssetType(result.getValue(0, DSL.field(
          "assettype", String.class))
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
          "marketsymbol", String.class))
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
          "fiscalyearend", String.class))
      );
      companyOverview.setLatestQuarter(result.getValue(0, DSL.field(
          "latestquarter", Date.class)).toString()
      );
      companyOverview.setMarketCapitalization(result.getValue(0, DSL.field(
          "marketcapitalization", Long.class))
      );
      companyOverview.setEBITDA(result.getValue(0, DSL.field(
          "ebitda", Double.class))
      );
      companyOverview.setPERatio(result.getValue(0, DSL.field(
          "peratio", Double.class))
      );
      companyOverview.setPEGRatio(result.getValue(0, DSL.field(
          "pegratio", Double.class))
      );
      companyOverview.setBookValue(result.getValue(0, DSL.field(
          "bookvalue", Double.class))
      );
      companyOverview.setDividendPerShare(result.getValue(0, DSL.field(
          "dividendpershare", Double.class))
      );
      companyOverview.setDividendYield(result.getValue(0, DSL.field(
          "dividendyield", Double.class))
      );
      companyOverview.setEPS(result.getValue(0, DSL.field(
          "eps", Double.class))
      );
      companyOverview.setRevenuePerShareTTM(result.getValue(0, DSL.field(
          "revenuepersharettm", Double.class))
      );
      companyOverview.setProfitMargin(result.getValue(0, DSL.field(
          "profitmargin", Double.class))
      );
      companyOverview.setOperatingMarginTTM(result.getValue(0, DSL.field(
          "opera  tingmarginttm", Double.class))
      );
      companyOverview.setReturnOnAssetsTTM(result.getValue(0, DSL.field(
          "returnonassetsttm", Double.class))
      );
      companyOverview.setReturnOnEquityTTM(result.getValue(0, DSL.field(
          "returnonequityttm", Double.class))
      );
      companyOverview.setRevenueTTM(result.getValue(0, DSL.field(
          "revenuettm", Long.class))
      );
      companyOverview.setGrossProfitTTM(result.getValue(0, DSL.field(
          "grossprofitttm", Long.class))
      );
      companyOverview.setDilutedEPSTTM(result.getValue(0, DSL.field(
          "dilutedepsttm", Double.class))
      );
      companyOverview.setQuarterlyEarningsGrowthYOY(result.getValue(0, DSL.field(
          "quarterlyearningsgrowthyoy", Double.class))
      );
      companyOverview.setQuarterlyRevenueGrowthYOY(result.getValue(0, DSL.field(
          "quarterlyrevenuegrowthyoy", Double.class))
      );
      companyOverview.setAnalystTargetPrice(result.getValue(0, DSL.field(
          "analysttargetprice", Double.class))
      );
      companyOverview.setTrailingPE(result.getValue(0, DSL.field(
          "trailingpe", Double.class))
      );
      companyOverview.setForwardPE(result.getValue(0, DSL.field(
          "forwardpe", Double.class))
      );
      companyOverview.setPriceToSalesRatioTTM(result.getValue(0, DSL.field(
          "pricetosalesratiottm", Double.class))
      );
      companyOverview.setPriceToBookRatio(result.getValue(0, DSL.field(
          "pricetobookratio", Double.class))
      );
      companyOverview.setEVToRevenue(result.getValue(0, DSL.field(
          "evtorevenue", Double.class))
      );
      companyOverview.setEVToEBITDA(result.getValue(0, DSL.field(
          "evtoebitda", Double.class))
      );
      companyOverview.setBeta(result.getValue(0, DSL.field(
          "beta", Double.class))
      );
      companyOverview.setWeek52High(result.getValue(0, DSL.field(
          "week52high", Double.class))
      );
      companyOverview.setWeek52Low(result.getValue(0, DSL.field(
          "week52low", Double.class))
      );
      companyOverview.setMovingAverage50(result.getValue(0, DSL.field(
          "movingaverage50", Double.class))
      );
      companyOverview.setMovingAverage200(result.getValue(0, DSL.field(
          "movingaverage200", Double.class))
      );
      companyOverview.setSharesOutstanding(result.getValue(0, DSL.field(
          "sharesoutstanding", Long.class))
      );
      companyOverview.setDividendDate(result.getValue(0, DSL.field(
          "dividenddate", Date.class)).toString()
      );
      companyOverview.setExDividendDate(result.getValue(0, DSL.field(
          "exdividenddate", Date.class)).toString()
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