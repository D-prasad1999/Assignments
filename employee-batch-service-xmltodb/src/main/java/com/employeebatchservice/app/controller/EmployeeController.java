package com.employeebatchservice.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	private Job job;
	
	@GetMapping("/import")
	public void loadDataToDB() throws Exception{
		 logger.info("Data import job has been triggered.");

		    JobParameters jobParams = new JobParametersBuilder()
		                                    .addLong("startAt", System.currentTimeMillis())
		                                    .toJobParameters();

		    try {
		        JobExecution jobExecution = jobLauncher.run(job, jobParams);
		        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
		            logger.info("Data import job has completed successfully.");
		        } 
		    } catch (Exception e) {
		        logger.error("An error occurred while executing the data import job.", e);
		    }
	}
}


