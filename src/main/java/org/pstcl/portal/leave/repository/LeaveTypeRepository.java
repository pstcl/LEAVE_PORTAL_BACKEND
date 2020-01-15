package org.pstcl.portal.leave.repository;

import org.pstcl.portal.leave.mvc.model.LeaveType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(allowCredentials="true")
@RepositoryRestResource(collectionResourceRel = "leaveTypeList", path="leave-type")
public interface LeaveTypeRepository extends MongoRepository<LeaveType, Integer>{
	
	@Override
	@RestResource(exported = false)
	void deleteById(Integer integer);

}
