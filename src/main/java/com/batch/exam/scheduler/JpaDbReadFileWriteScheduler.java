package com.batch.exam.scheduler;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.batch.exam.batch.job.DbReadFileWriteJob;
import com.batch.exam.batch.job.JpaDbReadFileWriteJob;
import com.batch.exam.config.Column;

import lombok.extern.slf4j.Slf4j;

/**
 * DB read File write batch scheduler(JPA)
 * 
 * @author ljs
 * @since 0.1
 */
@Slf4j
@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
@ConditionalOnProperty(	prefix = "spring.scheduler.cron.jpa-db-read-file-write-batch"
					  , name = "use-yn"
					  , havingValue= "Y"
					  , matchIfMissing = true )
public class JpaDbReadFileWriteScheduler {

	@Autowired
	SimpleJobLauncher jobLauncher;
	@Autowired
	JpaDbReadFileWriteJob job;
	
	private static final String jobName = Column.JPA_DB_READ_FILE_WRITE_JOB;
	private static final String stepName = Column.JPA_DB_READ_FILE_WRITE_STEP;
	
	@Scheduled( cron="${spring.scheduler.cron.jpa-db-read-file-write-batch.cron-cycle}" )
	public void scheduler() {
	
		log.info("[JpaDbReadFileWriteScheduler] scheduler() START.......jobName : {} / stepName : {}",jobName,stepName); 
		
		try {
		
			String jobId = String.valueOf(System.currentTimeMillis());
			JobParameters param = new JobParametersBuilder()
								.addString( Column.JOB_ID, jobId )
								.toJobParameters();
			
			JobExecution execution = jobLauncher.run( job.executeJob(jobName, stepName, param) , param );
		
		log.info("[JpaFileReadDbWriteScheduler] scheduler() END : {}",execution.getStatus());
			
		} catch (Exception e) {
			log.error("[JpaFileReadDbWriteScheduler] scheduler() => job start Error: ", e);
		}
		
	}
	
}
