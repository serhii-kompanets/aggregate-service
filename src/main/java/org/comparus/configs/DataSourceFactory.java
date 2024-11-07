package org.comparus.configs;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DataSourceFactory {
    private final DataSourceConfig config;

    @Autowired
    public DataSourceFactory(DataSourceConfig config) {
        this.config = config;
    }

    public DataSource createDataSource(DataSourceConfig.DataSourceProperties properties) {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl(properties.getUrl());
        dataSource.setUsername(properties.getUser());
        dataSource.setPassword(properties.getPassword());
        dataSource.setDriverClassName(getDriverClassName(properties.getStrategy()));

        return dataSource;
    }

    private String getDriverClassName(String providerStrategy) {
        return switch (providerStrategy) {
            case "postgres" -> "org.postgresql.Driver";
            case "mysql" -> "com.mysql.cj.jdbc.Driver";
            case "oracle" -> "oracle.jdbc.OracleDriver";
            default -> throw new IllegalArgumentException("Unsupported database strategy: " + providerStrategy);
        };
    }
}
