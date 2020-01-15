package org.pstcl.portal.leave.repository;

import org.pstcl.portal.leave.mvc.model.Employee;

public interface EmployeeFromServer {

	 Employee findByEmployeeCode(String employeeCode);
}
