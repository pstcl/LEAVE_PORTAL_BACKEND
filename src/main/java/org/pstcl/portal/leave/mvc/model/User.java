package org.pstcl.portal.leave.mvc.model;

import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Document
@RequiredArgsConstructor
public class User {

	public User(String empid, String employeePassword) {
		this.username=empid;
		this.password=employeePassword;
	}
	
	@Id
	private String username;
	@NonNull
	private String password;
	private Set<String> role;
}
