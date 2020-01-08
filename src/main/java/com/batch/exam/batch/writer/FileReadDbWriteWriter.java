package com.batch.exam.batch.writer;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisBatchItemWriter;
import org.mybatis.spring.batch.builder.MyBatisBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.batch.exam.info.User;

import lombok.extern.slf4j.Slf4j;

/**
 * File read DB write batch writer(mybatis)
 * 
 * @author ljs
 * @since 0.1
 */
@Slf4j
@Component
public class FileReadDbWriteWriter {
	
	@Autowired
	@Qualifier("sqlSessionFactory")
	SqlSessionFactory sqlSessionFactory;
	
	private final String queryId = "com.batch.exam.mapper.FileReadDbWriterMapper.insertUser";
	
	/**
	 * DB 저장
	 * 
	 * @return MyBatisBatchItemWriter<User>
	 *
	 * @author ljs
	 * @since 0.1
	 */
	public MyBatisBatchItemWriter<User> writer(){
		log.info("[FileReadDbWriteWriter] writer START...........");
		return new MyBatisBatchItemWriterBuilder<User>()
				.sqlSessionFactory( sqlSessionFactory )
				.statementId( queryId )
				.build();
	}

	
}
