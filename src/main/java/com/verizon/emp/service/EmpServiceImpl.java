
package com.verizon.emp.service;

import java.util.List;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.emp.dao.EmpRepository;
import com.verizon.emp.model.Employee;

@Service
public class EmpServiceImpl implements EmpService{

	@Autowired
	private EmpRepository empRepo;

	@Override
	public Employee getEmployeeByEmpId(int empId) {
	
		Employee emp=null;
		Optional<Employee> optEmployee =empRepo.findById(empId);
		if(optEmployee.isPresent()) {
			emp= optEmployee.get();
		}
		
		return emp;
			}

	@Override
	public List<Employee> getAllEmployees() {
	
		return empRepo.findAll();
	}

	@Override
	public Employee addEmployee(Employee emp) {
		
		return empRepo.save(emp);
	}

	@Override
	public Employee updateEmployee(Employee emp) {
		
		return empRepo.save(emp);
	}

	@Override
	public boolean existsByMobileNumber(String mobileNumber) {
		return empRepo.existsByMobileNumber(mobileNumber);
	}

	@Override
	public boolean existsByEmailId(String emailId) {
		
		return empRepo.existsByEmailId(emailId);
	}

	@Override
	public Employee findByMobileNumber(String mobileNumber) {
		
		return empRepo.findByMobileNumber(mobileNumber);
	}

	@Override
	public Employee findByEmailId(String emailId) {

		return empRepo.findByEmailId(emailId);
	}

	@Override
	public List<Employee> findAllByDepartment(String department) {
		
		return empRepo.findAllByDepartment(department);
	}

	@Override
	public boolean deleteEmployee(int empId) {
		boolean isDeleted=false;
		if(empRepo.existsById(empId))
		{
			empRepo.deleteById(empId);
			isDeleted=true;
		}
		return isDeleted;
	}
	
}
