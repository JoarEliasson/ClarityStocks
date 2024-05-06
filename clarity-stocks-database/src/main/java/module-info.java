module clarity.stocks.database {
  requires java.sql;
  requires org.jooq;
  requires clarity.stocks.common;
  requires org.postgresql.jdbc;
  requires com.zaxxer.hikari;
  requires clarity.stocks.data;
  requires clarity.stocks.api.integration;
}