package org.pstcl.portal.leave.mvc.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pstcl.portal.leave.mvc.model.Employee;
import org.pstcl.portal.leave.mvc.model.LeaveType;
import org.pstcl.portal.leave.mvc.service.HRDataService;
import org.pstcl.portal.leave.repository.LeaveTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HTTPController {
	
//	1. Employee:                          https://hrapipstcl.pspcl.in/api/employee/504002
//		2. DDO:                                  https://hrapipstcl.pspcl.in/api/ddo/202
//		3. EmployeeAuthenticate:      https://hrapipstcl.pspcl.in/api/EmployeeAuthenticate



	@Autowired
	private HRDataService httpService;
	
	@Autowired
	private LeaveTypeRepository leaveTypeRepository;
	
	
	
	

	
	@CrossOrigin(allowCredentials="true")
	@GetMapping(value = "/api/employee/{empid}",  produces = "application/json") 
	public  Employee  getEmployee(@PathVariable("empid") String employeeCode,HttpServletResponse response,HttpServletRequest request) {
		
		return httpService.employeeDetails(employeeCode);
	}
	
	@CrossOrigin(allowCredentials="true")
	@GetMapping(value = "/api/ddo/{ddocode}",  produces = "application/json") 
	public  ResponseEntity<String>  getDdo(@PathVariable("ddocode") String ddoCode,HttpServletResponse response,HttpServletRequest request) {
		
		return httpService.ddoDetails(ddoCode);
	}


//	public Object getEmpl
//	@CrossOrigin(allowCredentials="true")
//	@GetMapping(value = "/bpi/employee1/{empid}",  produces = "application/json") 
//	public  ResponseEntity<String>  getEmployeeStr(@PathVariable("empid") String employeeCode,HttpServletResponse response,HttpServletRequest request) {
//		
//		return httpService.employeeDetailsString(employeeCode);
//	}

}
