package com.batch.exam.batch.writer;

import javax.persistence.EntityManagerFactory;

import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.batch.exam.info.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JpaFileReadDbWriteWriter{
	
	@Autowired
	EntityManagerFactory entityManagerFactory;
	public JpaItemWriter<User> writer(){
		
		log.info("[JpaFileReadDbWriteWriter] writer() START...");
		
		return new JpaItemWriterBuilder<User>()
				.entityManagerFactory(entityManagerFactory)
				.build();
	}
	
}
