package main;

import org.h2.jdbcx.JdbcDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {

    private static ConnectionFactory connFactory;
    private static JdbcDataSource dataSource;
    private static Properties dbProperties;

    private ConnectionFactory() throws IOException {
        dbProperties = new Properties();
        dbProperties.load(ClassLoader.getSystemResourceAsStream("resources/config.properties"));
    }

    public static ConnectionFactory getInstance() throws IOException {
        synchronized (ConnectionFactory.class) {
            if (connFactory == null) {
                connFactory = new ConnectionFactory();
                dataSource = new JdbcDataSource();
                dataSource.setURL(dbProperties.getProperty("jdbc.url"));
                dataSource.setUser(dbProperties.getProperty("jdbc.user"));
                dataSource.setPassword(dbProperties.getProperty("jdbc.pass"));
            }
        }
        return connFactory;
    }

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


}
