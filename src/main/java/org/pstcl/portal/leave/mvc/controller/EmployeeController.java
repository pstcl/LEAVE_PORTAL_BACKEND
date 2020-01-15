package org.pstcl.portal.leave.mvc.controller;

import org.pstcl.portal.leave.mvc.model.Employee;
import org.pstcl.portal.leave.mvc.service.HTTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	private HTTPService httpService;
	
	@CrossOrigin(allowCredentials = "true")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE,path = "/getLoggedInEmployee")
	public Employee getLoggedInEmployee()
	{
		return httpService.getLoggedInEmployee();
	}
}
