module clarity.stocks.data {
  exports data.stock;
  exports data.search;
  exports data.database.dao;
  exports data.database;
  requires clarity.stocks.analysis;
  requires clarity.stocks.common;
  requires clarity.stocks.api.integration;
  requires java.sql;
  requires org.jooq;
  requires org.postgresql.jdbc;
  requires com.zaxxer.hikari;
}