package com.learn.SpringHibernate.Configuration.Private;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;


@Configuration
@EnableJpaRepositories(basePackages = "com.learn.SpringHibernate.Model")
@EnableTransactionManagement
public class SpringDataHibernateConfiguration {
	
	@Autowired
	Environment properties;
	
	@Bean(name="entityManagerFactory")
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setHibernateProperties(hibernateProperties());
        sessionFactory.setPackagesToScan("com.learn.SpringHibernate");
        return sessionFactory;
        

        
    }
	
    @Bean
    public DataSource dataSource() {
//    	JndiObjectFactoryBean a = new JndiObjectFactoryBean();
//    	a.setP
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(properties.getProperty("datasource.driverClassName"));
        dataSource.setUrl(properties.getProperty("datasource.url"));
        dataSource.setUsername(properties.getProperty("datasource.username"));
        dataSource.setPassword(properties.getProperty("datasource.password"));

        return dataSource;
    }
    
    private final Properties hibernateProperties() {
        Properties hibernateProperties = new Properties();
        hibernateProperties.setProperty(
          "hibernate.hbm2ddl.auto", "update");
        hibernateProperties.setProperty(
          "hibernate.dialect", "org.hibernate.dialect.Oracle12cDialect");
        hibernateProperties.setProperty("hibernate.show_sql", "true");
//        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", "true");
//        hibernateProperties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
//        hibernateProperties.setProperty("hibernate.cache.use_query_cache", "true");
        
        return hibernateProperties;
    }
	
//    @Bean
//    public DataSource dataSource() {
//        BasicDataSource dataSource = new BasicDataSource();
//        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        dataSource.setUrl("jdbc:mysql://localhost:3306/app_db");
//        dataSource.setUsername("db_user");
//        dataSource.setPassword("db_user_pass");
//
//        return dataSource;
//    }
//    
//    private final Properties hibernateProperties() {
//        Properties hibernateProperties = new Properties();
//        hibernateProperties.setProperty(
//          "hibernate.hbm2ddl.auto", "update");
//        hibernateProperties.setProperty(
//          "hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
//        hibernateProperties.setProperty("hibernate.show_sql", "true");
//
//        return hibernateProperties;
//    }

    @Bean(name = "transactionManager")
    public PlatformTransactionManager hibernateTransactionManager() {
        HibernateTransactionManager transactionManager
          = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        transactionManager.setNestedTransactionAllowed(true);
        return transactionManager;
        
//    	HibernateJpaVendorAdapter vendore = new HibernateJpaVendorAdapter();
//    	LocalContainerEntityManagerFactoryBean obj = new LocalContainerEntityManagerFactoryBean();
//    	obj.setD
//    	JtaTransactionManager manager = new JtaTransactionManager();
    	
    }
	
}
