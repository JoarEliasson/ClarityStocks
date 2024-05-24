module clarity.stocks.data {
  exports model.stock;
  exports model.search;
  requires clarity.stocks.analysis;
  requires clarity.stocks.common;
  requires clarity.stocks.api.integration;
  requires java.sql;
  requires org.jooq;
  requires org.postgresql.jdbc;
  requires com.zaxxer.hikari;
}