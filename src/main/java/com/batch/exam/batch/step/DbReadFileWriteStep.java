package com.batch.exam.batch.step;

import java.util.Map;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.batch.exam.batch.processor.DbReadFileWriteProcessor;
import com.batch.exam.batch.reader.DbReadFileWriteReader;
import com.batch.exam.batch.writer.DbReadFileWriteWriter;
import com.batch.exam.info.User;

import lombok.extern.slf4j.Slf4j;

/**
 * DB read File write batch Step(mybatis)
 * 
 * @author ljs
 * @since 0.1
 */
@Slf4j
@Component
public class DbReadFileWriteStep {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	DbReadFileWriteReader reader;
	@Autowired
	DbReadFileWriteProcessor processor;
	@Autowired
	DbReadFileWriteWriter writer;

	@Value("${spring.scheduler.cron.db-read-file-write-batch.chunk-size:1000}")
	int chunkSize;
	
	public Step executeStep( String stepName, Map<String,Object> param ) {
		
		log.info("[DbReadFileWriteStep] executeStep START...");
		
		return stepBuilderFactory.get( stepName )
				.<Map<String,Object>, User>chunk( chunkSize )
				.reader( reader.reader( param ) )
				.processor( processor )
				.writer( writer.writer() )
				.build();
	}
	
	
}
