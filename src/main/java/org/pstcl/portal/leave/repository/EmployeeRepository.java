package org.pstcl.portal.leave.repository;

import org.pstcl.portal.leave.mvc.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "employees", path="employee")
public interface EmployeeRepository extends MongoRepository<Employee, String>,EmployeeFromServer{
	

}
