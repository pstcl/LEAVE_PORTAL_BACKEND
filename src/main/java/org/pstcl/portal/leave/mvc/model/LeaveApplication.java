package org.pstcl.portal.leave.mvc.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import org.pstcl.portal.leave.config.id.exposure.IdExposable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@IdExposable(name="LeaveApplication")
@ToString
@EqualsAndHashCode
@Document(collection = "LeaveApplication")
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
	private List<Leave> leaveDetails;

	@Getter
	@Setter	
	@JsonManagedReference 
	@DBRef
	private LeaveStatus status;

}