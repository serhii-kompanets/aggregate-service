package org.comparus.services;

import org.comparus.configs.DataSourceConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest
@Testcontainers
class UserServiceTest {

    @Container
    private static final MySQLContainer<?> mysqlContainer1 = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("data_base_2")
            .withUsername("testuser")
            .withPassword("testpass")
            .withInitScript("sql/postgres/user_table.sql");

    @Container
    private static final MySQLContainer<?> mysqlContainer2 = new MySQLContainer<>("mysql:8.0")
            .withDatabaseName("testdb")
            .withUsername("testuser")
            .withPassword("testpass")
            .withInitScript("sql/mysql/users.sql");

    @Autowired
    private UserService userService;

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @BeforeEach
    void config() {
        DataSourceConfig.DataSourceProperties dataSourceProperties1 = dataSourceConfig.getSources().get(0);
        dataSourceProperties1.setUrl(mysqlContainer2.getJdbcUrl());
        dataSourceProperties1.setUser(mysqlContainer2.getUsername());
        dataSourceProperties1.setPassword(mysqlContainer2.getPassword());

        DataSourceConfig.DataSourceProperties dataSourceProperties2 = dataSourceConfig.getSources().get(1);
        dataSourceProperties2.setUrl(mysqlContainer1.getJdbcUrl());
        dataSourceProperties2.setUser(mysqlContainer1.getUsername());
        dataSourceProperties2.setPassword(mysqlContainer1.getPassword());
    }

    @BeforeAll
    static void setUp() {
        mysqlContainer1.start();
        mysqlContainer2.start();
    }

    @AfterAll
    static void tearDown() {
        mysqlContainer1.stop();
        mysqlContainer2.stop();
    }

    @Test
    void test() {
        List<Map<String, Object>> allUsers = userService.getAllUsers();

        assertThat(allUsers.size()).isEqualTo(11);
    }
}
