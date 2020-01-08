package com.batch.exam.batch.step;

import java.util.Map;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

import com.batch.exam.batch.processor.JpaFileReadDbWriteProcessor;
import com.batch.exam.batch.reader.JpaFileReadDbWriteReader;
import com.batch.exam.batch.writer.JpaFileReadDbWriteWriter;
import com.batch.exam.info.User;

import lombok.extern.slf4j.Slf4j;

/**
 * File read DB write batch step(JPA)
 * 
 * @author ljs
 * @since 0.1
 */
@Slf4j
@Component
public class JpaFileReadDbWriteStep {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	JpaFileReadDbWriteReader reader;
	@Autowired
	JpaFileReadDbWriteProcessor processor;
	@Autowired
	JpaFileReadDbWriteWriter writer;
	@Autowired
	@Qualifier("transactionManager2")
	PlatformTransactionManager transactionManager;
	
	@Value("${spring.scheduler.cron.jpa-file-read-db-write-batch.chunk-size:1000}")
	int chunkSize;
	
	public Step executeStep( String stepName, Map<String,Object> param ) {
		
		log.info("[JpaFileReadDbWriteStep] executeStep START...");
		
		return stepBuilderFactory.get( stepName )
				.<User, User>chunk( chunkSize )
				.reader( reader.reader() )
				.processor( processor )
				.writer( writer.writer() )
				.transactionManager( transactionManager )
				.build();
	}
	
	
}
