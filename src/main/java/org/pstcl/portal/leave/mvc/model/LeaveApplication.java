package org.pstcl.portal.leave.mvc.model;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotEmpty;

import org.pstcl.portal.leave.config.id.exposure.IdExposable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

	}

	@NotEmpty
	private Employee employee;



	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
		LocalDate localDate=LocalDate.now();
		this.id = employee.getEmpId()+"_"+localDate.getYear()+"_"+localDate.getMonthValue()+"_"+localDate.getDayOfMonth();

	}

	@Getter
	@Setter
	private List<Leave> leaveDetails;

	@Getter
	@Setter	
	private Integer status;

}