module clarity.stocks.database {
  exports dao;
  requires java.sql;
  requires org.jooq;
  requires clarity.stocks.common;
  requires org.postgresql.jdbc;
  requires com.zaxxer.hikari;
  requires clarity.stocks.api.integration;
}