package org.example.repositories.impl;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DBConfig {
    private String fileName = "src/main/resources/application.properties";

    public DBConfig(String fileName) {
        this.fileName = fileName;
    }

    public DBConfig() {
    }

    public DataSource getDataSource() {
        Properties properties = getProperties();

        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl(properties.getProperty("url"));
        hikariConfig.setUsername(properties.getProperty("username"));
        hikariConfig.setPassword(properties.getProperty("password"));
        hikariConfig.setDriverClassName("org.postgresql.Driver");

        return new HikariDataSource(hikariConfig);
    }

    private Properties getProperties() {
        Properties properties = new Properties();

        try(FileInputStream fis = new FileInputStream(this.fileName)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return properties;
    }
}
