package com.smartchoice.crawler.repository.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.smartchoice.crawler.repository")
@PropertySource("classpath:repository-test.properties")
public class PersistenceContextTest {

  @Value("${db.driver}")
  private String dbDriver;

  @Value("${db.password}")
  private String dbPassword;

  @Value("${db.url}")
  private String dbUrl;

  @Value("${db.username}")
  private String dbUsername;

  @Value("${hibernate.dialect}")
  private String hibernateDialect;

  @Value("${hibernate.format_sql}")
  private String hibernateFormatSql;

  @Value("${hibernate.hbm2ddl.auto}")
  private String hibernateDllAuto;

  @Value("${hibernate.ejb.naming_strategy}")
  private String hibernateNamingStrategy;

  /**
   * configure data source for testing.
   *
   * @return DataSource
   */
  @Bean
  public DataSource dataSource() {
    HikariDataSource dataSource = new HikariDataSource();
    dataSource.setDriverClassName(dbDriver);
    dataSource.setJdbcUrl(dbUrl);
    dataSource.setUsername(dbUsername);
    dataSource.setPassword(dbPassword);
    return dataSource;
  }

  /**
   * create Jpa Transaction Manager for testing.
   *
   * @return JpaTransactionManager
   */
  @Bean
  public JpaTransactionManager transactionManager() {
    JpaTransactionManager transactionManager = new JpaTransactionManager();
    transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
    return transactionManager;
  }

  /**
   * create ContainerEntityManager for testing.
   *
   * @return LocalContainerEntityManagerFactoryBean
   */
  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
    LocalContainerEntityManagerFactoryBean entityManagerFactoryBean =
        new LocalContainerEntityManagerFactoryBean();

    entityManagerFactoryBean.setDataSource(dataSource());
    entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
    entityManagerFactoryBean.setPackagesToScan("com.smartchoice.crawler.repository");

    Properties jpaProperties = new Properties();
    jpaProperties.put("hibernate.dialect", hibernateDialect);
    jpaProperties.put("hibernate.format_sql", hibernateFormatSql);
    jpaProperties.put("hibernate.hbm2ddl.auto", hibernateDllAuto);
    jpaProperties.put("hibernate.ejb.naming_strategy", hibernateNamingStrategy);
    entityManagerFactoryBean.setJpaProperties(jpaProperties);
    return entityManagerFactoryBean;
  }
}
