package org.pstcl.portal.leave.mvc.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.pstcl.portal.leave.mvc.model.sequenceUtil.SequenceMarker;
import org.pstcl.portal.leave.util.GlobalConstants;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Document(collection = "leave-status")
public class LeaveStatus implements SequenceMarker
{
	@Id
	private Integer id;
	private Integer statusValue;

	private LocalDateTime statusUpdateTime;
	
	@DBRef
	@JsonBackReference
	private LeaveApplication leaveApplication;
	
	private String remarks;

	private Boolean recommended;
	private Boolean withSubstitute;


	@DBRef
	private Employee markedTo;

	@DBRef
	private LeaveStatus previousStatus;

	public static LeaveStatus LeaveStatusForwarded(LeaveApplication leaveApplication,LeaveStatus previousLeaveStatus,Employee employeeMarkedTo) {
		LeaveStatus leaveStatus = LeaveStatusFactory(leaveApplication);
		leaveStatus.statusValue=GlobalConstants.STATUS_VALUE_PENDING_WITH_THIS_OFFICE;
		leaveStatus.previousStatus=previousLeaveStatus;
		leaveStatus.markedTo=employeeMarkedTo;
		return leaveStatus;
	}
	
	public static LeaveStatus LeaveStatusSaved(LeaveApplication leaveApplication) {
		LeaveStatus leaveStatus = LeaveStatusFactory(leaveApplication);
		leaveStatus.statusValue=GlobalConstants.STATUS_VALUE_SAVED;
		leaveStatus.previousStatus=null;
		leaveStatus.markedTo=leaveApplication.getEmployee();
		return leaveStatus;
	}
	
	public static LeaveStatus LeaveStatusUpdated(LeaveApplication leaveApplication,LeaveStatus previousLeaveStatus) {
		LeaveStatus leaveStatus = LeaveStatusFactory(leaveApplication);
		leaveStatus.statusValue=GlobalConstants.STATUS_VALUE_UPDATED;
		leaveStatus.previousStatus=previousLeaveStatus;
		leaveStatus.markedTo=leaveApplication.getEmployee();
		return leaveStatus;
	}

	private static LeaveStatus LeaveStatusFactory(LeaveApplication leaveApplication) {
		LeaveStatus leaveStatus=new LeaveStatus();
		leaveStatus.leaveApplication=leaveApplication;
		
		leaveStatus.statusUpdateTime=LocalDateTime.now();
		
		return leaveStatus;
	}

}
