package com.smoothstack.december.librarianService;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class LMSConfig {

    @Value("${db.driver}")
    private String DRIVER;

    @Value("${db.url}")
    private String URL;

    @Value("${db.username}")
    private String USERNAME;

    @Value("${db.password}")
    private String PASSWORD;

    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(this.DRIVER);
        ds.setUrl(this.URL);
        ds.setUsername(this.USERNAME);
        ds.setPassword(this.PASSWORD);
        return ds;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(this.dataSource());
    }

}
