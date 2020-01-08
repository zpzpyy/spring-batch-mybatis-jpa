package com.batch.exam.batch.reader;

import java.util.Map;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.batch.exam.config.Column;
import com.batch.exam.info.User;

import lombok.extern.slf4j.Slf4j;

/**
 * DB read File write batch reader(JPA)
 * 
 * @author ljs
 * @since 0.1
 */
@Slf4j
@Component
public class JpaDbReadFileWriteReader {
	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	
	public JpaPagingItemReader<User> reader( Map<String, Object> param ){
		
		log.info("[JpaDbReadFileWriteReader] reader() => param : {}",param);
		
		int chunkSize = Integer.parseInt( param.get(Column.CHUNK_SIZE).toString());
		
		return new JpaPagingItemReaderBuilder<User>()
				.queryString( "SELECT u FROM User u" )
				.pageSize( chunkSize )
				.entityManagerFactory( entityManagerFactory )
				.name(" JpaDbReadFileWriteReader ")
				.build();
	}
	
}
