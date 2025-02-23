/**
 * 
 */
package com.tel.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.tel.common.Env;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Clase para registrar objetos en el contexto de Spring.
 */
@Configuration
public class AppContext {

    /**
     * Crea un objeto DataSource.
     * @return DataSource
     */
    @Bean
    DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(Env.getPropertyByPrefix("ds", "jdbc.url"));
        ds.setUsername(Env.getPropertyByPrefix("ds", "username"));
        //ds.setPassword(Env.getPropertyByPrefix("ds", "password"));
        ds.setDriverClassName(Env.getPropertyByPrefix("ds", "driver.class.name"));
        ds.setMaximumPoolSize(Integer.parseInt(Env.getPropertyByPrefix("ds", "maximum.pool.size")));
//        System.out.println(Env.getPropertyByPrefix("ds", "jdbc.url"));
//        ds.setJdbcUrl("jdbc:mysql://localhost:3306/products?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=US/Central");
//        ds.setUsername("root");
//        ds.setPassword("password");
//        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        ds.setMaximumPoolSize(10);
        return ds;
    }

    /**
     * Crea un objeto LocalContainerEntityManagerFactoryBean.
     * 
     * @return LocalContainerEntityManagerFactoryBean
     */
    @Bean
    LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource()); // inyecta el datasource.
        emf.setPackagesToScan("com.tel.model");
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", //"org.hibernate.dialect.MySQL8Dialect");
                Env.getPropertyByPrefix("jpa", "dialect"));
        jpaProperties.setProperty("hibernate.hbm2ddl.auto", //"update");
                Env.getPropertyByPrefix("jpa", "hbm2ddl.auto"));
        jpaProperties.setProperty("hibernate.show_sql", //"true");
                Env.getPropertyByPrefix("jpa", "show.sql"));
        emf.setJpaProperties(jpaProperties);
        
        return emf;
    }

    /**
     * Crea un objeto PlatformTransactionManager.
     * 
     * @return PlatformTransactionManager
     */
    @Bean
    PlatformTransactionManager transactionManager() {
        JpaTransactionManager tm = new JpaTransactionManager();
        tm.setEntityManagerFactory(entityManagerFactory().getObject());
        return tm;
    }
}
