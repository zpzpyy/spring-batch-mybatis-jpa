package com.batch.exam.batch.reader;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DefaultFieldSetFactory;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Component;

import com.batch.exam.info.User;

import lombok.extern.slf4j.Slf4j;

/**
 * File read DB write batch reader(mybatis)
 * 
 * @author ljs
 * @since 0.1
 */
@Slf4j
@Component
public class FileReadDbWriteReader {
	
	@Value("${spring.scheduler.cron.file-read-db-write-batch.read-file-path}")
	String readFilePath; 
	
	@SuppressWarnings("unused")
	public FlatFileItemReader<User> reader() {
		
		log.info("[FileReadDbWriteReader] reader() START...");
		
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
				.name( "FileReadDbWriteReader" )
		    	.resource(new FileSystemResource( readFilePath ))
		    	.linesToSkip(1)
				.lineMapper(defaultLineMapper)
				.build();
	    
	}


	
}
