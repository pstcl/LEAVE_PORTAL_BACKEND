package org.pstcl.portal.leave.mvc.service;

import java.util.List;

import org.pstcl.portal.leave.mvc.model.Employee;
import org.springframework.http.ResponseEntity;

public interface HRDataService {

	ResponseEntity<String> ddoDetails(String ddocode);

	Employee employeeDetails(String employeCode);

	Employee getLoggedInEmployee();


	List<Employee> getDDOForEmployee(String employeCode);

	List<Employee> getControllingOfficerForEmployee(String employeCode);

	List<Employee> getApprovingAuthorityForEmployee(String employeCode);



}