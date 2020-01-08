package com.batch.exam.batch.reader;

import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.batch.MyBatisCursorItemReader;
import org.mybatis.spring.batch.builder.MyBatisCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * DB read File write batch reader(mybatis)
 * 
 * @author ljs
 * @since 0.1
 */
@Slf4j
@Component
public class DbReadFileWriteReader {
	
	@Autowired
	@Qualifier("sqlSessionFactory")
	SqlSessionFactory sqlSessionFactory;
	
	private final String queryId = "com.batch.exam.mapper.DbReadFileWriterMapper.selectUser";
	
	public MyBatisCursorItemReader<Map<String,Object>> reader( Map<String, Object> parameter ){
		
		log.info("[DbReadFileWriteReader] reader() => parameter : {}",parameter);
		
		return new MyBatisCursorItemReaderBuilder<Map<String,Object>>()
				.sqlSessionFactory(sqlSessionFactory)
				.queryId( queryId )
				.parameterValues( parameter )
				.build();
	}

	
}
