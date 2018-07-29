package com.hemalatha.db.performance.config;

import com.mchange.v2.c3p0.DriverManagerDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.hemalatha.db.performance.Jpadatarepositories"})
public class SpringDataJPAConfig {

    @Bean
    public LocalEntityManagerFactoryBean entityManagerFactory() {
        LocalEntityManagerFactoryBean entityManagerFactoryBean = new LocalEntityManagerFactoryBean();

        //entityManagerFactoryBean.setPersistenceUnitName("MyPU"); if META-INF/persistence.xml is there
        entityManagerFactoryBean.setPersistenceProvider(new HibernatePersistenceProvider()); //optional
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        entityManagerFactoryBean.setJpaProperties(jpaProperties());

        return entityManagerFactoryBean;
    }



    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/high_performance_java_persistence");
        dataSource.setUser("ns");
        dataSource.setPassword("ns");
        return dataSource;

//    properties.put("javax.persistence.jdbc.driver","com.mysql.cj.jdbc.Driver");
//    properties.put("javax.persistence.jdbc.url","jdbc:mysql://localhost:3306/high_performance_java_persistence");
//    properties.put("javax.persistence.jdbc.user","ns");
//    properties.put("javax.persistence.jdbc.password","ns");
    }


    public Properties jpaProperties() {
        Properties properties = new Properties();
        properties.put("javax.persistence.jdbc.driver","com.mysql.cj.jdbc.Driver");
        properties.put("javax.persistence.jdbc.url","jdbc:mysql://localhost:3306/db_schema_gen_test");
        properties.put("javax.persistence.jdbc.password","ns");
        properties.put("javax.persistence.jdbc.user","ns");
 //       properties.put("javax.persistence.jdbc.driver","org.h2.Driver");
//        properties.put("javax.persistence.jdbc.url","jdbc:h2:~/test");
//        properties.put("javax.persistence.jdbc.user","sa");
//        properties.put("javax.persistence.jdbc.password","sa");
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.hbm2ddl.auto", "update");
        properties.put("javax.persistence.schema-generation.database.action", "drop-and-create");

        properties.put("hibernate.generate_statistics", Boolean.TRUE.toString());
        properties.put("hibernate.show_sql", Boolean.TRUE.toString());
        properties.put("hibernate.use_sql_comments", Boolean.TRUE.toString());

        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        //transactionManager.setEntityManagerFactory(containerEntityManagerFactoryBean().getObject());
        transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
        return transactionManager;
    }


}
