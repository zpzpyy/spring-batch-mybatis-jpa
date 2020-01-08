package com.batch.exam.batch.processor;

import java.util.Map;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.batch.exam.info.User;

import lombok.extern.slf4j.Slf4j;

/**
 * DB read File write batch processor(mybatis)
 * 
 * @author ljs
 * @since 0.1
 */
@Slf4j
@Component
public class DbReadFileWriteProcessor implements ItemProcessor<Map<String,Object>, User>, StepExecutionListener {

	@Override
	public void beforeStep(StepExecution stepExecution) {
		log.info("[DbReadFileWriteProcessor] beforeStep() START...");
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return stepExecution.getExitStatus();
	}

	@Override
	public User process(Map<String,Object> item) throws Exception {
		
		User user = new User(item);
		
		// log.info("[DbReadFileWriteProcessor] process item :{}",item);
		// Read한 객체에 대해 처리로직...
		
		return user;
	}

	
	
	
}
