package org.pstcl.portal.leave.mvc.controller;

import java.util.List;

import org.pstcl.portal.leave.mvc.model.Employee;
import org.pstcl.portal.leave.mvc.service.HRDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	private HRDataService httpService;
	
	@CrossOrigin(allowCredentials = "true")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/getLoggedInEmployee")
	public Employee getLoggedInEmployee()
	{
		return httpService.getLoggedInEmployee();
	}

	@CrossOrigin(allowCredentials = "true")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/getControllingOfficer/{empid}")
	public List<Employee> getContollingOfficer(@PathVariable("empid") String employeeCode)
	{
		return httpService.getControllingOfficerForEmployee(employeeCode);
	}
	@CrossOrigin(allowCredentials = "true")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/getApprovingAuthority/{empid}")
	public List<Employee> getApprovingAuthority(@PathVariable("empid") String employeeCode)
	{
		return httpService.getApprovingAuthorityForEmployee(employeeCode);
	}
	
	@CrossOrigin(allowCredentials = "true")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/getDDOForEmployeeID/{empid}")
	public List<Employee> getDDOForEmployeeID(@PathVariable("empid") String employeeCode)
	{
		return httpService.getDDOForEmployee( employeeCode);
	}
	

}


