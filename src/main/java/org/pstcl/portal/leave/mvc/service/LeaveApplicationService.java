package org.pstcl.portal.leave.mvc.service;

import org.pstcl.portal.leave.mvc.model.LeaveApplication;
import org.pstcl.portal.leave.repository.LeaveApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LeaveApplicationService {

	@Autowired
	private LeaveApplicationRepository leaveApplicationRepository;
	
	public LeaveApplication saveLeaveApplication(LeaveApplication leaveApplication) {

		return leaveApplicationRepository.save(leaveApplication);
	}
	
	
}
