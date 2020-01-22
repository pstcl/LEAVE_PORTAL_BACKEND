package org.pstcl.portal.leave.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.pstcl.portal.leave.mvc.service.PseudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PseudoController {
	
//	@Autowired
//	private PseudoService httpService;
//	
//	@CrossOrigin(allowCredentials="true")
//	@GetMapping(value = "/api/EmployeeAuthenticate") 
//	public ResponseEntity<String> authorizeEmployee(HttpServletResponse response,HttpServletRequest request)
//	{
//		return httpService.authorizeEmployee(request); 
//	}
//	
//	
//	@CrossOrigin(allowCredentials="true")
//	@GetMapping(value = "/api/employee/{empid}",  produces = "application/json") 
//	public  ResponseEntity<String>  getEmployee(@PathVariable("empid") String employeeCode,HttpServletResponse response,HttpServletRequest request) {
//		
//		return httpService.employeeDetails(employeeCode,request);
//	}
//	
//	@CrossOrigin(allowCredentials="true")
//	@GetMapping(value = "/api/ddo/{ddocode}",  produces = "application/json") 
//	public  ResponseEntity<String>  getDdo(@PathVariable("ddocode") String ddoCode,HttpServletResponse response,HttpServletRequest request) {
//		
//		return httpService.ddoDetails(ddoCode,request);
//	}

}
