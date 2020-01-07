package com.batch.exam.batch.reader;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DefaultFieldSetFactory;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.batch.exam.info.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JpaFileReadDbWriteReader {

	@Value("${spring.scheduler.cron.jpa-file-read-db-write-batch.read-file-path}")
	String readFilePath; 
	
	@SuppressWarnings("unused")
	public FlatFileItemReader<User> reader() {
		
		log.info("[JpaFileReadDbWriteReader] reader() START...");
		
		Locale locale = Locale.getDefault();
		DefaultFieldSetFactory fieldSetFactory = new DefaultFieldSetFactory();
		fieldSetFactory.setNumberFormat(NumberFormat.getInstance(locale));
	    
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer(DelimitedLineTokenizer.DELIMITER_COMMA);
	    delimitedLineTokenizer.setNames(new String[]{"userId", "userName", "phone"});
	    
	    BeanWrapperFieldSetMapper<User> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<User>();
	    beanWrapperFieldSetMapper.setTargetType(User.class);
	    
	    DefaultLineMapper<User> defaultLineMapper = new DefaultLineMapper<User>();
	    defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
	    defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);
		
		return new FlatFileItemReaderBuilder<User>()
				.name( "JpaFileReadDbWriteReader" )
		    	.resource(new FileSystemResource( readFilePath ))
		    	.linesToSkip(1)
				.lineMapper(defaultLineMapper)
				.build();
	    
	}
	
	
}
