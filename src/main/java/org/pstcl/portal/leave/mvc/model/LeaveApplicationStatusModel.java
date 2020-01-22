package org.pstcl.portal.leave.mvc.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class LeaveApplicationStatusModel {

	@Getter
	@Setter
	private LeaveApplication leaveApplication;
	
	@Getter
	@Setter
	private List<LeaveStatus> leaveStatusList;
	
	

}
