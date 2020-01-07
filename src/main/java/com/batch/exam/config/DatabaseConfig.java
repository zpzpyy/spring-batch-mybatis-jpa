package com.batch.exam.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
/**
 * Datasource 설정
 * 
 * @author ljs
 * @since 0.1
 */
@Configuration
@MapperScan(value="com.batch.exam.mapper", sqlSessionFactoryRef="sqlSessionFactory")
@EnableTransactionManagement
public class DatabaseConfig {

	@Bean("dataSource")
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean("sqlSessionFactory")
	public SqlSessionFactory sqlSessionFactory ( @Qualifier("dataSource") DataSource dataSource, ApplicationContext  applicationContent) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setMapperLocations(applicationContent.getResources("classpath:conf/db/maria/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	@Bean(name = "sqlSessionTemplate")
	public SqlSessionTemplate sqlSessionTemplate( @Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	@Bean(name = "jdbcTemplate")
	public JdbcTemplate jdbcTemplate( @Qualifier("dataSource") DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
}
