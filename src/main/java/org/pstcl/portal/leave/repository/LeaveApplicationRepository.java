package org.pstcl.portal.leave.repository;

import org.pstcl.portal.leave.mvc.model.LeaveApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "leaveApplicationList", path="leave-application")
public interface LeaveApplicationRepository extends MongoRepository<LeaveApplication, String>{
	

}
