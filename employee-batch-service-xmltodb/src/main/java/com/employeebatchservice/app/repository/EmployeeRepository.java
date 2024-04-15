package com.employeebatchservice.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employeebatchservice.app.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
}
