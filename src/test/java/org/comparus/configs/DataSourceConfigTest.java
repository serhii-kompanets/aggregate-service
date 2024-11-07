package org.comparus.configs;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
//@ActiveProfiles("test")
public class DataSourceConfigTest {

    @Autowired
    private DataSourceConfig dataSourceConfig;

    @Test
    void testDataSourceConfigLoadsProperties() {
        assertThat(dataSourceConfig.getSources()).isNotNull();
        assertThat(dataSourceConfig.getSources()).hasSize(2);

        DataSourceConfig.DataSourceProperties mysqlProps1 = dataSourceConfig.getSources().get(0);
        assertThat(mysqlProps1.getName()).isEqualTo("data-base-1");
        assertThat(mysqlProps1.getUrl()).contains("jdbc:mysql://");
        assertThat(mysqlProps1.getUser()).isEqualTo("testuser");
        assertThat(mysqlProps1.getPassword()).isEqualTo("testpass");

        DataSourceConfig.DataSourceProperties postgresProps = dataSourceConfig.getSources().get(1);
        assertThat(postgresProps.getName()).isEqualTo("data-base-2");
        assertThat(postgresProps.getUrl()).contains("jdbc:postgresql://");
        assertThat(postgresProps.getUser()).isEqualTo("testuser");
        assertThat(postgresProps.getPassword()).isEqualTo("testpass");
    }
}
