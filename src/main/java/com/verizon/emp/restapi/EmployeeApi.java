package com.verizon.emp.restapi;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.verizon.emp.model.Employee;
import com.verizon.emp.service.EmpService;

@RestController
@CrossOrigin
@RequestMapping("/employees")
public class EmployeeApi {

	@Autowired
	private EmpService empService;
	
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees()
	{
		ResponseEntity<List<Employee>> resp=new ResponseEntity<>(empService.getAllEmployees(),HttpStatus.OK);
		return resp;
	}
	@PostMapping
	public ResponseEntity<Employee> addContact(@RequestBody Employee emp) {
		ResponseEntity<Employee> resp = null;

		if (empService.existsByEmailId(emp.getEmailId())) {
			resp = new ResponseEntity<Employee>(HttpStatus.ALREADY_REPORTED);
		}

		if (empService.existsByMobileNumber(emp.getMobileNumber())) {
			resp = new ResponseEntity<Employee>(HttpStatus.ALREADY_REPORTED);
		}

		if (resp == null) {
			Employee e = empService.addEmployee(emp);
			if (e == null)
				resp = new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
			else
				resp = new ResponseEntity<Employee>(e, HttpStatus.OK);
		}
		return resp;
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteContact(@PathVariable("id") int empId) {
		ResponseEntity<Void> resp = null;

		if (empService.deleteEmployee(empId))
			resp = new ResponseEntity<>(HttpStatus.OK);
		else
			resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);

		return resp;
	}
	@GetMapping("/{field}/{srhValue}")
	public ResponseEntity<List<Employee>> getAllEmployees(@PathVariable("field") String fieldBy,
			@PathVariable("srhValue") String searchValue) {

		ResponseEntity<List<Employee>> resp;

		switch (fieldBy) {
		case "mobileNumber":
			Employee eByMobNum = empService.findByMobileNumber(searchValue);
			if (eByMobNum != null) {
				resp = new ResponseEntity<>(Collections.singletonList(eByMobNum), HttpStatus.OK);
			} else {
				resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			break;
		case "emailId":
			Employee eByEmail = empService.findByEmailId(searchValue);
			if (eByEmail != null) {
				resp = new ResponseEntity<>(Collections.singletonList(eByEmail), HttpStatus.OK);
			} else {
				resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			break;
		case "department":
			List<Employee> results = empService.findAllByDepartment(searchValue);
			if (results != null && results.size() != 0) {
				resp = new ResponseEntity<>(results, HttpStatus.OK);
			} else {
				resp = new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			break;
		default:
			resp = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			break;
		}

		return resp;
	}
	@PutMapping
	public ResponseEntity<Employee> updateContact(@RequestBody Employee employee) {
		ResponseEntity<Employee> resp = null;

		Employee e = empService.getEmployeeByEmpId(employee.getEmpId());
		if (!employee.getEmailId().equals(e.getEmailId())) {
			if (empService.existsByEmailId(employee.getEmailId())) {
				resp = new ResponseEntity<Employee>(HttpStatus.ALREADY_REPORTED);
			}
		}

		if (!employee.getMobileNumber().equals(e.getMobileNumber())) {
			if (empService.existsByMobileNumber(employee.getMobileNumber())) {
				resp = new ResponseEntity<Employee>(HttpStatus.ALREADY_REPORTED);
			}
		}

		if (resp == null) {
			e = empService.updateEmployee(employee);
			if (e == null)
				resp = new ResponseEntity<Employee>(HttpStatus.BAD_REQUEST);
			else
				resp = new ResponseEntity<Employee>(e, HttpStatus.OK);
		}
		return resp;
	}

}
