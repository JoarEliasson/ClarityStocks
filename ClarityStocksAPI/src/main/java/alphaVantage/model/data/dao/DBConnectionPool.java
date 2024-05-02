package alphaVantage.model.data.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * The class provides a centralized connection pool management using HikariCP for efficient database
 * connections.
 * <p>
 * The class initializes a HikariDataSource with configurations loaded from a
 * properties file. The connection pool settings include prepared statement caching and limits on
 * statement cache size. It supports retrieval of a DSLContext configured for PostgreSQL to
 * interact with the database through jOOQ.
 * </p>
 * <p>
 * Usage of this class allows for resource-efficient database operations due to the pooling and
 * reusing of connections, reducing the overhead of creating connections frequently.
 * </p>
 * @author Kasper Schröder
 */
public class DBConnectionPool {
  private static final HikariDataSource dataSource;
  private static final String databasePropertiesPath = "src/main/resources/application.properties";

  static{
    Properties properties = new Properties();
    try (FileInputStream inputStream = new FileInputStream(databasePropertiesPath)) {
      properties.load(inputStream);
    } catch (IOException e) {
      System.out.println("Error reading database connection information from properties files");
      e.printStackTrace();
    }

    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(properties.getProperty("url"));
    config.setUsername(properties.getProperty("username"));
    config.setPassword(properties.getProperty("password"));
    config.addDataSourceProperty( "cachePrepStmts" , "true" );
    config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
    config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
    config.setMaximumPoolSize(10);
    dataSource = new HikariDataSource(config);
  }

  public static DSLContext getConnection(){
    return DSL.using(dataSource, SQLDialect.POSTGRES);

  }

}
