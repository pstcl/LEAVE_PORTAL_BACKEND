package org.pstcl.portal.leave.mvc.model;

import java.time.LocalDate;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LeaveDatesEntity {
	public LeaveDatesEntity() {
	}
	public LeaveDatesEntity(LeaveType leaveType) {
		this.typeOfLeave=leaveType;
		this.startDate=new Date(System.currentTimeMillis());
		this.endDate=new Date(System.currentTimeMillis());
	}
	@Id
	private Integer id;
	private LeaveType typeOfLeave;
	private Date startDate;
	private Date endDate;


}
