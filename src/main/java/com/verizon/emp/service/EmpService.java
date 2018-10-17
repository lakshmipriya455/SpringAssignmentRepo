package com.verizon.emp.service;

import java.util.List;


import com.verizon.emp.model.Employee;

public interface EmpService {

	Employee getEmployeeByEmpId(int empId);
	List<Employee> getAllEmployees();
	Employee addEmployee(Employee emp);
	Employee updateEmployee(Employee emp);
	boolean deleteEmployee(int empId);
	
	boolean existsByMobileNumber(String mobileNumber);
	boolean existsByEmailId(String emailId);
	
	Employee findByMobileNumber(String mobileNumber);
	Employee findByEmailId(String emailId);

	
	List<Employee> findAllByDepartment(String department);
}
