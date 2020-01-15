package org.pstcl.portal.leave.mvc.controller;

import java.util.List;

import org.pstcl.portal.leave.mvc.model.LeaveApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LeaveApplicationController {

	
	@GetMapping
	List<LeaveApplication> getLeaveApplications()
	{
		return null;
	}
	
}
