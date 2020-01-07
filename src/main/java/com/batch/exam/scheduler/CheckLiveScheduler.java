package com.batch.exam.scheduler;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.batch.exam.config.Constants;

import lombok.extern.slf4j.Slf4j;

/**
 * Daemon Live Check Scheduler
 * : 주기적으로 배치의 Live 메모리 체크
 * 
 * @author ljs
 * @since 0.1
 */
@Slf4j
@SpringBootApplication
@EnableScheduling
public class CheckLiveScheduler {
	
	private Logger liveTraceLogger = LoggerFactory.getLogger( Constants.LOGGING_LIVE );
	
	@Scheduled(fixedRateString = "${spring.scheduler.cycle.live-check.delay}", initialDelay = 30000) 
	public void schedule() {
		MemoryMXBean memoryMxBean = ManagementFactory.getMemoryMXBean();
	    MemoryUsage memUsage = memoryMxBean.getHeapMemoryUsage();
	    java.text.DecimalFormat df = new java.text.DecimalFormat("#0");
	    String smemUsed = df.format((double)memUsage.getUsed()/(1024 * 1024));
	    String smemMax = df.format((double)memUsage.getMax()/(1024 * 1024));
	    
	    log.info("[CheckLive]  max {} MB / used {} MB", smemMax, smemUsed);
	    liveTraceLogger.info("[CheckLive]  max {} MB / used {} MB", smemMax, smemUsed);
	}
	
}
