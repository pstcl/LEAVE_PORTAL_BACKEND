package org.pstcl.portal.leave.mvc.model;

import java.time.LocalDateTime;
import java.util.Date;

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
	private Integer actionTaken;

	private Date actionDateTime;
	private Date recievedDateTime;

	
	
	@DBRef
	@JsonBackReference
	private LeaveApplication leaveApplication;
	
	private String remarks;

	

	@DBRef
	private Employee markedTo;

	@DBRef
	private LeaveStatus previousStatus;

	
	public static LeaveStatus LeaveStatusApproved(LeaveApplication leaveApplication,LeaveStatus previousLeaveStatus,LeaveStatus valuesFromUI) {
		LeaveStatus leaveStatus = LeaveStatusFactory(leaveApplication);
		leaveStatus.actionDateTime=new Date(System.currentTimeMillis());

		leaveStatus.actionTaken=GlobalConstants.STATUS_VALUE_APPROVED;
		leaveStatus.previousStatus=previousLeaveStatus;
		leaveStatus.markedTo=leaveApplication.getEmployee();
		leaveStatus.remarks=valuesFromUI.remarks;
		return leaveStatus;
	}
	
	public static LeaveStatus LeaveStatusForwarded(LeaveApplication leaveApplication,LeaveStatus previousLeaveStatus,LeaveStatus valuesFromUI) {
		LeaveStatus leaveStatus = LeaveStatusFactory(leaveApplication);
		leaveStatus.actionDateTime=new Date(System.currentTimeMillis());

		leaveStatus.actionTaken=GlobalConstants.STATUS_VALUE_PENDING_WITH_THIS_OFFICE;
		leaveStatus.previousStatus=previousLeaveStatus;
		leaveStatus.markedTo=valuesFromUI.markedTo;
		return leaveStatus;
	}
	
	
	
	public static LeaveStatus LeaveStatusSaved(LeaveApplication leaveApplication) {
		LeaveStatus leaveStatus = LeaveStatusFactory(leaveApplication);
		leaveStatus.actionDateTime=new Date(System.currentTimeMillis());

		leaveStatus.actionTaken=GlobalConstants.STATUS_VALUE_SAVED;
		leaveStatus.previousStatus=null;
		leaveStatus.markedTo=leaveApplication.getEmployee();
		return leaveStatus;
	}
	
	public static LeaveStatus LeaveStatusUpdated(LeaveApplication leaveApplication,LeaveStatus previousLeaveStatus) {
		LeaveStatus leaveStatus = LeaveStatusFactory(leaveApplication);
		leaveStatus.actionDateTime=new Date(System.currentTimeMillis());

		leaveStatus.actionTaken=GlobalConstants.STATUS_VALUE_UPDATED;
		leaveStatus.previousStatus=previousLeaveStatus;
		leaveStatus.markedTo=leaveApplication.getEmployee();
		return leaveStatus;
	}

	private static LeaveStatus LeaveStatusFactory(LeaveApplication leaveApplication) {
		LeaveStatus leaveStatus=new LeaveStatus();
		leaveStatus.leaveApplication=leaveApplication;
		
		leaveStatus.actionDateTime=new Date(System.currentTimeMillis());
		leaveStatus.recievedDateTime=new Date(System.currentTimeMillis());
		
		return leaveStatus;
	}

}
