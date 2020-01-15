package org.pstcl.portal.leave.mvc.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "Leave")
public class Leave {
	public Leave() {
	}
	public Leave(LeaveType leaveType) {
		this.typeOfLeave=leaveType;
		this.startDate=LocalDate.now();
		this.endDate=LocalDate.now();
	}
	@Id
	private Integer id;
	private LeaveType typeOfLeave;
	private LocalDate startDate;
	private LocalDate endDate;


}
