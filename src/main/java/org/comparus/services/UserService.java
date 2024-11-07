package org.comparus.services;

import org.comparus.configs.DataSourceConfig;
import org.comparus.configs.DataSourceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final DataSourceConfig dataSourceConfig;
    private final DataSourceFactory dataSourceFactory;

    @Autowired
    public UserService(DataSourceConfig dataSourceConfig, DataSourceFactory dataSourceFactory) {
        this.dataSourceConfig = dataSourceConfig;
        this.dataSourceFactory = dataSourceFactory;
    }

    public List<Map<String, Object>> getAllUsers() {
        List<Map<String, Object>> results = new ArrayList<>();

        for (DataSourceConfig.DataSourceProperties source : dataSourceConfig.getSources()) {
            ResultSet resultSet = null;
            try (Connection connection = dataSourceFactory.createDataSource(source).getConnection()) {
                resultSet = executeQuery(connection, createQuery(source.getMapping(), source.getTable()));
                while (resultSet.next()) {
                    Map<String, Object> row = new HashMap<>();
                    for (Map.Entry<String,String> s : source.getMapping().entrySet()) {
                        row.put(s.getKey(), resultSet.getString(s.getKey()));
                    }
                    results.add(row);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return results;

    }

    private ResultSet executeQuery(Connection connection, String query) throws SQLException {
        return connection.createStatement().executeQuery(query);
    }

    private String createQuery(Map<String, String> mapping, String table) {


        String fields = mapping.keySet()
                .stream().map(s -> mapping.get(s) + " as " + s)
                .collect(Collectors.joining(","));

        String queryTemplate = "SELECT %s FROM %s";

        return String.format(queryTemplate, fields, table);
    }
}
