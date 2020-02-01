package org.pstcl.portal.leave.mvc.model;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.pstcl.portal.leave.config.id.exposure.IdExposable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@IdExposable(name="LeaveApplication")
@ToString
@EqualsAndHashCode
@Document(collection = "leave-application")
public class LeaveApplication {

	@Id
	private String id;



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id=id;
	}

	@NotEmpty
	@DBRef
	private Employee employee;



	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;

	}

	@Getter
	@Setter
	private List<LeaveDatesEntity> leaveDetails;
	
	

	@Getter
	@Setter
	private Boolean withSubsitute;
	
	


	@Getter
	@Setter
	private String reason;
	

	@Getter
	@Setter
	private String addressDuringLeave;
	

	@Getter
	@Setter
	private Date startDatePrefix;

	@Getter
	@Setter
	private Date endDateSuffix;


	@Getter
	@Setter
	private Integer noOfDaysSuffix;

	@Getter
	@Setter
	private Integer noOfDaysPrefix;



	@Getter
	@Setter
	private  Date previousLeaveReturnDate;

	@Getter
	@Setter
	private LeaveType previousLeaveType;

	@Getter
	@Setter
	private Integer previousNoOfDays;
	
	
	
	@Getter
	@Setter	
	@JsonManagedReference 
	@DBRef
	private LeaveStatus latestStatus;

	@Getter
	@Setter	
	@JsonManagedReference 
	@DBRef
	private LeaveStatus recommendationStatus;



	@Getter
	@Setter	
	@JsonManagedReference 
	@DBRef
	private LeaveStatus approvalStatus;



	public void updateThis(LeaveApplication leaveApplication) {
	
		this.leaveDetails =leaveApplication.leaveDetails;
		this.withSubsitute =leaveApplication.withSubsitute;
		this.reason =leaveApplication.reason;
		this.addressDuringLeave =leaveApplication.addressDuringLeave;
		this.startDatePrefix =leaveApplication.startDatePrefix;
		this.endDateSuffix =leaveApplication.endDateSuffix;
		this.noOfDaysSuffix =leaveApplication.noOfDaysSuffix;
		this.noOfDaysPrefix =leaveApplication.noOfDaysPrefix;
		this.previousLeaveReturnDate =leaveApplication.previousLeaveReturnDate;
		this.previousLeaveType =leaveApplication.previousLeaveType;
		this.previousNoOfDays =leaveApplication.previousNoOfDays;
	//	this.status =leaveApplication.status;
	}



	

}