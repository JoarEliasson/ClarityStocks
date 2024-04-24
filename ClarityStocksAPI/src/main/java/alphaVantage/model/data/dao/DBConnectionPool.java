package alphaVantage.model.data.dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

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
        config.setMaximumPoolSize(10);
        dataSource = new HikariDataSource(config);
    }

    public static DSLContext getConnection(){
        return DSL.using(dataSource, SQLDialect.POSTGRES);

    }

}
