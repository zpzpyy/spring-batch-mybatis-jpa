package com.batch.exam.batch.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.batch.exam.info.User;

import lombok.extern.slf4j.Slf4j;

/**
 * DB read File write batch writer(JPA)
 * 
 * @author ljs
 * @since 0.1
 */
@Slf4j
@Component
public class JpaDbReadFileWriteWriter{
	
	@Value("${spring.scheduler.cron.jpa-db-read-file-write-batch.write-file-path}")
	String writeFilePath;

	public FlatFileItemWriter<User> writer(){
		log.info("[JpaDbReadFileWriteWriter] writer START...........");
		
		DelimitedLineAggregator<User> aggregator = new DelimitedLineAggregator<User>();
		aggregator.setDelimiter(",");
		
		BeanWrapperFieldExtractor<User> fieldExtractor = new BeanWrapperFieldExtractor<>();
	    fieldExtractor.setNames(new String[]{"userId","userName","phone"});
	    aggregator.setFieldExtractor(fieldExtractor);
	    
		return new FlatFileItemWriterBuilder<User>()
				.name( "JpaDbReadFileWriteWriter" )
				.resource( new FileSystemResource( writeFilePath ) )
				.lineAggregator( aggregator )
				.build();
	}
	
	
}
