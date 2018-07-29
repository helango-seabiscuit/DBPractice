package com.hemalatha.db.performance.config;

import com.hemalatha.db.performance.dao.PostDao;
import com.hemalatha.db.performance.dao.PostDaoImpl;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class DataDomainConfig {

    @Bean
    public LocalEntityManagerFactoryBean entitymanagerbean(){
        LocalEntityManagerFactoryBean entityManagerFactoryBean = new LocalEntityManagerFactoryBean();

        //entityManagerFactoryBean.setPersistenceUnitName("MyPU"); if META-INF/persistence.xml is there
        entityManagerFactoryBean.setJpaProperties(jpaProperties());
        entityManagerFactoryBean.setPersistenceProvider(new HibernatePersistenceProvider());

        return entityManagerFactoryBean;
    }

    public Properties jpaProperties(){
        Properties properties = new Properties();

        properties.put("javax.persistence.jdbc.driver","org.h2.Driver");
        properties.put("javax.persistence.jdbc.url","jdbc:h2:~/test");
        properties.put("javax.persistence.jdbc.user","sa");
        properties.put("javax.persistence.jdbc.password","sa");
        properties.put("hibernate.show_sql","true");
        properties.put("hibernate.hbm2ddl.auto" ,"update");

        properties.put("hibernate.generate_statistics",Boolean.TRUE.toString());
        properties.put("hibernate.show_sql",Boolean.TRUE.toString());
        properties.put("hibernate.use_sql_comments",Boolean.TRUE.toString());

        return properties;
    }

    @Bean
    public JpaTransactionManager transactionManager(){
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        //transactionManager.setEntityManagerFactory(containerEntityManagerFactoryBean().getObject());
        transactionManager.setEntityManagerFactory(entitymanagerbean().getObject());
        return transactionManager;
    }

    @Bean
    public PostDao postDao(){
        return new PostDaoImpl();
    }



}
