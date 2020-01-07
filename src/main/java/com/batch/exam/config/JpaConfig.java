package com.batch.exam.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = "com.batch.exam",
		transactionManagerRef = "transactionManager2",
		entityManagerFactoryRef = "entityManagerFactory2"
)
public class JpaConfig {

	@Primary
	@Bean( name = "dataSource2" )
	@ConfigurationProperties("spring.datasource2")
	public DataSource mariaDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}
	
	@Primary
	@Bean( name = "entityManagerFactory2" )
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
					EntityManagerFactoryBuilder builder,
					@Qualifier("dataSource2") DataSource dataSource ) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hibernate.ejb.naming_strategy", "org.hibernate.cfg.ImprovedNamingStrategy");
		map.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
		
		return builder.dataSource( dataSource )
			   .packages( "com.batch.exam.info" )
			   .properties( map )
			   .build();
	}
	
	@Primary
	@Bean( name="transactionManager2")
	public PlatformTransactionManager transactionManager(
            @Qualifier("entityManagerFactory2") EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);
        return transactionManager;
    }

}
