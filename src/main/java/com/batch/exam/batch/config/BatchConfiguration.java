package com.batch.exam.batch.config;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * batch config
 * 
 * @author ljs
 * @since 0.1
 */
@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	/** Batch MetaData Save  => Remark
	 *  Batch MetaData Non't Save  => Remark Delete
	*   transactionManager()/mapjobRepositoryFactory()/jobRepository()
	*/
//	@Bean
//	public ResourcelessTransactionManager transactionManager() {
//		return new ResourcelessTransactionManager();
//	}
//	
//	@Bean
//	public MapJobRepositoryFactoryBean mapjobRepositoryFactory( ResourcelessTransactionManager txManager) throws Exception {
//		MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean(txManager);
//		factory.afterPropertiesSet();
//		return factory;
//	}
//	
//	@Bean
//	public JobRepository jobRepository( MapJobRepositoryFactoryBean factory) throws Exception {
//		return factory.getObject();
//	}
	
	@Bean(name = "simpleJobLauncher")
	public SimpleJobLauncher jobLauncher( JobRepository jobRepository) {
		SimpleJobLauncher launcher = new SimpleJobLauncher();
		launcher.setJobRepository(jobRepository);
		return launcher;
	}
}
