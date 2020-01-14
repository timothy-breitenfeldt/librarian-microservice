package com.smoothstack.december.librarianService;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.smoothstack.december.librarianService.dao.*;

@Configuration
public class LMSConfig {
    
    private static final String DRIVER = "org.sqlite.JDBC";
    private static final String URL = "jdbc:mysql://localhost:3306/library?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    
    @Bean
    public DataSource dataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(DRIVER);
        ds.setUrl(URL);
        ds.setUsername(USERNAME);
        ds.setPassword(PASSWORD);
        return ds;
    }
    
    @Bean
    @Qualifier(value="mySQLTemplate")
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(this.dataSource());
    }
    
    @Bean
    public DataSourceTransactionManager dataSourceTransactionManager() {
        return new DataSourceTransactionManager(this.dataSource());
    }
    
    @Bean
    public AuthorDAO authorDAO() {
        return new AuthorDAO();
    }
    
    @Bean
    public BookDAO bookDAO() {
        return new BookDAO();
    }
    
    @Bean
    public GenreDAO genreDAO() {
        return new GenreDAO();
    }
    
    @Bean
    public PublisherDAO publisherDAO() {
        return new PublisherDAO();
    }
    
    @Bean
    public LibraryBranchDAO libraryBranchDAO() {
        return new LibraryBranchDAO();
    }
    
    @Bean
    public BorrowerDAO borrowerDAO() {
        return new BorrowerDAO();
    }
    
    @Bean
    public BookCopyDAO bookCopyDAO() {
        return new BookCopyDAO();
    }
    
    @Bean
    public BookLoanDAO bookLoanDAO() {
        return new BookLoanDAO();
    }

}
