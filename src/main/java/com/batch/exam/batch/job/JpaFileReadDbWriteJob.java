package com.batch.exam.batch.job;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.batch.exam.batch.step.DbReadFileWriteStep;
import com.batch.exam.batch.step.FileReadDbWriteStep;
import com.batch.exam.batch.step.JpaDbReadFileWriteStep;
import com.batch.exam.batch.step.JpaFileReadDbWriteStep;

import lombok.extern.slf4j.Slf4j;

/**
 * File read DB write batch Job(JPA)
 * 
 * @author ljs
 * @since 0.1
 */
@Slf4j
@Component
public class JpaFileReadDbWriteJob {

	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	JpaFileReadDbWriteStep step;
	
	public Job executeJob( String jobName, String stepName, JobParameters param ) {
		
		log.info("[JpaFileReadDbWriteJob] executeJob() START....  jobName:{} / stepName:{} / param:{} ",jobName,stepName,param);
		
		// example parameter
		Map<String,Object> paramMap = new HashMap<String, Object>(); 
		paramMap.put("USER_ID", "test");
		
		return jobBuilderFactory.get( jobName )
				.start( step.executeStep( stepName, paramMap )  )
				.build();
		
	}
	
}
