package ru.clevertec.knyazev.config;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataSourceProperties {

    private String driverClassName;

    private String jdbcUrl;

    private String username;

    private String password;

    private Integer maxPoolSize;

    private Long connectionTimeout;
}
