package com.batch.exam.batch.step;

import java.util.Map;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.batch.exam.batch.processor.FileReadDbWriteProcessor;
import com.batch.exam.batch.reader.FileReadDbWriteReader;
import com.batch.exam.batch.writer.FileReadDbWriteWriter;
import com.batch.exam.info.User;

import lombok.extern.slf4j.Slf4j;

/**
 * File read DB write batch step(mybatis)
 * 
 * @author ljs
 * @since 0.1
 */
@Slf4j
@Component
public class FileReadDbWriteStep {

	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	@Autowired
	FileReadDbWriteReader reader;
	@Autowired
	FileReadDbWriteProcessor processor;
	@Autowired
	FileReadDbWriteWriter writer;
	
	@Value("${spring.scheduler.cron.file-read-db-write-batch.chunk-size:1000}")
	int chunkSize;
	
	public Step executeStep( String stepName, Map<String,Object> param ) {
		
		log.info("[FileReadDbWriteStep] executeStep START...");
		
		return stepBuilderFactory.get( stepName )
				.<User, User>chunk( chunkSize )
				.reader( reader.reader() )
				.processor( processor )
				.writer( writer.writer() )
				.build();
	}
	
	
}
