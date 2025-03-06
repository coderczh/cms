package com.coderczh.cms.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@Slf4j
@SpringBootConfiguration
public class DataSourceConfig {
    @Bean
    public ApplicationRunner runner(DataSource dataSource) {
        return args -> {
            dataSource.getConnection();
            log.info("=== HikariCP初始化完成 ===");
        };
    }
}
