package com.batch.exam.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DbReadFileWriterMapper {

	Map<String,Object> selectUser();
	
}
