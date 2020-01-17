package org.pstcl.portal.leave.mvc.service;

import org.pstcl.portal.leave.mvc.model.Employee;
import org.pstcl.portal.leave.util.GlobalProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public interface HTTPService {

	ResponseEntity<String> ddoDetails(String ddocode);

	Employee employeeDetails(String employeCode);

	Employee getLoggedInEmployee();

}