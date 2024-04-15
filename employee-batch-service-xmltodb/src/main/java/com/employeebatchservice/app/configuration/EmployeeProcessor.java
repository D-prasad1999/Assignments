package com.employeebatchservice.app.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import com.employeebatchservice.app.entity.Employee;

public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeProcessor.class);
	
	public Employee process(Employee employee) throws Exception {

		 int salary = employee.getSalary();
	        if (salary >= 26000) {
	        	logger.info("Processing employee with ID: {}", employee.getId());
	            return employee;
	        }
	        else {
	        	logger.debug("Skipping employee with ID: {}", employee.getId());
	        	return null;
	        }
	}
}

