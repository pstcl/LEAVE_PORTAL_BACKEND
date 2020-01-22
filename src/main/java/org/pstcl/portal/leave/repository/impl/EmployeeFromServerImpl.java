package org.pstcl.portal.leave.repository.impl;

import org.pstcl.portal.leave.mvc.model.Employee;
import org.pstcl.portal.leave.mvc.service.HRDataService;
import org.pstcl.portal.leave.repository.EmployeeFromServer;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeFromServerImpl implements EmployeeFromServer {

	//	@Autowired
	//	private HTTPService httpService;
	//	
	@Override
	public Employee findByEmployeeCode(String employeeCode) {
		return null;
		//		return httpService.employeeDetails(employeeCode).getBody()[0];
	}

}
