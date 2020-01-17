package org.pstcl.portal.leave.mvc.service;

import java.util.List;

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

	public LeaveApplication getLeaveApplication(String leaveApplicationId)
	{
		LeaveApplication leaveApplication = null;
		if(null==leaveApplicationId)
		{
			leaveApplication=new LeaveApplication();
		}
		else if(leaveApplicationRepository.findById(leaveApplicationId).isPresent())
		{
			leaveApplication= leaveApplicationRepository.findById(leaveApplicationId).get();

		}


		return leaveApplication;
	}

	public List<LeaveApplication> getAllLeaveApplications() {
		return leaveApplicationRepository.findAll();
	}

	public LeaveApplication createLeaveApplication(LeaveApplication leaveApplication) {
		return leaveApplicationRepository.save(leaveApplication);
	}

	public LeaveApplication updateLeaveApplication(LeaveApplication leaveApplication) {
		return leaveApplicationRepository.save(leaveApplication);
	}


}
