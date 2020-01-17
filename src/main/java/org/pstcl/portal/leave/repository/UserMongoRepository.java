package org.pstcl.portal.leave.repository;

import org.pstcl.portal.leave.mvc.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserMongoRepository extends MongoRepository<User, String>{
	
}
