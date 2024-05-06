package dao;

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
  /*
  private static HikariDataSource dataSource;
  private static final String databasePropertiesPath = "ClarityStocksAPI/src/main/resources/database.properties";

  static{
    Properties properties = new Properties();
    try (FileInputStream inputStream = new FileInputStream(databasePropertiesPath)) {
      properties.load(inputStream);
    } catch (IOException e) {
      System.out.println("Error reading database connection information from properties files");
      e.printStackTrace();
    }

    HikariConfig config = new HikariConfig();
    config.setJdbcUrl("jdbc:postgresql://claritystocksdatabase.postgres.database.azure.com:5432/postgres?sslmode=require");
    config.setUsername("ClarityStocks");
    config.setPassword("ProjectGroup25");
    config.addDataSourceProperty( "cachePrepStmts" , "true" );
    config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
    config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
    config.setMaximumPoolSize(10);
    dataSource = new HikariDataSource(config);
  }

  public static DSLContext getConnection(){
    return DSL.using(dataSource, SQLDialect.POSTGRES);

  }

 */

}
