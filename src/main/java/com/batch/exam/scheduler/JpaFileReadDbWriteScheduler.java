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

import com.batch.exam.batch.job.JpaFileReadDbWriteJob;
import com.batch.exam.config.Column;

import lombok.extern.slf4j.Slf4j;

/**
 * DB 읽어서 File write Batch
 * 
 * @author ljs
 * @since 0.1
 */
@Slf4j
@SpringBootApplication
@EnableScheduling
@EnableBatchProcessing
@ConditionalOnProperty(	prefix = "spring.scheduler.cron.jpa-file-read-db-write-batch"
					  , name = "use-yn"
					  , havingValue= "Y"
					  , matchIfMissing = true )
public class JpaFileReadDbWriteScheduler {

	@Autowired
	SimpleJobLauncher jobLauncher;
	@Autowired
	JpaFileReadDbWriteJob job;
	
	private static final String jobName = Column.JPA_FILE_READ_DB_WRITE_JOB;
	private static final String stepName = Column.JPA_FILE_READ_DB_WRITE_STEP;
	
	/**
	 * properties에 설정된 시간 주기로 구동
	 *
	 * @author ljs
	 * @since 0.1
	 */
	@Scheduled( cron="${spring.scheduler.cron.jpa-file-read-db-write-batch.cron-cycle}" )
	public void scheduler() {
	
		log.info("[JpaFileReadDbWriteScheduler] scheduler() START.......jobName : {} / stepName : {}",jobName,stepName); 
		
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
