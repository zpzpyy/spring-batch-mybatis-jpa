package com.batch.exam.config;

import org.springframework.stereotype.Component;

@Component
public class Column {
	
	/** batch jobId */
	public static String JOB_ID	= "JOB_ID";
	/** jobName(FileReadDbWriteJob) */
	public static String FILE_READ_DB_WRITE_JOB			= "FileReadDbWriteJob";
	/** stepName(FileReadDbWriteStep) */
	public static String FILE_READ_DB_WRITE_STEP		= "FileReadDbWriteStep";
	
	/** jobName(DbReadFileWriteJob) */
	public static String DB_READ_FILE_WRITE_JOB			= "DbReadFileWriteJob";
	/** stepName(DbReadFileWriteStep) */
	public static String DB_READ_FILE_WRITE_STEP		= "DbReadFileWriteStep";
	
	/** jobName(JpaDbReadFileWriteJob) */
	public static String JPA_DB_READ_FILE_WRITE_JOB		= "JpaDbReadFileWriteJob";
	/** stepName(JpaDbReadFileWriteStep) */
	public static String JPA_DB_READ_FILE_WRITE_STEP	= "JpaDbReadFileWriteStep";
	
	/** jobName(JpaFileReadDbWriteJob) */
	public static String JPA_FILE_READ_DB_WRITE_JOB		= "JpaFileReadDbWriteJob";
	/** stepName(JpaFileReadDbWriteStep) */
	public static String JPA_FILE_READ_DB_WRITE_STEP	= "JpaFileReadDbWriteStep";
	
	
}
