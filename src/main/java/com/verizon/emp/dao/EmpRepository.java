package com.verizon.emp.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.verizon.emp.model.Employee;

@Repository
public interface EmpRepository extends JpaRepository<Employee, Integer> {

	boolean existsByMobileNumber(String mobileNumber);
	boolean existsByEmailId(String emailId);
	
	Employee findByMobileNumber(String mobileNumber);
	Employee findByEmailId(String emailId);


	List<Employee> findAllByDepartment(String department);
}
