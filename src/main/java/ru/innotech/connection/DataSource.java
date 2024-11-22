
package ru.innotech.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;

@Component
public class DataSource {
    private  static HikariConfig config = new HikariConfig();
    private static HikariDataSource ds;
    static {
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/javapro04");
        config.setUsername("postgres");
        config.setPassword("1234");
        ds = new HikariDataSource(config);
    }

    @SneakyThrows
    @Bean
    public static Connection getConnection() {
        return ds.getConnection();
    }
}
