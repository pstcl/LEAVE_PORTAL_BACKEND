package org.pstcl.portal.leave.mvc.model;

import org.pstcl.portal.leave.config.id.exposure.IdExposable;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
@IdExposable(name="Employee")
@Data
@Document(collection = "employee")
public class Employee {		  

	@Id
	private String empId;
	private String fullName;
	private String firstName;
	private String middleName;
	private String lastName = null;
	private String fatherName;
	private String dob;
	private String doj;
	private String sex;
	private String maritalStatusCode;
	private String maritalStatus;
	private String desigCode;
	private String desigName;
	private String locationCode;
	private String locationName;
	private String panNo;
	private String gpfNo;
	private String bloodGroup;
	private String permDoorNo;
	private String permStreetNo;
	private String permCity;
	private String permPinCode = null;
	private String corDoorNo;
	private String corStreetNo;
	private String corCity;
	private String corPinCode;
	private String contactNo = null;
	private String emailId = null;
	private String ddoCodes;
	private String socialCategoryCode;
	private String socialCategory;
	private String reserveCategoryCode;
	private String reserveCategory = null;
	private String aadharNo;
	private String employeesStatusCode;
	private String employeeStatus;




}
