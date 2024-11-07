package org.comparus.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

@Configuration
@ConfigurationProperties(prefix = "data-sources")
public class DataSourceConfig {
    private List<DataSourceProperties> sources;

    public List<DataSourceProperties> getSources() {
        return sources;
    }

    public void setSources(List<DataSourceProperties> sources) {
        this.sources = sources;
    }

    public static class DataSourceProperties {
        private String name;
        private String strategy;
        private String url;
        private String table;
        private String user;
        private String password;
        private Map<String, String> mapping;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getStrategy() {
            return strategy;
        }

        public void setStrategy(String strategy) {
            this.strategy = strategy;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Map<String, String> getMapping() {
            return mapping;
        }

        public void setMapping(Map<String, String> mapping) {
            this.mapping = mapping;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", DataSourceProperties.class.getSimpleName() + "[", "]")
                    .add("name='" + name + "'")
                    .add("strategy='" + strategy + "'")
                    .add("url='" + url + "'")
                    .add("table='" + table + "'")
                    .add("user='" + user + "'")
                    .add("password='" + password + "'")
                    .add("mapping=" + mapping)
                    .toString();
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", DataSourceConfig.class.getSimpleName() + "[", "]")
                .add("sources=" + sources)
                .toString();
    }
}
