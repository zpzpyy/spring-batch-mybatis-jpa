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

import com.batch.exam.batch.processor.JpaDbReadFileWriteProcessor;
import com.batch.exam.batch.reader.JpaDbReadFileWriteReader;
import com.batch.exam.batch.writer.JpaDbReadFileWriteWriter;
import com.batch.exam.info.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JpaDbReadFileWriteStep {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	JpaDbReadFileWriteReader reader;
	@Autowired
	JpaDbReadFileWriteProcessor processor;
	@Autowired
	JpaDbReadFileWriteWriter writer;
	
	@Value("${spring.scheduler.cron.jpa-db-read-file-write-batch.chunk-size:1000}")
	int chunkSize;
	
	public Step executeStep( String stepName, Map<String,Object> param ) {
		
		log.info("[JpaDbReadFileWriteStep] executeStep START...");
		
		return stepBuilderFactory.get( stepName )
				.<User, User>chunk( chunkSize )
				.reader( reader.reader( param ) )
				.processor( processor )
				.writer( writer.writer() )
				.build();
	}
	
	
}
