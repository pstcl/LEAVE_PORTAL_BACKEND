package org.pstcl.portal.leave.mvc.service;

import java.util.List;

import org.pstcl.portal.leave.mvc.model.Employee;
import org.pstcl.portal.leave.util.GlobalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public interface HRDataService {

	ResponseEntity<String> ddoDetails(String ddocode);

	Employee employeeDetails(String employeCode);

	Employee getLoggedInEmployee();

	List<Employee> getContollingOfficer(String employeCode);

	List<Employee> getApprovingAuthority(String employeCode);

	List<Employee> getDDOForEmployee(String employeCode);

	List<Employee> getContollingOfficerForEmployee(String employeCode);

	List<Employee> getApprovingAuthorityForEmployee(String employeCode);



}